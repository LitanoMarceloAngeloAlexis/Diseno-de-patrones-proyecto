/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface ISurveyVista {

    HashMap<String, String> GetResponses();

    JPanel GetWindow();

    void OnCancelClickEvent(Runnable event);

    void OnLoadClickEvent(Runnable event);
    
}
