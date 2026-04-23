/**
 * Interfaz que define las funciones principales de un árbol AVL.
 */

public interface TDAArbolAVL<T extends Comparable<T>> {
    /**
     * Inserta un nuevo nodo en el árbol AVL.
     * @param dato El dato a insertar.
     */
    public void insertar(T dato);
    /**
     * Elimina un nodo del árbol AVL.
     * @param dato El dato a eliminar.
     */
    public void eliminar(T dato);
    /**
     * Busca un nodo en el árbol AVL.
     * @param dato El dato a buscar.
     * @return true si el nodo se encuentra, false en caso contrario.
     */
    public boolean buscar(T dato);
    /**
     * Devuelve la altura de un árbol
     * @return int altura
     */
    public int altura();
    
}