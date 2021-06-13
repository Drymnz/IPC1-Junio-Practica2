package src;
public class Cliente {
    public String nombre ;
    private int ID;
    private int telefono;
    private boolean tienePeliculaPrestada = false; 

    public Cliente (String nombre , int ID ,int telefono) {
        this.nombre =  nombre;
        this.ID = ID;
        this.telefono = telefono;
    }

    public void setTienePeliculaPrestada(boolean tienePeliculaPrestada){
        this.tienePeliculaPrestada = tienePeliculaPrestada;
    }

    public boolean getTienePeliculaPrestada (){
        return tienePeliculaPrestada;
    }
    public int getID(){
        return ID;
    }
    public int gettelefono(){
        return telefono;
    }
}