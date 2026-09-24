package app;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Ventana de confirmación de operación exitosa.
 * Muestra el saldo consultado directamente desde la Tarjeta.
 */
public class JPaneExito extends JFrame {

    private static final Logger logger = Logger.getLogger(JPaneExito.class.getName());
    private String Exitomensaje;

    /**
     * Constructor principal que recibe el mensaje de éxito y la Tarjeta correspondiente.
     */
    public JPaneExito(String Exitomensaje, Tarjeta tarjeta) {
        initComponents();
        setLocationRelativeTo(null);
        this.Exitomensaje = Exitomensaje;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jOptionPane1.setMessage(this.Exitomensaje);
        jOptionPane1.setMessageType(JOptionPane.INFORMATION_MESSAGE);

        jOptionPane1.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
                Object valorSeleccionado = jOptionPane1.getValue();

                if (valorSeleccionado == null || valorSeleccionado.equals(JOptionPane.UNINITIALIZED_VALUE)) {
                    return;
                }

                if ("Si".equals(valorSeleccionado)) {
                    // El saldo se obtiene directamente desde el objeto Tarjeta
                    double saldoActual = (tarjeta != null) ? tarjeta.getSaldo() : 0.0;
                    String recibo = "Saldo actual: " + saldoActual;

                    EventQueue.invokeLater(() -> new JPaneRecibo(recibo).setVisible(true));
                    dispose();
                } else if ("No".equals(valorSeleccionado)) {
                    dispose();
                }

                jOptionPane1.setValue(JOptionPane.UNINITIALIZED_VALUE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jOptionPane1.setOptions(new Object[]{"Si", "No"});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jOptionPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jOptionPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        Cliente clientePrueba = new Cliente("Ana Perez", "1001");
        
        Tarjeta tarjetaPrueba = new Tarjeta("1111", "1234", clientePrueba, 500000.0);

        EventQueue.invokeLater(() -> new JPaneExito("¿Desea imprimir el recibo?", tarjetaPrueba).setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JOptionPane jOptionPane1;
    // End of variables declaration                    
}