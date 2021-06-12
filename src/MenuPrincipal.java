package src;

import java.util.Scanner;

public class MenuPrincipal {
    public Scanner entradad = new Scanner(System.in);
    public ManejoCliente manejoClientes = new ManejoCliente();
    public ManejoPelicula catalogo = new ManejoPelicula();
    public Cliente usario = null;

    public MenuPrincipal(){
        boolean salir = false;
        // bucle para que no se salga facilmente el programa
        while (!salir) {
            System.out.println("Usario <"+manejoClientes.informacionCliente(usario)+">");
            System.out.println("<presione 1> Ver catalogo ");
            System.out.println("<presione 2> Devolver pelicula (NOTA:REQUISITOS TENER UNA PELICULA)");
            System.out.println("<presione 3> Registrase cliente");
            System.out.println("<presione 4> Registrase pelicula");
            System.out.println("<presione 5> Busacar cliente");
            System.out.println("<presione 0> Salir del programa");
            String opcion = entradad.nextLine();
                switch (opcion) {
                    case "0":
                            salir = true;
                         break;
                    case "1":
                        preguntarCualAlquilarPelicula();
                         break;
                    case "2":
                        devolverPelciula();
                         break;
                    case "3":
                        usario = registroCliente();
                         break;
                    case "4":
                         break;
                    case "5":
                        usario = buscarCliente();
                         break;
                 }  
        }
    }
    // para el menu devolver pelicula
    public void devolverPelciula(){
        boolean salir = false;
        String opcion = "";
        do {
            if ((usario != null)) {
                if ((usario.getTienePeliculaPrestada())) {
                    // si puede devolver una pelicula
                    
                } else {
                    System.out.println("El usario no tiene ninguna pelicula");
                    salir = true;
                }
            } else {
                while (!(opcion.equals("salir"))) {
                    System.out.println("<Presione 1> Para buscar cliente por medio del ID de la pelicula");
                    System.out.println("<Presione 2> Para buscar cliente en la base de datos");
                    System.out.println("<Presione 0> Para regresar al menu anterior");
                    opcion = entradad.nextLine();
                        switch (opcion) {
                            case "0":
                                opcion = "salir";
                                salir = true;
                                break;
                            case "1":
                                System.out.println("ingresse el ID de la pelicula");
                                int id = entradad.nextInt();
                                System.out.println(id);
                                usario = manejoClientes.buscarClienteNombreID("nombre", catalogo.reservoPelicula(id));
                                opcion = "salir";
                                break;
                            case "2":
                                usario = buscarCliente();
                                break;                    
                            default:
                                break;
                        }
                }
            } 
        } while (salir);
        
    }
    // para para preguntar cual va alquilar
    public void preguntarCualAlquilarPelicula (){
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("Usario <"+manejoClientes.informacionCliente(usario)+">");
            catalogo.verCatalogo();
            System.out.println("¿Cual desea alquilar? escriba su ID (el que le aparece en ID<ID>)");
            System.out.println("para regresar presiones 0");
            String opcion = entrada.nextLine();
            if (opcion.equals("0")) {
                salir = true;
            } else {
                if (usario != null) {
                    // convierto el opcion a int revisar por si errores aconsejable usar un try caht para capturar el error
                     int ID = Integer.parseInt(opcion);
                     salir = catalogo.reservar(usario, ID);
                } else {
                    System.out.println("Registre el cliente porfavor");
                    usario = registroCliente();
                }
            }
        }
    }
    public Cliente buscarCliente (){
        System.out.println("Ingrese el nombre");
        String nombre = entradad.nextLine();
        System.out.println("Ingrese el ID");
        int ID = entradad.nextInt();
        return manejoClientes.buscarClienteNombreID( nombre , ID);
    }
    public Cliente registroCliente(){

            System.out.println("Ingrese su nombre");
            String nombre = entradad.nextLine();
            System.out.println("ingrese su telefono");
            int telefono = entradad.nextInt();
            
        return manejoClientes.registrar(nombre, telefono);
    }
    public void registroPelicula (){
        System.out.println("Ingrese el ID de la pelicula");
        String ID = entradad.nextLine();
        System.out.println("Ingrese el nombre de la pelicula");
        String nombre = entradad.nextLine();
        System.out.println("Ingrese el año que fue postiada la pelicula");
        String fecha = entradad.nextLine();
        System.out.println("Ingrese la categoria o genero de la pelicula");
        String categoria = entradad.nextLine();
        
    }
}
