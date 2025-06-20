/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DisenoDePatrones.Vista.Layouts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class ReportForm extends javax.swing.JPanel {

    private Point initialClick;
    private JFrame parent;
    /**
     * Creates new form ReportForm
     */
    public ReportForm(JFrame parent) {
        this.parent = parent;
        initComponents();
        
        this.Topbar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        
        this.Topbar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setLocation(X, Y);
            }
        });
    }
    
    public JPanel GetMainContent() {
        return this.ContentMain;
    }

    public JPanel GetNextButton() {
        return this.btnSiguiente;
    }
    
    public JPanel GetCancelButton() {
        return this.btnCancelar;
    }
    
    public JPanel GetPreviousButton() {
        return this.btnAtras;
    }
    
    public JPanel GetThanksButton() {
        return this.btnThanks;
    }
    
    public JLabel ActionExitApplicaction() {
        return this.btnExit;
    }
    
    public JLabel ActionMinimize() {
        return this.btnMinimize;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main = new DisenoDePatrones.Vista.Components.PanelRound();
        Navbar = new javax.swing.JPanel();
        Content2 = new DisenoDePatrones.Vista.Components.PanelRound();
        tabPerfil = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        tabServices = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        tabInformation = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        ContentMain = new javax.swing.JPanel();
        Footer = new javax.swing.JPanel();
        btnCancelar = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        btnAtras = new DisenoDePatrones.Vista.Components.PanelRound();
        jlabel2 = new javax.swing.JLabel();
        btnSiguiente = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        btnThanks = new DisenoDePatrones.Vista.Components.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        Topbar = new DisenoDePatrones.Vista.Components.PanelRound();
        btnExit = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        Main.setBackground(new java.awt.Color(255, 255, 255));
        Main.setRoundBottomLeft(14);
        Main.setRoundBottomRight(14);
        Main.setRoundTopLeft(14);
        Main.setRoundTopRight(14);
        Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Navbar.setOpaque(false);
        Navbar.setPreferredSize(new java.awt.Dimension(800, 50));

        Content2.setBackground(new java.awt.Color(245, 245, 245));
        Content2.setRoundBottomLeft(8);
        Content2.setRoundBottomRight(8);
        Content2.setRoundTopLeft(8);
        Content2.setRoundTopRight(8);
        Content2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 6));

        tabPerfil.setBackground(new java.awt.Color(245, 245, 245));
        tabPerfil.setRoundBottomLeft(4);
        tabPerfil.setRoundBottomRight(4);
        tabPerfil.setRoundTopLeft(4);
        tabPerfil.setRoundTopRight(4);
        tabPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPerfilMouseClicked(evt);
            }
        });
        tabPerfil.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 6));

        jLabel2.setForeground(new java.awt.Color(65, 125, 255));
        jLabel2.setText("MI PERFIL");
        tabPerfil.add(jLabel2);

        Content2.add(tabPerfil);

        tabServices.setBackground(new java.awt.Color(65, 125, 255));
        tabServices.setRoundBottomLeft(4);
        tabServices.setRoundBottomRight(4);
        tabServices.setRoundTopLeft(4);
        tabServices.setRoundTopRight(4);
        tabServices.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 6));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SERVICIOS");
        tabServices.add(jLabel3);

        Content2.add(tabServices);

        tabInformation.setBackground(new java.awt.Color(245, 245, 245));
        tabInformation.setRoundBottomLeft(4);
        tabInformation.setRoundBottomRight(4);
        tabInformation.setRoundTopLeft(4);
        tabInformation.setRoundTopRight(4);
        tabInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabInformationMouseClicked(evt);
            }
        });
        tabInformation.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 6));

        jLabel4.setForeground(new java.awt.Color(65, 125, 255));
        jLabel4.setText("INFORMACION");
        tabInformation.add(jLabel4);

        Content2.add(tabInformation);

        Navbar.add(Content2);

        Main.add(Navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 36, 800, -1));

        ContentMain.setOpaque(false);
        ContentMain.setPreferredSize(new java.awt.Dimension(800, 423));
        ContentMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Main.add(ContentMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 92, 800, -1));

        Footer.setOpaque(false);
        Footer.setPreferredSize(new java.awt.Dimension(800, 77));
        Footer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 16));

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(65, 125, 255), 2, true));
        btnCancelar.setPreferredSize(new java.awt.Dimension(216, 36));
        btnCancelar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, -3));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(65, 125, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CANCELAR");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setPreferredSize(new java.awt.Dimension(216, 36));
        btnCancelar.add(jLabel5);

        Footer.add(btnCancelar);

        btnAtras.setBackground(new java.awt.Color(65, 125, 255));
        btnAtras.setPreferredSize(new java.awt.Dimension(216, 36));
        btnAtras.setRoundBottomLeft(4);
        btnAtras.setRoundBottomRight(4);
        btnAtras.setRoundTopLeft(4);
        btnAtras.setRoundTopRight(4);
        btnAtras.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jlabel2.setBackground(new java.awt.Color(65, 125, 255));
        jlabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jlabel2.setForeground(new java.awt.Color(255, 255, 255));
        jlabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabel2.setText("RETROCEDER");
        jlabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlabel2.setPreferredSize(new java.awt.Dimension(216, 36));
        btnAtras.add(jlabel2);

        Footer.add(btnAtras);

        btnSiguiente.setBackground(new java.awt.Color(65, 125, 255));
        btnSiguiente.setPreferredSize(new java.awt.Dimension(216, 36));
        btnSiguiente.setRoundBottomLeft(4);
        btnSiguiente.setRoundBottomRight(4);
        btnSiguiente.setRoundTopLeft(4);
        btnSiguiente.setRoundTopRight(4);
        btnSiguiente.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SIGUIENTE");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setPreferredSize(new java.awt.Dimension(216, 36));
        btnSiguiente.add(jLabel7);

        Footer.add(btnSiguiente);

        btnThanks.setBackground(new java.awt.Color(65, 125, 255));
        btnThanks.setPreferredSize(new java.awt.Dimension(216, 36));
        btnThanks.setRoundBottomLeft(4);
        btnThanks.setRoundBottomRight(4);
        btnThanks.setRoundTopLeft(4);
        btnThanks.setRoundTopRight(4);
        btnThanks.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("GRACIAS");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setPreferredSize(new java.awt.Dimension(216, 36));
        btnThanks.add(jLabel8);

        Footer.add(btnThanks);

        Main.add(Footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 521, -1, 80));

        Topbar.setBackground(new java.awt.Color(65, 125, 255));
        Topbar.setRoundTopLeft(14);
        Topbar.setRoundTopRight(14);

        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DisenoDePatrones/Vista/Assets/close.png"))); // NOI18N
        btnExit.setMaximumSize(new java.awt.Dimension(37, 30));
        btnExit.setPreferredSize(new java.awt.Dimension(30, 30));

        btnMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DisenoDePatrones/Vista/Assets/minimize.png"))); // NOI18N
        btnMinimize.setMaximumSize(new java.awt.Dimension(37, 30));
        btnMinimize.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gestion Ciudadana");

        javax.swing.GroupLayout TopbarLayout = new javax.swing.GroupLayout(Topbar);
        Topbar.setLayout(TopbarLayout);
        TopbarLayout.setHorizontalGroup(
            TopbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 606, Short.MAX_VALUE)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TopbarLayout.setVerticalGroup(
            TopbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopbarLayout.createSequentialGroup()
                .addGroup(TopbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Main.add(Topbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabInformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabInformationMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Esta opcion aun no esta planteada");
    }//GEN-LAST:event_tabInformationMouseClicked

    private void tabPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPerfilMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Esta opcion aun no esta planteada");
    }//GEN-LAST:event_tabPerfilMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DisenoDePatrones.Vista.Components.PanelRound Content2;
    private javax.swing.JPanel ContentMain;
    private javax.swing.JPanel Footer;
    private DisenoDePatrones.Vista.Components.PanelRound Main;
    private javax.swing.JPanel Navbar;
    private DisenoDePatrones.Vista.Components.PanelRound Topbar;
    private DisenoDePatrones.Vista.Components.PanelRound btnAtras;
    private DisenoDePatrones.Vista.Components.PanelRound btnCancelar;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnMinimize;
    private DisenoDePatrones.Vista.Components.PanelRound btnSiguiente;
    private DisenoDePatrones.Vista.Components.PanelRound btnThanks;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jlabel2;
    private DisenoDePatrones.Vista.Components.PanelRound tabInformation;
    private DisenoDePatrones.Vista.Components.PanelRound tabPerfil;
    private DisenoDePatrones.Vista.Components.PanelRound tabServices;
    // End of variables declaration//GEN-END:variables
}
