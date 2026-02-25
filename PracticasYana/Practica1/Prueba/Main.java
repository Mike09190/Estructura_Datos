import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //Atributos
        int fila = 0;
        int columna =0;
        int opc = 1;
        int[][] tableroJugador;
        int[][] tableroSolucion;
        //Objetos
        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        tableroSolucion = tablero.tableroSolucion();
        System.out.println("Bienvenido a Picross");
        tableroJugador = tablero.crearTablero();
        tablero.mostrarTablero(tableroJugador);
        //Do que se repite hasta que el jugado gane
        do{
            try{
                System.out.println("Ingrese la fila"); //Agregar excepción por tipo de dato incorrecto
                fila = scan.nextInt();
                System.out.println("Ingrese la columna"); //Agregar excepción por tipo de dato incorrecto
                columna = scan.nextInt();
            
                System.out.println("Elija una de las opc para continuar, con su valor númerico: ");
                System.out.println("1-Agregar casilla, 2-Limpiar casilla"); //Excepcion en caso de opc invalida
                opc = scan.nextInt();
                scan.nextLine();
            }catch(InputMismatchException o){
                System.out.println("Una de las opc es invalida vuelve a intentarlo");
                scan.nextLine();
                continue;
            }
            if(opc == 1){
                try{
                    tableroJugador = tablero.marcarCelda(fila-1, columna-1);
                    tablero.mostrarTablero(tableroJugador);
                }catch(ExcepcionNumeroInvalido e){
                    System.out.println(e.getMessage());
                }
            }else{
                try{
                    //Verificar que el tablero no este vacio
                    tableroJugador = tablero.vaciarCelda(fila-1, columna-1);
                    tablero.mostrarTablero(tableroJugador);
                }catch(ExcepcionNumeroInvalido e){
                    System.out.println(e.getMessage());

                }
            }
            
        }while(!tablero.victoria(tableroSolucion));
        System.out.println("Felicidades, lograste armar la figura");
    }

}
