/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Tramite;
import DisenoDePatrones.Vista.ILIFormalitiesVista;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.List;
/**
 *
 * @author Alex
 */
public class LIInformationController {
    private final ILIFormalitiesVista vistaLIInformation;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadano;
    private final ExecProcedures execProcedures;
    
    public LIInformationController(CiudadanoService ciudadano, Connection connection, ILIFormalitiesVista vistaLIInformation, IMenuVista menuVista) {
        this.vistaLIInformation = vistaLIInformation;
        this.menuVista = menuVista;
        this.ciudadano = ciudadano;
        this.execProcedures = new ExecProcedures(connection);
          
        this.vistaLIInformation.OnClickCloseButton(this::manejarEventoCerrar);
        this.CargarTramites();
    }
    
    private void CargarTramites() {
        List<Tramite> tramitesPendientes = this.execProcedures.obtenerTramitesPendientes();
        this.vistaLIInformation.ShowInTable(tramitesPendientes);
    }
    
    public void manejarEventoCerrar() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
}
