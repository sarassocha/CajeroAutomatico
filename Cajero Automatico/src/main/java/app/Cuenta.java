
package app;

public class Cuenta {
    private String numeroCuenta;
    private Cliente titular;
    private double saldo;
    private Tarjeta tarjeta; 
    private double limiteDiario;
    private double retiradoHoy;
 
    public Cuenta(String numeroCuenta, Cliente titular, double saldoInicial, double limiteDiario) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.limiteDiario = limiteDiario;
        this.retiradoHoy = 0.0;
        this.tarjeta = null;
    }
 
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
 
    public Cliente getTitular() {
        return titular;
    }
 
    public double getSaldo() {
        return saldo;
    }
 
    public Tarjeta getTarjeta() {
        return tarjeta;
    }
 
    public double getLimiteDiario() {
        return limiteDiario;
    }
 
    public double getRetiradoHoy() {
        return retiradoHoy;
    }
 
    public double getDisponibleHoy() {
        return limiteDiario - retiradoHoy;
    }
 
    public void asignarTarjeta(Tarjeta tarjeta) {
        if (this.tarjeta != null) {
            throw new IllegalStateException("La cuenta ya tiene una tarjeta asociada");
        }
        this.tarjeta = tarjeta;
    }
 

    public boolean retirar(double monto) {
        if (monto <= 0) {
            return false;
        }
        if (monto > saldo) {
            return false;
        }
        if (retiradoHoy + monto > limiteDiario) {
            return false;
        }
        saldo -= monto;
        retiradoHoy += monto;
        return true;
    }
 
    public boolean ingresar(double monto) {
        if (monto <= 0) {
            return false;
        }
        saldo += monto;
        return true;
    }
}
