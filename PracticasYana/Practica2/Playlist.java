import java.util.*;

public class Playlist {
    //Atributos
    private ListaDoblementeLigada<Cancion> playlist;
    
    /**
     * Constuctor sin parametros
     */
    public Playlist(){
        this.playlist = new ListaDoblementeLigada<Cancion>();
    }

    /**
     * Agregar cancion al inicio
     * @param Cancion cancion inicial
     */
    public void cancionInicio(Cancion cancion){
        playlist.agregar(0 ,cancion);
        
    }
    /**
     * Agregar cancion al final
     * @param Cancion cancion al final
     */
    public void cancionFinal(Cancion cancion){
        playlist.agregar(cancion);
    }

    /**
     * Eliminar cancion por título
     * @param String titulo
     */
    public void eliminarPorTitulo(String titulo){
        if(playlist.estaVacio()){
            return;
        }
        Iterator<Cancion> it = playlist.iterador();
        while(it.hasNext()){
            Cancion cancionActual = it.next(); 
            if(cancionActual.obtenTitulo().equalsIgnoreCase(titulo)){
                playlist.eliminar(cancionActual);
                
                System.out.println("Cancion elimanda con exito");
                return;
            }
        }
        
            System.out.println("Ese título no existe en esta playslist");
    }

    /**
     * Método para buscar una canción
     * @param Cancion cancion a buscar
     * @return Cancion cancion buscada
     */
    public Cancion buscarCancion(String titulo){
        Iterator<Cancion> it = playlist.iterador();

        while(it.hasNext()){
            Cancion cancionActual = it.next();
            if(cancionActual.obtenTitulo().trim().equalsIgnoreCase(titulo)){
                return cancionActual;
            }
        }
        return null;
    }

    /**
     * Método para buscar canción por título
     * @param String titulo de la cancion
     * @return Cancion, cancion a buscar
     */
    public Cancion buscarTitulo(String titulo){
        Iterator<Cancion> it = playlist.iterador();

        while (it.hasNext()) {
            Cancion actual = it.next();
            if(actual.obtenTitulo().equals(titulo)){
                return actual;
            }
        }
        return null;
    }

    /**
     * Método para mostrar toda la playlist completa 
     */    
    public void mostrarPlaylist(){
        Iterator<Cancion> it = playlist.iterador();
        int contador = 1;
        while(it.hasNext()){
            
            System.out.println(contador + "-" +it.next());
            contador++;
        }
    }
    /**
     * Método para mostrar la duración TOTAL de la playlist
     */
    public void duracionTotal(){
        int duracionTotal = 0;
        Iterator<Cancion> it = playlist.iterador();

        while(it.hasNext()){
            duracionTotal += it.next().obtenDuracion();
        }
        System.out.println("La duración total de la playlist es de: " + duracionTotal + "s");
    }

    /**
     * Método para mostrar la canción actual
     */
    public void mostrarCancionActual(){
        Cancion cancionActual = playlist.obtenerActual();
        if(cancionActual !=null){
            System.out.println("Reproduciendo: \n" + cancionActual);
        }else{
            System.out.println("No hay canción actual");
        }
    }

    /**
     * Método para avanzar la canción actual
     */
    public void avanzarCancion(){
        Cancion cancionActual = playlist.obtenerActual();
        if(cancionActual != null){
            playlist.avanzarActual();
            System.out.println("Canción adelantada con exito");
        }else{
            System.out.println("Ocurrio un error y no se pudo adelantar la canción");
        }

        Cancion nuevoActual = playlist.obtenerActual();
        if(nuevoActual != cancionActual){
            System.out.println("Canción adelantada con exito");
        }else{
            System.out.println("Estás al final de la playlist");
        }
    }

    /**
     * Método para retroceder la canción actual
     */
    public void retrocederCancion(){
        Cancion cancionActual = playlist.obtenerActual();
        if(cancionActual != null){
            playlist.anteriorActual();;
        }else{
            System.out.println("Ocurrio un error y no se pudo adelantar la canción");
        }
        Cancion nuevoActual = playlist.obtenerActual();
        if(nuevoActual != cancionActual){
            System.out.println("Canción cambiada con exito");
        }else{
            System.out.println("Estás al inicio de la playlist");
        }
    }
}
