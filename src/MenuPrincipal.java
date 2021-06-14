package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println("<presione 7> Listado de clientes");
            System.out.println("<presione 8> Cerrar seccion");
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
                    registroPelicula();
                    break;
                case "5":
                    usario = buscarCliente();
                case "6":
                    (new Reporte(catalogo)).mostar();
                case "7":
                    manejoClientes.mostrarListadoClientes(catalogo);
                case "8":
                    usario = new Cliente("nombre", 0, 0);
                    usario = null;
                    break;
            }
        }
    }

    // para el menu devolver pelicula
    public void devolverPelciula() {
        boolean salir = true;
        String opcion = "";
        Star.espacios();

        do {
            if ((usario != null)) {
                if ((usario.getTienePeliculaPrestada())) {
                    // si puede devolver una pelicula
                    Star.espacios();
                    Pelicula quePoseee = (catalogo.buscarPeliculaIDCliente(usario.getID()));
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
                    salir = false;
                    Star.espacios();
                }
            } else {
                while (!(opcion.equalsIgnoreCase("SALIR"))) {
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
                                    if (usario != null) {
                                        opcion = "SALIR";
                                    }
                            break;
                        case "2":
                            usario = buscarCliente();
                            if (usario != null) {
                                opcion = "SALIR";
                            }
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
            // verficador si es numero lo que se ingresa
            String opcion = entrada.nextLine();
            Pattern patron = Pattern.compile("[0-9]+");
            Matcher matcher = patron.matcher(opcion);
            if (opcion.equals("0")) {
                salir = true;
            }else if (opcion.equals("Ordenar")) {
                catalogo.listadoPelicula = (new Ordenamiento()).ascendenteNombre(catalogo.listadoPelicula);
            }else if (matcher.matches()) {
                if ((usario != null) && !(opcion.trim().equals(""))) {
                    int ID = Integer.parseInt(opcion);
                    salir = catalogo.reservar(usario, ID);
                    opcion = "";
                } else {
                    System.out.println("Registre el cliente porfavor");
                    usario = registroCliente();
                }
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
        String ID = entrada.nextLine();
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(ID);
        if (matcher.matches()) {
            int dar = Integer.parseInt(ID);
            return manejoClientes.buscarClienteNombreID(nombre, dar);
        }
        return null;
    }

    public Cliente registroCliente() {
        Star.espacios();
        System.out.println("Ingrese su nombre");
        Star.espacios();
        String nombre = entrada.nextLine();
        Star.espacios();
        System.out.println("ingrese su telefono");
        Star.espacios();
        String telefono = entrada.nextLine();
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcherFecha = patron.matcher(telefono);
        if (matcherFecha.matches()) {
            int telefono1 = Integer.parseInt(telefono);
            return manejoClientes.registrar(nombre, telefono1);
        } else {
            return null;
        }
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
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcherFecha = patron.matcher(fecha);
        Matcher matcherID = patron.matcher(ID);
        if (((matcherFecha.matches())&&(matcherID.matches()))&&(catalogo.addPelicula(ID, nombre, fecha, categoria))) {
            Star.espacios();
            System.out.println("Fue agregado la pelicula");
            Star.espacios();
        } else {
            Star.espacios();
            System.out.println(Star.colores(5)+"Error no se puedo agreagar (no puede tener el mismo ID que otra)"+Star.colores(0));
            Star.espacios();
        }
    }
}
