/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Components.ImageButton;
import DisenoDePatrones.Vista.Layouts.Contents.FormalitiesForm;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class FormalitiesVista implements IFormalitiesVista {
    private FormalitiesForm formalitiesForm = new FormalitiesForm();
    
    @Override
    public void MarcarTramiteEnProceso(String tipo) {
         ImageButton boton = this.GetBotonPorTipo(tipo);
        if (boton != null) {
            boton.setImageName("Views/NavTramitando.png");
            boton.setEnabled(false);
        }
    }

    @Override
    public ImageButton GetBotonPorTipo(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "pago de luz" -> this.formalitiesForm.btnSIP;
            case "renovación de dni" -> this.formalitiesForm.btnSPL;
            case "pago de impuestos municipales" -> this.formalitiesForm.btnST;
            case "subsidio" -> this.formalitiesForm.btnDC;
            case "pago de agua" -> this.formalitiesForm.btnAR;
            case "licencia de conducir" -> this.formalitiesForm.btnABD;
            case "actualización de datos" -> this.formalitiesForm.btnCC;
            case "inscripción escolar" -> this.formalitiesForm.btnCLA;
            case "reclamo por servicio público" -> this.formalitiesForm.btnDU;
            default -> null;
        };
    }
    
    @Override
    public void OnCloseButtonEventClick(Runnable callback) {
        this.formalitiesForm.btnClose.addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public JPanel GetWindow() {
        return this.formalitiesForm;
    }
}
