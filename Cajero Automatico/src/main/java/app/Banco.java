package app;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nombre;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta buscarCuentaPorTarjeta(String numeroTarjeta) {
        for (Cuenta cuenta : cuentas) {
            Tarjeta tarjeta = cuenta.getTarjeta();
            if (tarjeta != null && tarjeta.getNumero().equals(numeroTarjeta)) {
                return cuenta;
            }
        }
        return null;
    }

    public Cliente buscarClientePorTarjeta(String numeroTarjeta) {
        for (Cuenta cuenta : cuentas) {
            Tarjeta tarjeta = cuenta.getTarjeta();
            if (tarjeta != null && tarjeta.getNumero().equals(numeroTarjeta)) {
                return cuenta.getTitular();
            }
        }
        return null;
    }
}
