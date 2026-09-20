
package app;


public class Operacioncomprarentradas implements Operacion {
 
    private double precio;
 
    public Operacioncomprarentradas(double precio) {
        this.precio = precio;
    }
 
    @Override
    public String getNombre() {
        return "Comprar entradas de teatro";
    }
 
    @Override
    public String ejecutar(Cuenta cuenta) {
        if (cuenta.retirar(precio)) {
            return "Entrada comprada por $" + precio + ". Saldo actual: $" + cuenta.getSaldo();
        }
        return "No se pudo completar la compra (saldo o límite insuficiente)";
    }
}
