

public class Cancion{
    //Atributos
    private String titulo;
    private String artista;
    private int duracion;

    /**
     * Contructor con parametros de la clase cancion
     * @param String titulo
     * @param String artista
     * @param int duracion
     */
    public Cancion(String titulo, String artista, int duracion) {

        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    /**
     * Getter para obtener la duración de una cancion
     * @return String duracion
     */
    public int obtenDuracion(){
        return this.duracion;
    }
    /**
     * Getter para obtener el titulo de una canción
     * @return String titulo
     */
    public String obtenTitulo(){
        return this.titulo;
    }

    @Override
    public String toString() {
        return this.titulo + "\n -" + this.artista + "\n Duración: " + this.duracion + "s";
    }


}