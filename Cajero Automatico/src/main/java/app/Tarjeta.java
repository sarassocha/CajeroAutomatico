package app;

public class Tarjeta {

    public static final int MAX_INTENTOS = 3;

    private String numero;
    private String clave;
    private Cliente propietario;
    private int intentosFallidos;
    private boolean bloqueada;

    // Nuevos atributos de negocio para manejar el saldo en la Tarjeta
    private double saldo;
    private double limiteDiario;
    private double retiradoHoy;

    public Tarjeta(String numero, String clave, Cliente propietario, double saldoInicial, double limiteDiario) {
        this.numero = numero;
        this.clave = clave;
        this.propietario = propietario;
        this.intentosFallidos = 0;
        this.bloqueada = false;
        this.saldo = saldoInicial;
        this.limiteDiario = limiteDiario;
        this.retiradoHoy = 0.0;
    }

    public Tarjeta(String numero, String clave, Cliente propietario, double saldoInicial) {
        this(numero, clave, propietario, saldoInicial, 2000000.0); // Límite por defecto
    }

    public Tarjeta(String numero, String clave, Cliente propietario) {
        this(numero, clave, propietario, 0.0, 2000000.0);
    }

    public String getNumero() {
        return numero;
    }

    public String getClave() {
        return clave;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentosFallidos;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteDiario() {
        return limiteDiario;
    }

    public double getRetiradoHoy() {
        return retiradoHoy;
    }

    public boolean ingresar(double monto) {
        if (monto <= 0) {
            return false;
        }
        this.saldo += monto;
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo || (retiradoHoy + monto) > limiteDiario) {
            return false;
        }
        this.saldo -= monto;
        this.retiradoHoy += monto;
        return true;
    }

    public boolean verificarClave(String claveIntroducida) {
        if (bloqueada) {
            return false;
        }
        if (clave != null && clave.equals(claveIntroducida)) {
            intentosFallidos = 0;
            return true;
        }
        intentosFallidos++;
        if (intentosFallidos >= MAX_INTENTOS) {
            bloqueada = true;
        }
        return false;
    }
}
