/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Components.ImageButton;
import DisenoDePatrones.Vista.Components.NotifyElement.ColorTextDecorator;
import DisenoDePatrones.Vista.Components.NotifyElement.INotify;
import DisenoDePatrones.Vista.Components.NotifyElement.NotifyView;
import DisenoDePatrones.Vista.Layouts.MainForm.InformationNavForm;
import DisenoDePatrones.Vista.Layouts.MainForm.NavigationForm;
import DisenoDePatrones.Vista.Layouts.MainForm.PerfilNavForm;
import DisenoDePatrones.Vista.Layouts.MainForm.ServicesNavForm;
import DisenoDePatrones.Vista.Layouts.Reports.NotificationForm;
import DisenoDePatrones.Vista.Layouts.Reports.RegulationForm;
import DisenoDePatrones.Vista.Layouts.Window.WindowForm;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
// IMPLEMENTACION DE NAVIGATION VISTA CON EL PATRON DE FACADE
public class NavigationVista implements INavigationVista {
    private WindowForm parent;
    
    private final NavigationForm navigationForm = new NavigationForm();
    private final ServicesNavForm servicesNavForm = new ServicesNavForm();
    private final PerfilNavForm perfilNavForm = new PerfilNavForm();
    private final InformationNavForm informationNavForm = new InformationNavForm();
    
    private NotificationForm notiForm;
    private RegulationForm reguForm;    
    private ImageButton currentNavSelected;

    public NavigationVista(WindowForm parent) {        
        this.parent = parent;
        
        this.servicesNavForm.setVisible(false);
        this.perfilNavForm.setVisible(false);
        this.informationNavForm.setVisible(false);
        
        this.navigationForm.GetContentBody().add(this.servicesNavForm);
        this.navigationForm.GetContentBody().add(this.perfilNavForm);
        this.navigationForm.GetContentBody().add(this.informationNavForm);

        this.SwitchWithNavbarButton("Services");
        
        this.navigationForm.GetInformationButton().addActionListener((e) -> {
            this.SwitchWithNavbarButton("Information");
        });
        this.navigationForm.GetPerfilButton().addActionListener((e) -> {
            this.SwitchWithNavbarButton("Perfil");
        });
        this.navigationForm.GetServicesButton().addActionListener((e) -> {
            this.SwitchWithNavbarButton("Services");
        });
    }
    
    private void SwitchWithNavbarButton(String panel) {
        if (panel == null)
            return;
            
        ImageButton localNavSelected = (ImageButton) switch (panel) {
            case "Services" -> this.navigationForm.GetServicesButton();
            case "Perfil" -> this.navigationForm.GetPerfilButton();
            case "Information" -> this.navigationForm.GetInformationButton();
            default -> this.navigationForm.GetServicesButton();
        };
        
        if (this.currentNavSelected != null) {
            this.currentNavSelected.setForeground(new Color(65, 125, 255));
            this.currentNavSelected.setBackgroundColor(new Color(245, 245, 245));
            this.currentNavSelected.setFont(this.currentNavSelected.getFont().deriveFont(Font.PLAIN));
            this.currentNavSelected = null;
        }
        
        this.currentNavSelected = localNavSelected;
        this.currentNavSelected.setForeground(Color.WHITE);
        this.currentNavSelected.setBackgroundColor(new Color(65, 125, 255));
        this.currentNavSelected.setFont(this.currentNavSelected.getFont().deriveFont(Font.BOLD));
    
        this.servicesNavForm.setVisible(panel.equals("Services"));
        this.perfilNavForm.setVisible(panel.equals("Perfil"));
        this.informationNavForm.setVisible(panel.equals("Information"));
    }
    
    @Override
    public void LoadUserDataIntoPerfil(HashMap<String, String> infoData) {
        this.perfilNavForm.lbDNI.setText(infoData.get("dni"));
        this.perfilNavForm.lbNombres.setText(infoData.get("nombres"));
        this.perfilNavForm.lbApellidos.setText(infoData.get("apellidos"));
        this.perfilNavForm.lbEdad.setText(infoData.get("edad"));
        this.perfilNavForm.lbProcedencia.setText(infoData.get("procedencia"));
        this.perfilNavForm.lbEmail.setText(infoData.get("correo"));
    }
    
    @Override
    public void SetContentTextRegulations(String text) {
        this.reguForm.setText(text);
    }    
    
    @Override
    public void ShowRegulationsWindow(String method) {
        WindowForm regulationWindow = new WindowForm(WindowForm.WindowType.DIALOG, this.parent);
        regulationWindow.setTitle("Regulaciones Importantes");
        this.reguForm = new RegulationForm(regulationWindow);
        regulationWindow.add(this.reguForm);
        
        if (method.equals("READ")) {
            this.reguForm.ChangeWriteOrWrite(0);
        } else if (method.equals("WRITE")) {
            this.reguForm.ChangeWriteOrWrite(1); 
        }
        
        regulationWindow.setVisible(true);
    }
        
    @Override
    public void ShowRegulationsWindow(String method, Consumer<String> event) {
        this.ShowRegulationsWindow(method);
        this.reguForm.setOnTextChangeListener(event);
    }
        
