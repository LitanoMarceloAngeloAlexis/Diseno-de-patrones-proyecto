/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Layouts.Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class JFrameWrapper implements IWindowWrapper {
    
    private JFrame window;
    
    public JFrameWrapper() {
        this.window = new JFrame();
        this.window.setUndecorated(true);
        this.window.setTitle("JavaWindow");
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);
        this.window.setBackground(new Color(0, 0, 0, 0));
        this.window.setVisible(true);
    }
    
    @Override
    public void setTitle(String title) {
        this.window.setTitle(title);
    }
    
    @Override
    public void setVisible(boolean visible) {
        this.window.setVisible(visible);
    }
    
    @Override
    public void setModal(boolean modal) {}

    @Override
    public void setSize(int width, int height) {
        this.window.setSize(width, height);
    }
    
    @Override
    public void setExtendedState(int state) {
        this.window.setExtendedState(state);
    }

    @Override
    public void setLocation(int x, int y) {
        this.window.setLocation(x, y);
    }
    
    @Override
    public Point getLocation() {
        return this.window.getLocation();
    }

    @Override
    public Object getWindow() {
        return this.window;
    }
    
    @Override
    public void add(Component component) {
        this.window.add(component);
    }

    @Override
    public void dispose() {
        this.window.dispose();
    }
}
