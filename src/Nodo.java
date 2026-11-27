/**
 * Clase Nodo.
 * Representa un elemento del montículo mínimo. Cada nodo almacena una clave
 * numérica, que determina su posición (prioridad) dentro del montículo, y un
 * dato de texto asociado a esa clave.
 *
 * @author Ian Aarón Mora Espinoza
 */
public class Nodo {

    //Atributos.
    private int clave;
    private String dato;

    //Constructor.
    /**
     * Crea un nuevo nodo con la clave y el dato indicados.
     *
     * @param clave valor numérico que define la prioridad del nodo.
     * @param dato  información asociada al nodo.
     */
    public Nodo(int clave, String dato) {
        this.clave = clave;
        this.dato = dato;
    }

    //Getters.
    public int getClave() {
        return clave;
    }

    public String getDato() {
        return dato;
    }

    //Setters.
    public void setClave(int nuevaClave) {
        clave = nuevaClave;
    }

    public void setDato(String nuevoDato) {
        dato = nuevoDato;
    }

    //Métodos.
    /**
     * Devuelve una representación en texto del nodo con el formato (clave, dato).
     *
     * @return texto que representa al nodo.
     */
    @Override
    public String toString() {
        return "(" + clave + ", " + dato + ")";
    }
}