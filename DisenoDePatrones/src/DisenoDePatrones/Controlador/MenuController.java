/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Notificacion;
import DisenoDePatrones.Modelo.Reglamento;
import DisenoDePatrones.Vista.ICitizensVista;
import DisenoDePatrones.Vista.IDocumentsVista;
import DisenoDePatrones.Vista.IFormalitiesVista;
import DisenoDePatrones.Vista.ILIFormalitiesVista;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.INavigationVista;
import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.IResourcesVista;
import DisenoDePatrones.Vista.ISurveyVista;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */

public class MenuController {
    
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadanoService;
    private final Connection connection;
    private final ExecProcedures execProcedures;
    private final INavigationVista navigation;

    public MenuController(IMenuVista menuVista, CiudadanoService ciudadanoService, Connection connection) {
        this.menuVista = menuVista;
        this.ciudadanoService = ciudadanoService;
        this.connection = connection;
        this.execProcedures = new ExecProcedures(connection);
        
        this.navigation = this.menuVista.GetNavbar();
        navigation.LoadUserDataIntoPerfil(this.ciudadanoService.getHashMapInfo());
        navigation.OnServiceFormalities(this::manejarTramites);
        navigation.OnServiceReport(this::manejarReportes);
        navigation.OnServiceSurvey(this::manejarEncuestas);
        navigation.OnServiceDocuments(this::manejarDocumentos);
        navigation.OnInformationResources(this::manejarRecursos);
        navigation.OnInformationCitizens(this::manejarCiudadanos);
        navigation.OnInformationDocuments(this::manejarLITramites);
        
        navigation.OnRegulationsClickEvent(this::manejarClickReglamento);
        navigation.OnNotificationClickEvent(this::manejarNotificationClick);
        
        this.comprobarNotificaciones();
        this.opcionesDeRol();
    }
    
    private void comprobarNotificaciones() {
        String dni = this.ciudadanoService.getCiudadanoActual().getDNI();
        List<Notificacion> notificaciones = execProcedures.leerNotificacionesNoLeidas(dni);

        if (!notificaciones.isEmpty()) {
            this.navigation.ChangeStateNotification(1);
        } else {
            this.navigation.ChangeStateNotification(0);
        }
    }
    
    private void manejarClickReglamento(){
        Reglamento reglamento = Reglamento.getInstancia();
        if(this.ciudadanoService instanceof ProxyFuerzaOrdenService){
            this.navigation.ShowRegulationsWindow("READ");
            this.navigation.SetContentTextRegulations(reglamento.getContenido());
        } else if (this.ciudadanoService instanceof ProxyAgentePublicoService) {
            this.navigation.ShowRegulationsWindow("WRITE");
            this.navigation.SetContentTextRegulations(reglamento.getContenido());

            List<Ciudadano> fuerzas = execProcedures.obtenerRegistrosHumanos("FUERZAORDEN");
            List<FuerzaOrden> fuerzasOrden = new ArrayList<>();
            
            for (Ciudadano c : fuerzas) {
                if (c instanceof FuerzaOrden f) {
                    fuerzasOrden.add(f);
                }
            }
            
            reglamento.registrarObservador(new NotificacionDispatcher(execProcedures, fuerzasOrden));
            
            this.navigation.setOnCtrlEnterRegulation(() -> {
                System.out.println("Desde el controlador: Se presiono Ctrl+Enter");
                String nuevoContenido = this.navigation.obtenerTextoReglamento();
                reglamento.modificar(nuevoContenido);
            });
        }
    }
    
    private void manejarNotificationClick(){
        this.navigation.ShowNotificationWindow();
        
        String dni = this.ciudadanoService.getCiudadanoActual().getDNI();
        List<Notificacion> notificaciones = execProcedures.leerNotificacionesNoLeidas(dni);

        for (Notificacion noti : notificaciones) {
            String mensaje = noti.getMensaje();
            String fecha = noti.getFecha().toLocalDate().toString();
            System.out.println(mensaje + fecha);
            this.navigation.AddNewNotificationToList(mensaje, fecha, 0);
        }
    }
    
    private void opcionesDeRol(){
        if(ciudadanoService instanceof ProxyFuerzaOrdenService || ciudadanoService instanceof ProxyAgentePublicoService) {
            this.navigation.ChangeIconVisible(true);
        } else {
            this.navigation.ChangeIconVisible(false);
        }
        
        if (ciudadanoService instanceof ProxyFuerzaOrdenService) {
            this.navigation.EstablecerVistaFuerzaDelOrden();
        } else if (ciudadanoService instanceof ProxyAgentePublicoService) {
            this.navigation.EstablecerVistaAgentePublico();
        } else {
            this.navigation.EstablecerVistaCiudadano();
        }
    }

    private void manejarReportes() {
        this.menuVista.resetReportVista();
        IReportVista reportVista = this.menuVista.getReportVista();
        new ReportController(ciudadanoService, connection, reportVista, menuVista);
        this.menuVista.ChangeToView(MenuVista.Vistas.REPORT);
    }

    private void manejarTramites() {
        this.menuVista.resetFormalitiesVista();
        IFormalitiesVista formalitiesVista = this.menuVista.getFormalitiesVista();
        new FormalitiesController(ciudadanoService, connection, formalitiesVista, menuVista);
        this.menuVista.ChangeToView(MenuVista.Vistas.FORMALITIES);
    }

    private void manejarEncuestas() {
        this.menuVista.resetSurveyVista();
        ISurveyVista surveyVista = this.menuVista.getSurveyVista();
        new SurveyController(ciudadanoService, connection, surveyVista, menuVista);
        this.menuVista.ChangeToView(MenuVista.Vistas.SURVEY);
    }

    private void manejarDocumentos() {
        this.menuVista.resetDocumentsVista();
        IDocumentsVista documentsVista = this.menuVista.getDocumentsVista();
        new DocumentsController(ciudadanoService, connection, documentsVista, menuVista);
        this.menuVista.ChangeToView(MenuVista.Vistas.DOCUMENTS);
    }

    private void manejarRecursos() {
        this.menuVista.resetResourcesVista();
        IResourcesVista resourcesVista = this.menuVista.getResourcesVista();
        new ResourceController(ciudadanoService, connection, resourcesVista, menuVista); 
        this.menuVista.ChangeToView(MenuVista.Vistas.RESOURCES);
    }

    private void manejarCiudadanos() {
        this.menuVista.resetCitizensVista();
        ICitizensVista citizensVista = this.menuVista.getCitizensVista();
        new CitizensController(ciudadanoService, connection, citizensVista, menuVista); 
        this.menuVista.ChangeToView(MenuVista.Vistas.CITIZENS);
    }

    private void manejarLITramites() {
        this.menuVista.resetLIFormalitiesVista();
        ILIFormalitiesVista liFormalitiesVista = this.menuVista.getLIFormalitiesVista();
        new LIInformationController(ciudadanoService, connection, liFormalitiesVista, menuVista); 
        this.menuVista.ChangeToView(MenuVista.Vistas.LIFORMALITIES);
    }
}