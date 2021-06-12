package src;

import java.util.Scanner;

import src.Cliente;
import src.Pelicula;

public class Catalogo {
    public final int CANTIDAD_PELICULAS_DISPONIBLES = 5;
    public Pelicula[] listadoPeliculas = new Pelicula[CANTIDAD_PELICULAS_DISPONIBLES];
    public PrestamoPelicula[] listadoPrestamoPelicula = new PrestamoPelicula[CANTIDAD_PELICULAS_DISPONIBLES];

    public Catalogo () {
        cargarCatalogo();
    }
    public void cargarCatalogo(){
        listadoPeliculas[0]  = new Pelicula(105, "Guasón", 2019, "Suspenso");
        listadoPeliculas[1]  = new Pelicula(001, "The Karate Kid", 1984, "Aventura");
        listadoPeliculas[2]  = new Pelicula(002, "The Karate Kid Part II", 1984, "Aventura");
        listadoPeliculas[3]  = new Pelicula(003, "The Karate Kid Part III", 1989, "Aventura");
        listadoPeliculas[4]  = new Pelicula(004, "Fight Club", 1999, "Suspenso");
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
                if (usario != null) {
                    // convierto el opcion a int revisar por si errores aconsejable usar un try caht para capturar el error
                     int ID = Integer.parseInt(opcion);
                     salir = reservar(usario, ID);
                } else {
                    System.out.println("Registrese porfavor");
                    usario = Star.registro();
                }
                
            }
          
        }

    }
    public boolean reservar(Cliente usario,int ID){
        Scanner entrada = new Scanner(System.in);
        Pelicula alguilar = buscarPelicula(ID);
        if (existePelicula(ID) && (alguilar.getDisponible())) {
            System.out.println("Cuantos dias la queres");
            int diasPrestada = entrada.nextInt();
            prestaPelicula(alguilar, usario, diasPrestada);
            usario.setTienePeliculaPrestada(true);
            alguilar.setDisponible(false);
            System.out.println("fue reseravado entregado tu pelicula, disfrutala");
            return true;
        }else{
            System.out.println("Lo sentimos no esta en este momento");
            return false;
        } 
        
    }
    public void prestaPelicula(Pelicula pelicula,Cliente usario, int diasPrestada){
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if (listadoPrestamoPelicula[i]!=null) {
                listadoPrestamoPelicula[i] = new PrestamoPelicula(pelicula.ID, usario.ID, diasPrestada);
                i = listadoPrestamoPelicula.length;
            }
        }
    }
    // buscar pelicual por medio de su Id
    public Pelicula buscarPelicula(int ID){
        Pelicula returnar = null;
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
