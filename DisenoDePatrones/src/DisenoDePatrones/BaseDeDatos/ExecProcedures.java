
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

    public List<Object> obtenerRegistros(String tableName) {
        List<Object> registros = new ArrayList<>();
        String sql = "{call sp_SelectFromTable(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, tableName);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (tableName.equalsIgnoreCase("CIUDADANO")) {
                    String dni = rs.getString("DNI");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");
                    int edad = rs.getInt("EDAD");
                    String procedencia = rs.getString("PROCEDENCIA");

                    Ciudadano ciudadano = new Ciudadano(dni, nombre, apellido, edad, procedencia);
                    registros.add(ciudadano);
                } else if (tableName.equalsIgnoreCase("AGENTEPUBLICO")) {
                    String dni = rs.getString("DNI");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");                    
                    String cargo = rs.getString("CARGO");
                    int edad = rs.getInt("EDAD");
                    String procedencia = rs.getString("PROCEDENCIA");

                    AgentePublico agente = new AgentePublico(dni, nombre, apellido, cargo, edad, procedencia);
                    registros.add(agente);
                } else {
                    System.out.println("No hay un caso para esta tabla");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registros;
    }


}
