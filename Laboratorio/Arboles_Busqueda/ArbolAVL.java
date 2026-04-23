public class ArbolAVL<T extends Comparable<T>> implements TDAArbolAVL<T>{
    private class Nodo{
        T dato;
        int altura;
        Nodo izq;
        Nodo der;

        public Nodo(T dato){
            this.dato = dato;
            altura = 1;
            izq = null;
            der = null;
        }
    }

    private Nodo raiz;

    
    private int altura(Nodo n){
        return(n == null) ? 0: n.altura;
    }

    private int getFactorEquilibrio(Nodo n){
        return(n == null)? 0: altura(n.izq) - altura(n.der);
    }
    /**
     * Método para actualizar la altura de un nodo
     * @param n
     */
    private void actualizarAltura(Nodo n){
        n.altura = 1 + Math.max(altura(n.izq), altura(n.der));

    }
    /**
     * Método para rotar un nodo a la derecha
     * @param y Nodo a rotar
     * @return Nodo que es la nueva ráiz local
     */
    private Nodo RotarDerecha(Nodo y){
        Nodo x = y.izq;
        Nodo T2 = x.der;
        
        x.der = y;
        y.izq = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    /**
     * Método para rotar un Nodo a la izq
     * @param x Nodo que será rotado
     * @return Nodo que es la nueva raíz local
     */

    private Nodo RotarIzquierda(Nodo x){
        Nodo y = x.der;
        Nodo T2 = y.izq;

        // Realizar rotación
        y.izq = x.der;
        x.der = T2;

        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);

        // Retornar nueva raíz
        return y;
    }
    /**
     * Método para balancear un nodo después de una inserción o eliminación
     * @param nodo
     * @return Nodo balanceado
     */
    private Nodo balancear(Nodo nodo){
        actualizarAltura(nodo);
        int b = getFactorEquilibrio(nodo);

        if(b > 1 && getFactorEquilibrio(nodo.izq) >= 0){
            return RotarDerecha(nodo);
        }

        //Caso izq-der
        if(b > 1 && getFactorEquilibrio(nodo.der) >=0){
            nodo.izq = RotarIzquierda(nodo.izq);
            return RotarDerecha(nodo);
        }
        //Caso der-izq
        if(b<1 && getFactorEquilibrio(nodo.der) > 0){
            nodo.der = RotarDerecha(nodo.der);
            return RotarIzquierda(nodo);
        }
        return nodo;
    }

    @Override
    public void insertar(T dato){
        raiz = insertarRecursivo(raiz, dato);
    }

    private Nodo insertarRecursivo(Nodo nodo, T dato){
        if(nodo == null) return new Nodo(dato);

        int comparacion = dato.compareTo(nodo.dato);
        if(comparacion < 0){
            nodo.izq = insertarRecursivo(nodo.izq, dato);
        }else if(comparacion > 0){
            nodo.der = insertarRecursivo(nodo.der, dato);
        }else{
            return nodo;
        }

        return balancear(nodo);
    }

    @Override
    public void eliminar(T dato){
        raiz  = eliminarRecursivo(raiz, dato);
    }

    private Nodo eliminarRecursivo(Nodo nodo, T dato){
        if(nodo == null) return null;

        int comparacion = dato.compareTo(nodo.dato);

        if(comparacion < 0){
            nodo.izq = eliminarRecursivo(nodo.izq, dato);
        }else if(comparacion > 0){
            nodo.der = eliminarRecursivo(nodo.der, dato);
        }else{
            if(nodo.izq == null || nodo.der == null){
                nodo = (nodo.izq != null)? nodo.izq : nodo.der;

            }else{
                Nodo temp = obtenerMinimo(nodo.der);
                nodo.dato = temp.dato;
                nodo.der = eliminarRecursivo(nodo.der, temp.dato);
            }
        }
        if(nodo == null) return null;
        return balancear(nodo);
    }

    private Nodo obtenerMinimo(Nodo nodo){
        while(nodo.izq != null){
            nodo = nodo.izq;
        }
        return nodo;
    }

    @Override
    public int altura(){
        return altura(raiz);
    }

    @Override
    public boolean buscar(T dato){
        return buscarRecursivo(raiz, dato);
    }

    private boolean buscarRecursivo(Nodo actual, T dato){
        if(actual == null) return false;
        int comparacion = dato.compareTo(actual.dato);
        if(comparacion == 0) return true;

        return comparacion < 0 
        ? buscarRecursivo(actual.izq, dato) 
        : buscarRecursivo(actual.der, dato);
    }
}
