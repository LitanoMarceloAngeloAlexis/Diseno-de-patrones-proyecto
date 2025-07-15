/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Window.WindowForm;
import DisenoDePatrones.Vista.Layouts.Auths.AuthForm;
import DisenoDePatrones.Vista.Layouts.Auths.Login;
import DisenoDePatrones.Vista.Layouts.Auths.Register;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.AbstractButton;

/**
 *
 * @author Alex
 */
public class AuthVista implements IAuthVista {

    private WindowForm window;
    private AuthForm authform;
    
    private Login loginForm = new Login();
    private Register registerForm = new Register();

    public enum WindowAuth {
        LOGIN,
        REGISTER,
    }
    
    public enum Roles {
        CIUDADANO,
        FUERZA_ORDEN,
        AGENTE_PUBLICO
    }
    
    private Roles currentRol;
    private WindowAuth currentWindowAuth;
    
    public AuthVista() {
        this.window = new WindowForm(WindowForm.WindowType.FRAME);
        this.authform = new AuthForm(this.window);
        this.authform.getContent().add(this.loginForm);
        this.authform.getContent().add(this.registerForm);
        this.window.add(this.authform);
        this.window.setSize(800, 690);
        
        this.currentWindowAuth = WindowAuth.LOGIN;
        this.SwitchLoginOrRegister();
                
        this.loginForm.GetChangeToRegister().addActionListener((e) -> {
            this.currentWindowAuth = WindowAuth.REGISTER;
            this.SwitchLoginOrRegister();
        });
        
        this.registerForm.GetChangeToLogin().addActionListener((e) -> {
            this.currentWindowAuth = WindowAuth.LOGIN;
            this.SwitchLoginOrRegister();
        });
    }
    
    private void SwitchLoginOrRegister() {
        if (this.currentWindowAuth == WindowAuth.LOGIN) {
            this.loginForm.setVisible(true);
            this.registerForm.setVisible(false);
        } else if (this.currentWindowAuth == WindowAuth.REGISTER) {
            this.loginForm.setVisible(false);
            this.registerForm.setVisible(true);
        }
    }
        
    @Override
    public HashMap<String, String> GetLoginData() {
        return this.loginForm.GetData();
    }
    
    @Override
    public HashMap<String, String> GetRegisterData() {
        return this.registerForm.GetData();
    }
    
    @Override
    public void SetLoginData(HashMap<String, String> data) {
        this.loginForm.SetData(data);
    }
    
    @Override
    public void SetRegisterData(HashMap<String, String> data) {
        this.registerForm.SetData(data);
    }
    
    @Override
    public AuthVista.Roles GetRolSelected() {
        Enumeration<AbstractButton> botonesRol = null;

        if (this.currentWindowAuth == WindowAuth.LOGIN) {
            botonesRol = this.loginForm.GetButtonGroup().getElements();
        } else if (this.currentWindowAuth == WindowAuth.REGISTER) {
            botonesRol = this.registerForm.GetButtonGroup().getElements();
        }

        if (botonesRol == null)
            return null;

        while (botonesRol.hasMoreElements()) {
            AbstractButton boton = botonesRol.nextElement();
            if (boton.isSelected()) {
                String texto = boton.getText().toUpperCase().replace(" ", "_");

                try {
                    this.currentRol = Roles.valueOf(texto);
                } catch (IllegalArgumentException ex) {
                    this.currentRol = null;
                }

                break;
            }
        }
        
        return this.currentRol;
    }
    
    @Override
    public void OnClickAccederLogin(Runnable callback) {
        this.loginForm.GetButtonAcceder().addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void OnClickAccederRegister(Runnable callback) {
        this.registerForm.GetButtonAcceder().addActionListener((e) -> {
            callback.run();
        });
    }
    
    @Override
    public void CerrarVentana() {
        this.window.dispose();
    }
}
