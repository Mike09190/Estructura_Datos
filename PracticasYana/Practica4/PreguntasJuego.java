    import java.io.*;
    import java.util.*;
public class PreguntasJuego implements Serializable {
    private ArbolBinario<String> preguntas;
    private final String archivo = "preguntas.ser";

    public PreguntasJuego() {
        this.preguntas = cargarPreguntas();
    }

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
        //Crear nuevo arbol prefabricado
        ArbolBinario<String> nuevoArbol = new ArbolBinario<>();
        String raiz = "¿Se usa balón?";
        nuevoArbol.agregar(raiz);
        nuevoArbol.agregar("¿Es en equipo?", raiz);
        nuevoArbol.agregar("¿Es un deporte acuático?", raiz);
        nuevoArbol.agregar("¿Es futbol?", "¿Es en equipo?");
        nuevoArbol.agregar("¿Es natación?", "Es un deporte acuático");
         
        return nuevoArbol;
    }

    /**
     * Método para jugar las 20 preguntas
     * @return boolean
     * @param String
     */
    public void jugar(String respuesta){
        VerticeArbolBinario<String> actual = preguntas.raiz();
        int conta = 0;
        String res = "";
        Scanner scan = new Scanner(System.in);

        while(conta <20 &&  (actual.hayIzquierdo() || actual.hayDerecho())){
            System.out.println(actual.get());
            System.out.println("Responde con 'si' o 'no'");
            res = scan.nextLine();
            if(res.equalsIgnoreCase("si")){
                actual = actual.izquierdo();
            } else{
                actual = actual.derecho();
            }
            conta++;
        }
        if(conta == 20){
            System.out.println("No he podido adivinar el juego :(");
            System.out.println("¿Cuál era la respuesta correcta?");
            String respuestaCorrecta = scan.nextLine();
            // Aquí se podría agregar la respuesta correcta al árbol
            return;
        }
        verificarRespuesta(actual, res);

    }

    /**
     * Método privado para verificar la respuesta y en caso necesario agregar nuevas preguntas
     * @return boolean Si es correcta o no
     * @param vertice vertices con la hoja
     */
    private boolean verificarRespuesta(VerticeArbolBinario<String> vertice, String respuesta){
        //Rehacer la pregunta
        Scanner scan = new Scanner(System.in);
        System.out.println(vertice.get());
        if(respuesta.equalsIgnoreCase("si")){
            // Mostrar mensaje de victoria
            System.out.println("¡He adivinado el juego!");
            return true;
        }else{
            System.out.println("¿Cuál era tu deporte?");
            String respuestaCorrecta = scan.nextLine();

            System.out.println("Dame una pregunta para identificar tu deporte:");
            String preguntaNueva = scan.nextLine();

            System.out.println("El " + respuestaCorrecta + preguntaNueva);
            // Aquí se podría agregar la respuesta correcta al árbol
            System.out.println("La proxima lo hare mejor");
            agregarPregunta(preguntaNueva,respuestaCorrecta, vertice);
            return false;
        }
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
        VerticeArbolBinario<String> padreReferencia = v.padre();

        preguntas.eliminar(preguntaVieja);
        preguntas.agregar(preguntaNueva, padreReferencia.get());
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