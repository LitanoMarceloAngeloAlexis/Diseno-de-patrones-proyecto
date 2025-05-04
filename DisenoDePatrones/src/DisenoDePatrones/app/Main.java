package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        
        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();
        
    }
}
