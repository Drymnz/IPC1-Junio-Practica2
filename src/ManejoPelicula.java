package src;

import java.util.Scanner;

public class ManejoPelicula {
    public Pelicula[] listadoPelicula = new Pelicula[Star.CUANTOS_DATOS_ALMACENA_EL_PROGRAMA];
    public PrestamoPelicula[] listadoPrestamoPelicula = new PrestamoPelicula[Star.CUANTOS_DATOS_ALMACENA_EL_PROGRAMA];

    public ManejoPelicula  (){
        cargarCatalogo();
    }
    // eliminar este metodo solo es prueva si para ver como se ve en pantalla
    public void cargarCatalogo(){
        listadoPelicula[0]  = new Pelicula(105, "Guasón", 2019, "Suspenso");
        listadoPelicula[1]  = new Pelicula(001, "The Karate Kid", 1984, "Aventura");
        listadoPelicula[2]  = new Pelicula(002, "The Karate Kid Part II", 1984, "Aventura");
        listadoPelicula[3]  = new Pelicula(003, "The Karate Kid Part III", 1989, "Aventura");
        listadoPelicula[4]  = new Pelicula(004, "Fight Club", 1999, "Suspenso");
    }
    // mostrar peliculas disponibles
    public void verCatalogo(){
        for (int i = 0; i < listadoPelicula.length; i++) {
            if (listadoPelicula[i] != null) {
                System.out.println("ID<"+listadoPelicula[i].ID+">NOMBRE DE LA PELICULA:<" +listadoPelicula[i].nombre +"> AÑO <"+listadoPelicula[i].fecha+"> CATEGORIA <"+listadoPelicula[i].categoria+">" );
            }else{
                i = listadoPelicula.length;
            }
        }
    }
    public boolean reservar(Cliente usario,int ID){
        Scanner entrada = new Scanner(System.in);
        Pelicula alguilar = bucarPelicula(ID);
        if ((alguilar != null) && (alguilar.getDisponible()) && !(usario.getTienePeliculaPrestada())) {
            System.out.println("Cuantos dias la quieres");
            int diasPrestada = entrada.nextInt();
            prestaPelicula(alguilar, usario, diasPrestada);
            usario.setTienePeliculaPrestada(true);
            alguilar.setDisponible(false);
            System.out.println("fue reseravado entregado tu pelicula, disfrutala");
            return true;
        }else{
            switch ((alguilar == null)? 0:!(alguilar.getDisponible())? 1:(usario.getTienePeliculaPrestada())?2:3) {
                case 0:
                System.out.println("lo sentimos la pelicula no esta existe");
                    break;
                case 1:
                    System.out.println("Lo sentimos no esta disponible");
                        break;
                case 2:
                    System.out.println("Lo sentimos no puede tener mas de una pelicula en su poder");
                        break;
                default:
                    break;
            }
            
            return false;
        } 
        
    }
  
    //quien reservo la pelicula 
    public int reservoPelicula(int ID){
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if ((listadoPrestamoPelicula[i]!= null)&&(listadoPrestamoPelicula[i].IDPelicula == ID)) {
                return listadoPrestamoPelicula[i].IDCliente;
            }
        }
        return 0;
    }
    public boolean existeReservacion (int ID){
        if (existePelicula(ID)) {
            for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
                if ((listadoPrestamoPelicula[i] != null)&&(listadoPrestamoPelicula[i].IDPelicula == ID)) {
                    return true;
                }
            }
        } 
        return false;  
    }
   
    // buscar pelicual por medio de su Id
    public Pelicula bucarPelicula( int ID){
        Pelicula returnar = null;
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i] != null)&&(listadoPelicula[i].ID == ID)) {
                return listadoPelicula[i];
            }
        }
        return returnar;
    } 
    // verificar si existe la pelicula
    public boolean existePelicula(int ID){
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i] != null)&&(ID == listadoPelicula[i].ID)) {
                 return true;
            }
        }
        return false;
     }
     // este metodo realiza el registro de prestamo de peliculas
     public void prestaPelicula(Pelicula pelicula,Cliente usario, int diasPrestada){
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if (listadoPrestamoPelicula[i]!=null) {
                listadoPrestamoPelicula[i] = new PrestamoPelicula(pelicula.ID, usario.ID, diasPrestada);
                i = listadoPrestamoPelicula.length;
            }
        }
    }
}
