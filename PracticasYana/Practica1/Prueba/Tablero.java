

public class Tablero {
    private int[][] tablero; 
    /**
     * Constructor sin parametros
     */
    public Tablero() {
        this.tablero = new int[][]{
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        };
    }

    /**
     * método para crear el tablero 5x5 en blanco con las pistas
     * @return int[][]
     */
    public int[][] crearTablero(){      
        
        return this.tablero;
    }
    
    /**
     * método para ver el tablero con la solución, junto con las pistas
     */
    public int[][] tableroSolucion(){
        int[][] tablero = {
            {1,0,1,1,0},
            {1,1,1,0,0},
            {1,1,0,0,1},
            {0,1,0,1,1},
            {1,0,0,1,0}
        };
        return tablero;
    }
    /**
     * Método para obtener las pistas de las filas para cualquir matriz, no se 
     * porque lo quieren asi pero ya ni pedo
     * @return String[],
     * @param int[][] ,matriz a sacar pistas
    */ 
    private String[] pistasFilas(int[][] matriz){
        String[] pistas = new String[matriz.length]; //Arreglo de pistas del tamño de la matriz que le pasemos
        /**
         * Iteracion del for que se encarga de buscr por fila los lugares donde el valor sea 1.
         * Al encontrarlo en la segunda iteracion, suma el valor al contador para la pista.
         * una vez que no se cumple el primer condicional, o encuentra una celda no marcada, procede a verificar la cantidad de lugares con un 1 ya tenia
         * y ese valor lo agregar a un string con un espacio para el diseño, y resetea el valor del contador.
         * saliendo de la segunda iteracion verifica que el contador se alla quedado en 0, caso contrario guarda un espacio y lo resetea
         * guardamos en nuestro arreglo la pista de esa fila y proseguimos 
         */
        for(int i=0; i<matriz.length; i++){
            String pistasFila= "";
            int conta = 0;
            for(int j=0; j<matriz[i].length; j++){
                if(matriz[i][j]== 1){
                    conta ++;
                }
                else if(conta>0){
                    
                    pistasFila +=conta + " ";
                    conta = 0;     
                }
            }

        if (conta > 0) {
            pistasFila += conta + " ";
        }
            pistas[i] = pistasFila;
        }
        return pistas;
    }
    /**
     * Método para las pistas del tablero pero sus columnas
     * @param int[][] tablero
     * @return String[] pistas
     */
    private String[] pistasColumnas(int[][] matriz){
        String[] pistas = new String[matriz.length];

        for(int j=0; j<matriz[0].length; j++){
            String pistasCol= "";
            int conta = 0;
            for(int i=0; i<matriz.length; i++){
                if(matriz[i][j]== 1){
                    conta ++;
                }
                else if(conta>0){
                    
                    pistasCol +=conta + " ";
                    conta = 0;     
                }
            }

        if (conta > 0) {
            pistasCol += conta + " ";
        }
            pistas[j] = pistasCol;
        }
        return pistas;
    }


    /**
     * Método para mostrar el tablero con los cambios
     * usaremos las matrices que nos devuelvan los métodos agregar y quitar
     * @param int[][] ,matriz a mostrar
     */
    public void mostrarTablero(int[][] tablero){
        String[] pistasFila = pistasFilas(tableroSolucion()); //String del metodo privado pistasFila
        String[] pistasColumnas = pistasColumnas(tableroSolucion()); //String del metodo privado pistasColumnas
        

        System.out.print("      "); 
        /*Poner la primera fila de pistas en la parte superior
          Lo dividimos con el método .split() que divide un arreglo en sus elementos basado en la regla que le pongas
          verificamos su tamaño, si poseía dos elementos (3 contando el espacio), solo nos interesa el de arriba y lo imprimimos con un espacio
          en caso de que sea un elemento unico agregamos un espacio para no romper el diseño  
        */
        for (int j = 0; j < pistasColumnas.length; j++) {
        String[] partes = pistasColumnas[j].trim().split(" "); // trim() limpia espacios raros
        
        if (partes.length ==2 ) {
            // Si hay más de un elemento, el primero va arriba
            System.out.print(partes[0] + " ");
        } else {
            System.out.print("  "); 
        }
    }
    System.out.println();

    // inferior
    System.out.print("      ");
    for (int j = 0; j < pistasColumnas.length; j++) {
        String[] partes = pistasColumnas[j].trim().split(" ");
        
        if (partes.length ==2) {
            // Si hay dos, el segundo va abajo
            System.out.print(partes[1] + " ");
        } else {
            // Si solo hay uno, el primero va abajo
            System.out.print(partes[0] + " ");
        }
    }


        System.out.println("\n      ---------");
        System.out.println();
        for(int i=0;i<tablero.length;i++){
            if (pistasFila[i].length() <= 2) {
                System.out.print("  "); // Dos espacios extra para compensar
            } else if (pistasFila[i].length() > 2) { 
            System.out.print("");  // Un espacio extra
        }
        System.out.print(pistasFila[i] + "| ");
            for(int j=0; j<tablero.length; j++){
                System.out.print(tablero[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    /**
     * método para cambiar el valor de una celda "vacia" (0) a llena (1)
     * @param int fila
     * @param int columna
     * @return int[][] nuevo tablero
     */
    public int[][] marcarCelda(int fila, int columna) throws ExcepcionNumeroInvalido{
        if(fila >4 || fila <0 || columna <0 || columna > 4){
            throw new ExcepcionNumeroInvalido("Estás tratando de usar un valor invalido, vuelve a intentarlo");
        }
        else if(this.tablero[fila][columna] == 1){
            throw new ExcepcionNumeroInvalido("Esa casilla ya esta marcada, ingresa otra");
        }
        this.tablero[fila][columna] = 1;
        return this.tablero;
    }
    /**
     * método para cambiar el valor de una celda "llena" (1) a "vacia" (0)
     * @param int fila
     * @param int columna
     * @return int[][] nuevo tablero
     */
    public int[][] vaciarCelda(int fila, int columna)throws ExcepcionNumeroInvalido{
        if(fila >4 || fila <0 || columna <0 || columna > 4){
            throw new ExcepcionNumeroInvalido("Estás tratando de usar un valor invalido, vuelve a intentarlo");
        }
        if(this.tablero[fila][columna] == 0){
            throw new ExcepcionNumeroInvalido("Esa casilla ya esta vacia, ingresa otra");
        }
        this.tablero[fila][columna] = 0;
        return this.tablero;
    }
    /**
     * Método booleano para verificar si el jugador ya ha logrado llenar todo el tablero
     * @return true si logro completar el juego, false si aún no lo logra
     */
    public boolean victoria(int[][] solución){
        for(int i=0; i<solución.length; i++){
            for(int j=0; j<solución.length; j++){
                if(solución[i][j] != this.tablero[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

}
