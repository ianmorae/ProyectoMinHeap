import java.util.ArrayList;

/**
 * Clase MinHeap.
 * Implementa un montículo mínimo (min-heap) mediante un ArrayList. En esta
 * estructura la clave de cada nodo es menor o igual que la de sus hijos
 * (se permiten claves duplicadas) y la cima, ubicada en el índice 0, siempre
 * contiene el nodo con la clave más pequeña.
 *
 * Para un nodo ubicado en el índice i:
 *  - Su hijo izquierdo está en el índice 2i + 1.
 *  - Su hijo derecho está en el índice 2i + 2.
 *  - Su padre está en el índice (i - 1) / 2.
 *
 * @author Ian Aarón Mora Espinoza
 */
public class MinHeap {

    //Atributos.
    private ArrayList<Nodo> monticulo;

    //Constructor.
    /**
     * Crea un montículo mínimo vacío.
     */
    public MinHeap() {
        monticulo = new ArrayList<>();
    }

    //Getters.
    public ArrayList<Nodo> getMonticulo() {
        return monticulo;
    }

    //Setters.
    public void setMonticulo(ArrayList<Nodo> nuevoMonticulo) {
        monticulo = nuevoMonticulo;
    }

    //Métodos principales.
    /**
     * Consulta el nodo que se encuentra en la cima del montículo sin eliminarlo.
     *
     * @return el nodo con la clave más pequeña, o null si el montículo está vacío.
     */
    public Nodo peek() {
        if (estaVacio()) {
            return null;
        }
        return monticulo.get(0);
    }

    /**
     * Crea un nuevo nodo con los datos recibidos, lo inserta al final del
     * arreglo y lo reubica mediante upHeapify() para mantener la propiedad
     * de ordenamiento del montículo.
     *
     * @param clave valor numérico que define la prioridad del nuevo nodo.
     * @param dato  información asociada al nuevo nodo.
     */
    public void insertar(int clave, String dato) {
        Nodo nuevoNodo = new Nodo(clave, dato);
        monticulo.add(nuevoNodo);
        //El nuevo nodo queda en la última posición del arreglo.
        upHeapify(monticulo.size() - 1);
    }

    /**
     * Sube recursivamente el nodo ubicado en el índice recibido, intercambiándolo
     * con su padre mientras su clave sea menor que la de este.
     *
     * @param indice posición del nodo que se debe reubicar.
     */
    private void upHeapify(int indice) {
        //Caso base: el nodo llegó a la cima, por lo que no tiene padre.
        if (indice == 0) {
            return;
        }

        int indicePadre = obtenerPadre(indice);

        //Si el nodo es menor que su padre, se intercambian y se repite el proceso
        //desde la nueva posición del nodo.
        if (esMenor(indice, indicePadre)) {
            intercambiar(indice, indicePadre);
            upHeapify(indicePadre);
        }
    }

    /**
     * Elimina y retorna la cima del montículo. Antes de retornarla, coloca el
     * último elemento del arreglo en la cima y lo reubica mediante downHeapify()
     * para restaurar la propiedad de ordenamiento.
     *
     * @return el nodo con la clave más pequeña, o null si el montículo está vacío.
     */
    public Nodo eliminarMin() {
        if (estaVacio()) {
            return null;
        }

        Nodo cima = monticulo.get(0);
        Nodo ultimo = monticulo.remove(monticulo.size() - 1);

        //Si después de remover el último nodo aún quedan elementos, este pasa a
        //ocupar la cima y se reubica hacia abajo.
        if (!estaVacio()) {
            monticulo.set(0, ultimo);
            downHeapify(0);
        }

        return cima;
    }

    /**
     * Baja recursivamente el nodo ubicado en el índice recibido, intercambiándolo
     * con su hijo más pequeño mientras la clave de alguno de sus hijos sea menor
     * que la suya.
     *
     * @param indice posición del nodo que se debe reubicar.
     */
    private void downHeapify(int indice) {
        int indiceIzquierdo = obtenerHijoIzquierdo(indice);
        int indiceDerecho = obtenerHijoDerecho(indice);
        int indiceMenor = indice;

        //Se busca cuál es el menor entre el nodo actual y sus hijos (si existen).
        if (indiceIzquierdo < monticulo.size() && esMenor(indiceIzquierdo, indiceMenor)) {
            indiceMenor = indiceIzquierdo;
        }
        if (indiceDerecho < monticulo.size() && esMenor(indiceDerecho, indiceMenor)) {
            indiceMenor = indiceDerecho;
        }

        //Caso recursivo: si un hijo es menor, se intercambian y se repite el proceso
        //desde la nueva posición del nodo. Si no, el nodo ya está en su lugar.
        if (indiceMenor != indice) {
            intercambiar(indice, indiceMenor);
            downHeapify(indiceMenor);
        }
    }

