public class SimuAsteroides {
    //Atributos
    private int[] asteroides;
    private int[] tamañoAsteroides;
    private Stack<Integer> pilaAsteorides = new Pila<>();

    /**
     * Constructor con parametros
     * @param int[] arreglo con los asteroides
     */
    public SimuAsteroides(int[] asteroides){
        this.asteroides = asteroides;
        this.tamañoAsteroides = new int[asteroides.length];
        for(int i=0; i<asteroides.length; i++){
            this.tamañoAsteroides[i] = Math.abs(asteroides[i]);
        }
    }

    /**
     * Método para hacer el choque de asteroides
     * @return Arreglo con ele  mentos restantes
     */
    public void choque(){
        if(this.asteroides == null){
            System.out.println("No hay asteroides por chocar");
            return;
        } 
        pilaAsteorides.clear();
        //Guardar elementos en las dos posibles pilas
        for(int i=0; i<this.asteroides.length; i++){
            if(asteroides[i] > 0 ){ //Guardar en una pila los positivos
                pilaAsteorides.push(asteroides[i]);
           }else{
                boolean negativo = false;
                //Dentro de un while vamos a comparar los asteroides positivos con los negativos
                while(!pilaAsteorides.isEmpty() && pilaAsteorides.top() > 0){
                    //Caso de que el valor negativo sea mas grande que el positivo
                    if(tamañoAsteroides[i] > pilaAsteorides.top()){
                        pilaAsteorides.pop();
                        continue;
                        //Caso de que sean iguales, destruir ambos
                    }else if(tamañoAsteroides[i] == pilaAsteorides.top()){
                        pilaAsteorides.pop();
                        negativo = true;            
                        break;
                        //Caso de que el negativo sea menor, destruir el negativo
                    }else{
                        negativo = true;
                        break;
                    }
                }
                if(!negativo){
                    pilaAsteorides.push(asteroides[i]);
                }
            }
        }
    }

    //Método para mostrar los asteroides sobrevivientes
    public void mostrarResultado(){
        if(pilaAsteorides == null){
            System.out.println("[ ]");
            return;
        }
        System.out.print("[");
        pilaAsteorides.show();
        System.out.print("]");
    }




}
