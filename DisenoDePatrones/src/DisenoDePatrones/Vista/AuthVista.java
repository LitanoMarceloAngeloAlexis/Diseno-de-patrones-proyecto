/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import DisenoDePatrones.Vista.Layouts.Window.WindowForm;
import DisenoDePatrones.Vista.Layouts.Auths.AuthForm;
import DisenoDePatrones.Vista.Layouts.Auths.Login;
import DisenoDePatrones.Vista.Layouts.Auths.Register;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Alex
 */
public class AuthVista implements IAuthVista {

    private WindowForm window;
    private AuthForm authform;
    
    private JPanel currentViewer;
    private Login login;
    private Register register;

    public enum AuthState {
        LOGIN,
        REGISTER
    }
    
    public AuthVista() {
        this.window = new WindowForm(WindowForm.WindowType.FRAME);
        this.authform = new AuthForm(this.window);
        this.window.add(this.authform);
        this.window.setSize(800, 650);
        
        this.login = new Login();
        this.register = new Register();
        this.login.setVisible(false);
        this.register.setVisible(false);
        
        this.authform.getContent().add(this.login, new AbsoluteConstraints(0, 0, 800, 570));
        this.authform.getContent().add(this.register, new AbsoluteConstraints(0, 0, 800, 570));

        this.ChangeVistaTo(AuthState.LOGIN);
        
        this.login.GetChangeToRegister().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeVistaTo(AuthState.REGISTER);
            }
        });
        
        this.register.GetChangeToLogin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeVistaTo(AuthState.LOGIN);
            }
        });
    }
    
    @Override
    public void ChangeVistaTo(AuthState state) {
        if (state.equals(AuthState.LOGIN)) {
            this.currentViewer = this.login;
            this.login.setVisible(true);
            this.register.setVisible(false);
        } else if (state.equals(AuthState.REGISTER)) {
            this.currentViewer = this.register;
            this.login.setVisible(false);
            this.register.setVisible(true);
        }
    }
    
    @Override
    public HashMap<String, String> GetLoginData() {
        return this.login.GetData();
    }
    
    @Override
    public HashMap<String, String> GetRegisterData() {
        return this.register.GetData();
    }
    
    @Override
    public void SetLoginData(HashMap<String, String> data) {
        this.login.SetData(data);
    }
    
    @Override
    public void SetRegisterData(HashMap<String, String> data) {
        this.register.SetData(data);
    }
    
    @Override
    public AuthForm.Modos GetCurrentModeAccess() {
       return this.authform.getModeAccess();
    }
    
    @Override
    public void OnClickAccederLogin(Runnable callback) {
        this.login.GetButtonAcceder().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                callback.run();
            }
        });
    }
    
    @Override
    public void OnClickAccederRegister(Runnable callback) {
        this.register.GetButtonAcceder().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                callback.run();
            }
        });
    }
    
    @Override
    public void CerrarVentana() {
        this.window.dispose();
    }
}