    @Override
    public void ShowNotificationWindow() {
        WindowForm notificationWindow = new WindowForm(WindowForm.WindowType.DIALOG, this.parent);
        notificationWindow.setTitle("Regulaciones Importantes");
        notificationWindow.setSize(400, 500);
        this.notiForm = new NotificationForm(notificationWindow);
        notificationWindow.add(this.notiForm);
        notificationWindow.setLocation(950, 300);
        
        notificationWindow.setVisible(true);
    }
    
    @Override
    public void OnServiceReport(Runnable callback) {
        this.servicesNavForm.btnSReports.addActionListener((e) -> {
            callback.run();
        });
    }

    @Override
    public void OnServiceDocuments(Runnable callback) {
        this.servicesNavForm.btnSDocuments.addActionListener((e) -> {
            callback.run();
        });
    }

    @Override
    public void OnServiceFormalities(Runnable callback) {
        this.servicesNavForm.btnSFormalities.addActionListener((e) -> {
            callback.run();
        });
    }

    @Override
    public void OnServiceSurvey(Runnable callback) {
        this.servicesNavForm.btnSSurvey.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void OnInformationResources(Runnable callback) {
        this.informationNavForm.btnIResources.addActionListener((e) -> {
            callback.run();
        });
    }

    @Override
    public void OnInformationCitizens(Runnable callback) {
        this.informationNavForm.btnICitizens.addActionListener((e) -> {
            callback.run();
        });
    }

    @Override
    public void OnInformationDocuments(Runnable callback) {
        this.informationNavForm.btnIInformation.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void OnRegulationsClickEvent(Runnable event) {
        this.navigationForm.GetRegulationsButton().addActionListener((e) -> {            
            event.run();
        });
    }
        
    @Override
    public void OnNotificationClickEvent(Runnable event) {
        this.navigationForm.GetNotificationsButton().addActionListener((e) -> {            
            event.run();
        });
    }
    
    @Override
    public void ChangeStateNotification(int state) {
        ImageButton button = (ImageButton) this.navigationForm.GetNotificationsButton();
        
        if (state == 0) {
            button.setImageName("notificationO.png");
        } else if (state == 1) {
            button.setImageName("notificationI.png");
        }
    }
       
    @Override
    public void AddNewNotificationToList(String message, String date, int priority) {
        if (this.notiForm != null) {
            NotifyView not = new NotifyView();
            not.setContent(message);
            not.setDate(date);
            
            // PATRON DECORADOR APLICADO
            INotify component = new ColorTextDecorator(() -> not, new Color(40, 43, 50));
            
            if (priority == 1) {
                component = new ColorTextDecorator(() -> not, Color.RED);
            } else if (priority == 2) {
                component = new ColorTextDecorator(() -> not, Color.YELLOW);
            }
            
            this.notiForm.GetContentNotifiers().add(component.obtenerElementoDecorado());
        } else {
            System.out.println("ERROR: Aún no se ha cargado notiForm");
        }
    }
   
    @Override
    public void ChangeIconVisible(boolean valor) {
        this.navigationForm.SetIconRegla(valor);
    }
      
    @Override
    public void setOnCtrlEnterRegulation(Runnable listener) {
        if (this.reguForm != null) {
            this.reguForm.setOnCtrlEnterListener(listener);
        } else {
            System.out.println("reguForm aún no ha sido inicializado.");
        }
    }
       
    @Override
    public String obtenerTextoReglamento(){
        return this.reguForm.obtenerTexto();
    }
    
    @Override
    public void EstablecerVistaCiudadano() {
        this.servicesNavForm.btnSFormalities.setVisible(true);
        this.servicesNavForm.btnSSurvey.setVisible(true);
        this.servicesNavForm.btnSReports.setVisible(true);
        this.servicesNavForm.btnSDocuments.setVisible(false);
        this.informationNavForm.btnICitizens.setVisible(false);
        this.informationNavForm.btnIResources.setVisible(false);
        this.informationNavForm.btnIInformation.setVisible(false);
    }
    
    @Override
    public void EstablecerVistaFuerzaDelOrden() {
        this.servicesNavForm.btnSFormalities.setVisible(true);
        this.servicesNavForm.btnSSurvey.setVisible(true);
        this.servicesNavForm.btnSReports.setVisible(true);
        this.servicesNavForm.btnSDocuments.setVisible(false);
        this.informationNavForm.btnICitizens.setVisible(true);
        this.informationNavForm.btnIResources.setVisible(true);
        this.informationNavForm.btnIInformation.setVisible(false);
    }
    
    @Override
    public void EstablecerVistaAgentePublico() {
        this.servicesNavForm.btnSFormalities.setVisible(true);
        this.servicesNavForm.btnSSurvey.setVisible(true);
        this.servicesNavForm.btnSReports.setVisible(true);
        this.servicesNavForm.btnSDocuments.setVisible(true);
        this.informationNavForm.btnICitizens.setVisible(true);
        this.informationNavForm.btnIResources.setVisible(true);
        this.informationNavForm.btnIInformation.setVisible(true);
    }
    
    @Override
    public JPanel GetWindow() {
        return this.navigationForm;
    }
}
