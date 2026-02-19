import java.util.Iterator;
import java.util.NoSuchElementException;
public class Lista<T> implements Listable<T> {
    /**
     * Clase privada para agregar nodos
     */
    private class Nodo {
        T datos;
        Nodo siguiente;
        
        public Nodo(T datos){
            this.datos = datos;
            this.siguiente = null;
        }
    }
    //Atributos de la lista
    private Nodo cabeza;
    /**
     * Constructor de listas (Por defecto)
     */
    public Lista(){
        cabeza = null;
    }

    /**
     * Constructor por parámetros
     * @param T datos
     */
    public Lista(T dato){
        this.cabeza = new Nodo(dato);
    }

    @Override
    public void agregar(T elem){
        Nodo nuevo = new Nodo(elem);

        if(listaVacia()){
            cabeza = nuevo;
        }else{
            Nodo actual = cabeza;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }
    @Override
    public boolean contiene(T elem){
        Nodo actual = cabeza;
        while(actual !=null){
            if(actual.datos.equals(elem)){
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    @Override
    public boolean listaVacia(){
        return cabeza == null;
    }

    @Override
    public void vaciar(){
        cabeza = null;
    }

    @Override
    public T primerElemento(){
        if(listaVacia()){
            throw new NoSuchElementException("Lista vacia");
        }
        return cabeza.datos;
    }

    @Override
    public void eliminar(T elem){
        if(listaVacia()  || !contiene(elem)){
            throw new NoSuchElementException("Lista Vacia");
        }
        if(cabeza.datos.equals(elem)){
            cabeza = cabeza.siguiente;
        }

        Nodo actual = cabeza;

        while (actual.siguiente != null) {
            if(actual.siguiente.datos.equals(elem)){
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public void sustituirElemento(T origen, T nuevo){
        Nodo actual = cabeza;

        while(actual != nuevo){
            if(actual.datos.equals(origen)){
                actual.datos = nuevo;
                return;
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public Iterator<T> iterador(){
        return new iteradorLista();
        
    } 

    private class iteradorLista implements Iterator<T>{
        private Nodo actual = cabeza;

        @Override
        public boolean hasNext(){
            return actual.siguiente != null;
        }

        @Override
        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException("Chispasss");
            }
            T dato = actual.datos; 
            actual = actual.siguiente;
            return dato;
        }
    }
}
