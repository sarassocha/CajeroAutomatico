package app;

/**
 * Operación encargada de realizar la consulta de saldo disponible en la cuenta.
 */
public class Operacionconsultasaldo implements Operacion {

    /**
     * Constructor por defecto.
     */
    public Operacionconsultasaldo() {
    }

    @Override
    public String getNombre() {
        return "Consultar saldo";
    }

    public String ejecutar(Tarjeta tarjeta) {
        if (tarjeta == null) {
            return "Error: Tarjeta o cuenta no asociada.";
        }
        return String.valueOf(tarjeta.getSaldo());
    }

    /**
     * Obtiene el resumen del saldo de la cuenta indicada.
     *
     * @param cuenta La cuenta a consultar.
     * @return El nombre del titular junto con el saldo formateado.
     */
    public String ejecutar(Cuenta cuenta) {
        if (cuenta == null) {
            return "Error: Cuenta inexistente.";
        }
        return "Titular: " + cuenta.getTitular().getNombre() + " - Saldo: $" + formatearMonto(cuenta.getSaldo());
    }

    public String formatearMonto(double monto) {
        return String.format("%.2f", monto);
    }
}
