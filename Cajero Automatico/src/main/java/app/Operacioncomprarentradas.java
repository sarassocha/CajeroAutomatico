package app;

/**
 * Operación encargada de gestionar la compra de entradas de eventos o teatro.
 */
public class Operacioncomprarentradas implements Operacion {

    private double precio;
    private Evento evento;
    private int cantidadEntradas;

    /**
     * Crea una operación de compra de entradas especificada por un precio
     * unitario.
     *
     * @param precio El precio de la entrada.
     */
    public Operacioncomprarentradas(double precio) {
        this.precio = precio;
        this.evento = null;
        this.cantidadEntradas = 1;
    }

    /**
     * Compra entradas para un evento del catálogo del cajero, usando la tarifa
     * definida en el evento y la cantidad de entradas solicitadas.
     *
     * @param evento El evento seleccionado.
     * @param cantidadEntradas La cantidad de entradas a comprar.
     */
    public Operacioncomprarentradas(Evento evento, int cantidadEntradas) {
        this.evento = evento;
        this.cantidadEntradas = cantidadEntradas;
        this.precio = 0;
    }

    @Override
    public double getMonto() {
        if (evento != null) {
            return evento.getTarifa() * cantidadEntradas;
        }
        return precio * cantidadEntradas;
    }

    @Override
    public String getNombre() {
        return "Comprar entradas de teatro";
    }

    @Override
    public String ejecutar(Tarjeta tarjeta) {
        if (tarjeta == null) {
            String mensajeError = "Error: Tarjeta no válida para realizar la compra.";
            java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
            return mensajeError;
        }

        if (evento != null) {
            return ejecutarCompraEvento(tarjeta);
        }

        return ejecutarCompraPrecioDirecto(tarjeta);
    }

    private String ejecutarCompraEvento(Tarjeta tarjeta) {
        if (cantidadEntradas <= 0) {
            String mensajeError = "La cantidad de entradas debe ser mayor que cero.";
            java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
            return mensajeError;
        }

        double total = getMonto();

        if (tarjeta.retirar(total)) {
            String mensaje = "Compra exitosa: " + cantidadEntradas + " entrada(s) para "
                    + evento.getNombre() + " por $" + formatearMonto(total)
                    + ". Saldo actual: $" + formatearMonto(tarjeta.getSaldo());
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, tarjeta).setVisible(true));
            return mensaje;
        }

        String mensajeError = evaluarMensajeError(tarjeta, total);
        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }

    private String ejecutarCompraPrecioDirecto(Tarjeta tarjeta) {
        if (precio <= 0) {
            String mensajeError = "El precio de la entrada debe ser mayor que cero.";
            java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
            return mensajeError;
        }

        double total = getMonto();

        if (tarjeta.retirar(total)) {
            String mensaje = "Compra exitosa de entrada por $" + formatearMonto(total)
                    + ". Saldo actual: $" + formatearMonto(tarjeta.getSaldo());
            java.awt.EventQueue.invokeLater(() -> new JPaneExito(mensaje, tarjeta).setVisible(true));
            return mensaje;
        }

        String mensajeError = evaluarMensajeError(tarjeta, total);
        java.awt.EventQueue.invokeLater(() -> new JPaneErrores(mensajeError).setVisible(true));
        return mensajeError;
    }

    private String evaluarMensajeError(Tarjeta tarjeta, double total) {
        if (total > tarjeta.getSaldo()) {
            return "Fondos insuficientes: La tarjeta no tiene saldo disponible para realizar esta compra.";
        } else if ((tarjeta.getRetiradoHoy() + total) > tarjeta.getLimiteDiario()) {
            return "Límite diario superado: La transacción excede el límite máximo permitido para hoy.";
        }
        return "No se pudo realizar la transacción de compra de entradas.";
    }

    @Override
    public String formatearMonto(double monto) {
        return String.format("%.2f", monto);
    }
}
