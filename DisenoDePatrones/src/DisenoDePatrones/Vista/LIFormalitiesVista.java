/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Modelo.Tramite;
import DisenoDePatrones.Vista.Layouts.Contents.LIFormalitiesForm;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alex
 */
public class LIFormalitiesVista implements ILIFormalitiesVista {
    private LIFormalitiesForm liFormalitiesForm = new LIFormalitiesForm();
    
    @Override
    public void OnClickCloseButton(Runnable callback) {
        this.liFormalitiesForm.btnClose.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void ShowInTable(List<Tramite> formalities) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{ "ID", "DNI", "Título", "Estado" });

        for (Tramite t : formalities) {
            model.addRow(new Object[]{
                t.getId(),
                t.getDni(),
                t.getTitulo(),
                t.getEstado()
            });
        }

        this.liFormalitiesForm.table.setModel(model);
    }
    
    @Override
    public JPanel GetWindow() {
       return this.liFormalitiesForm;
    }
}
