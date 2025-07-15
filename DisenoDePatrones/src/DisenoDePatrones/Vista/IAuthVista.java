/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import java.util.HashMap;

/**
 *
 * @author Alex
 */
public interface IAuthVista {
    HashMap<String, String> GetLoginData();
    HashMap<String, String> GetRegisterData();
    void SetLoginData(HashMap<String, String> data);
    void SetRegisterData(HashMap<String, String> data);
    void OnClickAccederLogin(Runnable callback);
    void OnClickAccederRegister(Runnable callback);
    AuthVista.Roles GetRolSelected();
    void CerrarVentana();
}
