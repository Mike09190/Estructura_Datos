import java.io.*;
import java.util.*;
public class PreguntasJuego implements Serializable {
    //Atributos
    private ArbolBinario<String> preguntas;
    private final String archivo = "preguntas.ser";
    private boolean victoria;
    //Constructor sin parámetros
    public PreguntasJuego() {
        this.preguntas = cargarPreguntas();
    }
    /**
     * Método privado para cargar las preguntas al archivo inicial en forma de arbol
     * @return
     */
    private ArbolBinario<String> cargarPreguntas(){
        File arc = new File(archivo);
        if(arc.exists()){
            try{
                ObjectInputStream ob = new ObjectInputStream(new FileInputStream(arc));
                return (ArbolBinario<String>) ob.readObject();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //Crear nuevo arbol prefabricado (valores base)
        ArbolBinario<String> nuevoArbol = new ArbolBinario<>();
        String raiz = "¿Se usa balón?";
        String pregunta1 = "¿Es en equipo?";
        String pregunta2 = "¿Es un deporte acuático?";
        nuevoArbol.agregar(raiz);
        nuevoArbol.agregar(pregunta1, raiz);
        nuevoArbol.agregar(pregunta2, raiz);
        nuevoArbol.agregar("¿Es futbol?", pregunta1);
        nuevoArbol.agregar("¿Es golf?", pregunta1);
        nuevoArbol.agregar("¿Es natación?", pregunta2);
        nuevoArbol.agregar("¿Tiene algun vehículo?", pregunta2);

        return nuevoArbol;
    }

    /**
     * Método para jugar las 20 preguntas
     */
    public void jugar(){
        VerticeArbolBinario<String> actual = preguntas.raiz();
        int conta = 0;
        String res = "";
        Scanner scan = new Scanner(System.in);

        while( conta <20 &&  (actual.hayIzquierdo() || actual.hayDerecho())){
            System.out.println(actual.get());
            System.out.println("Responde con 'si' o 'no'");
            res = scan.nextLine();
            if(res.equalsIgnoreCase("si")){
                if(actual.hayIzquierdo()){
                    actual = actual.izquierdo();
                }else{
                    break;
                }
                
            } else{
                if(actual.hayDerecho()){
                    actual = actual.derecho();
                }else{
                    break;
                }
            }
            conta++;
        }
        //Al terminar las 20 preguntas perdio la computadora
        if(conta == 20){
            System.out.println("No he podido adivinar el juego :(");
            System.out.println("¿Cuál era la respuesta correcta?");
            String respuestaCorrecta = scan.nextLine();
            // Aquí se podría agregar la respuesta correcta al árbol
            return;
        }
        verificarRespuesta(actual);

    }

    /**
     * Método privado para verificar la respuesta y en caso necesario agregar nuevas preguntas
     * @return boolean Si es correcta o no
     * @param vertice vertices con la hoja
     */
    private boolean verificarRespuesta(VerticeArbolBinario<String> vertice){
        //Rehacer la pregunta
        Scanner scan = new Scanner(System.in);
        System.out.println(vertice.get());
        System.out.println("Responde con 'si' o 'no'");
        String respuesta = scan.nextLine();
        if(respuesta.equalsIgnoreCase("si")){
            // Mostrar mensaje de victoria
            System.out.println("¡He adivinado el juego!");
            victoria = true;
            
            return true;
            //Agregar la nueva pregunta en caso contrario
        }else{
            System.out.println("¿Cuál era tu deporte?");
            String respuestaCorrecta = scan.nextLine();

            System.out.println("Dame una pregunta para identificar tu deporte:");
            String preguntaNueva = scan.nextLine();

            System.out.println("El " + respuestaCorrecta + preguntaNueva);
            // Aquí se podría agregar la respuesta correcta al árbol
            System.out.println("La proxima lo hare mejor");
            agregarPregunta(preguntaNueva,respuestaCorrecta, vertice);
            victoria = false;
            
            return false;
        }
    }

    /**
     * Método para determinar cuando se ha ganado el juego
     * @param ganado
     * @return
     */
    public boolean victoria(){
        return victoria;
    }

    /**
     * Método para hace la insercion de la pregunta agregada por el usuario
     * @param preguntaNueva pregunta que se va a agregar
     * @param respuestaCorrecta respuesta correcta para la nueva pregunta
     * @param Vertice Hoja en la que termino la prueba
     */
    private void agregarPregunta(String preguntaNueva, String respuestaCorrecta, VerticeArbolBinario<String> v){
        if(v == null) return;

        String preguntaVieja = v.get();
        preguntas.sustituirElemento(v, preguntaNueva);

        
        preguntas.agregar(respuestaCorrecta, preguntaNueva);
        preguntas.agregar(preguntaVieja, preguntaNueva);

    }

    /**
     * Método para guardar el árbol de preguntas en un archivo
     */
    public void guardarPreguntas() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(this.preguntas);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}