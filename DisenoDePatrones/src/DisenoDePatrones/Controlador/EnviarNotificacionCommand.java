/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;

/**
 *
 * @author Angelo
 */
public class EnviarNotificacionCommand implements Command{

    private ExecProcedures execProcedures;
    private String dni;
    private String mensaje;

    public EnviarNotificacionCommand(ExecProcedures execProcedures, String dni, String mensaje) {
        this.execProcedures = execProcedures;
        this.dni = dni;
        this.mensaje = mensaje;
    }

    @Override
    public void execute() {
        execProcedures.crearNotificacion(dni, mensaje);
    }
    
}
