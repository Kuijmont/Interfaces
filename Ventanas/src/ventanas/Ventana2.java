/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

/**
 *
 * @author alumno
 */
public class Ventana2 extends javax.swing.JFrame {
    
    
    /**
     * Creates new form Ventana2
     */
    public Ventana2(javax.swing.JFrame padre) {
        Ventana2.padre=padre;
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

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("HOLA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
        public static void main(String args[]) {        
             /* Set the Nimbus look and feel */
        /* Create and display the form */        
             java.awt.EventQueue.invokeLater(new Runnable() {
                @Override            
                public void run() {               
                    new Ventana2(padre).setVisible(true);            
                }        
             });    
         } 
        protected void processWindowEvent(java.awt.event.WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING) {
            padre.setEnabled(true);
        }
     }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

static javax.swing.JFrame padre;

}
