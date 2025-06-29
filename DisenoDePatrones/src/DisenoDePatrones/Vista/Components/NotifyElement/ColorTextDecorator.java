/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Components.NotifyElement;

import java.awt.Color;

/**
 *
 * @author Alex
 */
public class ColorTextDecorator extends NotifyDecorator {
    private final Color color;

    public ColorTextDecorator(INotify original, Color color) {
        super(original);
        this.color = color;
    }

    @Override
    public NotifyView obtenerElementoDecorado() {
        NotifyView view = super.obtenerElementoDecorado();
        view.setContent(view.jLabel1.getText()); 
        view.jLabel1.setForeground(this.color);
        return view;
    }
}

