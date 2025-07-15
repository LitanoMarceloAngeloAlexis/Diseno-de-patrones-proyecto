/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.ISurveyVista;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class SurveyController {
    private final ISurveyVista vista;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadano;
    private final ExecProcedures execProcedures;

    public SurveyController(CiudadanoService ciudadanoService, Connection connection, ISurveyVista surveyVista, IMenuVista menuVista) {
        this.vista = surveyVista;
        this.menuVista = menuVista;
        this.ciudadano = ciudadanoService;
        this.execProcedures = new ExecProcedures(connection);

        this.vista.OnCancelClickEvent(this::manejarCancelClick);
        this.vista.OnLoadClickEvent(this::manejarLoadClick);
    }

    private void manejarCancelClick() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }

    private void manejarLoadClick() {
        Map<String, String> respuestas = this.vista.GetResponses();
        String dni = ciudadano.getCiudadanoActual().getDNI();

        boolean guardado = this.execProcedures.insertarEncuesta(dni, respuestas);

        if (guardado) {
            JOptionPane.showMessageDialog(null, "Encuesta guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar la encuesta.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
}
