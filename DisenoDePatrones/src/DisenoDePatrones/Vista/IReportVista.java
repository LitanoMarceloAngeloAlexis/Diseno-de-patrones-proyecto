package DisenoDePatrones.Vista;

import java.util.Map;

public interface IReportVista {
    void ShowNextStep();
    void ShowPreviousStep();
    Map<String, String> GetCurrentStepData();
    void SetCurrentStepData(Map<String, String> data);
    void OnNextClickEvent(Runnable event);
    void OnCancelClickEvent(Runnable event);
    void OnPreviousClickEvent(Runnable event);
    void OnThanksClickEvent(Runnable event);
    void Mostrar();
    void Cerrar();
}
