/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Components.ImageButton;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface IFormalitiesVista {

    ImageButton GetBotonPorTipo(String tipo);

    JPanel GetWindow();

    void MarcarTramiteEnProceso(String tipo);

    void OnCloseButtonEventClick(Runnable callback);
    
}
