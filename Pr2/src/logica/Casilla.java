package logica;

/**
 * Clase que guarda las cordeenadas de una casilla del tablero.
 */
public class Casilla {

	private int f;
	private int c;
	
	/**
	 * Constructor parametrizado.
	 * @param f Fila.
	 * @param c Columna.
	 */
	public Casilla (int f, int c){
		this.f = f;
		this.c = c;
	}
	
	/**
	 * Devuelve la fila.
	 * @return int
	 */
	public int getFila(){
		return this.f;
	}
	
	/**
	 * Devuelve la columna.
	 * @return int
	 */
	public int getColumna(){
		return this.c;
	}
}
