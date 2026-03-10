import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListasDoblementeLigadas<t> implements Listable<t>{
    /**
     * CLase auxiliar Nodo
     */
    private class Nodo{
        T dato;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T dato){
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }
    //Atributos
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    /**
     * Implementar métodos de la interfaz
     */
    public ListasDoblementeLigadas(){
        cabeza = null;
        cola = null;
        tamaño = 0;
    }
    
    @Override
    public void agregar(T elem){
        Nodo nuevo= new Nodo(elem);

        if(estaVacio()){
            cabeza = cola = nuevo;
        } else{
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
        }
        tamaño ++;
    }

    /**
     * Método para agregar un elemento por indice
     * @param T elemento por agregar
     * @param int i indice donde se va a colocar
     */
    public void agregar(int indice, T elem){
        if(indice <0 || indice > tamaño){
            throw new IndexOutOfBoundsException();
        }
        Nodo nuevo = new Nodo(elem);
        //Inicio
        if(indice == 0){
            if(estaVacio()){
                cabeza = cola = nuevo;
            }else{
                nuevo.siguiente = cabeza;
                cabeza.anterior = nuevo;
                cabeza = nuevo;
            }
        }else if(indice == tamaño){
            agregar(elem);
            return;
        }else{
            Nodo actual;
            if(indice <tamaño/2){
                actual= cabeza;
                for(int i= 0; i<indice; i++){
                    actual = actual.siguiente;
                }
            }else{
                actual = cola;
                for(int i= tamaño-1; i>indice; i++){
                    actual =actual.anterior;
                }
            }
            nuevo.anterior = actual.anterior;
            nuevo.siguiente = actual;
            actual.anterior.siguiente = nuevo;
            actual.anterior = nuevo;
        }
        tamaño ++;
    }
    @Override
    public boolean contiene(T elem){
        Nodo actual = cabeza;

        while(actual!=null){
            if(actual.dato.equals(elem)){
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean estaVacio(){
        return tamaño == 0;
    }

    @Override
    public void vaciar(){
        cabeza = cola = null;
        tamaño = 0;
    }
    @Override
    public T primerElemento(){
        if(estaVacio()){
            throw new NoSuchElementException();
        }
        return cabeza.dato;
    }

    @Override
    public void eliminar(T elem){
        Nodo actual = cabeza;

        while(actual!=null){
            if(actual.dato.equals(elem)){
                eliminarNodo(actual);
                return;
            }
            actual = actual.siguiente;
        }
    }
    private void eliminarNodo(Nodo nodo){
        if(nodo.equals(cabeza)){
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }else if(nodo == cola){
            cola = cola.anterior;
            cola.siguiente = null;
        }else{
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
        }
    }
    @Override
    public Iterator<t> iterador(){
        return new Iterator<t>() {
            private Nodo actual = cabeza;

            public boolean hasNext(){
                return actual != null;
            }

            public T next(){
                if(hasNext()){
                    throw new NoSuchElementException();
                }
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }


    




}