/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.MainForm.MainMenuForm;
import DisenoDePatrones.Vista.Layouts.Window.WindowForm;
import java.awt.Component;

/**
 *
 * @author Alex
 */
// IMPLEMENTACION DE MENU VISTA CON EL PATRON DE FACADE
public class MenuVista implements IMenuVista {

    private final WindowForm window;
    private final MainMenuForm menuForm;

    // Vista Persistente
    private final NavigationVista navigationVista;

    // Implementacion de lazy load
    private ReportVista reportVista;
    private SurveyVista surveyVista;
    private FormalitiesVista formalitiesVista;
    private DocumentsVista documentsVista;
    private ResourcesVista resourcesVista;
    private CitizensVista citizensVista;
    private LIFormalitiesVista liFormalitiesVista;

    public enum Vistas {
        NAVIGATION,
        REPORT,
        SURVEY,
        FORMALITIES,
        DOCUMENTS,
        RESOURCES,
        CITIZENS,
        LIFORMALITIES
    }

    public MenuVista() {
        this.window = new WindowForm(WindowForm.WindowType.FRAME);
        this.menuForm = new MainMenuForm(window);
        this.window.add(this.menuForm);
        this.window.setSize(840, 630);
        this.navigationVista = new NavigationVista(this.window);
        this.menuForm.BodyApplication().add(this.navigationVista.GetWindow());
    }

    @Override
    public NavigationVista GetNavbar() {
        return this.navigationVista;
    }

    // Lazy Getters y Reset Methods
    @Override
    public ReportVista getReportVista() {
        if (this.reportVista == null) {
            this.reportVista = new ReportVista();
            this.menuForm.BodyApplication().add(this.reportVista.GetWindow());
        }
        return this.reportVista;
    }

    @Override
    public void resetReportVista() {
        if (this.reportVista != null) {
            this.menuForm.BodyApplication().remove(this.reportVista.GetWindow());
            this.reportVista = null;
        }
    }

    @Override
    public SurveyVista getSurveyVista() {
        if (this.surveyVista == null) {
            this.surveyVista = new SurveyVista();
            this.menuForm.BodyApplication().add(this.surveyVista.GetWindow());
        }
        return this.surveyVista;
    }

    @Override
    public void resetSurveyVista() {
        if (this.surveyVista != null) {
            this.menuForm.BodyApplication().remove(this.surveyVista.GetWindow());
            this.surveyVista = null;
        }
    }

    @Override
    public FormalitiesVista getFormalitiesVista() {
        if (this.formalitiesVista == null) {
            this.formalitiesVista = new FormalitiesVista();
            this.menuForm.BodyApplication().add(this.formalitiesVista.GetWindow());
        }
        return this.formalitiesVista;
    }

    @Override
    public void resetFormalitiesVista() {
        if (this.formalitiesVista != null) {
            this.menuForm.BodyApplication().remove(this.formalitiesVista.GetWindow());
            this.formalitiesVista = null;
        }
    }

    @Override
    public DocumentsVista getDocumentsVista() {
        if (this.documentsVista == null) {
            this.documentsVista = new DocumentsVista();
            this.menuForm.BodyApplication().add(this.documentsVista.GetWindow());
        }
        return this.documentsVista;
    }

    @Override
    public void resetDocumentsVista() {
        if (this.documentsVista != null) {
            this.menuForm.BodyApplication().remove(this.documentsVista.GetWindow());
            this.documentsVista = null;
        }
    }

    @Override
    public ResourcesVista getResourcesVista() {
        if (this.resourcesVista == null) {
            this.resourcesVista = new ResourcesVista();
            this.menuForm.BodyApplication().add(this.resourcesVista.GetWindow());
        }
        return this.resourcesVista;
    }

    @Override
    public void resetResourcesVista() {
        if (this.resourcesVista != null) {
            this.menuForm.BodyApplication().remove(this.resourcesVista.GetWindow());
            this.resourcesVista = null;
        }
    }

    @Override
    public CitizensVista getCitizensVista() {
        if (this.citizensVista == null) {
            this.citizensVista = new CitizensVista();
            this.menuForm.BodyApplication().add(this.citizensVista.GetWindow());
        }
        return this.citizensVista;
    }

    @Override
    public void resetCitizensVista() {
        if (this.citizensVista != null) {
            this.menuForm.BodyApplication().remove(this.citizensVista.GetWindow());
            this.citizensVista = null;
        }
    }

    @Override
    public LIFormalitiesVista getLIFormalitiesVista() {
        if (this.liFormalitiesVista == null) {
            this.liFormalitiesVista = new LIFormalitiesVista();
            this.menuForm.BodyApplication().add(this.liFormalitiesVista.GetWindow());
        }
        return this.liFormalitiesVista;
    }

    @Override
    public void resetLIFormalitiesVista() {
        if (this.liFormalitiesVista != null) {
            this.menuForm.BodyApplication().remove(this.liFormalitiesVista.GetWindow());
            this.liFormalitiesVista = null;
        }
    }

    @Override
    public void ChangeToView(Vistas vista) {
        for (Component component : this.menuForm.BodyApplication().getComponents()) {
            component.setVisible(false);
        }

        switch (vista) {
            case NAVIGATION -> this.navigationVista.GetWindow().setVisible(true);
            case REPORT -> this.getReportVista().GetWindow().setVisible(true);
            case SURVEY -> this.getSurveyVista().GetWindow().setVisible(true);
            case FORMALITIES -> this.getFormalitiesVista().GetWindow().setVisible(true);
            case DOCUMENTS -> this.getDocumentsVista().GetWindow().setVisible(true);
            case RESOURCES -> this.getResourcesVista().GetWindow().setVisible(true);
            case CITIZENS -> this.getCitizensVista().GetWindow().setVisible(true);
            case LIFORMALITIES -> this.getLIFormalitiesVista().GetWindow().setVisible(true);
        }
    }
}
