package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Controlador.ReportController;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Controlador.ServiceFactory;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Reglamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();
        ExecProcedures exec = new ExecProcedures(conn);
        List<Ciudadano> listaCiudadanos = ServiceFactory.cargarCiudadanosSinDuplicados(exec);
        CiudadanoService ciudadano1 = ServiceFactory.crearService(listaCiudadanos,"23456789B"); 

        Reglamento reglamento = Reglamento.getInstancia();

        for (Ciudadano c : listaCiudadanos) {
            if (c instanceof FuerzaOrden) {
                reglamento.registrarObservador((FuerzaOrden) c);
            }
        }
        
        reglamento.modificar("Nueva actualización del artículo 42.");
                
        // Probando el menu con el patron de FACADE   

        ReportController controladorReporte = new ReportController(ciudadano1, conn);
    }
}
