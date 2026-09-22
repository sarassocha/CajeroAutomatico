
package app;

public final class MontosPermitidos {

    private MontosPermitidos() {
    }

    public static final double[] RETIRO = {
        10000, 20000, 50000, 100000, 200000, 300000, 500000
    };

    public static final double[] CONSIGNACION = {
        10000, 20000, 50000, 100000, 200000, 500000, 1000000
    };

    public static boolean esValido(double monto, double[] montosPermitidos) {
        for (double permitido : montosPermitidos) {
            if (Double.compare(monto, permitido) == 0) {
                return true;
            }
        }
        return false;
    }

   
public static String listar(double[] montosPermitidos) {
    String resultado = "";
    for (int i = 0; i < montosPermitidos.length; i++) {
        if (i > 0) {
            resultado += ", ";
        }
        resultado += "$" + Operacion.FORMATO_MONEDA.format(montosPermitidos[i]);
    }
    return resultado;
}
}
