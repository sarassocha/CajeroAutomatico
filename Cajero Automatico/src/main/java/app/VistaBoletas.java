package app;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Vista gráfica para la compra de boletas y entradas a eventos.
 */
public class VistaBoletas extends JFrame {

    private static final Logger logger = Logger.getLogger(VistaBoletas.class.getName());
    private Evento btsArirang;
    private Evento humbeTour;
    private Evento megaland;

    private Cajeroautomatico cajero;
    private String numeroTarjeta;
    private Cliente cliente;
    private Tarjeta tarjeta;
    private String mensaje;

    /**
     * Constructor completo con sesión de cliente.
     *
     * @param cajero Instancia del cajero automático.
     * @param numeroTarjeta Identificador de la tarjeta.
     * @param cliente Instancia del cliente en sesión.
     */
    public VistaBoletas(Cajeroautomatico cajero, String numeroTarjeta, Cliente cliente) {
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.cajero = cajero;
        this.numeroTarjeta = numeroTarjeta;
        this.cliente = cliente;
        if (cajero != null && cajero.getBanco() != null) {
            this.tarjeta = cajero.getBanco().buscarTarjetaPorNumero(numeroTarjeta);
        }
        inicializarEventos();
    }

    private void inicializarEventos() {
        btsArirang = new Evento("Bts Arirang", 450000);
        humbeTour = new Evento("Humbe Esencia Tour", 275000);
        megaland = new Evento("Megaland", 150000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jLabel2.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Bts Arirang", "Megaland", "Humbe Esencia Tour"}));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jLabel6.setText("Eventos Disponibles");
        jLabel7.setText("Seleccione evento");
        jLabel8.setText("Precio: 450mil");
        jLabel9.setText("Precio: 275mil");
        jLabel10.setText("Precio: 150mil");

        jButton1.setText("Comprar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Regresar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel1.setText("BTS");
        jLabel11.setText("Escencia");
        jLabel12.setText("Megaland");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel8)
                                .addGap(155, 155, 155)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(81, 81, 81))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)
                                .addGap(36, 36, 36)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(39, 39, 39))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(297, 297, 297)
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jLabel1)
                                .addGap(202, 202, 202)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(93, 93, 93)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel6)
                                .addGap(128, 128, 128)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                .addGap(98, 98, 98)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(16, 16, 16)));

        pack();
    }// </editor-fold>

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String eventoSeleccionado = (String) jComboBox1.getSelectedItem();
        Evento eventoActual = null;

        if ("Bts Arirang".equals(eventoSeleccionado)) {
            eventoActual = btsArirang;
        } else if ("Humbe Esencia Tour".equals(eventoSeleccionado)) {
            eventoActual = humbeTour;
        } else if ("Megaland".equals(eventoSeleccionado)) {
            eventoActual = megaland;
        }

        if (eventoActual != null) {
            if (cajero == null) {
                Banco bancoPrueba = new Banco("Banco Demo");
                Cliente ana = new Cliente("Ana Perez", "1001");
                bancoPrueba.agregarCliente(ana);
                Cuenta cuentaPrueba = new Cuenta("C001", ana, 500000, 400000);
                Tarjeta tarjetaPrueba = new Tarjeta("1111", "1234", ana, 100000, 10000000);
                cuentaPrueba.asignarTarjeta(tarjetaPrueba);
                bancoPrueba.agregarCuenta(cuentaPrueba);
                cajero = new Cajeroautomatico(bancoPrueba, 2000000);
                this.tarjeta = tarjetaPrueba;
            }

            if (this.tarjeta == null && cajero.getBanco() != null) {
                this.tarjeta = cajero.getBanco().buscarTarjetaPorNumero(numeroTarjeta);
            }

            if (this.tarjeta != null) {
                Operacioncomprarentradas operacionCompra = new Operacioncomprarentradas(eventoActual, 1);
                operacionCompra.ejecutar(this.tarjeta);
            } else {
                String mensajeError = "Error: Tarjeta no encontrada para el número " + numeroTarjeta;
                java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        java.awt.EventQueue.invokeLater(() -> new VistaMenu(cajero, numeroTarjeta, cliente).setVisible(true));
        dispose();
    }

    /**
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        Banco bancoPrueba = new Banco("Banco Demo");
        Cliente ana = new Cliente("Ana Perez", "1001");
        Cliente luis = new Cliente("Luis Gomez", "1002");
        bancoPrueba.agregarCliente(ana);
        Cuenta cuentaPrueba = new Cuenta("C001", ana, 500000, 400000);
        cuentaPrueba.asignarTarjeta(new Tarjeta("1111", "1234", ana, 100000, 10000000));
        cuentaPrueba.asignarTarjeta(new Tarjeta("3333", "5678", ana, 200000, 50000000));
        cuentaPrueba.asignarTarjeta(new Tarjeta("5555", "9012", luis, 10000000, 10000000));

        bancoPrueba.agregarCuenta(cuentaPrueba);
        Cajeroautomatico cajeroPrueba = new Cajeroautomatico(bancoPrueba, 2000000);

        java.awt.EventQueue.invokeLater(() -> new VistaBoletas(cajeroPrueba, "1111", ana).setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration
}
