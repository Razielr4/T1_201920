package model.data_structures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	
	Node<T> primerNodo;
	Node<T> last;
	
	private int tamano;
	
	public LinkedList (){
		primerNodo = null;
		tamano = 0;
		last = primerNodo;
	}
	
	public LinkedList (T elemento){ 
		
		if(elemento == null){ throw new NullPointerException();}
		
		primerNodo = new Node<T>(elemento); tamano = 1; last = primerNodo;
		
	}
	
	public int size(){
		return tamano;
	}
	
	public void add(T newElemento){
		last.cambiarSiguiente(new Node<T>(newElemento));
		last = last.darSiguiente();
		
		tamano ++;
	}
	
	public void remove(int pos){
		Node<T> dead = primerNodo;
		if(pos == 0){ primerNodo = primerNodo.darSiguiente();
		}else if(pos > 0){
				for(int i=1;i < pos && pos < tamano && pos>0;i++){
					 dead = dead.darSiguiente();
				}
				dead.cambiarSiguiente(dead.darSiguiente().darSiguiente());
			}
		tamano --;
		}
	
	public T get(int pos){
		Node<T> respuesta = primerNodo;
		
		for(int i=0;i < pos && respuesta != null ;i++){
			respuesta = respuesta.darSiguiente();
		}
		return respuesta.darElemento();
	}

	public void next()
	{
		if(last != null){
			last = last.darSiguiente();
		}
	}
	
	public T get(){
		if(last != null){
			return last.darElemento();
		}
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new iterador();
	}
	
	private class iterador implements Iterator<T>{
		
		Node<T> actual = primerNodo;
		
		public boolean hasNext() {
			
			return actual.darSiguiente() != null;
		}

		@Override
		public T next() {
			Node<T> dar = actual;
			actual = actual.darSiguiente();
			return dar.darElemento();
		}
		
	}
	
}
