public class Main {
    public static void main(String[] args) {
        PreguntasJuego juego = new PreguntasJuego();
        System.out.println("Bienvenido al juego de preguntas");
        int conta = 1;
        while(conta <= 20 && !juego.victoria()){
            System.out.println("Intento número " + conta);
            juego.jugar();
            juego.guardarPreguntas();
            if(!juego.victoria()){
                System.out.println("Vamos con el siguiente intento");
            }
            conta ++;
        }
        if(juego.victoria()){
            System.out.println("He logrado adivinar lo que estabas pensando en la pregunta numero " + conta);
        }else{
            System.out.println("Me ganaste como soy malo");
        }
    }
}