import java.util.Scanner;

public class Etiquetas {
    public static void main(String[] args) {
        //Atributos
        String cadenaHTML;
        boolean esbody = true;
        //Objetos
        Scanner scan = new Scanner(System.in);
        Pila<String> pila = new Pila<>();

        System.out.println("Ingresa la cadena HTML para verificar que es correcta");
        cadenaHTML = scan.nextLine(); 
        String[] cadena = cadenaHTML.split("<");
        for(String str : cadena){ 
            if(str.isEmpty()){
                continue;
            }
            str = str.split(">")[0].trim();
            if(!str.startsWith("/")){
                if(esbody && !str.equalsIgnoreCase("body")){
                    System.out.println("El Html empieza sin body, lo cual no es posible");
                    return;
                }
            pila.push(str);
            esbody = false;
            }else{
                if(pila.isEmpty()){
                    System.out.println("Error: La etiqueta de cierre " + str + " no tiene etiqueta de apertura");
                    return;
                }
                if(verificarEtiquetas(pila.top(), str)){
                    pila.pop();
                }else{
                    System.out.println("Error: La etiqueta de cierre " + str + " no coincide con la etiqueta de apertura " + pila.top());
                    break;
                }
            }       
        }
        if(pila.isEmpty()){
            System.out.println("La cadena HTML es correcta");
        }else{
            System.out.println("Error: La cadena HTML tiene etiquetas sin cerrar");
        }
        
    }

    //Método auxiliar para verificar si la etiqueta de cierre corresponde a la etiqueta de apertura
    private static boolean verificarEtiquetas(String apertura, String cierre) {
        return apertura.equals(cierre.substring(1));
    }

}
