/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.FuerzaOrden;
import DisenoDePatrones.Modelo.Observador;
import java.util.List;

/**
 *
 * @author Angelo
 */
public class NotificacionDispatcher implements Observador {

    private ExecProcedures execProcedures;
    private List<FuerzaOrden> fuerzas;

    public NotificacionDispatcher(ExecProcedures execProcedures, List<FuerzaOrden> fuerzas) {
        this.execProcedures = execProcedures;
        this.fuerzas = fuerzas;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        for (FuerzaOrden f : fuerzas) {
            Command comando = new EnviarNotificacionCommand(execProcedures, f.getDNI(), mensaje);
            comando.execute();
        }
    }
}