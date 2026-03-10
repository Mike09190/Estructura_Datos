import java.util.EmptyStackException;

public interface Pilas<T> {
    /**
     * Agrega un elemento en el tope
     * @param e elemento a agregar
     */
    public void push(T e);

    /**
     * Elimina y regresa el elemento en el tope de la lista
     * @return elemento en el tope
     * @throws EmptyStackException en caso de tener la pila vacia
     */
    public T pop() throws EmptyStackException;

    /**
     * Regresa el elemento tope de la lista sin eliminar
     * @return elemento en el tope
     * @throws EmptyStackException en caso de tener la pila vacia
     */
    public T top() throws EmptyStackException;

    /**
     * Verifica que la pila sea vacia
     * @return boolean true si es vacia, caso contrario false
     */
    public boolean isEmpty();

    /**
     * Limpia la pila
     */
    public void clear();

    /**
     * Permitir visualizar los elementos de la pila
     */
    public void show();

}
