/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

/**
 *
 * @author Angelo
 */
public class NotificacionSimple implements NotificacionComponente {
    private Command comando;

    public NotificacionSimple(Command comando) {
        this.comando = comando;
    }

    @Override
    public void enviar() {
        comando.execute();
    }
}