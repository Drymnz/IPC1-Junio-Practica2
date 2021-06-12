package src;
public class Cliente {
    public String nombre ;
    public int ID;
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
}