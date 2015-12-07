package logica;

/**
 * 
 */
public class Casilla {

	private int f;
	private int c;
	
	/**
	 * 
	 * @param f
	 * @param c
	 */
	public Casilla (int f, int c){
		this.f = f;
		this.c = c;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFila(){
		return this.f;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColumna(){
		return this.c;
	}
}
