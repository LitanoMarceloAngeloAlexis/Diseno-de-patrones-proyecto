/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Vista.Layouts.Contents.ResourcesForm;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class ResourcesVista implements IResourcesVista {
    private ResourcesForm resourcesForm = new ResourcesForm();
    
    @Override
    public void OnClickCloseButton(Runnable callback) {
        this.resourcesForm.btnClose.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void ShowInTable(List<Ciudadano> ciudadanos) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{ "DNI", "Nombre", "Apellido", "Edad", "Procedencia" });

        for (Ciudadano c : ciudadanos) {
            model.addRow(new Object[]{
                c.getDNI(),
                c.getNombre(),
                c.getApellido(),
                c.getEdad(),
                c.getProcedencia(),
            });
        }

        this.resourcesForm.table.setModel(model);
    }
    
    @Override
    public JPanel GetWindow() {
       return this.resourcesForm;
    }
}
