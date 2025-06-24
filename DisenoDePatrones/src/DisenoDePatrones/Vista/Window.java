/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class Window extends JFrame {
    public Window() {
        super();
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    public Window(boolean frame) {
        this.InitWindow();
        this.setUndecorated(frame);
    }

    public Window(boolean frame, boolean transparency) {
        this(frame);
        if (transparency) {
            this.setUndecorated(true);
            this.setBackground(new Color(0, 0, 0, 0));
        }
    }
    
    public Window(boolean frame, boolean transparency, boolean visible) {
        this(frame, transparency);
        this.setVisible(visible);
    }
    
    private void InitWindow() {
        this.setSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
    }
    
    public void SetSizeWindow(int width, int heigth) {
        this.setSize(new Dimension(width, heigth));
    }
}
