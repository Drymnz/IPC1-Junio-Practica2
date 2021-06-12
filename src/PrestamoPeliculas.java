package src;
public class PrestamoPeliculas {
    protected int IDPelicula;
    protected int IDCliente;
    protected int diasPrestada;
    
    public PrestamoPeliculas(int IDPelicula,int IDCliente ,int diasPrestada){
        this.IDPelicula = IDPelicula;
        this.IDCliente = IDCliente;
        this.diasPrestada = diasPrestada;
    }
}
