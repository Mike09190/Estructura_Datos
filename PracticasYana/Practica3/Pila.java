
import java.util.*;

public class Pila<T> implements Stack<T>{
    /**
     * Clase privada Nodo para manejar los nodos de la lista
     */
    private class Node{
        //Atributos
         T dato;
         Node nodoAbajo;

        private Node(T dato){
            this.dato = dato;
            this.nodoAbajo = null;
        }
    }
    //Atributos necesarios de la pila
    private Node tope;

    /**
     * Constructor de la pila sin param
     */
    public Pila(){
        this.tope = null;
    }



    @Override
    public void push(T elem){
       Node nuevo = new Node(elem);
       
       nuevo.nodoAbajo = tope;
       tope = nuevo;
    }

    @Override
    public T pop() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T devolver = tope.dato;
        tope = tope.nodoAbajo;
        
        return devolver;
    }

    @Override
    public T top() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return tope.dato;
    }

    @Override
    public boolean isEmpty(){
        return tope == null;
    }

    @Override
    public void clear(){
        tope = null;
    }

    @Override
    public void show(){
        if(isEmpty()){
            System.out.println("La pila esta vacía");
            return;
        }
        Node aux = tope;
        imprimirPila(aux);
    }
    private void imprimirPila(Node aux){
        if (aux == null) {
        return;
        }

        imprimirPila(aux.nodoAbajo);
    
        // 2. Al regresar de la recursión, imprimimos el dato
        
        System.out.print(aux.dato + "  ");
    }
}
