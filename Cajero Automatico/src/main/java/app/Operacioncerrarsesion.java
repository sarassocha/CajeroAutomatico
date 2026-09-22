
package app;


public class Operacioncerrarsesion implements Operacion {

    @Override
    public String getNombre() {
        return "Cerrar sesión";
    }

    @Override
    public String ejecutar(Cuenta cuenta) {
        String titular;
          if (cuenta != null && cuenta.getTitular() != null) {
          titular = cuenta.getTitular().getNombre();
          } else {
          titular = null;
}
        if (titular != null) {
            return "Sesión finalizada para " + titular + ". Gracias por utilizar el cajero automatico.";
        }
        return "Sesion finalizada. Gracias por utilizar el cajero automatico.";
    }

    @Override
    public boolean finalizaEjecucion() {
        return true;
    }
}
