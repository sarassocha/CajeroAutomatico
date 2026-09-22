
package app;

import java.text.NumberFormat;
import java.util.Locale;

public interface Operacion {


    private static NumberFormat crearFormatoMoneda() {
        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("es", "CO"));       
        formato.setMinimumFractionDigits(2);
        formato.setMaximumFractionDigits(2);
        return formato;
    }
    
    NumberFormat FORMATO_MONEDA = crearFormatoMoneda();


    String getNombre();

    String ejecutar(Cuenta cuenta);


    default String formatearMonto(double monto) {
        return FORMATO_MONEDA.format(monto);
    }


    default boolean finalizaEjecucion() {
        return false;
    }
    
 
    default double getMonto() {
        return 0.0;
    }
}


