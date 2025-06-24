package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Notificacion;
import DisenoDePatrones.Modelo.Reglamento;
import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.Layouts.Reports.RegulationForm;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep2;
import DisenoDePatrones.Vista.ReportVista;
import DisenoDePatrones.Vista.Window;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

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
        this.vista.OnRegulationsClickEvent(this::manejarClickReglamento);
        this.ciudadano = ciudadano;
        this.data = ciudadano.getHashMapInfo();
        this.vista.SetCurrentStepData(data);
        this.comprobarNotificaciones();
    }

    private void manejarNextClick() {
        if(vista.GetCurrentStep() == 2){
            ReportStep2 step2 = (ReportStep2) vista.getCurrentStepPanel();
            String asunto = step2.getAsunto().trim();
            String hora = step2.getHora().trim();
            String fecha = step2.getFecha().trim();
            String descripcion = step2.getDescripcion().trim();

            if (asunto.isEmpty() || hora.isEmpty() || fecha.isEmpty() || descripcion.isEmpty() || asunto.equals("...") || hora.equals("...") || fecha.equals("...") || descripcion.equals("...")) { 
                JOptionPane.showMessageDialog(null, "Recuerda rellenar todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
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
    
    private void comprobarNotificaciones() {
        String dni = ciudadano.getCiudadanoActual().getDNI();
        List<Notificacion> notificaciones = execProcedures.leerNotificacionesNoLeidas(dni);

        if (!notificaciones.isEmpty()) {
            vista.ChangeStateNotification(1);
        } else {
            vista.ChangeStateNotification(0);
        }
    }
    
    private void manejarClickReglamento(){
        vista.ShowRegulationsWindow("READ");
    }
    
}
