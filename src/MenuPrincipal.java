package src;

import java.util.Scanner;

public class MenuPrincipal {
    public Scanner entrada = new Scanner(System.in);
    public ManejoCliente manejoClientes = new ManejoCliente();
    public ManejoPelicula catalogo = new ManejoPelicula();
    public Cliente usario = null;

    public MenuPrincipal() {
        boolean salir = false;
        // bucle para que no se salga facilmente el programa
        while (!salir) {
            Star.espacios();
            System.out.println("Usario <" + manejoClientes.informacionCliente(usario) + ">");
            System.out.println("<presione 1> Ver catalogo ");
            System.out.println("<presione 2> Devolver pelicula (NOTA:REQUISITOS TENER UNA PELICULA)");
            System.out.println("<presione 3> Registrase cliente");
            System.out.println("<presione 4> Registrase pelicula");
            System.out.println("<presione 5> Busacar cliente");
            System.out.println("<presione 6> Reportes");
            System.out.println("<presione 7> Cerrar seccion");
            System.out.println("<presione 0> Salir del programa");
            Star.espacios();
            String opcion = entrada.nextLine();
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
                case "6":
                    //(new Reporte(catalogo)).
                case "7":
                    usario = new Cliente("nombre", 0, 0);
                    usario = null;
                    break;
            }
        }
    }

    // para el menu devolver pelicula
    public void devolverPelciula() {
        boolean salir = false;
        String opcion = "";
        Star.espacios();

        do {
            if ((usario != null)) {
                if ((usario.getTienePeliculaPrestada())) {
                    // si puede devolver una pelicula
                    Star.espacios();
                    Pelicula quePoseee = (catalogo.buscarPeliculaIDCliente(usario.ID));
                    System.out.println("Usario <" + manejoClientes.informacionCliente(usario) + ">");
                    System.out.println("¿Devolvio la pelicula? --> <" + Star.colores(3) + quePoseee.nombre
                            + Star.colores(0) + "> (NOTA: Porfavor escribir SI o NO para responder)");
                    Star.espacios();
                    String afirmar = entrada.nextLine();
                    if (afirmar.equalsIgnoreCase("SI")) {
                        usario.setTienePeliculaPrestada(false);
                        quePoseee.setDisponible(true);
                    }

                } else {
                    Star.espacios();
                    System.out.println(Star.colores(2) + "El usario no tiene ninguna pelicula" + Star.colores(0));
                    salir = true;
                    Star.espacios();
                }
            } else {
                while (!(opcion.equals("SALIR"))) {
                    System.out.println("<Presione 1> Para buscar cliente por medio del ID de la pelicula");
                    System.out.println("<Presione 2> Para buscar cliente en la base de datos");
                    System.out.println("<Escriba SALIR> Para regresar al menu anterior");
                    Star.espacios();
                    opcion = entrada.nextLine();
                    switch (opcion) {
                        case "SALIR":
                            salir = false;
                            break;
                        case "1":
                            Star.espacios();
                            System.out.println("ingresse el ID de la pelicula");
                            Star.espacios();
                            usario = manejoClientes.buscarClienteNombreID("",
                                    catalogo.reservoPelicula(entrada.nextInt()));
                            opcion = "SALIR";
                            break;
                        case "2":
                            usario = buscarCliente();
                            break;
                    }
                }
            }
        } while (salir);

    }

    // para para preguntar cual va alquilar
    public void preguntarCualAlquilarPelicula() {
        boolean salir = false;
        while (!salir) {
            Star.espacios();
            System.out.println("Usario <" + manejoClientes.informacionCliente(usario) + ">");
            catalogo.verCatalogo();
            System.out.println("<Escriba el ID> ¿Cual desea alquilar?(NOTA:el que le aparece en ID<ID>)");
            System.out.println("<Escriba Ordenar > Pordenar de forma ascendente respecto al nombre");
            System.out.println("<Presione 0 > Para regresar");
            Star.espacios();
            String opcion = entrada.nextLine();
            switch (opcion) {
                case "0":
                    salir = true;
                    break;
                case "Ordenar":
                    catalogo.listadoPelicula = (new Ordenamiento()).ascendenteNombre(catalogo.listadoPelicula);
                    break;
                default:
                    if ((usario != null) && !(opcion.trim().equals(""))) {
                        // convierto el opcion a int revisar por si errores aconsejable usar un try caht
                        // para capturar el error
                        int ID = Integer.parseInt(opcion);
                        salir = catalogo.reservar(usario, ID);
                    } else {
                        System.out.println("Registre el cliente porfavor");
                        usario = registroCliente();
                    }
                    break;
            }
        }
    }

    public Cliente buscarCliente() {
        Star.espacios();
        System.out.println("Ingrese el nombre");
        Star.espacios();
        String nombre = entrada.nextLine();
        Star.espacios();
        System.out.println("Ingrese el ID");
        Star.espacios();
        int ID = entrada.nextInt();
        return manejoClientes.buscarClienteNombreID(nombre, ID);
    }

    public Cliente registroCliente() {
        Star.espacios();
        System.out.println("Ingrese su nombre");
        Star.espacios();
        String nombre = entrada.nextLine();
        Star.espacios();
        System.out.println("ingrese su telefono");
        Star.espacios();
        int telefono = entrada.nextInt();

        return manejoClientes.registrar(nombre, telefono);
    }

    public void registroPelicula() {
        Star.espacios();
        System.out.println("Ingrese el ID de la pelicula (NOTA: SOLO NUMERO PORFAVOR)");
        Star.espacios();
        String ID = entrada.nextLine();
        Star.espacios();
        System.out.println("Ingrese el nombre de la pelicula");
        Star.espacios();
        String nombre = entrada.nextLine();
        Star.espacios();
        System.out.println("Ingrese el año que fue postiada la pelicula (NOTA: SOLO NUMERO PORFAVOR)");
        Star.espacios();
        String fecha = entrada.nextLine();
        Star.espacios();
        System.out.println("Ingrese la categoria o genero de la pelicula");
        Star.espacios();
        String categoria = entrada.nextLine();
        if (catalogo.addPelicula(ID, nombre, fecha, categoria)) {
            Star.espacios();
            System.out.println("Fue agregado la pelicula");
            Star.espacios();
        } else {
            Star.espacios();
            System.out.println("Error no se puedo agreagar (no puede tener el mismo ID que otra)");
            Star.espacios();
        }
    }
}
