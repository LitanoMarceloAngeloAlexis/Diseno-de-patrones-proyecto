/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Modelo.Tramite;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface ILIFormalitiesVista {

    JPanel GetWindow();

    void OnClickCloseButton(Runnable callback);

    void ShowInTable(List<Tramite> formalities);
    
}
