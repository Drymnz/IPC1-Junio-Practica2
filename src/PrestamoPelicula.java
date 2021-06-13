package src;
public class PrestamoPelicula {
    private int IDPelicula;
    private int IDCliente;
    private int diasPrestada;
    
    public PrestamoPelicula(int IDPelicula,int IDCliente ,int diasPrestada){
        this.IDPelicula = IDPelicula;
        this.IDCliente = IDCliente;
        this.diasPrestada = diasPrestada;
    }
    public int getIDPelicula(){
        return IDPelicula;
    }
    public int getIDCliente(){
        return IDCliente;
    }
    public int getIdiasPrestada(){
        return diasPrestada;
    }
}
