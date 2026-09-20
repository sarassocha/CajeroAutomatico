
package app;


public class Tarjeta {
        public static final int MAX_INTENTOS = 3;
 
    private String numero;
    private String clave;
    private int intentosFallidos;
    private boolean bloqueada;
 
    public Tarjeta(String numero, String clave) {
        this.numero = numero;
        this.clave = clave;
        this.intentosFallidos = 0;
        this.bloqueada = false;
    }
 
    public String getNumero() {
        return numero;
    }
 
    public boolean isBloqueada() {
        return bloqueada;
    }
 
    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentosFallidos;
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
