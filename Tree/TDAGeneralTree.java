import java.util.List;

public interface TDAGeneralTree<T> {

    /**
     * Metodo que devuelve la raiz del arbol
     * 
     * @return node del arbol
     */
    public GeneralNode<T> raiz();

    /**
     * Metodo para agregar un elemnto al arbol
     * 
     * @param T    el dato para agregar
     * @param Node padre del nuevo elemento
     */
    public void agregar(T dato, GeneralNode<T> padre);

    /**
     * Metodo que devuelve todos los hijos de un node
     * 
     * @param nodo del arbol
     * @return List de nodes
     */
    public List<GeneralNode<T>> hijos(GeneralNode<T> nodo);

    /**
     * Metoodo para saber cuantos nodos hay
     * 
     * @return int tamano del arbol
     */
    public int tamano();

    /**
     * Metodo para saber la altura del arbol
     * 
     * @return int tamano del arbol
     */
    public int altura();

    /**
     * Metodo para saber si un arbol tiene elementos
     * 
     * @return boolean True si es Vacio, False si tiene uno o mas elementos
     */
    public boolean esVacio();

    /**
     * Metodo para vaciar el arbol
     */
    public void vaciar();

}