    /**
     * Convierte un arreglo potencialmente desordenado en un montículo mínimo
     * válido y reemplaza con él el contenido anterior del atributo monticulo.
     * Para ello aplica downHeapify() a cada nodo que tiene hijos, empezando por
     * el último de ellos y avanzando hacia la cima.
     *
     * @param arreglo lista de nodos que se desea convertir en montículo.
     */
    public void heapify(ArrayList<Nodo> arreglo) {
        //Se copia el arreglo recibido para no modificar la lista original.
        monticulo = new ArrayList<>(arreglo);

        //El último nodo con hijos es el padre del último elemento del arreglo.
        int ultimoConHijos = (monticulo.size() / 2) - 1;

        for (int i = ultimoConHijos; i >= 0; i--) {
            downHeapify(i);
        }
    }

    //Métodos auxiliares.
    /**
     * @param indice posición de un nodo.
     * @return índice del padre del nodo.
     */
    private int obtenerPadre(int indice) {
        return (indice - 1) / 2;
    }

    /**
     * @param indice posición de un nodo.
     * @return índice del hijo izquierdo del nodo.
     */
    private int obtenerHijoIzquierdo(int indice) {
        return (2 * indice) + 1;
    }

    /**
     * @param indice posición de un nodo.
     * @return índice del hijo derecho del nodo.
     */
    private int obtenerHijoDerecho(int indice) {
        return (2 * indice) + 2;
    }

    /**
     * Compara las claves de dos nodos del montículo.
     *
     * @param indiceA posición del primer nodo.
     * @param indiceB posición del segundo nodo.
     * @return true si la clave del primer nodo es menor que la del segundo.
     */
    private boolean esMenor(int indiceA, int indiceB) {
        return monticulo.get(indiceA).getClave() < monticulo.get(indiceB).getClave();
    }

    /**
     * Intercambia de posición dos nodos del montículo.
     *
     * @param indiceA posición del primer nodo.
     * @param indiceB posición del segundo nodo.
     */
    private void intercambiar(int indiceA, int indiceB) {
        Nodo temporal = monticulo.get(indiceA);
        monticulo.set(indiceA, monticulo.get(indiceB));
        monticulo.set(indiceB, temporal);
    }

    /**
     * @return true si el montículo no contiene nodos.
     */
    public boolean estaVacio() {
        return monticulo.isEmpty();
    }

    /**
     * @return cantidad de nodos almacenados en el montículo.
     */
    public int getTamano() {
        return monticulo.size();
    }

    //Métodos para mostrar el montículo.
    /**
     * Genera una representación del montículo como arreglo, indicando el
     * índice que ocupa cada nodo.
     *
     * @return texto con el contenido del arreglo.
     */
    public String mostrarArreglo() {
        String resultado = "";
        for (int i = 0; i < monticulo.size(); i++) {
            resultado += "[" + i + "] " + monticulo.get(i) + "   ";
        }
        return resultado;
    }

    /**
     * Genera una representación del montículo en forma de árbol, mostrando
     * los nodos que pertenecen a cada nivel (el nivel 0 corresponde a la cima).
     *
     * @return texto con los nodos organizados por niveles.
     */
    public String mostrarPorNiveles() {
        String resultado = "";
        int inicioNivel = 0;
        int nodosEnNivel = 1;
        int nivel = 0;

        //Cada nivel del árbol tiene el doble de nodos que el nivel anterior.
        while (inicioNivel < monticulo.size()) {
            int finNivel = Math.min(inicioNivel + nodosEnNivel, monticulo.size());
            resultado += "Nivel " + nivel + ": ";
            for (int i = inicioNivel; i < finNivel; i++) {
                resultado += monticulo.get(i) + " ";
            }
            resultado += "\n";

            inicioNivel = finNivel;
            nodosEnNivel *= 2;
            nivel++;
        }
        return resultado;
    }
}