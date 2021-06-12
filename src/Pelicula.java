package src;
public class Pelicula {
    public int ID;
    public String nombre;
    public int fecha;
    public String categoria;
    public boolean disponible = true;

    public Pelicula(int ID, String nombre,int fecha,String categoria ){
        this.ID = ID;
        this.nombre = nombre;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public void setDisponible (boolean disponible){
        this.disponible = disponible;
    }

    public boolean getDisponible (){
        return disponible;
    }
}
