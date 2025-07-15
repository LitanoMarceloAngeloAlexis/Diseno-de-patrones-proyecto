package DisenoDePatrones.Vista.Components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageButton extends JButton {

    private String imageName;
    private Color backgroundColor = null;
    private int borderRadius = 0;

    public ImageButton() {
        super();
        setDefaults();
    }

    private void setDefaults() {
        setText(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String value) {
        this.imageName = value;
        cargarImagenDesdeNombre();
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }

    private void cargarImagenDesdeNombre() {
        if (imageName != null && !imageName.isEmpty()) {
            URL url = getClass().getResource("/DisenoDePatrones/Vista/Assets/" + imageName);
            if (url != null) {
                setIcon(new ImageIcon(url));
            } else {
                System.err.println("No se encontró la imagen: " + imageName);
                setIcon(null);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (backgroundColor != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
            g2.dispose();
        }
        super.paintComponent(g);
    }
}
