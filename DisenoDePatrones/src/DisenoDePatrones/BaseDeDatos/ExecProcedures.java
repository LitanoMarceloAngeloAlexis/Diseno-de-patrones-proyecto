
package DisenoDePatrones.BaseDeDatos;

import DisenoDePatrones.Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecProcedures {

    private Connection connection;

    public ExecProcedures(Connection connection) {
        this.connection = connection;
    }

    public List<Ciudadano> obtenerRegistrosHumanos(String tableName) {
        List<Ciudadano> registros = new ArrayList<>();
        String sql = null;

        if (tableName.equalsIgnoreCase("CIUDADANO")) {
            sql = "{call sp_GetCiudadanos}";
        } else if (tableName.equalsIgnoreCase("AGENTEPUBLICO")) {
            sql = "{call sp_GetAgentesPublicos}";
        } else if (tableName.equalsIgnoreCase("FUERZAORDEN")) {
            sql = "{call sp_GetFuerzaOrden}";
        } else if (tableName.equalsIgnoreCase("CIUDADANOCOMUN")) {
            sql = "{call sp_GetCiudadanosComunes}";
        } else {
            System.out.println("Tabla no reconocida");
            return registros;
        }

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dni = rs.getString("DNI");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                int edad = rs.getInt("EDAD");
                String procedencia = rs.getString("PROCEDENCIA");
                String cargo = null;
                String rango = null;

                // USANDO FACTORY :D
                if(tableName.equalsIgnoreCase("FUERZAORDEN")){
                    cargo = rs.getString("CARGO");
                    rango = rs.getString("RANGO");
                } else if(tableName.equalsIgnoreCase("AGENTEPUBLICO")){
                    cargo = rs.getString("CARGO");
                }
                
                Ciudadano registro = RegistroFactory.crearRegistro(tableName, dni, nombre, apellido, edad, procedencia, cargo, rango);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros;

    }
    
    public List<Tramite> ObtenerTramitesActivosPorDni(String dni) {
        List<Tramite> tramites = new ArrayList<>();
        String sql = "{call sp_GetTramitesPorDNI(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String estado = rs.getString("estado");
                int id = rs.getInt("id");

                if (!estado.equalsIgnoreCase("Finalizado")) {
                    tramites.add(new Tramite(id, dni, titulo, estado));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tramites;
    }
    
    public boolean InsertarTramite(String dni, String titulo) {
        String sql = "{call sp_InsertarTramite(?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, titulo);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Tramite> obtenerTramitesPendientes() {
        List<Tramite> tramites = new ArrayList<>();
        String sql = "{call sp_GetTramitesPendientes}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String dni = rs.getString("Dni");
                String titulo = rs.getString("Titulo");
                String estado = rs.getString("Estado");

                tramites.add(new Tramite(id, dni, titulo, estado));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tramites;
    }

    
    public List<DelincuenciaDistritos> ObtenerSIGDelincuencial() {
        List<DelincuenciaDistritos> delincuenciaDistritos = new ArrayList<>();
        String sql = "{call sp_SIGDelincuencial}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String Departamento = rs.getString("Departamento");
                String Distrito = rs.getString("Distrito");
                Double TasaDelincuencia = rs.getDouble("TasaDelincuencia");
                String NivelRiesgo = rs.getString("NivelRiesgo");
                int PoblacionAproximada = rs.getInt("PoblacionAproximada");

                DelincuenciaDistritos distritoActual = new DelincuenciaDistritos(id, Departamento, Distrito, TasaDelincuencia,NivelRiesgo,PoblacionAproximada);
                delincuenciaDistritos.add(distritoActual);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return delincuenciaDistritos;
    }
    
    public boolean insertarReporte(String dni, String fecha, String hora, String motivo, String descripcion) {
        String sql = "{call sp_InsertarReporte(?, ?, ?, ?, ?)}";
        
        System.out.println(dni + fecha +hora+motivo+descripcion);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, fecha);
            stmt.setString(3, hora);
            stmt.setString(4, motivo);
            stmt.setString(5, descripcion);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertarReporte(Reporte reporte) {
        String sql = "{call sp_InsertarReporte(?, ?, ?, ?, ?)}";
        
        String dni = reporte.getAutor().getDNI();
        String fecha= reporte.getFecha();
        String hora= reporte.getHora();
        String motivo= reporte.getMotivo();
        String descripcion= reporte.getDescripcion();
        
        System.out.println(dni + fecha +hora+motivo+descripcion);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, fecha);
            stmt.setString(3, hora);
            stmt.setString(4, motivo);
            stmt.setString(5, descripcion);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean autenticarLogin(String dni, String contraseña) {
        String sql = "{call autenticar_login(?, ?, ?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, contraseña);
            stmt.registerOutParameter(3, Types.BIT);

            stmt.execute();

            return stmt.getBoolean(3);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean crearNotificacion(String dni, String mensaje) {
        String sql = "{call CrearNotificacion(?, ?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, mensaje);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Notificacion> leerNotificacionesNoLeidas(String dni) {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "{call LeerNotificacionesNoLeidas(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mensaje = rs.getString("mensaje");
                Timestamp fecha = rs.getTimestamp("fecha");

                Notificacion notificacion = new Notificacion(id, dni, mensaje, fecha.toLocalDateTime(), false);
                notificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notificaciones;
    }

    public boolean insertarEncuesta(String dni, Map<String, String> respuestas) {
        String sql = "{call sp_InsertarEncuesta(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, respuestas.getOrDefault("Pregunta 1", "sin_respuesta"));
            stmt.setString(3, respuestas.getOrDefault("Pregunta 2", "sin_respuesta"));
            stmt.setString(4, respuestas.getOrDefault("Pregunta 3", "sin_respuesta"));
            stmt.setString(5, respuestas.getOrDefault("Pregunta 4", "sin_respuesta"));
            stmt.setString(6, respuestas.getOrDefault("Pregunta 5", "sin_respuesta"));
            stmt.setString(7, respuestas.getOrDefault("Pregunta 6", "sin_respuesta"));

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertarDocumento(Documento doc) {
        String sql = "{call sp_InsertarDocumento(?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, doc.getDni());
            stmt.setString(2, doc.getNombreArchivo());
            stmt.setString(3, doc.getTipoMime());
            stmt.setInt(4, doc.getTamaño());
            stmt.setBytes(5, doc.getArchivo());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Documento> obtenerDocumentosPorDni(String dni) {
        List<Documento> documentos = new ArrayList<>();
        String sql = "{call sp_ObtenerDocumentosPorDNI(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Documento doc = new Documento();
                doc.setId(rs.getInt("Id"));
                doc.setDni(dni);
                doc.setNombreArchivo(rs.getString("NombreArchivo"));
                doc.setTipoMime(rs.getString("TipoMime"));
                doc.setTamaño(rs.getInt("Tamaño"));
                Timestamp ts = rs.getTimestamp("FechaSubida");
                if (ts != null) {
                    doc.setFechaSubida(ts.toLocalDateTime());
                }
                documentos.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentos;
    }

    public Documento obtenerDocumentoPorId(int id) {
        String sql = "{call sp_ObtenerDocumentoPorId(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Documento doc = new Documento();
                doc.setId(id);
                doc.setNombreArchivo(rs.getString("NombreArchivo"));
                doc.setTipoMime(rs.getString("TipoMime"));
                doc.setArchivo(rs.getBytes("Archivo"));
                return doc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarDocumentoPorId(int id) {
        String sql = "{call sp_EliminarDocumentoPorId(?)}";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
