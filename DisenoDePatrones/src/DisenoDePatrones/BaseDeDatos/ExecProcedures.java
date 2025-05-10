
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

    public List<Object> obtenerRegistrosHumanos(String tableName) {
        List<Object> registros = new ArrayList<>();
        String sql = null;

        // Escoge el procedimiento correcto
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
                
                Object registro = RegistroFactory.crearRegistro(tableName, dni, nombre, apellido, edad, procedencia, cargo, rango);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros;

    }


}
