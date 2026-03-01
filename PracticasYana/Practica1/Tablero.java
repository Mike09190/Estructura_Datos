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
        int mitad = matriz.length/2; //Variable para verificar si la cantidad de bloques es 1 o 0, para rellenar con 0 las pistas necesarias
        for(int j=0; j<matriz[0].length; j++){
            String pistasCol= "";
            int conta = 0;
            int grupo = 0; //Variable para ayudar a la posicion de la pista
            boolean arribaAbajo = false;
            for(int i=0; i<matriz.length; i++){
                if(matriz[i][j]== 1){
                    conta ++;
                    if(i<mitad){
                        arribaAbajo = true;
                    } //La posicion del numero sera arriba, ya que encontro un valor antes de la mitad del tablero
                }
                else if(conta>0){
                    pistasCol +=conta + " ";
                    grupo ++;
                    conta = 0;
                      
                }
            }
            //Verificar la cantidad de elementos, para rellenar con 0 los necesarios
            if (conta > 0) {
                pistasCol += conta + " ";
                grupo++;
            }
            if(grupo == 1){
                if(!arribaAbajo){
                    pistasCol = "0 " + pistasCol; //Si el bloque esta abajo, agregamos un 0 al inicio
                }else{
                    pistasCol += "0 "; //Si el bloque esta arriba, agregamos
                }
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
        
        //Imprimir las pistas de las columnas
        System.out.print("      ");
        for(int i =0; i<pistasColumnas.length; i++){
            String[] pistasArriba = pistasColumnas[i].split(" ");
            System.out.print(pistasArriba[0]+ " ");
        }
        
        System.out.println("");
        System.out.print("      ");
        for(int i =0; i<pistasColumnas.length; i++){
            String[] pistasArriba = pistasColumnas[i].split(" ");
            System.out.print(pistasArriba[1]+ " ");
        }
         
        System.out.println();
        System.out.println("      ---------");
        
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
    public boolean victoria(){
        for(int i=0; i<tableroSolucion().length; i++){
            for(int j=0; j<tableroSolucion().length; j++){
                if(tableroSolucion()[i][j] != this.tablero[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

}

