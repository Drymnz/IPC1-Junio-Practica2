public class Cliente {
    public String nombre ;
    public int ID;
    private int telefono;
    private boolean tienePeliculaPrestada = false; 

    public Cliente () {
        
    }

    public void setTienePeliculaPrestada(boolean tienePeliculaPrestada){
        this.tienePeliculaPrestada = tienePeliculaPrestada;
    }

    public boolean getTienePeliculaPrestada (){
        return tienePeliculaPrestada;
    }
}