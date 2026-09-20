
package app;


public class Operacioningreso implements Operacion {
 
    private double monto;
 
    public Operacioningreso(double monto) {
        this.monto = monto;
    }
 
    @Override
    public String getNombre() {
        return "Ingresar dinero";
    }
 
    @Override
    public String ejecutar(Cuenta cuenta) {
        if (cuenta.ingresar(monto)) {
            return "Ingreso exitoso de $" + monto + ". Saldo actual: $" + cuenta.getSaldo();
        }
        return "Cantidad invalida";
    }
}
