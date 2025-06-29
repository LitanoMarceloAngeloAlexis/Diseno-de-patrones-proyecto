/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DisenoDePatrones.Vista.Layouts.Window;

import java.awt.Component;
import java.awt.Point;

/**
 *
 * @author Alex
 */
public interface IWindowWrapper {
    void setModal(boolean modal);
    void setTitle(String title);
    void setVisible(boolean visible);
    void setSize(int width, int height);
    void setLocation(int x, int y);
    void setExtendedState(int state);
    Point getLocation();
    Object getWindow();
    void add(Component component);
    void dispose();
}
