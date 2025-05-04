package DisenoDePatrones.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        
        String url = "jdbc:sqlserver://localhost:1433;databaseName=GestionCiudadana;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "cambiarcontraseña"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
