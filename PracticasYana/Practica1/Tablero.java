

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
     * Método para mostrar el tablero con los cambios
     * usaremos las matrices que nos devuelvan los métodos agregar y quitar
     * @param int[][] matriz a mostrar
     */
    public void mostrarTablero(int[][] tablero){
        String[] pistasFila = {"1 2", "  3", "2 1", "1 2", "1 1"};

        System.out.println("     3   2 1  ");
        System.out.println("     1 3   2 2");
        System.out.println("     ---------");
        for(int i=0;i<tablero.length;i++){
            System.out.print(pistasFila[i]+ "| ");
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
        if(this.tablero[fila][columna] == 1){
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
