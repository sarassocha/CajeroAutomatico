package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la entidad bancaria que gestiona clientes, cuentas y tarjetas
 * asociadas.
 */
public class Banco {

    private String nombre;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();

    /**
     * Construye una nueva instancia de Banco.
     *
     * @param nombre El nombre del banco.
     */
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

    /**
     * Busca y retorna la tarjeta asociada al número provisto.
     *
     * @param numeroTarjeta El número identificador de la tarjeta.
     * @return El objeto Tarjeta encontrado o null si no existe.
     */
    public Tarjeta buscarTarjetaPorNumero(String numeroTarjeta) {
        if (numeroTarjeta == null) {
            return null;
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTarjetas() != null) {
                for (Tarjeta tarjeta : cuenta.getTarjetas()) {
                    if (tarjeta.getNumero().equals(numeroTarjeta)) {
                        return tarjeta;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Busca la cuenta bancaria a la que pertenece la tarjeta dada.
     *
     * @param numeroTarjeta El número de la tarjeta.
     * @return La cuenta asociada o null si no se encuentra.
     */
    public Cuenta buscarCuentaPorTarjeta(String numeroTarjeta) {
        if (numeroTarjeta == null) {
            return null;
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getTarjetas() != null) {
                for (Tarjeta tarjeta : cuenta.getTarjetas()) {
                    if (tarjeta.getNumero().equals(numeroTarjeta)) {
                        return cuenta;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Busca el cliente titular de la cuenta asociada a la tarjeta dada.
     *
     * @param numeroTarjeta El número de la tarjeta.
     * @return El cliente titular o null si no existe.
     */
    public Cliente buscarClientePorTarjeta(String numeroTarjeta) {
        Cuenta cuenta = buscarCuentaPorTarjeta(numeroTarjeta);
        return (cuenta != null) ? cuenta.getTitular() : null;
    }
}
