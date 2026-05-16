import java.util.ArrayList;

/**
 * Implementación de una tabla de dispersión usando encadenamientos
 * 
 */

public class TablaDispersion<k,v>{
    private class HashNodo<k,v>{
        private k key;
        private v value;
        HashNodo<k,v> next;
        //Nodo interno para la lista ligada de la tabla
        public HashNodo(k key, v value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private ArrayList<HashNodo<k,v>> arregloCubetas;
    private int numCubetas; //El tamaño del arreglo digamos 
    private int size; //Número de elementos 

    /**
     * Método constructor sin parámetros
     */
    public TablaDispersion(){
        this.numCubetas = 11; //Número primo para eviar la mayoria de colisiones
        this.size = 0;
        this.arregloCubetas = new ArrayList<>(numCubetas);
        for(int i = 0; i<numCubetas; i++){
            this.arregloCubetas.add(null); //Que todas las cubetas tengan valor nulo
        }
    }

    /**
     * Método para obtener el tamaño de una tabla
     * @return int número de elementos guardados
     */
    public int getSize(){
        return size;
    }

    /**
     * Método para ver si la tabla esta vacía
     * @return boolean true si esta vacía, caso contrario false
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Método para la función de dispersión, usando modulo arit.
     * 
     * @return int índice del elemento
     * @param K la llave del elemento
     */
    private int getIndiceCubeta(k key){
        int hashCode = key.hashCode();
        //Comparación bit a bit para evitar que salgan negativos
        int indice = (hashCode & 0x7FFFFFFF) % numCubetas; //Aseguramos que el índice sea positivo
        return indice;
    }

    /**
     * Método para agregar elementos 
     * 
     * @param k key
     * @param v valor
     */
    public void agregar(k key, v value){
        int indice = getIndiceCubeta(key);
        HashNodo<k,v> cabeza = arregloCubetas.get(indice); //Guardamos la cabeza de la lista

        HashNodo<k,v> actual = cabeza;
        while(actual != null){
            if(actual.key.equals(key)){
                actual.value = value; //Si la llave ya existe, actualizamos el valor
                return;
            }
            actual = actual.next; //Avanzamos al siguiente nodo
        
        }
        size ++;
        //Si no encontramos la llave, agregamos un nuevo nodo a la lista
        HashNodo<k,v> nuevoNodo = new HashNodo<>(key,value);
        nuevoNodo.next = cabeza; //El siguiente nodo del nuevo agregado es la cabeza
        arregloCubetas.set(indice, nuevoNodo); //Actualizamos la cabeza de la lista en la cubeta correspondiente

        double alpha = (1.0 * size)/numCubetas; //Factor de carga 

        if(alpha > 0.7){
            System.out.println("Factor de carga alto: " + alpha);
        }
    }

    public v get(k key){
        int indice = getIndiceCubeta(key);
        HashNodo<k,v> cabeza = arregloCubetas.get(indice);
        HashNodo<k,v> actual = cabeza;
        while(actual !=null){
            if(actual.key.equals(key)){
                return actual.value;
            }
            actual = actual.next;
        }
        return null;

        
    }

}
