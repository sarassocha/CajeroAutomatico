package app;

public class Operacioningreso implements Operacion { // O implementando únicamente Operacion según tu interfaz

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

    @Override
    public String ejecutar(Cuenta cuenta) {
        if (!MontosPermitidos.esValido(monto, MontosPermitidos.CONSIGNACION)) {
            String mensaje = "Monto no válido. Las cantidades establecidas para consignación son: "
                    + MontosPermitidos.listar(MontosPermitidos.CONSIGNACION);
            java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensaje).setVisible(true));
            return mensaje;
        }

        if (cuenta.ingresar(monto)) {
            String mensaje = "Ingreso exitoso por: $" + monto + " ¿Desea imprimir su recibo?";
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, cuenta).setVisible(true));
            return mensaje;
        }

        String mensajeError = "Cantidad inválida. El monto a ingresar debe ser mayor que cero o no se pudo completar la operación.";
        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }
}
