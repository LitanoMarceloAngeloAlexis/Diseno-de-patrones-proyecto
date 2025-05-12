package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Modelo.AgentePublico;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.Reporte;
import DisenoDePatrones.Modelo.Tramite;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("AREA DE PRUEBA==============================================================");
        System.out.println("-----Conexion a base de datos (SINGLETON)------------------");

        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();

        ExecProcedures exec = new ExecProcedures(conn);

        List<Object> listaAgentes = exec.obtenerRegistrosHumanos("AGENTEPUBLICO");
        List<Object> listaCiudadanos = exec.obtenerRegistrosHumanos("CIUDADANO");
        List<Object> listaCiudadanosComunes = exec.obtenerRegistrosHumanos("CIUDADANOCOMUN");
        List<Tramite> tramites = exec.obtenerTramites();

        List<Ciudadano> ciudadanosCasted = new ArrayList<>();

        for (Object obj : listaCiudadanos) {
            ciudadanosCasted.add((Ciudadano) obj);
        }

        CiudadanoService ciudadano1Service = new CiudadanoService(ciudadanosCasted, "12345678A");
        Reporte reporte = ciudadano1Service.crearReporte("20:30", "05/05/25", "ROBO EN DISTRITO", "A ALTAS HORAS DE LA NOCHE\n   SE METIERON A ROBAR MI CASA");
        ciudadano1Service.GeneracionReportes(reporte);
        ciudadano1Service.VisualizarDatosPersonales();
        ciudadano1Service.ConsultaEstadoTramites(tramites);
        ciudadano1Service.HistorialInteraccion();
    }
}
