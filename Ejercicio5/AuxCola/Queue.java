import java.util.*;

public class Queue<T> implements TDAQueue<T>{

	private ArrayList<T> lista = new ArrayList<>();

	@Override
	public void agregar(T e){
		lista.add(lista.size(), e);
	}

	@Override
	public T eliminar() throws NoSuchElementException{
		if(lista.isEmpty()){
			throw new NoSuchElementException();
		}
		return lista.remove(0);
	}

	@Override
	public T tomar() throws NoSuchElementException{
		if(lista.isEmpty()){
			throw new NoSuchElementException();
		}
		return lista.get(0);
	}

	@Override
	public boolean estaVacia(){
		return lista.isEmpty();	
	}

	@Override
	public int tamano(){
		return lista.size();
	}

	@Override 
	public void vaciar(){
		lista.clear();
	}

	@Override
	public void mostrar(){
		Iterator<T> iterator = lista.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}



	
}