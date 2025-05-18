package DisenoDePatrones.app;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Controlador.AgentePublicoService;
import DisenoDePatrones.Controlador.CiudadanoService;
import DisenoDePatrones.Controlador.FuerzaOrdenService;
import DisenoDePatrones.Controlador.HabitanteService;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.DelincuenciaDistritos;
import DisenoDePatrones.Modelo.Reporte;
import DisenoDePatrones.Modelo.Tramite;
import DisenoDePatrones.Vista.ReportVista;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        // Probando el menu con el patron de FACADE
        ReportVista reporteVista = new ReportVista();
        reporteVista.Mostrar();
        
        System.out.println("AREA DE PRUEBA==============================================================");
        
        //CONEXION A BASE DE DATOS
        System.out.println("-----Conexion a base de datos------------------");

        DatabaseConnection dbc = DatabaseConnection.getInstance();
        Connection conn = dbc.getConnection();

        ExecProcedures exec = new ExecProcedures(conn);
        
        //OBTENIENDO LAS LISTAS SEGUN PROCEDIMIENTO ALMACENADOD

        List<Ciudadano> listaAgentes = exec.obtenerRegistrosHumanos("AGENTEPUBLICO");
        List<Ciudadano> listaCiudadanos = exec.obtenerRegistrosHumanos("CIUDADANO");
        List<Ciudadano> listaHabitantes = exec.obtenerRegistrosHumanos("CIUDADANOCOMUN");
        List<Ciudadano> listaFuerzas = exec.obtenerRegistrosHumanos("FUERZAORDEN");

        List<Tramite> tramites = exec.obtenerTramites();

        // CREANDO UN SERVICIO DE CIUDADANO SEGUN UN DNI
        System.out.println("##################################################### FUNCIONES DE UN CIUDADANO (AGENTE PUBLICO, HABITANTE, FUERZA DEL ORDEN)");
        CiudadanoService ciudadano1Service = new CiudadanoService(listaCiudadanos, "12345678A");
        Reporte reporte = ciudadano1Service.crearReporte("20:30", "05/05/25", "ROBO EN DISTRITO", "A ALTAS HORAS DE LA NOCHE\n   SE METIERON A ROBAR MI CASA");
        ciudadano1Service.GeneracionReportes(reporte);
        ciudadano1Service.VisualizarDatosPersonales();
        ciudadano1Service.ConsultaEstadoTramites(tramites);
        ciudadano1Service.HistorialInteraccion();
        
        System.out.println("##################################################### FUNCIONES DE UN HABITANTE");

        HabitanteService habitante1Service = new HabitanteService(listaHabitantes, "72147777A");
        habitante1Service.ParticiparEncuestas();
        
        System.out.println("##################################################### FUNCIONES DE UNA FUERZA DEL ORDEN");

        List<DelincuenciaDistritos> delincuenciaDistritos = exec.ObtenerSIGDelincuencial();
        FuerzaOrdenService fuerzaOrden1Service = new FuerzaOrdenService(listaFuerzas, "01234567J");
        fuerzaOrden1Service.SIGDelincuencial(delincuenciaDistritos, "Lambayeque");
        fuerzaOrden1Service.HistorialInteraccion();
        
        System.out.println("##################################################### FUNCIONES DE UN AGENTE PUBLICO");

        AgentePublicoService agente1Service = new AgentePublicoService(listaAgentes, "12345678A");
        agente1Service.VisualizarReglamentos();
    }
}
