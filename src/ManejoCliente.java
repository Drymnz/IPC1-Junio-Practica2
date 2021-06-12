package src;

import java.util.Scanner;

public class ManejoCliente {
    public Cliente[] listadoCliente = new Cliente[20];
    public  ManejoCliente(){

    }
    
    public Cliente registrar(String nombre, int telefono){
        for (int i = 0; i < listadoCliente.length; i++) {
            if (listadoCliente[i] == null) {
                return new Cliente(nombre, generarID(nombre,telefono), telefono);      
            }
        }
        return null;
    }
    // crear id para clientes
    public static int generarID(String nombre, int telefono){
        int aleatorio = (int) (Math.random()*10);
        if ((aleatorio % 2)==0) {
            return nombre.length() +telefono;
        } else {
            return -nombre.length() +telefono;
        }
    }
    // bucar cliente por medio de id y nombre
    public Cliente buscarClienteNombreID(String nombre ,int ID){
        Cliente returnar = null;
        for (int i = 0; i < listadoCliente.length; i++) {
            if (listadoCliente[i].nombre.equals(nombre) | (listadoCliente[i].ID == ID)) {
                return listadoCliente[i];
            }
        }
        return returnar;
    }
   
    public String informacionCliente(Cliente usario){
        if (usario != null) {
            return usario.nombre +" ID:" +usario.ID;
        } else {
            return "No hay usario logiado";
        }
    }
    
}
