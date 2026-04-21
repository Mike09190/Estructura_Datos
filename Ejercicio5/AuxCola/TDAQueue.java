import java.util.NoSuchElementException;

public interface TDAQueue <T>{

	/**
	 * Metodo para agregar elementos al final
	 * 
	 * @param e el elmento a agregar
	 */
	public void agregar(T e);

	/**
	 * Metodo para eliminar el primer elemento
	 * 
	 * @return el elemento
	 * @throws NoSuchElementException si la cola esta vacia 
	 */
	public T eliminar() throws NoSuchElementException;

	/**
	 * Metodo devuelve el elemento siguiente (primero) de la cola sin eleminar
	 * 
	 * @return el siguiente elemento de la cola
	 * @throws NoSuchElementException si la cola esta vacia   
	 */
	public T tomar() throws NoSuchElementException;

	/**
	 * verificar si la cola esta vacia
	 * 
	 * @return true si la cola no tiene elementos
	 */
	 public boolean estaVacia();

	 /**
	  * Devuelve el numero de elementos en la cola
	  *
	  * @return int tamano 
	  */ 
	 public int tamano();

	 /**
	  * Metodo para eliminar todos los elementos 
	  * 
	  */
	 public void vaciar();


	 /**
	  * Metodo que muestra todo
	  * 
	  */
	 public void mostrar();








}