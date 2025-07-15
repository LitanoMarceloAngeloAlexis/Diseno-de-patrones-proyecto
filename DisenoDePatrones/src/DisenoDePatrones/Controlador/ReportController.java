package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Reporte;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep2;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;

public class ReportController {
    private IReportVista vista;
    private IMenuVista menuVista;
    private CiudadanoService ciudadano;
    private ExecProcedures execProcedures;
    private Reporte ultimoReporteEnviado;
    Map<String, String> datosReporte;
    Map<String, String> data;
    
    public ReportController(CiudadanoService ciudadano, Connection conn, IReportVista vista, IMenuVista menuVista){
        this.vista = vista;
        this.menuVista = menuVista;
        this.execProcedures = new ExecProcedures(conn);
        this.vista.OnCancelClickEvent(this::manejarCancelClick);
        this.vista.OnNextClickEvent(this::manejarNextClick);
        this.vista.OnPreviousClickEvent(this::manejarPreviousClick);
        this.vista.OnThanksClickEvent(this::manejarCancelClick);
        this.vista.OnOtherReportClickEvent(this::manejarOtherReportClick);
        this.ciudadano = ciudadano;
        this.data = ciudadano.getHashMapInfo();
        this.vista.SetCurrentStepData(data);
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
            Reporte reporte = new Reporte(ciudadano.getCiudadanoActual(), datosReporte.get("hora"), datosReporte.get("fecha"), datosReporte.get("asunto"), datosReporte.get("descripcion"));
            
            execProcedures.insertarReporte(reporte);
            this.ultimoReporteEnviado = reporte;
        }
        vista.ShowNextStep();
    }
    
    private void manejarCancelClick() {
       this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }

    private void manejarPreviousClick() {
       vista.ShowPreviousStep();
    }
 
    
    private void manejarOtherReportClick(){
        this.vista.ShowPreviousStep();
        
        if (ultimoReporteEnviado != null) {
            Reporte reporteClonado = (Reporte) ultimoReporteEnviado.clone();
            ReportStep2 step2 = (ReportStep2) vista.getCurrentStepPanel();
            step2.setAsunto(reporteClonado.getMotivo());
            step2.setDescripcion(reporteClonado.getDescripcion());
            step2.setFecha(reporteClonado.getFecha());
            step2.setHora(reporteClonado.getHora());
        } else {
            JOptionPane.showMessageDialog(null, "No hay reporte anterior para clonar", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
