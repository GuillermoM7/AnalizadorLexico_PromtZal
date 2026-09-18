package com.mycompany.analizadorlexico.frontend;

import com.mycompany.analizadorlexico.AnalizadorLexico;
import com.mycompany.analizadorlexico.modelos.ErrorLexico;
import com.mycompany.analizadorlexico.modelos.Token;
import com.mycompany.analizadorlexico.reportes.GeneradorGrafo;
import com.mycompany.analizadorlexico.reportes.GeneradorReportes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class PantallaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName());
    private AnalizadorLexico analizador;

    public PantallaPrincipal() {
        initComponents();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAFD = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaToken = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaError = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAbrir.setText("Abrir Archivo");
        btnAbrir.addActionListener(this::btnAbrirActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnAFD.setText("Ver AFD");
        btnAFD.addActionListener(this::btnAFDActionPerformed);

        btnReportes.setText("Reportes");
        btnReportes.addActionListener(this::btnReportesActionPerformed);

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(this::btnAnalizarActionPerformed);

        txtArchivo.setColumns(20);
        txtArchivo.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        tablaToken.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "Lexema", "Tipo", "Fila", "Columnma"
            }
        ));
        jScrollPane2.setViewportView(tablaToken);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tokens", jPanel2);

        tablaError.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Lexema/Caracter", "Descripcion", "Fila", "Columna"
            }
        ));
        jScrollPane3.setViewportView(tablaError);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Errores", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrir)
                .addGap(18, 18, 18)
                .addComponent(btnAnalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAFD)
                .addContainerGap())
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnGuardar)
                    .addComponent(btnAnalizar)
                    .addComponent(btnReportes)
                    .addComponent(btnAFD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

      
    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed

        if (analizador == null || analizador.getListaTokens().isEmpty() && analizador.getListaErrores().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Primero debes analizar un código para generar reportes.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        GeneradorReportes reportador = new GeneradorReportes();

        reportador.generarReporteTokens(analizador.getListaTokens());
        reportador.generarReporteErrores(analizador.getListaErrores());
        reportador.generarReporteEstadisticas(analizador.getListaTokens(), analizador.getListaErrores());

        javax.swing.JOptionPane.showMessageDialog(this, "¡Los 3 reportes HTML se generaron exitosamente en la carpeta del proyecto!");
        
    }//GEN-LAST:event_btnReportesActionPerformed

    
    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        String codigoFuente = txtArchivo.getText();

        if (codigoFuente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El editor está vacío. Escribe o abre un archivo .pz primero.");
            return;
        }
        
        DefaultTableModel modeloTokens = (DefaultTableModel) tablaToken.getModel();
        DefaultTableModel modeloErrores = (DefaultTableModel) tablaError.getModel();
        modeloTokens.setRowCount(0);
        modeloErrores.setRowCount(0);

        analizador = new AnalizadorLexico();
        analizador.analizarTexto(codigoFuente); 

        for (Token t : analizador.getListaTokens()) {
            modeloTokens.addRow(new Object[]{t.getId(), t.getLexema(), t.getTipo(), t.getFila(), t.getColumna()});
        }


        for (ErrorLexico e : analizador.getListaErrores()) {
            modeloErrores.addRow(new Object[]{e.getCaracter(),e.getDescripcion(), e.getFila(), e.getColumna()});
        }

        JOptionPane.showMessageDialog(this, "Análisis finalizado con éxito.");
    }//GEN-LAST:event_btnAnalizarActionPerformed

    
    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Buscar archivo PromptZal");

        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Archivos PromptZal (*.pz)", "pz");
        selector.setFileFilter(filtro);

        int respuesta = selector.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selector.getSelectedFile();
    
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivoSeleccionado));
                StringBuilder contenido = new StringBuilder();
                String linea;
        
                while ((linea = lector.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                lector.close();
        
                txtArchivo.setText(contenido.toString());
        
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar archivo PromptZal");

        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Archivos PromptZal (*.pz)", "pz");
        selector.setFileFilter(filtro);

        int respuesta = selector.showSaveDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".pz")) {
                archivo = new File(archivo.getAbsolutePath() + ".pz");
            }
    
            try {
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
                escritor.write(txtArchivo.getText()); 
                escritor.close();
        
                javax.swing.JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.");
        
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    
    private void btnAFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAFDActionPerformed
 
        GeneradorGrafo graficador = new GeneradorGrafo();
        graficador.generarGrafoGraphviz();

        File archivoImagen = new File("automata.png");

        if (archivoImagen.exists()) {
            ImageIcon icono = new ImageIcon(archivoImagen.getAbsolutePath());
            JLabel etiquetaImagen = new JLabel(icono);
            JScrollPane scroll = new JScrollPane(etiquetaImagen);
            scroll.setPreferredSize(new java.awt.Dimension(800, 500));
    
            javax.swing.JOptionPane.showMessageDialog(this, scroll, "Autómata Finito Determinista (AFD)", javax.swing.JOptionPane.PLAIN_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "La imagen no se generó. Verifica la instalación de Graphviz.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAFDActionPerformed

    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PantallaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAFD;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnReportes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaError;
    private javax.swing.JTable tablaToken;
    private javax.swing.JTextArea txtArchivo;
    // End of variables declaration//GEN-END:variables
}
