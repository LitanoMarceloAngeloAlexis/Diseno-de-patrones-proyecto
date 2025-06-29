package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Notificacion;
import DisenoDePatrones.Modelo.Reglamento;
import DisenoDePatrones.Modelo.Reporte;
import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep2;
import DisenoDePatrones.Vista.ReportVista;
import java.sql.Connection;
import java.util.ArrayList;
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
        this.vista.OnNotificationClickEvent(this::manejarNotificationClick);
        this.vista.OnOtherReportClickEvent(this::manejarOtherReportClick);
        this.ciudadano = ciudadano;
        this.data = ciudadano.getHashMapInfo();
        this.vista.SetCurrentStepData(data);
        this.comprobarNotificaciones();
        this.opcionesDeRol();
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
            
            // !! TE QUEDASTE AQUI, ESTABAMOS MODIFICANDO EL PROCEDURE PARA QUE ACEPTE REPORTES!!!!
            
            //execProcedures.insertarReporte(data.get("dni"), datosReporte.get("fecha"), datosReporte.get("hora"), datosReporte.get("asunto"), datosReporte.get("descripcion"));
       
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
        Reglamento reglamento = Reglamento.getInstancia();
        if(ciudadano instanceof ProxyFuerzaOrdenService){
            vista.ShowRegulationsWindow("READ");
            vista.SetContentTextRegulations(reglamento.getContenido());
        } else if (ciudadano instanceof ProxyAgentePublicoService) {
            vista.ShowRegulationsWindow("WRITE");
            vista.SetContentTextRegulations(reglamento.getContenido());

            List<Ciudadano> fuerzas = execProcedures.obtenerRegistrosHumanos("FUERZAORDEN");
            List<FuerzaOrden> fuerzasOrden = new ArrayList<>();
            
            for (Ciudadano c : fuerzas) {
                if (c instanceof FuerzaOrden f) {
                    fuerzasOrden.add(f);
                }
            }
            
            reglamento.registrarObservador(new NotificacionDispatcher(execProcedures, fuerzasOrden));
            
            vista.setOnCtrlEnterRegulation(() -> {
                System.out.println("Desde el controlador: Se presiono Ctrl+Enter");
                String nuevoContenido = vista.obtenerTextoReglamento();
                reglamento.modificar(nuevoContenido);
            });
        }
    }
    
    private void manejarNotificationClick(){
        vista.ShowNotificationWindow();
        
        String dni = ciudadano.getCiudadanoActual().getDNI();
        List<Notificacion> notificaciones = execProcedures.leerNotificacionesNoLeidas(dni);

        for (Notificacion noti : notificaciones) {
            String mensaje = noti.getMensaje();
            String fecha = noti.getFecha().toLocalDate().toString();
            System.out.println(mensaje + fecha);
            vista.AddNewNotificationToList(mensaje, fecha);
        }
    }
    
    private void manejarOtherReportClick(){
        this.vista.ShowPreviousStep();
        
    }
    
    private void opcionesDeRol(){
        if(ciudadano instanceof ProxyFuerzaOrdenService || ciudadano instanceof ProxyAgentePublicoService){
            this.vista.ChangeIconVisible(true);
        }
    }
}
