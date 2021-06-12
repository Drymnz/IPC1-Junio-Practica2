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
            System.out.println("Usario <"+nombreUsario(usario)+">");
            System.out.println("<presione 1> Ver catalogo ");
            System.out.println("<presione 2> Devolver pelicula (NOTA:REQUISITOS TENER UNA PELICULA)");
            System.out.println("<presione 3> Registrase cliente");
            System.out.println("<presione 4> Busacar cliente");
            System.out.println("<presione 0> Salir del programa");
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
                            devolverPelicula(usario, almacen);
                         break;
                    case "3":
                            usario = registro();
                         break;
                    case "4":
                            usario = almacen.buscar();
                         break;
                    default:
                         break;
                 }  
        }
       
    }
    public String nombreUsario(Cliente usario){
        if (usario != null) {
            return usario.nombre;
        } else {
            return "No hay usario logiado";
        }
    }
    public void devolverPelicula (Cliente usario,Catalogo almacen){
        if ((usario != null)) {
            if ((usario.getTienePeliculaPrestada())) {
                // si puede devolver una pelicula
            } else {
                System.out.println("El usario no tiene ninguna pelicula");
            }
        } else {
            Scanner entrada = new Scanner(System.in);
            String opcion = "";
            while (!(opcion.equals("salir"))) {
                System.out.println("<Presione 1> Para buscar cliente por medio del ID de la pelicula");
                System.out.println("<Presione 2> Para buscar cliente en la base de datos");
                System.out.println("<Escriba salir> Para regresar al menu anterior");
                opcion = entrada.nextLine();
                    switch (opcion) {
                        case "0":
                            opcion = "salir";
                            break;
                        case "1":
                            System.out.println("ingresse el ID de la pelicula");
                            usario = almacen.buscarIDPelicuala(entrada.nextInt());
                            break;
                        case "2":
                            almacen.buscar();   
                            break;                    
                        default:
                            break;
                    }
            }
        }
    }
   
}
