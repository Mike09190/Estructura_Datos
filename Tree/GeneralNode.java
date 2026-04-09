import java.util.ArrayList;
import java.util.List;

/**
 * Clase para eñ manejo de nodos en arboles generales
 * 
 * @version 9 de abril de 2026
 */

public class GeneralNode<T> {
    public T dato;
    public List<GeneralNode<T>> hijos;

    public GeneralNode(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return dato.toString();
    }

}