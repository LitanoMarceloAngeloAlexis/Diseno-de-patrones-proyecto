package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Controlador.ReportController;
import DisenoDePatrones.Modelo.Ciudadano;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();
        ExecProcedures exec = new ExecProcedures(conn);
        List<Ciudadano> listaCiudadanos = exec.obtenerRegistrosHumanos("CIUDADANO");
        CiudadanoService ciudadano1 = new CiudadanoService(listaCiudadanos, "12345678A");
        
        // Probando el menu con el patron de FACADE   
        ReportController controladorReporte = new ReportController(ciudadano1);
        //ReportVista reporteVista = new ReportVista();
        //reporteVista.Mostrar();

    }
}
