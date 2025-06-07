package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.ReportVista;
import java.sql.Connection;
import java.util.Map;

public class ReportController {
    private IReportVista vista;
    private CiudadanoService ciudadano;
    private ExecProcedures execProcedures;
    Map<String, String> datosReporte;
    Map<String, String> data;
    
    public ReportController(CiudadanoService ciudadano, Connection conn){
        this.execProcedures = new ExecProcedures(conn);
        this.vista = new ReportVista();
        this.vista.Mostrar();
        this.vista.OnNextClickEvent(this::manejarNextClick);
        this.vista.OnCancelClickEvent(this::manejarCancelClick);
        this.vista.OnPreviousClickEvent(this::manejarPreviousClick);
        this.vista.OnThanksClickEvent(this::manejarCancelClick);
        this.ciudadano = ciudadano;
        this.data = ciudadano.getHashMapInfo();
        this.vista.SetCurrentStepData(data);
    }

    private void manejarNextClick() {
        if(vista.GetCurrentStep() == 2){
            
            datosReporte = vista.GetCurrentStepData();
            execProcedures.insertarReporte(data.get("dni"), datosReporte.get("fecha"), datosReporte.get("hora"), datosReporte.get("asunto"), datosReporte.get("descripcion"));
       
        }
        vista.ShowNextStep();
    }
    
    private void manejarCancelClick() {
        vista.Cerrar();
    }

    private void manejarPreviousClick() {
       vista.ShowPreviousStep();
    }
    
}
