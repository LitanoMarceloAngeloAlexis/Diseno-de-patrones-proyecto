/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DisenoDePatrones.Vista.Components.NotifyElement.NotifyView;
import java.awt.Color;
import DisenoDePatrones.Vista.Components.NotifyElement.NotifyDecorator;
import DisenoDePatrones.Vista.Components.NotifyElement.INotify;
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
        view.jLabel1.setForeground(this.color);
        return view;
    }
}
