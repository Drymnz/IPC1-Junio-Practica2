package src;

public class Reporte {
    public ManejoPelicula informacion = null;

    public Reporte(ManejoPelicula informacion){
        this.informacion = informacion;
    }
    public void mostar(){
        Star.espacios();
        System.out.println("Reportes de peliculas cantidad de categorias diferentes < "+ cuantosCategoriasPelicula(informacion.listadoPelicula)+" >");
        cantidadPorCategoria(informacion.listadoPelicula);
        System.out.println("Top peliculas mas alquiladas");
        topPeliculasMasPrestadasOMenosPrestadas(true);
        System.out.println("Top peliculas menos alquiladas");
        topPeliculasMasPrestadasOMenosPrestadas(false);
    }
    // muestra cuantas cetegorias diferentes hay
    public int cuantosCategoriasPelicula(Pelicula[] listadoPelicula){
        int contador = 0;
        if ((listadoPelicula != null)&&(listadoPelicula[0] != null)) {
            listadoPelicula = (new Ordenamiento()).ascendenteCategorias(listadoPelicula);
            contador++;
            for (int i = 0; i < listadoPelicula.length; i++) {
                String selecion = "";
                if (listadoPelicula[i]!= null) {
                    selecion = listadoPelicula[i].categoria;
                }
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if ((listadoPelicula[j]!=null)&&!(listadoPelicula[j].categoria.trim().equalsIgnoreCase(selecion))) {
                        i = j-1;
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
    public void cantidadPorCategoria(Pelicula[] listadoPelicula){
        if ((listadoPelicula != null)&&(listadoPelicula[0] != null)) {
            listadoPelicula = (new Ordenamiento()).ascendenteCategorias(listadoPelicula);
            int contador = 0;
            String selecion = "";
            for (int i = 0; i < listadoPelicula.length; i++) {
                if (listadoPelicula[i]!= null) {
                    selecion = listadoPelicula[i].categoria;
                }
                for (int j = i+1; j < listadoPelicula.length; j++) {
                    if ((listadoPelicula[j]!=null)&&(listadoPelicula[j].categoria.equalsIgnoreCase(selecion))) {
                        contador++;
                        i = j;
                    }
                }
                contador ++;
                if (!(selecion.trim().equals(""))) {
                    System.out.println("La categoria < "+selecion+" > tiene :  < "+(contador)+" >");
                }
                selecion= "";
                contador = 0;
            }
        }
    }
    public void topPeliculasMasPrestadasOMenosPrestadas(boolean acendenteODesendente){
        informacion.listadoPrestamoPelicula = (new Ordenamiento()).ordenarAscendente(informacion.listadoPrestamoPelicula); // si lo ordena bien
        int [] contador = new int [informacion.listadoPelicula.length];
        Pelicula [] contadorParalelo = new Pelicula[informacion.listadoPelicula.length];
        if (informacion.listadoPrestamoPelicula!=null) {
            // aqui cuenta cuantas veces una pelicula aparece en el listado de prestamoPelicuals
            for (int i = 0; i < informacion.listadoPelicula.length; i++) {
                for (int j = 0; j < informacion.listadoPrestamoPelicula.length; j++) {// j sera pra prestamos peliculas
                    if ((informacion.listadoPrestamoPelicula[j]!=null)&&(informacion.listadoPelicula[i]!=null) &&(informacion.listadoPelicula[i].ID == informacion.listadoPrestamoPelicula[j].getIDPelicula())) {
                        contador[i] ++;
                    }
                }
                contadorParalelo[i] = informacion.listadoPelicula[i];  
            }
            //aqui ordena de forma ascendente respecto al contador
            contadorParalelo = (new Ordenamiento()).topPeliculas(contadorParalelo, contador);
            if ((contadorParalelo == null) && (contadorParalelo[0]==null)) {
                System.out.println(Star.colores(5)+"Ninguna pelicula a sido alquilada"+Star.colores(0));
            }else {
                if (acendenteODesendente) {
                    for (int i = 0; i < contadorParalelo.length; i++) {
                        if (contadorParalelo[i] != null) {
                            System.out.println("ID<" + Star.colores(3) + contadorParalelo[i].ID + Star.colores(0)+ ">NOMBRE DE LA PELICULA:<" + Star.colores(3) + contadorParalelo[i].nombre + Star.colores(0)+ "> AÑO <" + Star.colores(3) + contadorParalelo[i].fecha + Star.colores(0) + "> CATEGORIA <"+ Star.colores(3) + contadorParalelo[i].categoria + Star.colores(0) + ">" + "<"+ informacion.disponible(contadorParalelo[i].disponible) + ">");
                        }
                    }
                } else {
                    for (int i = (contadorParalelo.length-1); i >= 0; i--) {
                        if (contadorParalelo[i] != null) {
                            System.out.println("ID<" + Star.colores(3) + contadorParalelo[i].ID + Star.colores(0)+ ">NOMBRE DE LA PELICULA:<" + Star.colores(3) + contadorParalelo[i].nombre + Star.colores(0)+ "> AÑO <" + Star.colores(3) + contadorParalelo[i].fecha + Star.colores(0) + "> CATEGORIA <"+ Star.colores(3) + contadorParalelo[i].categoria + Star.colores(0) + ">" + "<"+ informacion.disponible(contadorParalelo[i].disponible) + ">");
                        } 
                    }
                }
            }
        }
    }
}
