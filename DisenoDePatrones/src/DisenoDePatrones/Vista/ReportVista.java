package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Window.WindowForm;
import DisenoDePatrones.Vista.Components.NotifyView;
import DisenoDePatrones.Vista.Layouts.Reports.NotificationForm;
import DisenoDePatrones.Vista.Layouts.Reports.RegulationForm;
import DisenoDePatrones.Vista.Layouts.Reports.ReportForm;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep1;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep2;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep3;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

// IMPLEMENTACION DE REPORTE VISTA CON EL PATRON DE FACADE
public class ReportVista implements IReportVista {
    private int currentStep = 1;
    private JPanel currentStepPanel;
        
    private WindowForm window;
    private ReportForm reportForm;
    private RegulationForm reguForm;
    private NotificationForm notiForm;
    
    public ReportVista() {
        this.window = new WindowForm(WindowForm.WindowType.FRAME);
        this.reportForm = new ReportForm(this.window);
        this.window.add(this.reportForm);
        this.window.setSize(800, 600);
        
        this.SwitchStep(this.currentStep);
    }
    
    @Override
    public void SetContentTextRegulations(String text) {
        this.reguForm.setText(text);
    }
    
    @Override
    public void ShowRegulationsWindow(String method) {
        WindowForm regulationWindow = new WindowForm(WindowForm.WindowType.DIALOG, this.window);
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
        WindowForm notificationWindow = new WindowForm(WindowForm.WindowType.DIALOG, this.window);
        notificationWindow.setTitle("Regulaciones Importantes");
        notificationWindow.setSize(400, 500);
        this.notiForm = new NotificationForm(notificationWindow);
        notificationWindow.add(this.notiForm);
        
        notificationWindow.setVisible(true);
    }

    @Override
    public void ShowNextStep() {
        if (this.currentStep >= 3)
            return;

        this.currentStep += 1;
        this.SwitchStep(this.currentStep);
    }

    @Override
    public void ShowPreviousStep() {
        if (this.currentStep <= 1)
            return;

        this.currentStep -= 1;
        this.SwitchStep(this.currentStep);
    }

    @Override
    public Map<String, String> GetCurrentStepData() {
        Map<String, String> data = new HashMap<>();

        switch (this.currentStepPanel) {
            case ReportStep1 step1 -> {
                data.put("dni", step1.getDNI());
                data.put("nombres", step1.getNombres());
                data.put("apellidos", step1.getApellidos());
                data.put("edad", step1.getEdad());
                data.put("procedencia", step1.getProcedencia());
                data.put("correo", step1.getCorreo());
            }
            case ReportStep2 step2 -> {
                data.put("asunto", step2.getAsunto());
                data.put("hora", step2.getHora());
                data.put("fecha", step2.getFecha());
                data.put("descripcion", step2.getDescripcion());
            }
            default -> {}
        }

        return data;
    }

    @Override
    public void SetCurrentStepData(Map<String, String> data) {
        String[] Step1Keys = { "dni", "nombres", "apellidos", "edad", "procedencia", "correo" };
        String[] Step2Keys = { "asunto", "hora", "fecha", "descripcion" };
        
        switch (this.currentStepPanel) {
            case null -> {}

            case ReportStep1 step1 -> {
                for (String key : Step1Keys) {
                    if (data.containsKey(key)) {
                        String value = data.get(key);
                        switch (key) {
                            case "dni" -> step1.setDNI(value);
                            case "nombres" -> step1.setNombres(value);
                            case "apellidos" -> step1.setApellidos(value);
                            case "edad" -> step1.setEdad(value);
                            case "procedencia" -> step1.setProcedencia(value);
                            case "correo" -> step1.setCorreo(value);
                            default -> System.err.println("Clave desconocida para ReportStep1: " + key);
                        }
                    }
                }
            }
            
            case ReportStep2 step2 -> {
                 for (String key : Step2Keys) {
                    if (data.containsKey(key)) {
                        String value = data.get(key);
                        switch (key) {
                            case "asunto" -> step2.setAsunto(value);
                            case "hora" -> step2.setHora(value);
                            case "fecha" -> step2.setFecha(value);
                            case "descripcion" -> step2.setDescripcion(value);
                            default -> System.err.println("Clave desconocida para ReportStep1: " + key);
                        }
                    }
                }
            }
            default -> {}
        }
    }

