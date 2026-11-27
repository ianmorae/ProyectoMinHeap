import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Menu.
 * Ofrece un menú de consola que permite al usuario utilizar todas las
 * operaciones del montículo mínimo: insertar, eliminar el mínimo, consultar
 * la cima (peek), construir un montículo a partir de un arreglo (heapify)
 * y mostrar su contenido.
 *
 * @author Ian Aarón Mora Espinoza
 */
public class Menu {

    //Atributos.
    private MinHeap monticulo;
    private Scanner entrada;

    //Constructor.
    /**
     * Crea el menú con un montículo vacío y un lector de entrada por consola.
     */
    public Menu() {
        monticulo = new MinHeap();
        entrada = new Scanner(System.in);
    }

    //Métodos.
    /**
     * Muestra el menú principal y ejecuta la opción elegida por el usuario
     * hasta que este decida salir.
     */
    public void menu() {
        int opcion;

        System.out.println("==============================================");
        System.out.println("     IMPLEMENTACIÓN DE UN MONTÍCULO MÍNIMO    ");
        System.out.println("==============================================");

        do {
            mostrarOpciones();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    insertarNodo();
                    break;
                case 2:
                    eliminarMinimo();
                    break;
                case 3:
                    consultarMinimo();
                    break;
                case 4:
                    construirDesdeArreglo();
                    break;
                case 5:
                    mostrarMonticulo();
                    break;
                case 0:
                    System.out.println("\nGracias por utilizar el programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n[!] Opción inválida. Digite un número del 0 al 5.");
            }
        } while (opcion != 0);

