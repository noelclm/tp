package logica;

/**
 * Clase que gestiona una celula.
 */
public abstract class Celula {

	protected boolean esComestible;
	
	/**
	 * Mira donde se puede mover la celula y devuelve la casilla.
	 * @param casillaInicial Posición del tablero.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public abstract Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public abstract boolean esComestible();
	
	/**
	 * Constructor por defecto.
	 */
	public Celula(){
		
	}
	
}
