package src;
public class PrestamoPelicula {
    protected int IDPelicula;
    protected int IDCliente;
    protected int diasPrestada;
    
    public PrestamoPelicula(int IDPelicula,int IDCliente ,int diasPrestada){
        this.IDPelicula = IDPelicula;
        this.IDCliente = IDCliente;
        this.diasPrestada = diasPrestada;
    }
}
