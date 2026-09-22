
package app;

public class Evento {

    private String nombre;
    private double tarifa;

    public Evento(String nombre, double tarifa) {
        this.nombre = nombre;
        this.tarifa = tarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTarifa() {
        return tarifa;
    }

    @Override
    public String toString() {
        return nombre + " - $" + Operacion.FORMATO_MONEDA.format(tarifa) + " por entrada";
    }
}
