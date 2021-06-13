package src;

public class ManejoCliente {
    public Cliente[] listadoCliente = new Cliente[1];

    public ManejoCliente() {

    }

    // añadir nuevo cliente
    public Cliente registrar(String nombre, int telefono) {
        if (buscarClienteNombreID(nombre, 0) == null) {
            do {
                for (int i = 0; i < listadoCliente.length; i++) {
                    if (listadoCliente[i] == null) {
                        listadoCliente[i] = new Cliente(nombre, generarID(nombre, telefono), telefono);
                        return listadoCliente[i];
                    }
                } 
                aumentarEspacio();
            } while (true);
        } else {
            Star.espacios();
            System.out.println("lleno el array");
            Star.espacios();
        }
        return null;
    }
    private void aumentarEspacio(){
        int espacioAumentado = listadoCliente.length + 3;
        Cliente[] nuevoListado = new Cliente[espacioAumentado];
        for (int i = 0; i < listadoCliente.length; i++) {
            nuevoListado[i] = listadoCliente[i];
        }
        listadoCliente = nuevoListado;
    }

    // crear id para clientes
    public int generarID(String nombre, int telefono) {
        int aleatorio = (int) (Math.random() * 10);
        int ID = 0;
        int contador = 0;
        do {
            if ((aleatorio % 2) == 0) {
                ID = nombre.length() + telefono + contador;
            } else {
                ID = -nombre.length() + telefono + contador;
            }
            contador++;
        } while (buscarClienteNombreID(nombre, ID) != null);
        return ID;
    }

    // bucar cliente por medio de id y nombre
    public Cliente buscarClienteNombreID(String nombre, int ID) {
        Cliente returnar = null;
        for (int i = 0; i < listadoCliente.length; i++) {
            if ((listadoCliente[i] != null) && (((listadoCliente[i].getID() == ID)) | (listadoCliente[i].nombre.equals(nombre)))) {
                return listadoCliente[i];
            }
        }
        return returnar;
    }

    public String informacionCliente(Cliente usario) {
        if (usario != null) {
            return " "+Star.colores(3)+usario.nombre + Star.colores(0)+" ID:" +Star.colores(3)+ usario.getID()+Star.colores(0)+" ";
        } else {
            return Star.colores(3)+"No hay usario logiado"+ Star.colores(0);
        }
    }
    public void mostrarListadoClientes(ManejoPelicula catalogo){
        Star.espacios();
        System.out.println("El listado de cliente es :");
        for (int i = 0; i < listadoCliente.length; i++) {
            if (listadoCliente[i]!=null) {
                System.out.println("ID < "+Star.colores(4)+listadoCliente[i].getID()+Star.colores(0)+" > Nombre < "+Star.colores(4)+listadoCliente[i].nombre+Star.colores(0)+" > Telefono < "+Star.colores(4)+listadoCliente[i].gettelefono()+Star.colores(0)+" > Tiene pelicula < "+tienePelicula(listadoCliente[i],catalogo)+" >");
            }   
        }
        System.out.println("");
        Star.espacios();
    }
    // metodo que escribe si tiene pelicula, y si tiene escribir cual
    public String tienePelicula (Cliente usario,ManejoPelicula catalogo){
        if (usario.getTienePeliculaPrestada()) {
            return Star.colores(3)+(catalogo.buscarPeliculaIDCliente(usario.getID())).nombre+Star.colores(0);
        }else{
            return Star.colores(1)+"No tiene ninguna"+Star.colores(0);
        }
    }

}
