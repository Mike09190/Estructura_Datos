import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //variables
        int opc;
        String titulo, artista;
        int duracion;
        //Objetos
        Scanner scan  = new Scanner(System.in);
        Cancion cancion;
        Playlist playlist = new Playlist();

        System.out.println("Bienvenido, empezemos tu playlist");
        System.out.println("A continuación elija una de las opc");
        do{
            System.out.println("1-Agregar una canción al inicio de la playlist");
            System.out.println("2-Agregar una canción al final de la playlist");
            System.out.println("3-Eliminar una canción por título");
            System.out.println("4-Buscar una canción");
            System.out.println("5-Mostrar todas las canciones de la playlist");
            System.out.println("6-Mostrar la duración total de la playlist");
            System.out.println("7-Reproducir la canción actual");
            System.out.println("8-Avanzar a la siguiente canción");
            System.out.println("9-Regresar a la canción anterior");
            System.out.println("10-Salir de la playlist");
            System.out.println("Elige una de las opc con su valor númerico: ");
            opc = scan.nextInt();
            scan.nextLine();
            switch (opc) {
                case 1:
                    System.out.println("A continuación escriba los siguientes datos de la canción: ");
                    System.out.print("Tìtulo: ");
                    titulo = scan.nextLine(); 
                    System.out.print("Artista: ");
                    artista = scan.nextLine();
                    try{
                        System.out.print("Duración: ");
                        duracion = scan.nextInt();
                        scan.nextLine();
                        cancion = new Cancion(titulo, artista, duracion);
                        playlist.cancionInicio(cancion);
                    }catch(InputMismatchException e){
                        System.out.println("La duracion tiene que ser númerica");
                        scan.nextLine();
                        opc = 0;    
                    }
                    break;

                case 2:
                    System.out.println("A continuación escriba los siguientes datos de la canción: ");
                    System.out.print("Tìtulo: ");
                    titulo = scan.nextLine();
                    System.out.print("Artista: ");
                    artista = scan.nextLine();
                    try{
                    System.out.print("Duración: ");
                    duracion = scan.nextInt();
                    cancion = new Cancion(titulo, artista, duracion);
                    playlist.cancionFinal(cancion);
                    }catch(InputMismatchException e){
                        System.out.println("La duración tiene que estar escrita en números");
                        opc = 0;
                        scan.nextLine();
                    }

                    break;
                case 3:
                    System.out.println("A continuación escriba el título de la canción para eliminar: ");
                    titulo = scan.nextLine();
                    playlist.eliminarPorTitulo(titulo);
                    break;
                case 4:
                    System.out.println("Escriba la canción a buscar, mediante título");
                    titulo = scan.nextLine();
                    Cancion cancionBuscada = playlist.buscarCancion(titulo);
                    if(cancionBuscada == null){
                        System.out.println("No se encuentra la canción");
                        break;
                    }
                    System.out.println("La canción buscada es: "+ cancionBuscada);
                    System.out.println();
                    break;
                case 5:
                    playlist.mostrarPlaylist();    
                    break;
                case 6:
                    playlist.duracionTotal();
                    break;
                case 7:
                    playlist.mostrarCancionActual();
                    break;
                case 8:
                    playlist.avanzarCancion();
                    break;
                case 9:
                    playlist.retrocederCancion();
                    break;
                default:
                    System.out.println("-----------");
                    break;
            }
        }while(opc != 10);
    }
}
