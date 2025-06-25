package DisenoDePatrones.Vista;

import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JPanel;

public interface IReportVista {
    void ShowNextStep();
    void ShowPreviousStep();
    Map<String, String> GetCurrentStepData();
    void SetCurrentStepData(Map<String, String> data);
    void OnNextClickEvent(Runnable event);
    void OnCancelClickEvent(Runnable event);
    void OnPreviousClickEvent(Runnable event);
    void OnThanksClickEvent(Runnable event);
    void OnRegulationsClickEvent(Runnable event);
    void OnNotificationClickEvent(Runnable event);
    void Cerrar();
    void Mostrar();
    int GetCurrentStep();
    JPanel getCurrentStepPanel();
    void ChangeStateNotification(int state);
    void ShowRegulationsWindow(String method);
    void ShowRegulationsWindow(String method, Consumer<String> event);
    void ShowNotificationWindow();
    void SetContentTextRegulations(String text);
    void AddNewNotificationToList(String message, String date);
    void ChangeIconVisible(boolean valor);
    void setOnCtrlEnterRegulation(Runnable listener);
    String obtenerTextoReglamento();
}
