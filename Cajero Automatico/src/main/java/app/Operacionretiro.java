package app;

public class Operacionretiro implements Operacion {

    private double monto;

    public Operacionretiro(double monto) {
        this.monto = monto;
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public String getNombre() {
        return "Retirar dinero";
    }

    @Override
    public String ejecutar(Cuenta cuenta) {

        if (cuenta.retirar(monto)) {
            String mensaje = "Retiro exitoso por: $" + monto + " ¿Desea imprimir su recibo?";
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, cuenta).setVisible(true));
            return mensaje;
        }

        String mensajeError;

        // Validaciones exactas basadas en los criterios de tu clase Cuenta
        if (monto <= 0) {
            mensajeError = "Monto inválido: Ingrese una cantidad mayor a cero.";
        } else if (monto > cuenta.getSaldo()) {
            mensajeError = "Fondos insuficientes: La cuenta no tiene saldo disponible para este retiro.";
        } else if ((cuenta.getRetiradoHoy() + monto) > cuenta.getLimiteDiario()) {
            mensajeError = "Límite diario superado: Ha alcanzado o superado el límite de retiros permitidos para hoy.";
        } else {
            mensajeError = "No se pudo realizar la transacción.";
        }

        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }

}
