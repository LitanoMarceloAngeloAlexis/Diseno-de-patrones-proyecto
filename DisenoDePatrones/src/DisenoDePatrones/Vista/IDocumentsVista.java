/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista;

import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public interface IDocumentsVista {

    JPanel GetWindow();

    void OnClickCloseButton(Runnable callback);

    void OnClickUploadedButton(Runnable callback);
}
