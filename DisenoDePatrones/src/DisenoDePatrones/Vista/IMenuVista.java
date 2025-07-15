/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

/**
 *
 * @author Alex
 */
public interface IMenuVista {

    void ChangeToView(MenuVista.Vistas vista);

    NavigationVista GetNavbar();

    CitizensVista getCitizensVista();

    DocumentsVista getDocumentsVista();

    FormalitiesVista getFormalitiesVista();

    LIFormalitiesVista getLIFormalitiesVista();

    // Lazy Getters y Reset Methods
    ReportVista getReportVista();

    ResourcesVista getResourcesVista();

    SurveyVista getSurveyVista();

    void resetCitizensVista();

    void resetDocumentsVista();

    void resetFormalitiesVista();

    void resetLIFormalitiesVista();

    void resetReportVista();

    void resetResourcesVista();

    void resetSurveyVista();
    
}
