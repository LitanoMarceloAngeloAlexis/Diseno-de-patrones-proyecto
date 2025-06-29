/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Layouts.Window;

import java.awt.Component;
import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
//Patron Bridge / Strategy
public class WindowForm {
    private IWindowWrapper window;
    
    public enum WindowType { FRAME, DIALOG }
    
    public WindowForm(WindowType type) {
        if (type == WindowType.FRAME) {
            this.window = new JFrameWrapper();
        }
    }
    
    public WindowForm(WindowType type, WindowForm parent) {
        if (type == WindowType.DIALOG) {
            this.window = new JDialogWrapper(parent);
        }
    }
    
    public Object getWindow() {
        return this.window.getWindow();
    }
    
    public void setTitle(String title) {
        this.window.setTitle(title);
    }
    
    public void setVisible(boolean visible) {
        this.window.setVisible(visible);
    }
    
    public void setModal(boolean modal) {
        this.window.setModal(modal);
    }

    public void setSize(int width, int height) {
        this.window.setSize(width, height);
    }
    
    public void setExtendedState(int state) {
        this.window.setExtendedState(state);
    }

    public void setLocation(int x, int y) {
        this.window.setLocation(x, y);
    }
    
    public Point getLocation() {
        return this.window.getLocation();
    }

    public void add(Component component) {
        this.window.add(component);
    }

    public void dispose() {
        this.window.dispose();
    }
}
