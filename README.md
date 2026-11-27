# Práctica 3 — Montículo mínimo (Min-Heap) en Java

**Curso:** SOFT-10 Estructuras de Datos — Universidad CENFOTEC
**Estudiante:** Ian Aarón Mora Espinoza

Implementación de un montículo mínimo mediante un `ArrayList`, con un menú de consola que permite utilizar todas sus operaciones.

## Estructura del proyecto

| Archivo | Descripción |
|---|---|
| `src/Nodo.java` | Elemento del montículo: una clave numérica (prioridad) y un dato asociado. |
| `src/MinHeap.java` | Montículo mínimo: `insertar()`, `eliminarMin()`, `peek()`, `heapify()` y los métodos recursivos `upHeapify()` y `downHeapify()`. |
| `src/Menu.java` | Menú de consola con validación de entradas. |
| `src/Main.java` | Punto de entrada del programa. |
| `Dijkstra.txt` | Explicación de la aplicación de los montículos al algoritmo de Dijkstra. |

## Cómo ejecutar

**Desde VSCode:** abrir la carpeta del proyecto y ejecutar `Main.java` con el botón *Run*.

**Desde la terminal** (en la raíz del proyecto):

```
javac -d bin src/*.java
java -cp bin Main
```
