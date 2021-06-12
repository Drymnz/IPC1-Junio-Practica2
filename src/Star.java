package src;

import java.util.Scanner;

public class Star {
    public static void main(String[] args) {
        Star ejecutar = new Star();
    }
    public static Cliente registro (){
        Scanner entrada = new Scanner(System.in);
        Cliente returnar = null;
            System.out.println("Ingrese su nombre");
            String nombre = entrada.nextLine();
            System.out.println("ingrese su telefono");
            int telefono = entrada.nextInt();
            returnar = new Cliente(nombre, generarID(nombre,telefono), telefono);
        return returnar;
    }
    public static int generarID(String nombre, int telefono){
        int aleatorio = (int) (Math.random()*10);
        if ((aleatorio % 2)==0) {
            return nombre.length() +telefono;
        } else {
            return -nombre.length() +telefono;
        }
    }

    public Star (){
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        Cliente usario = null;
        Catalogo almacen = new Catalogo();
        // bucle para que no se salga facilmente el programa
        while (!salir) {
            System.out.println("Hola cliente para poder ver el catalogo presione 1");
            System.out.println("si desea devolver una pelicula presiones 2 (NOTA:REQUISITOS TENER UNA PELICULA)");
            System.out.println("registrase presione 3");
            System.out.println("si desea salir del programa presione 0");
            String opcion = entrada.nextLine();
                switch (opcion) {
                    case "0":
                            salir = true;
                         break;
                    case "1":
                    almacen.mostarCatalogo();
                    almacen.alquilar(usario);
                         break;
                    case "2":
                            devolverPelicula(usario);
                         break;
                    case "3":
                            usario = registro();
                         break;
            
                    default:
                         break;
                 }  
        }
       
    }
    public void devolverPelicula (Cliente usario){
        if ((usario != null)&& (usario.getTienePeliculaPrestada())) {
            
        } else {
            System.out.println("Lo sentimos no cumple los requisitos para devolver una pelicula");
        }
    }
   
}
