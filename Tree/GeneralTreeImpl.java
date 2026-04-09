import java.util.List;

public class GeneralTreeImpl<T> implements TDAGeneralTree<T> {

    private GeneralNode<T> raiz;
    private int tamano;

    /**
     * Metodo constructor de un arbol general
     */
    public GeneralTreeImpl(T datoRaiz) {
        this.raiz = new GeneralNode<>(datoRaiz);
        this.tamano = 1;
    }

    @Override
    public GeneralNode<T> raiz() {
        return raiz;
    }

    @Override
    public void agregar(T dato, GeneralNode<T> padre) {
        if (padre == null) {
            throw new IllegalArgumentException("El nodo padre no puede ser null");
        }
        padre.hijos.add(new GeneralNode<>(dato));
        tamano++;
    }

    @Override
    public List<GeneralNode<T>> hijos(GeneralNode<T> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo a consultar es nodo");
        }
        return nodo.hijos;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public int altura() {
        if (raiz == null) {
            return 0;
        }
        return calcularAltura(raiz);
    }

    private int calcularAltura(GeneralNode<T> nodo) {
        // Caso base
        if (nodo.hijos.isEmpty()) {
            return 1;
        }
        int maximo = 0;
        for (GeneralNode<T> hijo : nodo.hijos) {
            maximo = Math.max(maximo, calcularAltura(hijo));
        }
        return 1 + maximo;
    }

    @Override
    public boolean esVacio() {
        return tamano == 0;
    }

    @Override
    public void vaciar() {
        raiz = null;
        tamano = 0;
    }

}