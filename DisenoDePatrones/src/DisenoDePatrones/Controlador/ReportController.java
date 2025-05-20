package DisenoDePatrones.Controlador;

import DisenoDePatrones.Vista.IReportVista;
import DisenoDePatrones.Vista.ReportVista;
import java.util.Map;

public class ReportController {
    private IReportVista vista;
    private CiudadanoService ciudadano;
    
    public ReportController(CiudadanoService ciudadano){
        this.vista = new ReportVista();
        this.vista.Mostrar();
        this.vista.OnNextClickEvent(this::manejarNextClick);
        this.vista.OnCancelClickEvent(this::manejarCancelClick);
        this.vista.OnPreviousClickEvent(this::manejarPreviousClick);
        this.vista.OnThanksClickEvent(this::manejarCancelClick);
        this.ciudadano = ciudadano;
        Map data = ciudadano.getHashMapInfo();
        this.vista.SetCurrentStepData(data);
    }

    private void manejarNextClick() {
        vista.ShowNextStep();
    }
    
    private void manejarCancelClick() {
        vista.Cerrar();
    }

    private void manejarPreviousClick() {
       vista.ShowPreviousStep();
    }
    
}
