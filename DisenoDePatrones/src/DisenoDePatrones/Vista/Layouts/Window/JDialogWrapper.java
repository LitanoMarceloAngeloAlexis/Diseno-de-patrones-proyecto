/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Layouts.Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class JDialogWrapper implements IWindowWrapper {
    
    private JDialog dialog;
    
    public JDialogWrapper(WindowForm parent)  {
        this.dialog = new JDialog((JFrame) parent.getWindow());
        this.dialog.setUndecorated(true);
        this.dialog.setTitle("JavaDialog");
        this.dialog.setSize(800, 600);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setBackground(new Color(0, 0, 0, 0));
        this.dialog.setModal(false);
    }
    
    @Override
    public void setTitle(String title) {
        this.dialog.setTitle(title);
    }
    
    @Override
    public void setVisible(boolean visible) {
        this.dialog.setVisible(visible);
    }
    
    @Override
    public void setModal(boolean modal) {
        this.dialog.setModal(modal);
    }

    @Override
    public void setSize(int width, int height) {
        this.dialog.setSize(width, height);
    }
    
    @Override
    public void setExtendedState(int state) {}

    @Override
    public void setLocation(int x, int y) {
        this.dialog.setLocation(x, y);
    }
    
    @Override
    public Point getLocation() {
        return this.dialog.getLocation();
    }

    @Override
    public Object getWindow() {
        return this.dialog;
    }
    
    @Override
    public void add(Component component) {
        this.dialog.add(component);
    }

    @Override
    public void dispose() {
        this.dialog.dispose();
    }
}
