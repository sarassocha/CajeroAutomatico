
package app;


public class Operacioncomprarentradas implements Operacion {

    private double precio;
    private Evento evento;
    private int cantidadEntradas;

    public Operacioncomprarentradas(double precio) {
        this.precio = precio;
        this.evento = null;
        this.cantidadEntradas = 1;
    }

    /**
     * Compra entradas para un evento del catálogo del cajero
     * ({@link Cajeroautomatico#getEventosDisponibles()}), usando la
     * tarifa definida en el evento y la cantidad de entradas solicitadas.
     */
    public Operacioncomprarentradas(Evento evento, int cantidadEntradas) {
        this.evento = evento;
        this.cantidadEntradas = cantidadEntradas;
        this.precio = 0;
    }

    @Override
    public String getNombre() {
        return "Comprar entradas de teatro";
    }

    @Override
    public String ejecutar(Cuenta cuenta) {
        if (evento != null) {
            return ejecutarCompraEvento(cuenta);
        }
        if (precio <= 0) {
            return "El precio de la entrada debe ser mayor que cero.";
        }
        if (cuenta.retirar(precio)) {
            return "Entrada comprada por $" + formatearMonto(precio) + ". Saldo actual: $" + formatearMonto(cuenta.getSaldo());
        }
        return "No se pudo completar la compra (saldo o límite diario insuficiente)";
    }

    private String ejecutarCompraEvento(Cuenta cuenta) {
        if (cantidadEntradas <= 0) {
            return "La cantidad de entradas debe ser mayor que cero.";
        }
        double total = evento.getTarifa() * cantidadEntradas;
        if (cuenta.retirar(total)) {
            return cantidadEntradas + " entrada(s) para \"" + evento.getNombre() + "\" compradas por $"
                    + formatearMonto(total) + ". Saldo actual: $" + formatearMonto(cuenta.getSaldo());
        }
        return "No se pudo completar la compra de entradas para \"" + evento.getNombre()
                + "\" (saldo o límite diario insuficiente).";
    }
}
