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

    public String ejecutar(Tarjeta tarjeta) {

        if (tarjeta.retirar(monto)) {
            String mensaje = "Retiro exitoso por: $" + monto + " ¿Desea imprimir su recibo?";
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, tarjeta).setVisible(true));
            return mensaje;
        }

        String mensajeError;

        if (monto <= 0) {
            mensajeError = "Monto inválido: Ingrese una cantidad mayor a cero.";
        } else if (monto > tarjeta.getSaldo()) {
            mensajeError = "Fondos insuficientes: La tarjeta no tiene saldo disponible para este retiro.";
        } else if ((tarjeta.getRetiradoHoy() + monto) > tarjeta.getLimiteDiario()) {
            mensajeError = "Límite diario superado: Ha alcanzado o superado el límite de retiros permitidos para hoy.";
        } else {
            mensajeError = "No se pudo realizar la transacción.";
        }

        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }
}
