import java.util.Scanner;

public class MainAsteorides {
    public static void main(String[] args) {
        //Objetos 
        Scanner scan = new Scanner(System.in);
        SimuAsteroides simulador;
        //variables
        int[] asteroides;
        boolean tamañoInvalido = false;
        int tamaño;
        System.out.println("Sistema de asteoides");
        do{
            System.out.println("Ingrese la cantidad de asteroides");
            tamaño = scan.nextInt();
            if(tamaño <= 0){
                System.out.println("No puede tener un tamaño 0 o negativo");
                tamañoInvalido = true;
            }else{
                tamañoInvalido = false;
            }
        }while(tamañoInvalido);
        asteroides = new int[tamaño];
        for(int i=0; i<tamaño; i++){
            System.out.println("Ingrese el tamaño del asteroide ("+ (i+1) +"), no pueden tener tamaño 0");
            int tamAsteroide = scan.nextInt();
            while (tamAsteroide == 0) {
                System.out.println("¡Error! El tamaño no puede ser 0. Intente de nuevo:");
                tamAsteroide = scan.nextInt();
            }
            asteroides[i] = tamAsteroide;
        }
        simulador = new SimuAsteroides(asteroides);
        //Choque de asteroides
        simulador.choque();
        simulador.mostrarResultado();
    }
}
