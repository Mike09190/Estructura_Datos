/**
 * Implementación abstracta de un Árbol Binario genérico.
 */
public abstract class ArbolBin<T> {

	/**
	 * Clase interna que representa un vertice del arbol.
	 * Cada vertice contiene un elemento y referencias a su padre
	 * y a sus hijos izquierdo y derecho.
	 */
	protected class Vertice<T> {

		public T elemento;
		public Vertice<T> padre;
		public Vertice<T> izquierdo;
		public Vertice<T> derecho;

		/**
		 * Indica si el vertice tiene padre.
		 * 
		 * @return true si tiene padre, false en otro caso
		 */
		public boolean hayPadre() {
			return padre != null;
		}

		/**
		 * Indica si el vertice tiene hijo izquierdo.
		 * 
		 * @return true si existe hijo izquierdo
		 */
		public boolean hayIzquierdo() {
			return izquierdo != null;
		}

		/**
		 * Indica si el vertice tiene hijo derecho.
		 * 
		 * @return true si existe hijo derecho
		 */
		public boolean hayDerecho() {
			return derecho != null;
		}

		/**
		 * Retorna el padre del vertice.
		 * 
		 * @return vertice padre
		 */
		public Vertice<T> padre() {
			return padre;
		}

		/**
		 * Retorna el hijo izquierdo del vertice.
		 * 
		 * @return vertice izquierdo
		 */
		public Vertice<T> izquierdo() {
			return izquierdo;
		}

		/**
		 * Retorna el hijo derecho del vertice.
		 * 
		 * @return vertice derecho
		 */
		public Vertice<T> derecho() {
			return derecho;
		}

		/**
		 * Calcula la altura del subarbol cuya raiz es este vertice.
		 * 
		 * Se define como 1 + la máxima altura entre sus hijos.
		 * Este método es recursivo, ya que depende de la altura
		 * de los subárboles izquierdo y derecho.
		 * 
		 * @return altura del vértice
		 */
		public int altura() {
			int izq = (izquierdo == null) ? -1 : izquierdo.altura();
			int der = (derecho == null) ? -1 : derecho.altura();
			return 1 + Math.max(izq, der);
		}

		/**
		 * Calcula la profundidad del vertice dentro del arbol.
		 * 
		 * La profundidad es la distancia desde la raiz hasta este vertice.
		 * Se calcula recursivamente subiendo por los padres.
		 * 
		 * @return profundidad del vértice
		 */
		public int profundidad() {
			if (padre == null) {
				return 0;
			}
			return 1 + padre.profundidad();
		}

		/**
		 * Retorna el elemento almacenado en el vertice.
		 * 
		 * @return elemento del vertice
		 */
		public T get() {
			return elemento;
		}
	}

	protected Vertice<T> raiz;
	protected int elementos;

	/**
	 * Construye un árbol binario vacío.
	 */
	public ArbolBin() {
		raiz = null;
		elementos = 0;
	}

	/**
	 * Determina si el árbol contiene un elemento.
	 * 
	 * Este método utiliza el método busca(), el cual está
	 * implementado de forma recursiva.
	 * 
	 * @param elemento elemento a buscar
	 * @return true si el elemento está en el árbol
	 */
	public boolean contiene(T elemento) {
		return busca(elemento) != null;
	}

	/**
	 * Indica si el arbol está vacio.
	 * 
	 * @return true si no tiene elementos
	 */
	public boolean esVacia() {
		return raiz == null;
	}

	/**
	 * Retorna el número de elementos del arbol.
	 * 
	 * @return cantidad de elementos
	 */
	public int getElementos() {
		return elementos;
	}

	/**
	 * Elimina todos los elementos del arbol.
	 */
	public void limpia() {
		this.raiz = null;
		this.elementos = 0;
	}

	/**
	 * Representacion en cadena del arbol.
	 */
	public String toString() {
		return esVacia() ? "" : str(raiz, 0, new int[altura() + 1]);
	}

	private String dib(int l, int[] a) {
		String s = "";
		for (int i = 0; i < l; i++)
			s += (a[i] == 1 ? "│" : " ") + "  ";
		return s;
	}

	private String str(Vertice v, int l, int[] a) {
		String s = v.toString() + '\n';
		a[l] = 1;
		if (v.hayIzquierdo() && v.hayDerecho()) {
			s += dib(l, a);
			s += "├─›";
			s += str(v.izquierdo, l + 1, a);
			s += dib(l, a);
			s += "└─»";
			a[l] = 0;
			s += str(v.derecho, l + 1, a);
		} else if (v.hayIzquierdo()) {
			s += dib(l, a);
			s += "└─›";
			a[l] = 0;
			s += str(v.izquierdo, l + 1, a);
		} else if (v.hayDerecho()) {
			s += dib(l, a);
			s += "└─»";
			a[l] = 0;
			s += str(v.derecho, l + 1, a);
		}
		return s;
	}

	/**
	 * Busca un elemento en el arbol.
	 * 
	 * Este metodo inicia la búsqueda desde la raiz
	 * utilizando un recorrido recursivo.
	 * 
	 * @param elemento elemento a buscar
	 * @return vertice que contiene el elemento, o null si no existe
	 */
	public Vertice<T> busca(T elemento) {
		return buscaRecursivo(raiz, elemento);
	}

	/**
	 * Metodo auxiliar recursivo para buscar un elemento en el arbol.
	 * 
	 * Recorre el arbol en profundidad
	 * primero el nodo actual, luego el subarbol izquierdo
	 * y finalmente el subarbol derecho.
	 * 
	 * @param v        vertice actual
	 * @param elemento elemento a buscar
	 * @return vertice encontrado o null
	 */
	public Vertice<T> buscaRecursivo(Vertice<T> v, T elemento) {
		if (v == null) {
			return null;
		} else {
			if (v.elemento.equals(elemento)) {
				return v;
			}
			Vertice<T> izq = buscaRecursivo(v.izquierdo, elemento);
			if (izq != null) {
				return izq;
			}
			return buscaRecursivo(v.derecho, elemento);
		}
	}

	/**
	 * Retorna la raiz del arbol.
	 * 
	 * @return vertice raíz
	 */
	public Vertice<T> raiz() {
		return raiz;
	}

	/**
	 * Calcula la altura del arbol.
	 * 
	 * Utiliza el metodo altura() del vertice raiz.
	 * 
	 * @return altura del arbol, o -1 si esta vacio
	 */
	public int altura() {
		return (raiz == null) ? -1 : raiz.altura();
	}

	/**
	 * Crea un nuevo vertice con el elemento dado.
	 * 
	 * Este metodo es utilizado por las clases que extienden
	 * este arbol para garantizar una creacion consistente.
	 * 
	 * @param elemento elemento a almacenar
	 * @return nuevo vertice
	 */
	protected Vertice<T> nuevoVertice(T elemento) {
		Vertice<T> v = new Vertice<>();
		v.elemento = elemento;
		return v;
	}
}