/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Controlador;

import DisenoDePatrones.BaseDeDatos.ExecProcedures;
import DisenoDePatrones.Modelo.Documento;
import DisenoDePatrones.Vista.Components.CardFile;
import DisenoDePatrones.Vista.IDocumentsVista;
import DisenoDePatrones.Vista.IMenuVista;
import DisenoDePatrones.Vista.Layouts.Contents.DocumentsForm;
import DisenoDePatrones.Vista.MenuVista;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class DocumentsController {
    private final IDocumentsVista vista;
    private final IMenuVista menuVista;
    private final CiudadanoService ciudadano;
    private final ExecProcedures execProcedures;

    public DocumentsController(CiudadanoService ciudadanoService, Connection connection, IDocumentsVista documentsVista, IMenuVista menuVista) {
        this.vista = documentsVista;
        this.menuVista = menuVista;
        this.ciudadano = ciudadanoService;
        this.execProcedures = new ExecProcedures(connection);
        
        this.vista.OnClickCloseButton(this::manejarCloseClick);
        this.vista.OnClickUploadedButton(this::SubirDocumento);
        
        this.CargarDocumentos();
    }

    private void SubirDocumento() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            try {
                byte[] contenido = Files.readAllBytes(archivo.toPath());

                Documento doc = new Documento();
                doc.setDni(ciudadano.getCiudadanoActual().getDNI());
                doc.setNombreArchivo(archivo.getName());
                doc.setTipoMime(Files.probeContentType(archivo.toPath()));
                doc.setTamaño((int) archivo.length());
                doc.setArchivo(contenido);

                if (execProcedures.insertarDocumento(doc)) {
                    JOptionPane.showMessageDialog(null, "Documento subido exitosamente.");
                    this.CargarDocumentos();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo subir el documento.");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al leer archivo.");
            }
        }
    }

    private void CargarDocumentos() {
        DocumentsForm documentsForm = (DocumentsForm) this.vista.GetWindow();
        documentsForm.panelContenedor.removeAll();

        String dni = this.ciudadano.getCiudadanoActual().getDNI();
        List<Documento> documentos = this.execProcedures.obtenerDocumentosPorDni(dni);

        for (Documento doc : documentos) {
            CardFile card = new CardFile();
            card.setFileName(doc.getNombreArchivo());
            card.setCreationDate(doc.getFechaSubida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            card.setFileSize((doc.getTamaño() / 1024) + " KB");
            card.setFileType(doc.getNombreArchivo());

            card.getBtnDownload().addActionListener(e -> this.DescargarDocumento(doc.getId()));
            card.getBtnDelete().addActionListener(e -> {
                if (execProcedures.eliminarDocumentoPorId(doc.getId())) {
                    JOptionPane.showMessageDialog(null, "Documento eliminado.");
                    this.CargarDocumentos();
                }
            });

            documentsForm.panelContenedor.add(card);
        }

        documentsForm.panelContenedor.revalidate();
        documentsForm.panelContenedor.repaint();
    }

    private void DescargarDocumento(int id) {
        Documento doc = execProcedures.obtenerDocumentoPorId(id);
        if (doc == null || doc.getArchivo() == null) {
            JOptionPane.showMessageDialog(null, "No se pudo descargar el archivo.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(doc.getNombreArchivo()));
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                Files.write(chooser.getSelectedFile().toPath(), doc.getArchivo());
                JOptionPane.showMessageDialog(null, "Archivo descargado exitosamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo.");
            }
        }
    }
    
    private void manejarCloseClick() {
        this.menuVista.ChangeToView(MenuVista.Vistas.NAVIGATION);
    }
}