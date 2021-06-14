package src;

public class Ordenamiento {
    public Ordenamiento(){
    }
    public Pelicula[] ascendenteNombre(Pelicula[] listadoPelicula){
        String seleccion = "";
        Pelicula selecionPelicula = null;
        int posicion = 0;
        if (listadoPelicula != null) {
            for (int i = 0; i < listadoPelicula.length; i++) {
                if (listadoPelicula[i]!= null) {
                    seleccion = listadoPelicula[i].nombre;
                }
                posicion = i;
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if (((listadoPelicula[i]!=null)&&(listadoPelicula[j]!=null))&&(seleccion.compareToIgnoreCase(listadoPelicula[j].nombre)>0)) {
                        selecionPelicula = listadoPelicula[j];
                        seleccion = listadoPelicula[j].nombre;
                        posicion = j;
                    }
                }
                if ((listadoPelicula[i]!=null)&&(selecionPelicula!=null)) {
                    listadoPelicula[posicion] = listadoPelicula[i];
                    listadoPelicula[i] = selecionPelicula;
                }
                selecionPelicula = null;
            } 
        }
        return listadoPelicula;
    }
    public Pelicula[] ascendenteCategorias(Pelicula[] listadoPelicula){
        String seleccion = "";
        Pelicula selecionPelicula = null;
        int posicion = 0;
        if (listadoPelicula != null) {
            for (int i = 0; i < listadoPelicula.length; i++) {
                if (listadoPelicula[i]!= null) {
                    seleccion = listadoPelicula[i].categoria;
                }
                posicion = i;
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if (((listadoPelicula[i]!=null)&&(listadoPelicula[j]!=null))&&(seleccion.compareToIgnoreCase(listadoPelicula[j].categoria)>0)) {
                        selecionPelicula = listadoPelicula[j];
                        seleccion = listadoPelicula[j].categoria;
                        posicion = j;
                    }
                }
                if ((listadoPelicula[i]!=null)&&(selecionPelicula!=null)) {
                    listadoPelicula[posicion] = listadoPelicula[i];
                    listadoPelicula[i] = selecionPelicula;
                }
                selecionPelicula = null;
            } 
        }
        return listadoPelicula;
    }
    public PrestamoPelicula[] ordenarAscendente (PrestamoPelicula[] listado){
        int seleccion = 0;
        PrestamoPelicula seleccionPrestamosPelicula= null;
        int posicion = 0;
        if (listado != null) {
            for (int i = 0; i < listado.length; i++) {
                if (listado[i] != null) {
                    seleccion = listado[i].getIDPelicula();
                }
                posicion = i;
                    for (int j = i+1; j < listado.length-1; j++) {
                            if (((listado[i]!=null)&&(listado[j]!=null))&&(seleccion>listado[j].getIDPelicula())) {
                                seleccionPrestamosPelicula = listado[j];
                                seleccion = listado[j].getIDPelicula();
                                posicion = j;
                            } 
                    }
                if ((listado[i] != null)&&(seleccionPrestamosPelicula!=null)) {
                    listado[posicion] = listado[i];
                    listado[i] = seleccionPrestamosPelicula;
                }
                seleccionPrestamosPelicula = null;
            }
        }
        return listado;
    }
    public Pelicula[] topPeliculas (Pelicula[] listadoPeliculas, int[] contador){
        int selecion = 0;
        Pelicula mover = null;
        int posicion = 0 ;
        for (int i = 0; i < contador.length; i++) {
            selecion = contador[i];
            mover = listadoPeliculas[i];
            posicion = i;
            for (int j = i+1; j < contador.length; j++) {
                    if (selecion > contador[j]) {
                        selecion = contador[j];
                        mover = listadoPeliculas[j];
                        posicion = j;
                    }
            }
            contador[posicion] = contador[i];
            contador[i] = selecion;
            listadoPeliculas[posicion] = listadoPeliculas[i];
            listadoPeliculas[i] = mover;
        }
        return listadoPeliculas;
    }
    
}
