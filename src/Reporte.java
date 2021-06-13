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
    public void topPeliculasMasPrestadas(){
        informacion.listadoPrestamoPelicula = (new Ordenamiento()).ordenarAscendente(informacion.listadoPrestamoPelicula);
    }
    public void topPeliculasMenosPrestadas(Pelicula[] listadoPelicula){

    } 
    public void fueRentada(){

    } 
    
}
