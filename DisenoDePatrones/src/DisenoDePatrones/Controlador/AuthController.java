/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.DatabaseConnection;
import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Ciudadano;
import DisenoDePatrones.Vista.AuthVista;
import DisenoDePatrones.Vista.IAuthVista;
import DisenoDePatrones.Vista.Layouts.Auths.AuthForm;
import java.sql.Connection;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Angelo
 */
public class AuthController {
    private IAuthVista authVista;
    private ExecProcedures execProcedures;
    private Connection conn;
    
    public AuthController(){
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            this.execProcedures = new ExecProcedures(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL CONECTAR A LA BASE DE DATOS");
            JOptionPane.showMessageDialog(null, "FALLO LA CONEXION A LA BASE DE DATOS", "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.authVista = new AuthVista();
        this.authVista.OnClickAccederLogin(this::manejarAccederLogin);
    }
    
    private void manejarAccederLogin(){
        HashMap<String, String> authData = authVista.GetLoginData();
        
        String dni = authData.get("CampoDNI");
        String contraseña = authData.get("CampoPassword");
        
        boolean autenticador = execProcedures.autenticarLogin(dni, contraseña);
        
        if (autenticador) {
            List<Ciudadano> listaCiudadanos = ServiceFactory.cargarCiudadanosSinDuplicados(execProcedures);
            AuthForm.Modos mode = authVista.GetCurrentModeAccess();
            CiudadanoService ciudadano1 = ServiceFactory.crearService(listaCiudadanos,dni,mode);
            authVista.CerrarVentana();
            ReportController controladorReporte = new ReportController(ciudadano1, conn);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            System.out.println("FALLO DE AUTENTICACIÓN");
        }
    }
}
