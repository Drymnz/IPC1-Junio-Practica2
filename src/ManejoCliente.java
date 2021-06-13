package src;

import java.util.Scanner;

public class ManejoCliente {
    public Cliente[] listadoCliente = new Cliente[Star.CUANTOS_DATOS_ALMACENA_EL_PROGRAMA];

    public ManejoCliente() {

    }

    // añadir nuevo cliente
    public Cliente registrar(String nombre, int telefono) {
        if (buscarClienteNombreID(nombre, 0) == null) {
            for (int i = 0; i < listadoCliente.length; i++) {
                if (listadoCliente[i] == null) {
                    listadoCliente[i] = new Cliente(nombre, generarID(nombre, telefono), telefono);
                    return listadoCliente[i];
                }
            }
        } else {
            Star.espacios();
            System.out.println("lleno el array");
            Star.espacios();
        }
        return null;
    }

    // crear id para clientes
    public int generarID(String nombre, int telefono) {
        int aleatorio = (int) (Math.random() * 10);
        int ID = 0;
        int contador = 0;
        do {
            if ((aleatorio % 2) == 0) {
                ID = nombre.length() + telefono + contador;
            } else {
                ID = -nombre.length() + telefono + contador;
            }
            contador++;
        } while (buscarClienteNombreID(nombre, ID) != null);
        return ID;
    }

    // bucar cliente por medio de id y nombre
    public Cliente buscarClienteNombreID(String nombre, int ID) {
        Cliente returnar = null;
        for (int i = 0; i < listadoCliente.length; i++) {
            if ((listadoCliente[i] != null) && (((listadoCliente[i].ID == ID)) || (listadoCliente[i].nombre.equals(nombre)))) {
                return listadoCliente[i];
            }
        }
        return returnar;
    }

    public String informacionCliente(Cliente usario) {
        if (usario != null) {
            return " "+Star.colores(3)+usario.nombre + Star.colores(0)+" ID:" +Star.colores(3)+ usario.ID+Star.colores(0)+" ";
        } else {
            return Star.colores(3)+"No hay usario logiado"+ Star.colores(0);
        }
    }

}
