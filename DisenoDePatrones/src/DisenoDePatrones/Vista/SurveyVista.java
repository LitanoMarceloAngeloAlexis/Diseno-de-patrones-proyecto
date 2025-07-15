/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Contents.SurveyForm;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class SurveyVista implements ISurveyVista {
    private final SurveyForm surveyForm = new SurveyForm();
    private final HashMap<String, String> respuestas = new HashMap<>();
    
    private String GetSelectedText(ButtonGroup group) {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "sin_respuesta";
    }
    
    private void SetResponses() {
        this.respuestas.put("Pregunta 1", this.GetSelectedText(this.surveyForm.firstQ));
        this.respuestas.put("Pregunta 2", this.GetSelectedText(this.surveyForm.secondQ));
        this.respuestas.put("Pregunta 3", this.GetSelectedText(this.surveyForm.threeQ));
        this.respuestas.put("Pregunta 4", this.GetSelectedText(this.surveyForm.fourthQ));
        this.respuestas.put("Pregunta 5", this.GetSelectedText(this.surveyForm.fiveQ));
        this.respuestas.put("Pregunta 6", this.surveyForm.valueDescripcion.getText());      
        this.respuestas.put("Pregunta 7", this.surveyForm.valueDescripcion1.getText()); 
    }
    
    @Override
    public HashMap<String, String> GetResponses() {
        return this.respuestas;
    }
    
    @Override
    public void OnCancelClickEvent(Runnable event) {
        this.surveyForm.btnCancelarE.addActionListener((e) -> {
            event.run();
        });
    }
      
    @Override
    public void OnLoadClickEvent(Runnable event) {
        this.surveyForm.btnEntregar.addActionListener((e) -> {
            SetResponses();
            event.run();
        });
    }
    
    @Override
    public JPanel GetWindow() {
        return this.surveyForm;
    }
}
