package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Reports.ReportStep1;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep2;
import DisenoDePatrones.Vista.Layouts.Reports.ReportStep3;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

// IMPLEMENTACION DE REPORTE VISTA CON EL PATRON DE FACADE
public class ReportVista implements IReportVista {
    private int currentStep = 1;
    private JPanel currentStepPanel;
        
    private JPanel reportForm;
    private final ReportStep1 reportStep1;
    private final ReportStep2 reportStep2;
    private final ReportStep3 reportStep3;
    
    public ReportVista() {
        this.reportForm = new JPanel();
        this.reportForm.setLayout(new CardLayout());
        
        this.reportStep1 = new ReportStep1();
        this.reportStep2 = new ReportStep2();
        this.reportStep3 = new ReportStep3();

        this.reportForm.add(this.reportStep1, "STEP1");
        this.reportForm.add(this.reportStep2, "STEP2");
        this.reportForm.add(this.reportStep3, "STEP3");
        
        this.currentStep = 1;
        this.SwitchStep(this.currentStep);
    }
    
    @Override
    public void SwitchStep(int stepNumber) {
        CardLayout cl = (CardLayout)(this.reportForm.getLayout());
        switch (stepNumber) {
            case 1 -> {
                cl.show(this.reportForm, "STEP1");
                this.currentStepPanel = this.reportStep1;
                this.currentStep = 1;
            }
            case 2 -> {
                cl.show(this.reportForm, "STEP2");
                this.currentStepPanel = this.reportStep2;
                this.currentStep = 2;
            }
            case 3 -> {
                cl.show(this.reportForm, "STEP3");
                this.currentStepPanel = this.reportStep3;
                this.currentStep = 3;
            }
            default -> {
                cl.show(this.reportForm, "STEP1");
                this.currentStepPanel = this.reportStep1;
                this.currentStep = 1;
            }
        }
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
        this.reportStep1.btnNext.addActionListener((e) -> {
            event.run();
        });
        
        this.reportStep2.btnNext.addActionListener((e) -> {
            event.run();
        });
    }
    
    @Override
    public void OnPreviousClickEvent(Runnable event) {
        this.reportStep2.btnPrevious.addActionListener((e) -> {
            event.run();
        });
    }
       
    @Override
    public void OnCancelClickEvent(Runnable event) {
        this.reportStep1.btnCancelar.addActionListener((e) -> {
            event.run();
        });
        
        this.reportStep2.btnCancelar.addActionListener((e) -> {
            event.run();
        });
    }
    
    @Override
    public void OnThanksClickEvent(Runnable event) {
        this.reportStep3.btnThanks.addActionListener((e) -> {
            event.run();
        });
    }
       
    @Override
    public void OnOtherReportClickEvent(Runnable event) {
        this.reportStep3.btnRepeat.addActionListener((e) -> {
            event.run();
        });
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
    public JPanel GetWindow() {
        return this.reportForm;
    }
}
