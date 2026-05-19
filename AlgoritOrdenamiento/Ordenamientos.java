/**
 * Clase utilitaria que contiene la implementacion de los algoritmos de
 * ordenamiento para arreglos nativos en Java
 */

public class Ordenamientos {

    /**
     * Metodo para ordenar usando BubbleSort
     * 
     * @param array por ordenar
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        boolean intercambio;
        int n = arr.length;

        do {
            intercambio = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    intercambio = true;
                }
            }
            n--;
        } while (intercambio);
    }

    /**
     * Metodo para ordenar usando Selection
     * 
     * @param array por ordenar
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            if (indiceMinimo != i) {
                int temp = arr[indiceMinimo];
                arr[indiceMinimo] = arr[i];
                arr[i] = temp;
            }
        }

    }

    /**
     * Metodo para ordenar usando Insertion
     * 
     * @param array por ordenar
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int actual = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > actual) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = actual;
        }

    }
}