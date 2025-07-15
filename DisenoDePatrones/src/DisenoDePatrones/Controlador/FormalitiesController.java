/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Tramite;
import DisenoDePatrones.Vista.IFormalitiesVista;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.Layouts.Contents.FormalitiesForm;
import DisenoDePatrones.Vista.MenuVista;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class FormalitiesController {
    private final IFormalitiesVista vista;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadanoService;
    private final ExecProcedures execProcedures;

    public FormalitiesController(CiudadanoService ciudadanoService, Connection connection, IFormalitiesVista formalitiesVista, IMenuVista menuVista) {
        this.vista = formalitiesVista;
        this.menuVista = menuVista;
        this.ciudadanoService = ciudadanoService;
        this.execProcedures = new ExecProcedures(connection);
        
        this.ConfigurarEventosTramites();
        this.InicializarTramites();
        
        this.vista.OnCloseButtonEventClick(this::manejarEventoClose);
    }

    public void ConfigurarEventosTramites() {
        FormalitiesForm formalitiesForm = (FormalitiesForm) this.vista.GetWindow();
        formalitiesForm.btnSIP.addActionListener(e -> this.IniciarTramite("Solicitud de Pago de Luz"));
        formalitiesForm.btnSPL.addActionListener(e -> this.IniciarTramite("Solicitud de Renovación de DNI"));
        formalitiesForm.btnST.addActionListener(e -> this.IniciarTramite("Solicitud de Pago de Impuestos Municipales"));
        formalitiesForm.btnDC.addActionListener(e -> this.IniciarTramite("Solicitud de Subsidio"));
        formalitiesForm.btnAR.addActionListener(e -> this.IniciarTramite("Solicitud de Pago de Agua"));
        formalitiesForm.btnABD.addActionListener(e -> this.IniciarTramite("Solicitud de Licencia de Conducir"));
        formalitiesForm.btnCC.addActionListener(e -> this.IniciarTramite("Solicitud de Actualización de Datos"));
        formalitiesForm.btnCLA.addActionListener(e -> this.IniciarTramite("Solicitud de Inscripción Escolar"));
        formalitiesForm.btnDU.addActionListener(e -> this.IniciarTramite("Solicitud de Reclamo por Servicio Público"));
    }

    public void InicializarTramites() {
        String dni = this.ciudadanoService.getCiudadanoActual().getDNI();
        List<Tramite> tramites = this.execProcedures.ObtenerTramitesActivosPorDni(dni);

        for (Tramite tramite : tramites) {
            String titulo = tramite.getTitulo().toLowerCase();

            if (titulo.contains("pago de luz")) {
                this.vista.MarcarTramiteEnProceso("pago de luz");
            } else if (titulo.contains("renovación de dni")) {
                this.vista.MarcarTramiteEnProceso("renovación de dni");
            } else if (titulo.contains("pago de impuestos")) {
                this.vista.MarcarTramiteEnProceso("pago de impuestos municipales");
            } else if (titulo.contains("subsidio")) {
                this.vista.MarcarTramiteEnProceso("subsidio");
            } else if (titulo.contains("pago de agua")) {
                this.vista.MarcarTramiteEnProceso("pago de agua");
            } else if (titulo.contains("licencia de conducir")) {
                this.vista.MarcarTramiteEnProceso("licencia de conducir");
            } else if (titulo.contains("actualización de datos")) {
                this.vista.MarcarTramiteEnProceso("actualización de datos");
            } else if (titulo.contains("inscripción escolar")) {
                this.vista.MarcarTramiteEnProceso("inscripción escolar");
            } else if (titulo.contains("reclamo por servicio")) {
                this.vista.MarcarTramiteEnProceso("reclamo por servicio público");
            }
        }
    }

    private void IniciarTramite(String tipoTramite) {
        String dni = this.ciudadanoService.getCiudadanoActual().getDNI();
        boolean exito = this.execProcedures.InsertarTramite(dni, tipoTramite);

        if (exito) {
            String tipoSimple = tipoTramite.toLowerCase().replace("solicitud de ", "");
            this.vista.MarcarTramiteEnProceso(tipoSimple);
            JOptionPane.showMessageDialog(null, "Trámite iniciado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Ya tienes este trámite en proceso o ocurrió un error.");
        }
    }
    
    private void manejarEventoClose() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
}
