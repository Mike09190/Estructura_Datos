import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de un Árbol Binario Completo.
 * 
 * En este tipo de árbol, los elementos se agregan de izquierda a derecha
 * por niveles, manteniendo el árbol lo más compacto posible.
 * 
 */
public class ArbolBinCom<T> extends ArbolBin<T> {

    private class Iterador implements Iterator<T> {
        private Queue<Vertice<T>> cola;

        /**
         * Inicializa el iterador agregando la raíz a la cola.
         */
        public Iterador() {
            cola = new Queue<>();
            if (raiz != null) {
                cola.agregar(raiz);
            }
        }

        @Override
        public boolean hasNext() {
            return !cola.estaVacia();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Vertice<T> v = cola.eliminar();

            if (v.izquierdo != null)
                cola.agregar(v.izquierdo);

            if (v.derecho != null)
                cola.agregar(v.derecho);

            return v.elemento;
        }
    }

    /**
     * Constructor que crea un árbol vacío.
     */
    public ArbolBinCom() {
        super();
    }

    /**
     * Agrega un elemento al arbol manteniendo la propiedad
     * de arbol binario completo.
     * 
     * Se inserta en el primer lugar disponible por niveles
     */
    public void agrega(T elemento) {
        Vertice<T> nuevo = nuevoVertice(elemento);

        if (raiz == null) {
            raiz = nuevo;
            elementos++;
            return;
        }

        Queue<Vertice<T>> cola = new Queue<>();
        cola.agregar(raiz);

        while (!cola.estaVacia()) {
            Vertice<T> actual = cola.eliminar();

            if (actual.izquierdo == null) {
                actual.izquierdo = nuevo;
                nuevo.padre = actual;
                elementos++;
                return;
            } else {
                cola.agregar(actual.izquierdo);
            }

            if (actual.derecho == null) {
                actual.derecho = nuevo;
                nuevo.padre = actual;
                elementos++;
                return;
            } else {
                cola.agregar(actual.derecho);
            }
        }
    }

    /**
     * Elimina un elemento del arbol.
     * 
     * Se intercambia con el ultimo elemento y luego se elimina.
     */
    public void elimina(T elemento) {
        Vertice<T> v = busca(elemento);
        if (v == null)
            return;

        // Encontrar último nodo
        Queue<Vertice<T>> cola = new Queue<>();
        cola.agregar(raiz);
        Vertice<T> ultimo = null;

        while (!cola.estaVacia()) {
            ultimo = cola.eliminar();

            if (ultimo.izquierdo != null)
                cola.agregar(ultimo.izquierdo);

            if (ultimo.derecho != null)
                cola.agregar(ultimo.derecho);
        }

        // Intercambiar elementos
        v.elemento = ultimo.elemento;

        // Eliminar ultimo nodo
        if (ultimo.padre != null) {
            if (ultimo.padre.izquierdo == ultimo)
                ultimo.padre.izquierdo = null;
            else
                ultimo.padre.derecho = null;
        } else {
            raiz = null;
        }

        elementos--;
    }

    @Override
    public int altura() {
        if (elementos == 0)
            return -1;
        return (int) Math.floor(Math.log(elementos) / Math.log(2));
    }

    /**
     * Regresa un iterador para recorrer el árbol.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }
}