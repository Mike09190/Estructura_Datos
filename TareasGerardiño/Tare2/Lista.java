import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T extends Comparable<T>> implements Listable<T> {

    // 🔹 Clase interna Nodo
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;

    // Constructor
    public Lista() {
        cabeza = null;
    }

    // 🔹 Agregar al final
    @Override
    public void agregar(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
    }

    // 🔹 Buscar elemento
    @Override
    public boolean contiene(T elem) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato.equals(elem)) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    // 🔹 Verificar si está vacía
    @Override
    public boolean estaVacio() {
        return cabeza == null;
    }

    // 🔹 Vaciar lista
    @Override
    public void vacia() {
        cabeza = null;
    }

    // 🔹 Obtener primer elemento
    @Override
    public T primerElemento() {
        if (cabeza == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        return cabeza.dato;
    }

    // 🔹 Eliminar elemento
    @Override
    public void eliminar(T elem) {
        if (cabeza == null) return;

        // Caso especial: eliminar cabeza
        if (cabeza.dato.equals(elem)) {
            cabeza = cabeza.siguiente;
            return;
        }

        Nodo actual = cabeza;

        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(elem)) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }
    
    // 🔹 Insertar elemento en el indice
    @Override
    public void insertar(int indice, T elem) {
        if(indice <0){
            throw new IndexOutOfBoundsException();
        }
        Nodo nuevo = new Nodo(elem);
        if(estaVacio()){
            return;
        }
        if(indice == 0){
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            return;
        }
        //Buscar el Nodo con el indice, con el indice empezando en 0
        int conta = 0;
        Nodo actual = cabeza;
        while(actual.siguiente!=null && conta<indice-1){
            actual = actual.siguiente;
            conta ++;
        }
        
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

     // 🔹 Hace la reversa de la Lista
    
    public void reversa() {
        if(estaVacio()){
            return;
        }
        Nodo anterior = null;
        Nodo actual = cabeza;
        Nodo sig = null;
        while(actual.siguiente !=null){
            sig = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = sig;
        }
        cabeza = anterior;
    }

     // 🔹 Hace la reversa de la Lista recursivamente
    
    public Nodo reversa(Nodo cabeza) {
        // 1. Caso base: si cabeza es null o el siguiente es null
        if (cabeza == null || cabeza.siguiente == null) {
            return cabeza;
        }
        //Recursividad
        Nodo nuevaCabeza = reversa(cabeza.siguiente);
        cabeza.siguiente.siguiente = cabeza;
        cabeza.siguiente = null;
        return nuevaCabeza;
    }

    // 🔹 Ordena la lista con el algoritmo BubbleSort

    public void bubbleSort() {
        if (cabeza == null) return;
            
            boolean huboCambio;

            do{
                huboCambio = false;
                Nodo actual = cabeza;
                while (actual.siguiente !=null) {
                    if(actual.dato.compareTo(actual.siguiente.dato)>0){
                        T temp = actual.dato;
                        actual.dato = actual.siguiente.dato;
                        actual.siguiente.dato = temp;
                        
                        huboCambio = true;
                    }
                    actual = actual.siguiente;
                }
            }while(huboCambio);
    }

    // 🔹 Iterador
    @Override
    public Iterator<T> iterador() {
        return new Iterator<T>() {

            private Nodo actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}