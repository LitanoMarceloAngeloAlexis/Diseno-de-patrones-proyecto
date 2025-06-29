package DisenoDePatrones.Vista.Components.TextBoxElement;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextBox extends JPanel {
      
    static class TextField2 extends JTextField {
        public String placeholder = "placeholder";
        public Color placeholderForeground = Color.LIGHT_GRAY;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (this.getText().isEmpty() && !this.placeholder.isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int x = this.getInsets().left;
                int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();

                g2.setFont(this.getFont());
                g2.setColor(this.placeholderForeground);
                g2.drawString(this.placeholder, x, y);
                g2.dispose();
            }
        }
    }
    
    private final TextField2 textField;

    private Point gap = new Point(0, 0);
    private int cornerRadius = 0;
    private Color borderColor = Color.BLACK;
    private int borderThickness = 0;
    private Color backgroundColor = new Color(242, 242, 242);
    private String text = "TextBox";
    private Font fontFamily = new Font("Segeo UI", 0, 12);
    private int horizontalAlignment = 0;

    public TextBox() {
        initComponents();
        this.textField = new TextField2();
        this.textField.setOpaque(false);
        this.textField.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.textField.setForeground(Color.BLACK);
        this.textField.setText(this.text);
        this.updateLayout();
        this.add(this.textField);
    }

    private void initComponents() {
        this.setLayout(new CardLayout());
        this.setOpaque(false);
    }
    
    private void updateLayout() {
        this.setLayout(new CardLayout(this.gap.x, this.gap.y));
    }

    public Point getGap() {
        return this.gap;
    }

    public void setGap(Point gap) {
        this.gap = gap;
        this.updateLayout();
        this.revalidate();
        this.repaint();
    }

    public String getText() {
        return this.textField.getText();
    }

    public void setText(String text) {
        this.text = text;
        this.textField.setText(text);
    }
    
    public Font getFontFamily() {
        return this.textField.getFont();
    }
    
    public void setFontFamily(Font fontFamily) {
        this.fontFamily = fontFamily;
        this.textField.setFont(this.fontFamily);
    }

    public String getPlaceholder() {
        return this.textField.placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.textField.placeholder = placeholder;
        this.textField.repaint();
    }

    public Color getPlaceholderForeground() {
        return this.textField.placeholderForeground;
    }

    public void setPlaceholderForeground(Color placeholderForeground) {
        this.textField.placeholderForeground = placeholderForeground;
        this.textField.repaint();
    }

    public int getCornerRadius() {
        return this.cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        this.repaint();
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        this.repaint();
    }

    public int getBorderThickness() {
        return this.borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        this.repaint();
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

    public Color getTextForeground() {
        return this.textField.getForeground();
    }

    public void setTextForeground(Color foreground) {
        this.textField.setForeground(foreground);
        this.textField.repaint();
    }
    
    public int getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    public void setHorizontalAlignment(int horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        this.textField.setHorizontalAlignment(this.horizontalAlignment);
    }
    
    //Valores del JPanel
    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        this.revalidate();
        this.repaint();
    }

    @Override
    public Dimension getMinimumSize() {
        return super.getMinimumSize();
    }

    @Override
    public void setMinimumSize(Dimension minimumSize) {
        super.setMinimumSize(minimumSize);
        this.revalidate();
        this.repaint();
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize();
    }

    @Override
    public void setMaximumSize(Dimension maximumSize) {
        super.setMaximumSize(maximumSize);
        this.revalidate();
        this.repaint();
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = this.getWidth();
        int height = this.getHeight();

        g2.setColor(this.backgroundColor);
        g2.fillRoundRect(this.borderThickness / 2, this.borderThickness / 2, width - this.borderThickness, height - this.borderThickness, this.cornerRadius, this.cornerRadius);
       
        if (this.borderThickness > 0) {
            g2.setColor(this.borderColor);
            g2.setStroke(new BasicStroke(this.borderThickness));
            g2.drawRoundRect(this.borderThickness / 2, this.borderThickness / 2, width - this.borderThickness, height - this.borderThickness, this.cornerRadius, this.cornerRadius);
        }

        g2.dispose();
    }
}
