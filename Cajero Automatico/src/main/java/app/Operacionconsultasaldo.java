
package app;


public class Operacionconsultasaldo implements Operacion {
 
    @Override
    public String getNombre() {
        return "Consultar saldo";
    }
 
    @Override
    public String ejecutar(Cuenta cuenta) {
        return "Titular: " + cuenta.getTitular().getNombre() + " - Saldo: $" + formatearMonto(cuenta.getSaldo());
    }
}
