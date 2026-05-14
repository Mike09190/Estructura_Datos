/**
 * Clase para manejar Mini-heaps
 */
public class MinHeap {

    private int[] heap;
    private int size;
    private int capacidad;

    /**
     * Metodo constructor
     * 
     * @param capacidad capacidad de nuestro heap
     */
    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        this.size = 0;
        this.heap = new int[capacidad];
    }

    /**
     * Metodo para obtener indice del padre
     * 
     * @param i elemento al que estamos buscando al padre
     * 
     * @return int el indice del padre
     */
    private int obtenerIndicePadre(int i) {
        return (i - 1) / 2;
    }

    /**
     * Metodo para obtener el indice del hijo izq de un elemento
     * 
     * @param i elemento al que se le busca el hijo izq
     * 
     * @return int del hijo izquierdo
     */
    private int obtenerHijoIzquierdo(int i) {
        return (2 * i) + 1;
    }

    /**
     * Metodo para obtener el indice del hijo der de un elemento
     * 
     * @param i elemento al que se le busca el hijo der
     * 
     * @return int del hijo derecho
     */
    private int obtenerHijoDerecho(int i) {
        return (2 * i) + 2;
    }

    /**
     * Metodo para insertar un nuevo elemento
     * 
     * @param valor el elemento a insertar
     */
    public void insertar(int valor) {
        if (size == capacidad) {
            System.out.println("Error, el monticulo esta lleno");
            return;
        }
        heap[size] = valor;
        size++;

        bubbleUp(size - 1);
    }

    /**
     * Metodo para reordenar un valor hacia arriba
     * 
     * @param i indice del valor a acomodar
     */
    private void bubbleUp(int i) {
        while (i != 0 && heap[i] < heap[obtenerIndicePadre(i)]) {
            swap(i, obtenerIndicePadre(i));
            i = obtenerIndicePadre(i);
        }
    }

    /**
     * Metodo para intercambiar dos valores
     * 
     * @param i indice uno
     * @param j indice dos
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Metodo eliminar y devolver el valor minimo
     * 
     * @return int el valor del nodo raiz, del nodo que estamos eliminando
     */
    public int eliminarMinimo() {
        if (size <= 0)
            return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return heap[0];
        }
        int raiz = heap[0];
        heap[0] = heap[size - 1];
        size--;

        bubbleDown(0);
        return raiz;
    }

    /**
     * Metodo para ordenar hacia abajo un elemento
     * 
     * @param i indice del valor a reordenar
     */
    public void bubbleDown(int i) {
        int izq = obtenerHijoIzquierdo(i);
        int der = obtenerHijoDerecho(i);
        int menor = i;

        if (izq < size && heap[izq] < heap[i]) {
            menor = izq;
        }
        if (der < size && heap[der] < heap[menor]) {
            menor = der;
        }

        if (menor != i) {
            swap(i, menor);
            bubbleDown(menor);
        }
    }

    /**
     * Metodo para imprimir los elementos actuales del heap
     */
    public void imprimirArreglo() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}