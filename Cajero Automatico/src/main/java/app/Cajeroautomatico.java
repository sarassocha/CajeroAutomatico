package app;
import javax.swing.JOptionPane;
public class Cajeroautomatico {
    private Banco banco;
    private double saldoEfectivoInterno;
    private boolean bloqueadoPorEfectivo;

    public Cajeroautomatico(Banco banco, double saldoInicialEfectivo) {
        this.banco = banco;
        this.saldoEfectivoInterno = saldoInicialEfectivo;
        this.bloqueadoPorEfectivo = (saldoInicialEfectivo <= 0);
    }
    

    
    public Cuenta buscarCuentaPorTarjeta(String numeroTarjeta) {
        return banco.buscarCuentaPorTarjeta(numeroTarjeta);
    }
    
public String realizarOperacion(String numeroTarjeta, String clave, Operacion operacion) {
        if (bloqueadoPorEfectivo) {
            return "Cajero fuera de servicio: No hay efectivo disponible en la máquina.";
        }

        Cuenta cuenta = buscarCuentaPorTarjeta(numeroTarjeta);
        if (cuenta == null) {
            return "Tarjeta no reconocida";
        }

        Tarjeta tarjeta = cuenta.getTarjeta();
        if (tarjeta.isBloqueada()) {
            return "Tarjeta bloqueada";
        }

        if (!tarjeta.verificarClave(clave)) {
            if (tarjeta.isBloqueada()) {
                return "Clave incorrecta. La tarjeta ha sido bloqueada.";
            }
            return "Clave incorrecta. Intentos restantes: " + tarjeta.getIntentosRestantes();
        }

        if (operacion.getMonto() > 0 && !tieneEfectivoSuficiente(operacion.getMonto())) {
            return "Retiro rechazado. El cajero no tiene efectivo suficiente.";
        }

        String resultado = operacion.ejecutar(cuenta);

        if (operacion.getMonto() > 0) {
            dispensarEfectivo(operacion.getMonto());
        }

        // recibo
        int opcion = JOptionPane.showConfirmDialog(
            null, 
            "¿Desea imprimir el recibo de la transacción?", 
            "Impresión de Recibo", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                null, 
                "--- RECIBO DE TRANSACCIÓN ---\n" + resultado, 
                "Recibo Impreso", 
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        // --------------------------------------

        return resultado;
    }
// fin recibo xd


    public boolean estaBloqueadoPorEfectivo() {
        return bloqueadoPorEfectivo;
    }

    public double getSaldoEfectivoInterno() {
        return saldoEfectivoInterno;
    }

    public boolean tieneEfectivoSuficiente(double monto) {
        return !bloqueadoPorEfectivo && saldoEfectivoInterno >= monto;
    }

    public void dispensarEfectivo(double monto) {

        this.saldoEfectivoInterno -= monto;

        if (this.saldoEfectivoInterno <= 0) {
            this.bloqueadoPorEfectivo = true;
        }
    }
}