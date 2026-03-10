import java.util.Iterator;

public interface Listable<T> {
    /**
     * Método para agregar elementos a una lista
     * @param T elemento a agregar
     */
    public void agregar(T elem);

    /**
     * Método para buscar elementos de una lista
     * @param T elemento a buscar
    * @return boolean true si lo encuentra
     */
    public boolean contiene(T elem);

    /**
     * Método para verificar si la lista esta vacía
     * @return boolean true si esta vacía, false si tiene algún elemento
     */
    public boolean listaVacia();

    /**
     * Método para vaciar la lista
     */
    public void vaciar();
    
    /**
     * Método para encontrar el primer elemento de una lista
     * @return T elemento en primera posición
     */
    public T primerElemento();

    /**
     * Método para eliminar un elemento de la lista
     * 
     * @param T elemento a eliminar
     */
    public void eliminar(T elem);

    /**
     * Método para sustituir un elemento de la lista por otro
     * @param T elemento de origin
     * @param T nuevo elemento
     */
    public void sustituirElemento(T origen, T nuevo);

    /**
     * Iterador para recorrer la lista
     * @return iterator
     */
    Iterator<T> iterador();


}
