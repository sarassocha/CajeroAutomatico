package app;

/**
 * Operación encargada del cierre de sesión en el cajero automático.
 */
public class Operacioncerrarsesion implements Operacion {

    /**
     * Constructor por defecto.
     */
    public Operacioncerrarsesion() {
    }

    @Override
    public String getNombre() {
        return "Cerrar sesión";
    }

    @Override
    public String ejecutar(Tarjeta tarjeta) {

        return "Sesión finalizada. Gracias por utilizar el cajero automático.";
    }

    /**
     * Ejecuta el cierre de sesión utilizando la información del titular de la
     * cuenta.
     *
     * @param cuenta La cuenta activa del usuario.
     * @return Mensaje de despedida y finalización de sesión.
     */
    public String ejecutar(Cuenta cuenta) {
        String titular = null;
        if (cuenta != null && cuenta.getTitular() != null) {
            titular = cuenta.getTitular().getNombre();
        }
        if (titular != null) {
            return "Sesión finalizada para " + titular + ". Gracias por utilizar el cajero automático.";
        }
        return "Sesion finalizada. Gracias por utilizar el cajero automático.";
    }

    @Override
    public boolean finalizaEjecucion() {
        return true;
    }
}
