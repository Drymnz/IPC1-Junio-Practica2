package src;

public class Star {
        public static final int CUANTOS_DATOS_ALMACENA_EL_PROGRAMA = 20;

        public static void main(String[] args) {
                // se inicia el programa
                MenuPrincipal ejecutar = new MenuPrincipal();
        }

        public static String colores(int numero) {
                switch (numero) {
                        case 0:
                                return "\u001B[0m";// Reset
                        case 1:
                                return "\033[32m";// colorVerde
                        case 2:
                                return ("\033[36m");// colorCyan
                        case 3:
                                return ("\033[34m");// colorAzul
                        case 4:
                                return ("\033[33m");// colorAmarillo
                        case 5:
                                return ("\033[31m");// colorRojo

                        default:
                                return "";
                }
        }
        public static void espacios(){
                for (int i = 0; i < 100; i++) {
                        System.out.print(colores(4)+"#"+colores(0));
                }
                System.out.println("");
        }
}
