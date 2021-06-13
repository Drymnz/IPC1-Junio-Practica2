package src;

public class Ordenamiento {
    public Pelicula[] auxiliarListadoPelicula ;
    public PrestamoPelicula[] auxiliarListadoPrestamoPelicula;
    public Cliente[] auxiliarListadoCliente; 

    public Ordenamiento(){
    }
    public Pelicula[] ascendenteNombre(Pelicula[] listadoPelicula){
        String seleccion = "";
        Pelicula selecionPelicula = null;
        int posicion = 0;
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
        return listadoPelicula;
    }
    public Pelicula[] ascendenteCategorias(Pelicula[] listadoPelicula){
        String seleccion = "";
        Pelicula selecionPelicula = null;
        int posicion = 0;
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
        return listadoPelicula;
    }
    public Pelicula[] ascendenteID(Pelicula[] listadoPelicula,boolean ascendenteODesendente){
        int seleccion = 0;
        Pelicula selecionPelicula = null;
        int posicion = 0;
        for (int i = 0; i < listadoPelicula.length; i++) {
            if (listadoPelicula[i]!= null) {
                seleccion = listadoPelicula[i].ID;
            }
            posicion = i;
            for (int j = i+1; j < listadoPelicula.length; j++) {
                if (ascendenteODesendente) {
                    if (((listadoPelicula[i]!=null)&&(listadoPelicula[j]!=null))&&(seleccion<listadoPelicula[j].ID)) {
                        selecionPelicula = listadoPelicula[j];
                        seleccion = listadoPelicula[j].ID;
                        posicion = j;
                    }  
                }else {
                    if (((listadoPelicula[i]!=null)&&(listadoPelicula[j]!=null))&&(seleccion>listadoPelicula[j].ID)) {
                        selecionPelicula = listadoPelicula[j];
                        seleccion = listadoPelicula[j].ID;
                        posicion = j;
                    }
                }
               
            }
            if ((listadoPelicula[i]!=null)&&(selecionPelicula!=null)) {
                listadoPelicula[posicion] = listadoPelicula[i];
                listadoPelicula[i] = selecionPelicula;
            }
            selecionPelicula = null;
        }
        return listadoPelicula;
    }
}
