/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorbd;

//import gestorbd.JFrameClientes;
//import gestorbd.JFrameClientes;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author alumno
 */
public class JFrameVentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public JFrameVentanaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMantenimiento = new javax.swing.JMenu();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemProveedores = new javax.swing.JMenuItem();
        jMenuItemArticulos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuAlmacen = new javax.swing.JMenu();
        jMenuItemPedidos = new javax.swing.JMenuItem();
        jMenuItemExtractos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión de Almacén");
        setResizable(false);

        jMenuMantenimiento.setMnemonic('m');
        jMenuMantenimiento.setText("Mantenimiento");

        jMenuItemClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemClientes.setMnemonic('c');
        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenuMantenimiento.add(jMenuItemClientes);

        jMenuItemProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemProveedores.setMnemonic('p');
        jMenuItemProveedores.setText("Proveedores");
        jMenuItemProveedores.setEnabled(false);
        jMenuItemProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProveedoresActionPerformed(evt);
            }
        });
        jMenuMantenimiento.add(jMenuItemProveedores);

        jMenuItemArticulos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemArticulos.setMnemonic('a');
        jMenuItemArticulos.setText("Artículos");
        jMenuItemArticulos.setEnabled(false);
        jMenuItemArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArticulosActionPerformed(evt);
            }
        });
        jMenuMantenimiento.add(jMenuItemArticulos);
        jMenuMantenimiento.add(jSeparator1);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setMnemonic('s');
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuMantenimiento.add(jMenuItemSalir);

        jMenuBar1.add(jMenuMantenimiento);

        jMenuAlmacen.setMnemonic('a');
        jMenuAlmacen.setText("Almacén");

        jMenuItemPedidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPedidos.setMnemonic('p');
        jMenuItemPedidos.setText("Pedidos");
        jMenuItemPedidos.setToolTipText("");
        jMenuItemPedidos.setEnabled(false);
        jMenuItemPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPedidosActionPerformed(evt);
            }
        });
        jMenuAlmacen.add(jMenuItemPedidos);

        jMenuItemExtractos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExtractos.setMnemonic('e');
        jMenuItemExtractos.setText("Extractos");
        jMenuItemExtractos.setEnabled(false);
        jMenuItemExtractos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExtractosActionPerformed(evt);
            }
        });
        jMenuAlmacen.add(jMenuItemExtractos);

        jMenuBar1.add(jMenuAlmacen);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProveedoresActionPerformed
        // TODO add your handling code here:
         enDesarrollo(jMenuMantenimiento.getText(), jMenuItemProveedores.getText());
    }//GEN-LAST:event_jMenuItemProveedoresActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        // TODO add your handling code here:
   jFrameClientes v=new jFrameClientes(this);
        setEnabled(false);
        v.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArticulosActionPerformed
        // TODO add your handling code here:
         enDesarrollo(jMenuMantenimiento.getText(), jMenuItemArticulos.getText());
    }//GEN-LAST:event_jMenuItemArticulosActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPedidosActionPerformed
        // TODO add your handling code here:
         enDesarrollo(jMenuAlmacen.getText(), jMenuItemPedidos.getText());
    }//GEN-LAST:event_jMenuItemPedidosActionPerformed

    private void jMenuItemExtractosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExtractosActionPerformed
        // TODO add your handling code here:
         enDesarrollo(jMenuAlmacen.getText(), jMenuItemExtractos.getText());
    }//GEN-LAST:event_jMenuItemExtractosActionPerformed

    /**
     * @param args the command line arguments
     */
    
    private void enDesarrollo(String nombreMenu, String nombreItem)
    {
        String t= nombreMenu+"-"+nombreItem;
        JOptionPane.showMessageDialog(rootPane,"En desarrollo", t, WIDTH);
    }
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameVentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAlmacen;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemArticulos;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemExtractos;
    private javax.swing.JMenuItem jMenuItemPedidos;
    private javax.swing.JMenuItem jMenuItemProveedores;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuMantenimiento;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
