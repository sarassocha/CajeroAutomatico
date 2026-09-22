/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package app;

/**
 *
 * @author USER
 */
public class VistaMenu extends javax.swing.JFrame {

    private Banco banco;
    private Cajeroautomatico cajero;
    private String numeroTarjeta;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaMenu.class.getName());

    /**
     * Creates new form Vista
     */
    public VistaMenu(Cajeroautomatico cajero, String numeroTarjeta) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.cajero = cajero;
        this.numeroTarjeta = numeroTarjeta;
    }

    public VistaMenu() {
        initComponents();
        inicializarSistema();
        this.numeroTarjeta = "1111";
    }

    private void inicializarSistema() {
        banco = new Banco("Banco Demo");
        Cliente ana = new Cliente("Ana Perez", "1001");
        Cliente luis = new Cliente("Luis Gomez", "1002");
        banco.agregarCliente(ana);
        banco.agregarCliente(luis);

        Cuenta cuenta1 = new Cuenta("C001", ana, 500000, 400000);
        cuenta1.asignarTarjeta(new Tarjeta("1111", "1234"));
        banco.agregarCuenta(cuenta1);

        Cuenta cuenta2 = new Cuenta("C002", ana, 1000000, 500000);
        banco.agregarCuenta(cuenta2);

        Cuenta cuenta3 = new Cuenta("C003", luis, 200000, 200000);
        cuenta3.asignarTarjeta(new Tarjeta("2222", "4321"));
        banco.agregarCuenta(cuenta3);

        cajero = new Cajeroautomatico(banco, 200000);
        actualizarEstadoInterfaz();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Retirar dinero");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Ingresar dinero");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("Comprar Entradas");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jLabel4.setFont(new java.awt.Font("Vineta BT", 0, 12)); // NOI18N
        jLabel4.setText("CAJERO");

        jButton1.setText("Salir");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarEstadoInterfaz() {

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        java.awt.EventQueue.invokeLater(() -> new VistaRetirarDinero(cajero, numeroTarjeta).setVisible(true));
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        java.awt.EventQueue.invokeLater(() -> new VistaIngresarDinero(cajero, numeroTarjeta).setVisible(true));
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        java.awt.EventQueue.invokeLater(() -> new VistaBoletas(cajero, numeroTarjeta).setVisible(true));
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

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

        java.awt.EventQueue.invokeLater(() -> new VistaMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