    @Override
    public void OnNextClickEvent(Runnable event) {
        this.reportForm.GetNextButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.run();
            }
        });
    }
    
    @Override
    public void OnPreviousClickEvent(Runnable event) {
        this.reportForm.GetPreviousButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.run();
            }
        });
    }
    
    @Override
    public void OnCancelClickEvent(Runnable event) {
        this.reportForm.GetCancelButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.run();
            }
        });
    }
    
    @Override
    public void OnThanksClickEvent(Runnable event) {
        this.reportForm.GetThanksButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                event.run();
            }
        });
    }
    
    @Override
    public void OnRegulationsClickEvent(Runnable event) {
        this.reportForm.GetRegulationsButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               event.run();
            }
        });
    }
    
    @Override
    public void OnNotificationClickEvent(Runnable event) {
        this.reportForm.GetNotificationButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               event.run();
            }
        });
    }   
    
    @Override
    public void OnOtherReportClickEvent(Runnable event) {
        this.reportForm.getBtnOtherReport().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               event.run();
            }
        });
    }
    
    @Override
    public void Mostrar() {
        this.window.setVisible(true);
    }
    
    @Override
    public void Cerrar() {
        this.window.dispose();
    }

    //@Override
    private void SwitchStep(int step) {
        JPanel mainContent = this.reportForm.GetMainContent();

        if (this.currentStepPanel != null) {
            mainContent.remove(this.currentStepPanel);
        }

        switch (step) {
            case 1 -> {
                this.reportForm.GetPreviousButton().setVisible(false);
                this.reportForm.GetThanksButton().setVisible(false);
                this.reportForm.GetNextButton().setVisible(true);
                this.reportForm.getBtnOtherReport().setVisible(false);
                this.currentStepPanel = new ReportStep1();
            }
            case 2 -> {
                this.currentStepPanel = new ReportStep2();
                this.reportForm.GetThanksButton().setVisible(false);
                this.reportForm.GetNextButton().setVisible(true);
                this.reportForm.GetPreviousButton().setVisible(true);
                this.reportForm.getBtnOtherReport().setVisible(false);
            }
            case 3 -> {
                this.reportForm.GetPreviousButton().setVisible(false);
                this.reportForm.GetNextButton().setVisible(false);
                this.reportForm.GetCancelButton().setVisible(false);
                this.reportForm.GetThanksButton().setVisible(true);
                this.reportForm.getBtnOtherReport().setVisible(true);
                this.currentStepPanel = new ReportStep3();
            }
            default -> {
                this.currentStepPanel = new JPanel();
            }
        }
        
        //this.changeEvent.vrun(this.currentStep);
        mainContent.add(this.currentStepPanel, new AbsoluteConstraints(0, 0, 800, 420));
        mainContent.revalidate();
        mainContent.repaint();
    }

    @Override
    public int GetCurrentStep() {
        return currentStep;
    }
    
    @Override
    public JPanel getCurrentStepPanel(){
        return this.currentStepPanel;
    }
    
    @Override
    public void ChangeStateNotification(int state) {
        JLabel button = this.reportForm.GetNotificationButton();
        
        if (state == 0) {
            button.setIcon(new ImageIcon(getClass().getResource("/DisenoDePatrones/Vista/Assets/notificationO.png")));
        } else if (state == 1) {
            button.setIcon(new ImageIcon(getClass().getResource("/DisenoDePatrones/Vista/Assets/notificationI.png")));
        }
    }
    
    @Override
    public void AddNewNotificationToList(String message, String date) {
        if (this.notiForm != null) {
            NotifyView not = new NotifyView();
            not.setContent(message);
            not.setDate(date);
            this.notiForm.addNotification(not);
        } else {
            System.out.println("ERROR: Aún no se ha cargado notiForm");
        }
    }

    @Override
    public void ChangeIconVisible(boolean valor) {
        this.reportForm.SetIconRegla(valor);
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
}
