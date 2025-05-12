package DisenoDePatrones.BaseDeDatos;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONObject;

public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection connection;

    private String url;
    private String user;
    private String password;
    
    private DatabaseConnection() throws SQLException{
        loadConfig();
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa | url: " + url);

        } catch (SQLException ex) {
            throw new SQLException("Error al conectar a la base de datos: ", ex);
        }
    }
    
    private void loadConfig() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/DisenoDePatrones/BaseDeDatos/dbConfig.json")));
            JSONObject config = new JSONObject(content);

            String host = config.getString("host");
            int port = config.getInt("port");
            String databaseName = config.getString("databaseName");
            boolean encrypt = config.getBoolean("encrypt");
            boolean trustServerCertificate = config.getBoolean("trustServerCertificate");
            this.user = config.getString("user");
            this.password = config.getString("password");

            this.url = String.format( "jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=%s;trustServerCertificate=%s",
                                    host, port, databaseName, encrypt, trustServerCertificate);
        } catch (Exception e) {
            throw new RuntimeException("No lei el JSON", e);
        }
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
}
