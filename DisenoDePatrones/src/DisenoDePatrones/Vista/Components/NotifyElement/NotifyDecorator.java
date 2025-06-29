/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Components.NotifyElement;

/**
 *
 * @author Alex
 */
public abstract class NotifyDecorator implements INotify {
    protected final INotify original;

    public NotifyDecorator(INotify original) {
        this.original = original;
    }

    @Override
    public NotifyView obtenerElementoDecorado() {
        return original.obtenerElementoDecorado();
    }
}
