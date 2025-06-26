/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class GrupoNotificaciones implements NotificacionComponente {
    private List<NotificacionComponente> componentes = new ArrayList<>();

    public void agregar(NotificacionComponente componente) {
        componentes.add(componente);
    }

    @Override
    public void enviar() {
        for (NotificacionComponente c : componentes) {
            c.enviar();
        }
    }
}
