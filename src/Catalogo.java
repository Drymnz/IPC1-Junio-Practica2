package src;

import java.util.Scanner;

import src.Película;

public class Catalogo {
    public final int CANTIDAD_PELICULAS_DISPONIBLES = 5;
    public Película[] listadoPeliculas = new Película[CANTIDAD_PELICULAS_DISPONIBLES];
    public PrestamoPelicula[] listadoPrestamoPelicula = new PrestamoPelicula[CANTIDAD_PELICULAS_DISPONIBLES];

    public Catalogo () {
        cargarCatalogo();
    }
    public void cargarCatalogo(){
        listadoPeliculas[0]  = new Película(105, "Guasón", 2019, "Suspenso");
        listadoPeliculas[1]  = new Película(001, "The Karate Kid", 1984, "Aventura");
        listadoPeliculas[2]  = new Película(002, "The Karate Kid Part II", 1984, "Aventura");
        listadoPeliculas[3]  = new Película(003, "The Karate Kid Part III", 1989, "Aventura");
        listadoPeliculas[4]  = new Película(004, "Fight Club", 1999, "Suspenso");
    }
    public void mostarCatalogo(){
        for (int i = 0; i < listadoPeliculas.length; i++) {
            System.out.println("ID<"+listadoPeliculas[i].ID+">NOMBRE DE LA PELICULA:<" +listadoPeliculas[i].nombre +"> AÑO <"+listadoPeliculas[i].fecha+"> CATEGORIA <"+listadoPeliculas[i].categoria+">" );
        }
    }
    public void alquilar(Cliente usario){
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("¿Cual desea alquilar? escriba su ID (el que le aparece en ID<ID>)");
            System.out.println("para regresar presiones 0");
            String opcion = entrada.nextLine();
            if (opcion.equals("0")) {
                salir = true;
            } else {
                // convierto el opcion a int revisar por si errores aconsejable usar un try caht para capturar el error
                int ID = Integer.parseInt(opcion);
                salir = reservar(usario, ID);
            }
          
        }

    }
    public boolean reservar(Cliente usario,int ID){
        Scanner entrada = new Scanner(System.in);
        if (existePelicula(ID) && (buscarPelicula(ID).getDisponible())) {
            System.out.println("Cuantos dias la queres");
            int dias = entrada.nextInt();
            System.out.println("fue reseravado entregado tu pelicula, disfrutala");
            return true;
        }else{
            System.out.println("Lo sentimos no esta en este momento");
            return false;
        } 
        
    }
    public Película buscarPelicula(int ID){
        Película returnar = null;
        for (int i = 0; i < listadoPeliculas.length; i++) {
            if (listadoPeliculas[i].ID == ID) {
                return listadoPeliculas[i];
            }
        }
        return returnar;
    }
    // verificar si existe la pelicula
    public boolean existePelicula(int ID){
       for (int i = 0; i < listadoPeliculas.length; i++) {
           if (ID == listadoPeliculas[i].ID) {
                return true;
           }
       }
       return false;
    }
}
