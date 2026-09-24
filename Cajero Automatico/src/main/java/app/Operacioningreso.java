package app;

public class Operacioningreso implements Operacion {

    private double monto;

    public Operacioningreso(double monto) {
        this.monto = monto;
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public String getNombre() {
        return "Ingresar dinero";
    }

    public String ejecutar(Tarjeta tarjeta) {
        if (!MontosPermitidos.esValido(monto, MontosPermitidos.CONSIGNACION)) {
            String mensaje = "Monto no válido. Las cantidades establecidas para consignación son: "
                    + MontosPermitidos.listar(MontosPermitidos.CONSIGNACION);
            java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensaje).setVisible(true));
            return mensaje;
        }

        if (tarjeta.ingresar(monto)) {
            String mensaje = "Ingreso exitoso por: $" + monto + " ¿Desea imprimir su recibo?";
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, tarjeta).setVisible(true));
            return mensaje;
        }

        String mensajeError = "Cantidad inválida. El monto a ingresar debe ser mayor que cero o no se pudo completar la operación.";
        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }
}
