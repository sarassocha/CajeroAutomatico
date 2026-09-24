package app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Vista del menú principal para seleccionar operaciones del cajero automático.
 */
public class VistaMenu extends JFrame {
    
    private static final Logger LOGGER = Logger.getLogger(VistaMenu.class.getName());
    
    private final Cajeroautomatico cajero;
    private final String numeroTarjeta;
    private final Cliente cliente;

    /**
     * Constructor principal con inyección de sesión completa.
     */
    public VistaMenu(Cajeroautomatico cajero, String numeroTarjeta, Cliente cliente) {
        initComponents();
        setLocationRelativeTo(null);
        this.cajero = cajero;
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal - Cajero");
        
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
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 125,
                                                        Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(jLabel4)))
                                .addContainerGap(86, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addContainerGap(34, Short.MAX_VALUE)));
        
        pack();
    }// </editor-fold>

    private void jButton3ActionPerformed(ActionEvent evt) {
        if (!validarSesion()) {
            return;
        }
        EventQueue.invokeLater(() -> new VistaIngresarDinero(cajero, numeroTarjeta).setVisible(true));
        this.dispose();
    }
    
    private void jButton2ActionPerformed(ActionEvent evt) {
        if (!validarSesion()) {
            return;
        }
        EventQueue.invokeLater(() -> new VistaRetirarDinero(cajero, numeroTarjeta).setVisible(true));
        this.dispose();
    }
    
    private void jButton4ActionPerformed(ActionEvent evt) {
        if (!validarSesion()) {
            return;
        }
        EventQueue.invokeLater(() -> new VistaBoletas(cajero, numeroTarjeta, cliente).setVisible(true));
        this.dispose();
    }
    
    private void jButton1ActionPerformed(ActionEvent evt) {
        Tarjeta tarjetaActual = (cajero != null) ? cajero.getBanco().buscarTarjetaPorNumero(numeroTarjeta) : null;
        JOptionPane.showMessageDialog(
                null,
                "Sesión finalizada correctamente. Por favor retire su tarjeta.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
        EventQueue.invokeLater(() -> new VistaTarjeta(cajero, cliente).setVisible(true));
        this.dispose();
    }
    
    private boolean validarSesion() {
        if (cajero == null || numeroTarjeta == null || numeroTarjeta.trim().isEmpty()) {
            mostrarMensajeError("Error: Sesión no válida o tiempo expirado. Ingrese su tarjeta de nuevo.");
            EventQueue.invokeLater(() -> new VistaTarjeta(cajero, cliente).setVisible(true));
            this.dispose();
            return false;
        }
        return true;
    }
    
    private void mostrarMensajeError(String mensaje) {
        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensaje).setVisible(true));
    }
    
    private void mostrarMensajeExito(String mensaje, Tarjeta tarjeta) {
        java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, tarjeta).setVisible(true));
    }
    
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, "Error al configurar el LookAndFeel", ex);
        }
        Banco bancoPrueba = new Banco("Banco Demo");
        Cliente ana = new Cliente("Ana Perez", "1001");
        Cliente luis = new Cliente("Luis Gomez", "1002");
        bancoPrueba.agregarCliente(ana);
        bancoPrueba.agregarCliente(luis);
        
        Cuenta cuentaCompartida = new Cuenta("C001", ana, 500000, 400000);
        
        cuentaCompartida.agregarTitular(luis);
        
        cuentaCompartida.asignarTarjeta(new Tarjeta("1111", "1234", ana, 100000, 10000000));
        cuentaCompartida.asignarTarjeta(new Tarjeta("3333", "5678", ana, 200000, 50000000));
        cuentaCompartida.asignarTarjeta(new Tarjeta("5555", "9012", luis, 10000000, 10000000));
        
        bancoPrueba.agregarCuenta(cuentaCompartida);
        Cajeroautomatico cajeroPrueba = new Cajeroautomatico(bancoPrueba, 2000000);
        
        EventQueue.invokeLater(() -> new VistaMenu(cajeroPrueba, cuentaCompartida.getNumeroCuenta(), ana).setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration
}
