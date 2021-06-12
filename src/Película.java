public class Película {
    public int ID;
    public String nombre;
    public int fecha;
    public String categoria;
    public boolean disponible = true;

    public void setDisponible (boolean disponible){
        this.disponible = disponible;
    }

    public boolean getDisponible (){
        return disponible;
    }
}
