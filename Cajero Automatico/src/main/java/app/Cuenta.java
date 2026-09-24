
package app;

public class Cuenta {
    private String numeroCuenta;
    private java.util.List<Cliente> titulares;
    private double saldo;
    private java.util.List<Tarjeta> tarjetas; 
    private double limiteDiario;
    private double retiradoHoy;
 
    public Cuenta(String numeroCuenta, Cliente titular, double saldoInicial, double limiteDiario) {
        this.numeroCuenta = numeroCuenta;
        this.titulares = new java.util.ArrayList<>();
        this.titulares.add(titular);
        this.saldo = saldoInicial;
        this.limiteDiario = limiteDiario;
        this.retiradoHoy = 0.0;
        this.tarjetas = new java.util.ArrayList<>();
    }
 
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
 
    public java.util.List<Cliente> getTitulares() {
        return titulares;
    }
    
    public Cliente getTitular() {
        return titulares.isEmpty() ? null : titulares.get(0);
    }
 
    public double getSaldo() {
        return saldo;
    }
 
    public java.util.List<Tarjeta> getTarjetas() {
        return tarjetas;
    }
    
    public Tarjeta getTarjeta() {
        return tarjetas.isEmpty() ? null : tarjetas.get(0);
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
        this.tarjetas.add(tarjeta);
    }
    
    public void agregarTitular(Cliente titular) {
        if (!this.titulares.contains(titular)) {
            this.titulares.add(titular);
        }
    }
    
    public boolean tieneTarjeta(String numeroTarjeta) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNumero().equals(numeroTarjeta)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean esTitular(Cliente cliente) {
        return this.titulares.contains(cliente);
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