        entrada.close();
    }

    /**
     * Imprime las opciones disponibles del menú principal.
     */
    private void mostrarOpciones() {
        System.out.println("\n---------------- MENÚ PRINCIPAL ----------------");
        System.out.println("Nodos en el montículo: " + monticulo.getTamano());
        System.out.println("1. Insertar un nodo");
        System.out.println("2. Eliminar el mínimo (eliminarMin)");
        System.out.println("3. Consultar el mínimo (peek)");
        System.out.println("4. Construir montículo desde un arreglo (heapify)");
        System.out.println("5. Mostrar el montículo");
        System.out.println("0. Salir");
        System.out.println("------------------------------------------------");
    }

    /**
     * Opción 1: solicita la clave y el dato de un nuevo nodo y lo inserta
     * en el montículo.
     */
    private void insertarNodo() {
        System.out.println("\n--- Insertar un nodo ---");
        int clave = leerEntero("Digite la clave (número entero): ");
        String dato = leerTexto("Digite el dato asociado: ");

        monticulo.insertar(clave, dato);
        System.out.println("\n[OK] Nodo " + new Nodo(clave, dato) + " insertado correctamente.");
        mostrarMonticulo();
    }

    /**
     * Opción 2: elimina la cima del montículo y muestra el nodo eliminado.
     */
    private void eliminarMinimo() {
        System.out.println("\n--- Eliminar el mínimo ---");
        Nodo eliminado = monticulo.eliminarMin();

        if (eliminado == null) {
            System.out.println("[!] El montículo está vacío, no hay nodos para eliminar.");
            return;
        }

        System.out.println("[OK] Se eliminó la cima: " + eliminado);
        mostrarMonticulo();
    }

    /**
     * Opción 3: muestra la cima del montículo sin eliminarla.
     */
    private void consultarMinimo() {
        System.out.println("\n--- Consultar el mínimo ---");
        Nodo cima = monticulo.peek();

        if (cima == null) {
            System.out.println("[!] El montículo está vacío.");
        } else {
            System.out.println("La cima del montículo es: " + cima);
        }
    }

    /**
     * Opción 4: construye un arreglo de nodos (ingresado manualmente o generado
     * de forma aleatoria) y lo convierte en un montículo válido mediante heapify().
     * El contenido anterior del montículo se reemplaza.
     */
    private void construirDesdeArreglo() {
        System.out.println("\n--- Construir montículo desde un arreglo (heapify) ---");
        System.out.println("Nota: el contenido actual del montículo será reemplazado.");
        System.out.println("1. Ingresar los nodos manualmente");
        System.out.println("2. Generar nodos aleatorios");
        int forma = leerEnteroEnRango("Seleccione una opción: ", 1, 2);

        int cantidad = leerEnteroEnRango("¿Cuántos nodos tendrá el arreglo? (1 - 30): ", 1, 30);
        ArrayList<Nodo> arreglo;

        if (forma == 1) {
            arreglo = ingresarArregloManual(cantidad);
        } else {
            arreglo = generarArregloAleatorio(cantidad);
        }

        System.out.println("\nArreglo recibido (desordenado):");
        for (int i = 0; i < arreglo.size(); i++) {
            System.out.print("[" + i + "] " + arreglo.get(i) + "   ");
        }
        System.out.println();

        monticulo.heapify(arreglo);
        System.out.println("\n[OK] El arreglo se convirtió en un montículo mínimo válido.");
        mostrarMonticulo();
    }

    /**
     * Solicita al usuario la clave y el dato de cada nodo del arreglo.
     *
     * @param cantidad número de nodos que se van a ingresar.
     * @return lista con los nodos ingresados.
     */
    private ArrayList<Nodo> ingresarArregloManual(int cantidad) {
        ArrayList<Nodo> arreglo = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nNodo " + (i + 1) + " de " + cantidad + ":");
            int clave = leerEntero("  Clave (número entero): ");
            String dato = leerTexto("  Dato asociado: ");
            arreglo.add(new Nodo(clave, dato));
        }
        return arreglo;
    }

    /**
     * Genera un arreglo de nodos con claves aleatorias entre 1 y 99.
     *
     * @param cantidad número de nodos que se van a generar.
     * @return lista con los nodos generados.
     */
    private ArrayList<Nodo> generarArregloAleatorio(int cantidad) {
        ArrayList<Nodo> arreglo = new ArrayList<>();
        Random aleatorio = new Random();

        for (int i = 0; i < cantidad; i++) {
            int clave = aleatorio.nextInt(99) + 1;
            arreglo.add(new Nodo(clave, "Elemento " + (i + 1)));
        }
        return arreglo;
    }

    /**
     * Opción 5: muestra el montículo como arreglo y en forma de árbol por niveles.
     */
    private void mostrarMonticulo() {
        if (monticulo.estaVacio()) {
            System.out.println("\n[!] El montículo está vacío.");
            return;
        }

        System.out.println("\nMontículo como arreglo:");
        System.out.println(monticulo.mostrarArreglo());
        System.out.println("\nMontículo como árbol (por niveles):");
        System.out.print(monticulo.mostrarPorNiveles());
    }

    //Métodos de lectura y validación de datos.
    /**
     * Lee un número entero desde la consola, repitiendo la solicitud hasta
     * que el usuario digite un valor válido.
     *
     * @param mensaje texto que se muestra al usuario.
     * @return el número entero digitado.
     */
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("[!] Valor inválido. Debe digitar un número entero.");
            }
        }
    }

    /**
     * Lee un número entero que se encuentre dentro de un rango determinado.
     *
     * @param mensaje texto que se muestra al usuario.
     * @param minimo  valor mínimo permitido.
     * @param maximo  valor máximo permitido.
     * @return el número entero digitado dentro del rango.
     */
    private int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        int numero = leerEntero(mensaje);
        while (numero < minimo || numero > maximo) {
            System.out.println("[!] Debe digitar un número entre " + minimo + " y " + maximo + ".");
            numero = leerEntero(mensaje);
        }
        return numero;
    }

    /**
     * Lee un texto no vacío desde la consola.
     *
     * @param mensaje texto que se muestra al usuario.
     * @return el texto digitado.
     */
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String texto = entrada.nextLine().trim();
        while (texto.isEmpty()) {
            System.out.println("[!] El dato no puede quedar vacío.");
            System.out.print(mensaje);
            texto = entrada.nextLine().trim();
        }
        return texto;
    }
}