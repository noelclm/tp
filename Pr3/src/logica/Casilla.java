package logica;

/**
 * Clase que guarda las cordeenadas de una casilla del tablero.
 */
public class Casilla {

	private int fila;
	private int columna;
	
	/**
	 * Constructor parametrizado.
	 * @param f Fila.
	 * @param c Columna.
	 */
	public Casilla (int fila, int columna){
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Devuelve la fila.
	 * @return int
	 */
	public int getFila(){
		return this.fila;
	}
	
	/**
	 * Devuelve la columna.
	 * @return int
	 */
	public int getColumna(){
		return this.columna;
	}
	
}
