/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Vista.ICitizensVista;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alex
 */
public class CitizensController {
    private final ICitizensVista vistaCitizens;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadano;
    private final ExecProcedures execProcedures;
    
    public CitizensController(CiudadanoService ciudadano, Connection connection, ICitizensVista vistaCitizens, IMenuVista menuVista) {
        this.vistaCitizens = vistaCitizens;
        this.menuVista = menuVista;
        this.ciudadano = ciudadano;
        this.execProcedures = new ExecProcedures(connection);
        
        this.vistaCitizens.OnClickCloseButton(this::manejarEventoCerrar);
        this.CargarCiudadanos();
    }
    
    public void manejarEventoCerrar() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
    
    private void CargarCiudadanos() {
        List<Ciudadano> ciudadanos = new ArrayList<>();
        ciudadanos.addAll(this.execProcedures.obtenerRegistrosHumanos("CIUDADANO"));
        ciudadanos.addAll(this.execProcedures.obtenerRegistrosHumanos("CIUDADANOCOMUN"));
        ciudadanos.addAll(this.execProcedures.obtenerRegistrosHumanos("AGENTEPUBLICO"));
        ciudadanos.addAll(this.execProcedures.obtenerRegistrosHumanos("FUERZAORDEN"));
        this.vistaCitizens.ShowInTable(ciudadanos);
    }
}
