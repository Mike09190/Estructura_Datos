/**
     * Implementación de una pila usando listas
     * @author Mendéz Mendez
     */
import java.util.*;
public class Gerardozzz<T> implements Pilas<T> {
    // Atributos
    /**Lista auxiliar para las operaciones */
    private ArrayList<T> lista = new ArrayList<>();
    

    @Override
    public void push(T e){
        lista.add(0, e);
    }

    @Override
    public T pop() throws EmptyStackException{
        if(lista.isEmpty()){
            throw new EmptyStackException();
        }
        return lista.remove(0);
    }

    @Override
    public T top() throws EmptyStackException{
        if(lista.isEmpty()){
            throw new EmptyStackException();
        }
        return lista.get(0);
    }

    @Override 
    public boolean isEmpty(){
        return lista.isEmpty();
    }

    @Override 
    public void clear(){
        lista.clear();
    }

    @Override
    public void show(){
        Iterator<T> iterator = lista.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}