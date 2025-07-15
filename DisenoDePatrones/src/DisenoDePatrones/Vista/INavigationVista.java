/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import java.util.HashMap;
import java.util.function.Consumer;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface INavigationVista {

    void AddNewNotificationToList(String message, String date, int priority);

    void ChangeIconVisible(boolean valor);

    void ChangeStateNotification(int state);

    void EstablecerVistaAgentePublico();

    void EstablecerVistaCiudadano();

    void EstablecerVistaFuerzaDelOrden();

    JPanel GetWindow();

    void LoadUserDataIntoPerfil(HashMap<String, String> infoData);

    void OnInformationCitizens(Runnable callback);

    void OnInformationDocuments(Runnable callback);

    void OnInformationResources(Runnable callback);

    void OnNotificationClickEvent(Runnable event);

    void OnRegulationsClickEvent(Runnable event);

    void OnServiceDocuments(Runnable callback);

    void OnServiceFormalities(Runnable callback);

    void OnServiceReport(Runnable callback);

    void OnServiceSurvey(Runnable callback);

    void SetContentTextRegulations(String text);

    void ShowNotificationWindow();

    void ShowRegulationsWindow(String method);

    void ShowRegulationsWindow(String method, Consumer<String> event);

    String obtenerTextoReglamento();

    void setOnCtrlEnterRegulation(Runnable listener);
    
}
