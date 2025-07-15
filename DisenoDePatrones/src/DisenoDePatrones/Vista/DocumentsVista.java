/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Contents.DocumentsForm;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class DocumentsVista implements IDocumentsVista {
    private DocumentsForm documentsForm = new DocumentsForm(); 
    
    @Override
    public void OnClickCloseButton(Runnable callback) {
        this.documentsForm.btnClose.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void OnClickUploadedButton(Runnable callback) {
        this.documentsForm.btnUploaded.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public JPanel GetWindow() {
       return this.documentsForm;
    }
}
