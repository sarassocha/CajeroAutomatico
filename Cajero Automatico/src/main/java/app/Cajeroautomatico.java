package app;


public class Cajeroautomatico {
        private Banco banco;
 
    public Cajeroautomatico(Banco banco) {
        this.banco = banco;
    }
 
    public Cuenta buscarCuentaPorTarjeta(String numeroTarjeta) {
        return banco.buscarCuentaPorTarjeta(numeroTarjeta);
    }
 
    /**
     * Verifica la tarjeta y la clave; si son correctas, ejecuta la
     * operación indicada sobre la cuenta asociada.
     */
    public String realizarOperacion(String numeroTarjeta, String clave, Operacion operacion) {
        Cuenta cuenta = buscarCuentaPorTarjeta(numeroTarjeta);
        if (cuenta == null) {
            return "Tarjeta no reconocida";
        }
 
        Tarjeta tarjeta = cuenta.getTarjeta();
        if (tarjeta.isBloqueada()) {
            return "Tarjeta bloqueada";
        }
 
        if (!tarjeta.verificarClave(clave)) {
            if (tarjeta.isBloqueada()) {
                return "Clave incorrecta. La tarjeta ha sido bloqueada.";
            }
            return "Clave incorrecta. Intentos restantes: " + tarjeta.getIntentosRestantes();
        }
 
        return operacion.ejecutar(cuenta);
    }
}
