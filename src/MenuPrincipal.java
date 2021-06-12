package src;

import java.util.Scanner;

public class MenuPrincipal {
    public Scanner entradad = new Scanner(System.in);
    public ManejoCliente manejoClientes = new ManejoCliente();
    public ManejoPelicula catalogo = new ManejoPelicula();

    public MenuPrincipal(){
        boolean salir = false;
        Cliente usario = null;
        // bucle para que no se salga facilmente el programa
        while (!salir) {
            System.out.println("Usario <"+manejoClientes.informacionCliente(usario)+">");
            System.out.println("<presione 1> Ver catalogo ");
            System.out.println("<presione 2> Devolver pelicula (NOTA:REQUISITOS TENER UNA PELICULA)");
            System.out.println("<presione 3> Registrase cliente");
            System.out.println("<presione 4> Busacar cliente");
            System.out.println("<presione 0> Salir del programa");
            String opcion = entradad.nextLine();
                switch (opcion) {
                    case "0":
                            salir = true;
                         break;
                    case "1":
                        catalogo.verCatalogo();
                        preguntarCualAlquilarPelicula(usario);
                         break;
                    case "2":
                        devolverPelciula(usario);
                         break;
                    case "3":
                        usario = registro();
                         break;
                    case "4":
                        usario = buscarCliente();
                         break;
                    default:
                         break;
                 }  
        }
    }
    public void devolverPelciula(Cliente usario){
        Scanner entrada = new Scanner(System.in);
        String entregar = "si";
        String opcion = "";
        do {
            if ((usario != null)) {
                if ((usario.getTienePeliculaPrestada())) {
                    // si puede devolver una pelicula
                    
                } else {
                    System.out.println("El usario no tiene ninguna pelicula");
                    entregar = "si";
                }
            } else {
                
                while (!(opcion.equals("salir1"))) {
                    System.out.println("<Presione 1> Para buscar cliente por medio del ID de la pelicula");
                    System.out.println("<Presione 2> Para buscar cliente en la base de datos");
                    System.out.println("<Escriba salir> Para regresar al menu anterior");
                    opcion = entrada.nextLine();
                        switch (opcion) {
                            case "0":
                                opcion = "salir";
                                entregar = "si";
                                break;
                            case "1":
                                System.out.println("ingresse el ID de la pelicula");
                                opcion = "salir1";
                                entregar = "no";
                                break;
                            case "2":
                                usario = buscarCliente();
                                break;                    
                            default:
                                break;
                        }
                }
            } 
        } while (entregar.equals("si"));
        
    }
    public void preguntarCualAlquilarPelicula (Cliente usario){
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
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
                    System.out.println("Ingrese un cliente");
                    usario = registro();
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
    public Cliente registro(){

            System.out.println("Ingrese su nombre");
            String nombre = entradad.nextLine();
            System.out.println("ingrese su telefono");
            int telefono = entradad.nextInt();
            
        return manejoClientes.registrar(nombre, telefono);
    }
}
