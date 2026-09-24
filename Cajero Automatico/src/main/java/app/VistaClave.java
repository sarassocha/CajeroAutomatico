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
 * Vista para la autenticación de clave de tarjeta en el sistema de cajero
 * automático.
 */
public class VistaClave extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(VistaClave.class.getName());

    private final Cajeroautomatico cajero;
    private final String numeroTarjeta;
    private final Cliente cliente;

    /**
     * Constructor principal con inyección del cajero, número de tarjeta y
     * cliente.
     */
    public VistaClave(Cajeroautomatico cajero, String numeroTarjeta, Cliente cliente) {
        initComponents();
        setLocationRelativeTo(null);
        this.cajero = cajero;
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPasswordField1 = new javax.swing.JTextField(); // Cambiado a JTextField
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingreso de Clave");

        jLabel1.setText("Ingresa la clave:");

        jButton1.setText("Regresar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Ingresar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(ActionEvent evt) {
        EventQueue.invokeLater(() -> new VistaTarjeta(cajero, cliente).setVisible(true));
        this.dispose();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        if (!validarInstanciaCajero()) {
            return;
        }

        String claveIngresada = jPasswordField1.getText().trim();

        if (claveIngresada.isEmpty()) {
            mostrarMensaje("Por favor ingresa la clave.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cuenta cuenta = cajero.buscarCuentaPorTarjeta(numeroTarjeta);
        if (cuenta == null) {
            mostrarMensaje("Error: Tarjeta o cuenta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tarjeta tarjeta = obtenerTarjetaAsociada(cuenta);
        if (tarjeta == null) {
            mostrarMensaje("No se encontró la tarjeta asociada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tarjeta.isBloqueada()) {
            mostrarMensaje("Esta tarjeta se encuentra bloqueada.", "Tarjeta Bloqueada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        procesarAutenticacion(tarjeta, claveIngresada, cuenta);
    }

    private boolean validarInstanciaCajero() {
        if (cajero == null) {
            mostrarMensaje("Error: No hay conexión con el cajero.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Tarjeta obtenerTarjetaAsociada(Cuenta cuenta) {
        if (cuenta.getTarjetas() == null) {
            return null;
        }
        return cuenta.getTarjetas().stream()
                .filter(t -> numeroTarjeta.equals(t.getNumero()))
                .findFirst()
                .orElse(null);
    }

    private void procesarAutenticacion(Tarjeta tarjeta, String clave, Cuenta cuenta) {
        if (tarjeta.verificarClave(clave)) {
            Cliente clienteDestino = obtenerClienteValido(tarjeta, cuenta);

            EventQueue.invokeLater(() -> new VistaMenu(cajero, numeroTarjeta, clienteDestino).setVisible(true));
            this.dispose();
        } else {
            jPasswordField1.setText("");
            if (tarjeta.isBloqueada()) {
                mostrarMensaje("Clave incorrecta. La tarjeta ha sido bloqueada.", "Bloqueo de Tarjeta", JOptionPane.ERROR_MESSAGE);
            } else {
                mostrarMensaje("Clave incorrecta. Intentos restantes: " + tarjeta.getIntentosRestantes(), "Clave Incorrecta", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private Cliente obtenerClienteValido(Tarjeta tarjeta, Cuenta cuenta) {
        if (this.cliente != null) {
            return this.cliente;
        }

        if (tarjeta.getPropietario() != null) {
            return tarjeta.getPropietario();
        }

        return null;
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
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

        EventQueue.invokeLater(() -> new VistaClave(cajeroPrueba, cuentaCompartida.getNumeroCuenta(), ana).setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPasswordField1; // Declarado como JTextField
    // End of variables declaration                    
}
