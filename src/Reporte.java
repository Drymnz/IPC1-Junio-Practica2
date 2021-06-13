package src;

public class Reporte {
    public ManejoPelicula informacion = null;

    public Reporte(ManejoPelicula informacion){
        this.informacion = informacion;
    }
    public void mostar(){
        System.out.println("Reportes de peliculas cantidad de categorias diferentes :"+ cuantosCategoriasPelicula(informacion.listadoPelicula));
        cantidadPorCategoria(informacion.listadoPelicula);
    }
    // muestra cuantas cetegorias diferentes hay
    public int cuantosCategoriasPelicula(Pelicula[] listadoPelicula){
        int contador = 0;
        if ((listadoPelicula != null)&&(listadoPelicula[0] != null)) {
            listadoPelicula = (new Ordenamiento()).ascendenteCategorias(listadoPelicula);
            for (int i = 0; i < listadoPelicula.length; i++) {
                String selecion = "";
                if (listadoPelicula[i]!= null) {
                    selecion = listadoPelicula[i].categoria;
                }
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if ((listadoPelicula[j]!=null)&&!(listadoPelicula[j].categoria.equalsIgnoreCase(selecion))) {
                        i = j-1;
                        contador++;
                    }
                }
            }
            contador++;
        }
        return contador;
    }
    public void cantidadPorCategoria(Pelicula[] listadoPelicula){
        int contador = 0;
        if ((listadoPelicula != null)&&(listadoPelicula[0] != null)) {
            listadoPelicula = (new Ordenamiento()).ascendenteCategorias(listadoPelicula);
            String selecion = "";
            for (int i = 0; i < listadoPelicula.length; i++) {
                if (listadoPelicula[i]!= null) {
                    selecion = listadoPelicula[i].categoria;
                }
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if ((listadoPelicula[j]!=null)&&(listadoPelicula[j].categoria.equalsIgnoreCase(selecion))) {
                        contador++;
                    }
                }
            }
            contador++;
            if (!(selecion.trim().equals(""))) {
                System.out.println("La categoria < "+selecion+" > tiene :"+contador);
            }
        }
    }
    public void topPeliculasMasPrestadasOMenosPrestadas(boolean acendenteODesendente){
        informacion.listadoPrestamoPelicula = (new Ordenamiento()).ordenarAscendente(informacion.listadoPrestamoPelicula);
        int cont = 0;
        int [] contador = new int [informacion.listadoPelicula.length];
        Pelicula [] contadorParalelo = new Pelicula[contador.length];
        if (informacion.listadoPrestamoPelicula!=null) {
            for (int i = 0; i < informacion.listadoPrestamoPelicula.length; i++) {
                for (int j = i+1; j < informacion.listadoPelicula.length-1; j++) {
                    if (informacion.listadoPrestamoPelicula[i].getIDPelicula() == informacion.listadoPrestamoPelicula[j].getIDPelicula()) {
                        contador[cont] ++;
                        contadorParalelo[cont] = informacion.bucarPelicula(informacion.listadoPrestamoPelicula[i].getIDPelicula());
                        i = j;
                    }
                }
                cont++;
            }
            contadorParalelo = (new Ordenamiento()).topPeliculas(contadorParalelo, contador, acendenteODesendente);
            for (int i = 0; i < contadorParalelo.length; i++) {
                if (contadorParalelo[i] != null) {
                    System.out.println("ID<" + Star.colores(3) + contadorParalelo[i].ID + Star.colores(0)+ ">NOMBRE DE LA PELICULA:<" + Star.colores(3) + contadorParalelo[i].nombre + Star.colores(0)+ "> AÑO <" + Star.colores(3) + contadorParalelo[i].fecha + Star.colores(0) + "> CATEGORIA <"+ Star.colores(3) + contadorParalelo[i].categoria + Star.colores(0) + ">" + "<"+ informacion.disponible(contadorParalelo[i].disponible) + ">");
                } else {
                    i = contadorParalelo.length;
                }
            }
        }
    }
    
}
