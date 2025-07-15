/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.IResourcesVista;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ResourceController {
    private final IResourcesVista vistaResources;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadano;
    private final ExecProcedures execProcedures;
    
    public ResourceController(CiudadanoService ciudadano, Connection connection, IResourcesVista vistaResources, IMenuVista menuVista) {
        this.vistaResources = vistaResources;
        this.menuVista = menuVista;
        this.ciudadano = ciudadano;
        this.execProcedures = new ExecProcedures(connection);
        
        this.vistaResources.OnClickCloseButton(this::manejarEventoCerrar);
        this.CargarRecursos();
    }
    
    public void manejarEventoCerrar() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
    
    private void CargarRecursos() {
        List<Ciudadano> ciudadanos = this.execProcedures.obtenerRegistrosHumanos("CIUDADANO");
        this.vistaResources.ShowInTable(ciudadanos);
    }
}
