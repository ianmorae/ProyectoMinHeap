/**
 * Clase Main.
 * Punto de entrada del programa. Su única responsabilidad es crear el menú
 * de consola e iniciar su ejecución.
 *
 * Curso: SOFT-10 Estructuras de Datos - Universidad CENFOTEC.
 * Tercera práctica de implementación de estructuras de datos: montículo mínimo.
 *
 * @author Ian Aarón Mora Espinoza
 */
public class Main {

    /**
     * Método principal de la aplicación.
     *
     * @param args argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        //Se crea el menú, el cual contiene internamente el montículo mínimo
        //sobre el que se realizan todas las operaciones.
        Menu menu = new Menu();

        //Se inicia el menú de consola; se ejecuta hasta que el usuario elige salir.
        menu.menu();
    }
}