/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface IReportVista {

    int GetCurrentStep();

    Map<String, String> GetCurrentStepData();

    JPanel GetWindow();

    void OnCancelClickEvent(Runnable event);

    void OnNextClickEvent(Runnable event);

    void OnOtherReportClickEvent(Runnable event);

    void OnPreviousClickEvent(Runnable event);

    void OnThanksClickEvent(Runnable event);

    void SetCurrentStepData(Map<String, String> data);

    void ShowNextStep();

    void ShowPreviousStep();

    void SwitchStep(int stepNumber);

    JPanel getCurrentStepPanel();
    
}
