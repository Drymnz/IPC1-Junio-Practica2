package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejoPelicula {
    public Pelicula[] listadoPelicula = new Pelicula[1];
    public PrestamoPelicula[] listadoPrestamoPelicula = new PrestamoPelicula[1];
    public ManejoPelicula() {
      
    }
    // mostrar peliculas disponibles
    public void verCatalogo() {
        for (int i = 0; i < listadoPelicula.length; i++) {
            if (listadoPelicula[i] != null) {
                System.out.println("ID<" + Star.colores(3) + listadoPelicula[i].ID + Star.colores(0)+ ">NOMBRE DE LA PELICULA:<" + Star.colores(3) + listadoPelicula[i].nombre + Star.colores(0)+ "> AÑO <" + Star.colores(3) + listadoPelicula[i].fecha + Star.colores(0) + "> CATEGORIA <"+ Star.colores(3) + listadoPelicula[i].categoria + Star.colores(0) + ">" + "<"+ disponible(listadoPelicula[i].disponible) + ">");
            } else {
                i = listadoPelicula.length;
            }
        }
    }
    // para marcar si esta disponible
    public String disponible(boolean disponible) {
        if (disponible) {
            return Star.colores(1) + "DISPONIBLE" + Star.colores(0);
        } else {
            return Star.colores(5) + "NODISPONIBLE" + Star.colores(0);
        }
    }

    // añadir peliculas al listado
    public boolean addPelicula(String textoID, String nombre, String textoFecha, String categoria) {
        int ID = Integer.parseInt(textoID);
        if (!(existeIDPeliculas(ID))) {
            do {
                for (int i = 0; i < listadoPelicula.length; i++) {
                    if ((listadoPelicula[i] == null)) {
                        int fecha = Integer.parseInt(textoFecha);
                        listadoPelicula[i] = new Pelicula(ID, nombre, fecha, categoria);
                        return true;
                    }
                }
                aumentarEspacioListadoPelicula();
            } while (true);
        }
        return false;
    }
    private void aumentarEspacioListadoPelicula(){
        int espacioAumentado = listadoPelicula.length + 3;
        Pelicula[] nuevoListado = new Pelicula[espacioAumentado];
        for (int i = 0; i < listadoPelicula.length; i++) {
            nuevoListado[i] = listadoPelicula[i];
        }
        listadoPelicula = null;
        listadoPelicula = nuevoListado;
    }

    // revisar si exite ya este ide en peliculas
    public boolean existeIDPeliculas(int ID) {
        for (int i = 0; i < listadoPelicula.length; i++) {
            if ((listadoPelicula[i] != null) && (listadoPelicula[i].ID == ID)) {
                return true;
            }
        }
        return false;
    }

    // mover si esta reservado la pelicula
    public boolean reservar(Cliente usario, int ID) {
        Pelicula alguilar = bucarPelicula(ID);
        if ((alguilar != null) && (alguilar.getDisponible()) && !(usario.getTienePeliculaPrestada())) {
            Star.espacios();
            System.out.println("¿Cuantos dias la quieres alquilar? (NOTA: por favor solo numero)");
            Star.espacios();
            String diasPrestada = new Scanner(System.in).nextLine();
            Pattern patron = Pattern.compile("[0-9]+");
            Matcher matcher = patron.matcher(diasPrestada);
            if (matcher.matches()) {
                int dias = Integer.valueOf(diasPrestada);
                addReservacionPelicula(alguilar, usario, dias);
                usario.setTienePeliculaPrestada(true);
                alguilar.setDisponible(false);
                Star.espacios();
                System.out.println(Star.colores(1) + "fue reseravado entregado tu pelicula, disfrutala" + Star.colores(0));
                return true;
            }else {
                Star.espacios();
                System.out.println(Star.colores(5) + "Incorrecto la fecha" + Star.colores(0));
                return false ;
            } 
        } else {
            Star.espacios();
            System.out.print(Star.colores(5));
            switch ((alguilar == null) ? 0: !(alguilar.getDisponible()) ? 1 : (!usario.getTienePeliculaPrestada()) ? 2 : 3) {
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
            if ((listadoPrestamoPelicula[i] != null) && (listadoPrestamoPelicula[i].getIDPelicula() == ID)) {
                return listadoPrestamoPelicula[i].getIDCliente();
            }
        }
        return 0;
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


    // buscar pelicula usando el id del cliente
    public Pelicula buscarPeliculaIDCliente(int IDCliente) {
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            if ((listadoPrestamoPelicula[i] != null) && (listadoPrestamoPelicula[i].getIDCliente() == IDCliente)) {
                return bucarPelicula(listadoPrestamoPelicula[i].getIDPelicula());
            }
        }
        return null;
    }

    // este metodo realiza el registro de prestamo de peliculas
    public void addReservacionPelicula(Pelicula pelicula, Cliente usario, int diasPrestada) {
        boolean salir = true;
        do {
            for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
                if (listadoPrestamoPelicula[i] == null) { // null si esta vacio
                    listadoPrestamoPelicula[i] = new PrestamoPelicula(pelicula.ID, usario.getID(), diasPrestada);
                    i = listadoPrestamoPelicula.length;
                    salir = false;
                }
            }
            aumentarEspacioListadoReservacionPelicula();
        } while (salir);
    }
    private void aumentarEspacioListadoReservacionPelicula(){
        int espacioAumentado = listadoPrestamoPelicula.length + 3;
        PrestamoPelicula[] nuevoListado = new PrestamoPelicula[espacioAumentado];
        for (int i = 0; i < listadoPrestamoPelicula.length; i++) {
            nuevoListado[i] = listadoPrestamoPelicula[i];
        }
        listadoPrestamoPelicula = null;
        listadoPrestamoPelicula = nuevoListado;
    }
}
