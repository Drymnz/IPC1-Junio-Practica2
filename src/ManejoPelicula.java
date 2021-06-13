package src;

import java.util.Scanner;

public class ManejoPelicula {
    public Pelicula[] listadoPelicula = new Pelicula[Star.CUANTOS_DATOS_ALMACENA_EL_PROGRAMA];
    public PrestamoPelicula[] listadoPrestamoPelicula = new PrestamoPelicula[Star.CUANTOS_DATOS_ALMACENA_EL_PROGRAMA];

    public ManejoPelicula() {
        cargarCatalogo();
    }

    // eliminar este metodo solo es prueva si para ver como se ve en pantalla
    public void cargarCatalogo() {
        listadoPelicula[0] = new Pelicula(105, "Guasón", 2019, "Suspenso");
        listadoPelicula[1] = new Pelicula(001, "The Karate Kid", 1984, "Aventura");
        listadoPelicula[2] = new Pelicula(002, "The Karate Kid Part II", 1984, "Aventura");
        listadoPelicula[3] = new Pelicula(003, "The Karate Kid Part III", 1989, "Aventura");
        listadoPelicula[4] = new Pelicula(004, "Fight Club", 1999, "Suspenso");
    }

    // mostrar peliculas disponibles
    public void verCatalogo() {
        for (int i = 0; i < listadoPelicula.length; i++) {
            if (listadoPelicula[i] != null) {
                System.out.println("ID<" + Star.colores(3) + listadoPelicula[i].ID + Star.colores(0)
                        + ">NOMBRE DE LA PELICULA:<" + Star.colores(3) + listadoPelicula[i].nombre + Star.colores(0)
                        + "> AÑO <" + Star.colores(3) + listadoPelicula[i].fecha + Star.colores(0) + "> CATEGORIA <"
                        + Star.colores(3) + listadoPelicula[i].categoria + Star.colores(0) + ">" +"<"+disponible(listadoPelicula[i].disponible)+">");
            } else {
                i = listadoPelicula.length;
            }
        }
    }
    public String disponible (boolean disponible){
        if (disponible) {
            return Star.colores(1)+"DISPONIBLE"+Star.colores(0);
        } else {
            return Star.colores(5)+"NODISPONIBLE"+Star.colores(0);
        }
    }

    // añadir peliculas al listado
    public boolean addPelicula(String textoID, String nombre, String textoFecha, String categoria) {
        int ID = Integer.parseInt(textoID);
        if (existeIDPeliculas(ID)) {
            for (int i = 0; i < listadoPelicula.length; i++) {
                if ((listadoPelicula[i] == null)) {
                    
                    int fecha = Integer.parseInt(textoFecha);
                    listadoPelicula[i] = new Pelicula(ID, nombre, fecha, categoria);
                    return true;
                }
            } 
        }
        return false;
    }
    // revisar si exite ya este ide en peliculas
    public boolean existeIDPeliculas(int ID){
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i]!= null)&& (listadoPelicula[i].ID == ID)) {
                return true;
            }
        }
        return false;
    }
    // mover si esta reservado la pelicula
    public boolean reservar(Cliente usario, int ID) {
        Scanner entrada = new Scanner(System.in);
        Pelicula alguilar = bucarPelicula(ID);
        if ((alguilar != null) && (alguilar.getDisponible()) && !(usario.getTienePeliculaPrestada())) {
            Star.espacios();
            System.out.println("Cuantos dias la quieres");
            Star.espacios();
            int diasPrestada = entrada.nextInt();
            prestaPelicula(alguilar, usario, diasPrestada);
            usario.setTienePeliculaPrestada(true);
            alguilar.setDisponible(false);
            Star.espacios();
            System.out.println(Star.colores(1)+"fue reseravado entregado tu pelicula, disfrutala"+Star.colores(0));
            return true;
        } else {
            Star.espacios();
            System.out.print(Star.colores(5));
            switch ((alguilar == null) ? 0
                    : !(alguilar.getDisponible()) ? 1 : (usario.getTienePeliculaPrestada()) ? 2 : 3) {
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
            System.out.print(Star.colores(0));
            Star.espacios();
            return false;
        }

    }

    // buscar la prestamoPelicula mediante el id de la palicula
    public int reservoPelicula(int ID) {
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if ((listadoPrestamoPelicula[i] != null) && (listadoPrestamoPelicula[i].IDPelicula == ID)) {
                return listadoPrestamoPelicula[i].IDCliente;
            }
        }
        return 0;
    }

    public boolean existeReservacion(int ID) {
        if (existePelicula(ID)) {
            for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
                if ((listadoPrestamoPelicula[i] != null) && (listadoPrestamoPelicula[i].IDPelicula == ID)) {
                    return true;
                }
            }
        }
        return false;
    }

    // buscar pelicual por medio de su Id
    public Pelicula bucarPelicula(int ID) {
        Pelicula returnar = null;
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i] != null) && (listadoPelicula[i].ID == ID)) {
                return listadoPelicula[i];
            }
        }
        return returnar;
    }

    // verificar si existe la pelicula
    public boolean existePelicula(int ID) {
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i] != null) && (ID == listadoPelicula[i].ID)) {
                return true;
            }
        }
        return false;
    }
    // buscar pelicula usando el id del cliente
    public Pelicula buscarPeliculaIDCliente (int IDCliente){
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if ((listadoPrestamoPelicula[i] != null)&&(listadoPrestamoPelicula[i].IDCliente == IDCliente)) {
                return bucarPelicula(listadoPrestamoPelicula[i].IDPelicula);
            }
        }
        return null;
    }
    // este metodo realiza el registro de prestamo de peliculas
    public void prestaPelicula(Pelicula pelicula, Cliente usario, int diasPrestada) {
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if (listadoPrestamoPelicula[i] == null) {
                listadoPrestamoPelicula[i] = new PrestamoPelicula(pelicula.ID, usario.ID, diasPrestada);
                i = listadoPrestamoPelicula.length;
            }
        }
    }
}
