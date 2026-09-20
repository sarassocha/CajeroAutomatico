
package app;


public class Operacionretiro implements Operacion {

    private double monto;

    public Operacionretiro(double monto) {
        this.monto = monto;
    }

    @Override
    public String getNombre() {
        return "Retirar dinero";
    }

    @Override
    public String ejecutar(Cuenta cuenta) {
        if (cuenta.retirar(monto)) {
            return "Retiro exitoso de $" + monto + ". Saldo actual: $" + cuenta.getSaldo();
        }
        return "Retiro rechazado. Verifique el saldo o el límite diario disponible ($"
                + cuenta.getDisponibleHoy() + ")";
    }
}
