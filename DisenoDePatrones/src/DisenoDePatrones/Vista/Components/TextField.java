package DisenoDePatrones.Vista.Components; // Asegúrate de que este sea tu paquete real

import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

public class TextField extends JTextField {
    private String placeholder = "";
    private Color placeholderForeground = Color.LIGHT_GRAY;

    public TextField() {
        super();
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.repaint();
    }
    
    public Color getPlaceholderForeground() {
        return placeholderForeground;
    }
    
    public void setPlaceholderForeground(Color placeholder) {
        this.placeholderForeground = placeholder;
        this.repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !placeholder.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int x = getInsets().left; 
            int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();
            
            g2.setColor(this.placeholderForeground); 
            g2.drawString(placeholder, x, y);
            g2.dispose();
        }
    }
}