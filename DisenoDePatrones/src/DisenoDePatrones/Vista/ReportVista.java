package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.ReportForm;
import DisenoDePatrones.Vista.Layouts.ReportStep1;
import DisenoDePatrones.Vista.Layouts.ReportStep2;
import DisenoDePatrones.Vista.Layouts.ReportStep3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

// IMPLEMENTACION DE REPORTE VISTA CON EL PATRON DE FACADE
public class ReportVista implements IReportVista {
    private JFrame window;
    private ReportForm reportForm;
    private int currentStep = 1;
    private JPanel currentStepPanel;
    private IRunnableParams<Integer> changeEvent;

    public ReportVista() {
        this.window = new JFrame();
        this.reportForm = new ReportForm(window);

        this.window.setUndecorated(true);
        this.window.setBackground(new Color(0, 0, 0, 0));
        this.window.setSize(new Dimension(800, 600));
        this.window.setLocationRelativeTo(null);
        this.window.add(this.reportForm);

        this.window.setVisible(false);
        this.SwitchStep(this.currentStep);
        
        this.reportForm.ActionExitApplicaction().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cerrar();
            }
        });
        
         this.reportForm.ActionMinimize().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.setExtendedState(window.ICONIFIED);
            }
        });
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
    public void OnChangeStep(IRunnableParams<Integer> event) {
        this.changeEvent = event;
    }
    
    @Override
    public void Mostrar() {
        this.window.setVisible(true);
    }
    
    @Override
    public void Cerrar() {
        this.window.dispose();
    }

    private void SwitchStep(int step) {
        JPanel mainContent = this.reportForm.GetMainContent();

        if (this.currentStepPanel != null) {
            mainContent.remove(this.currentStepPanel);
        }

        switch (step) {
            case 1 -> {
                this.reportForm.GetPreviousButton().setVisible(false);
                this.reportForm.GetThanksButton().setVisible(false);
                this.currentStepPanel = new ReportStep1();
            }
            case 2 -> {
                this.currentStepPanel = new ReportStep2();
                this.reportForm.GetThanksButton().setVisible(false);
                this.reportForm.GetPreviousButton().setVisible(true);
            }
            case 3 -> {
                this.reportForm.GetPreviousButton().setVisible(false);
                this.reportForm.GetNextButton().setVisible(false);
                this.reportForm.GetCancelButton().setVisible(false);
                this.reportForm.GetThanksButton().setVisible(true);
                this.currentStepPanel = new ReportStep3();
            }
            default -> {
                this.currentStepPanel = new JPanel();
            }
        }
        
        this.changeEvent.vrun(this.currentStep);
        mainContent.add(this.currentStepPanel, new AbsoluteConstraints(0, 0, 800, 420));
        mainContent.revalidate();
        mainContent.repaint();
    }
}