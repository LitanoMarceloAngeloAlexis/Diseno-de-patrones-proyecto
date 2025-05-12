
package DisenoDePatrones.BaseDeDatos;

import DisenoDePatrones.Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Tramite> obtenerTramites() {
        List<Tramite> tramites = new ArrayList<>();
        String sql = "{call sp_GetTramites}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dniFromDB = rs.getString("DNI");
                int id = rs.getInt("ID");
                String titulo = rs.getString("titulo");
                String estado = rs.getString("estado");

                Tramite tramite = new Tramite(id, dniFromDB, titulo, estado);
                tramites.add(tramite);
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

}
