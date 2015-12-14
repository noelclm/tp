package logica;

/**
 * Clase que gestiona una celula.
 */
public abstract class Celula {

	protected boolean esComestible;
	protected String texto;
	
	/**
	 * Mira donde se puede mover la celula y devuelve la casilla.
	 * @param casillaInicial Posición del tablero.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public abstract Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public abstract boolean esComestible();
	
	/**
	 * Constructor por defecto.
	 */
	public Celula(){
		
		this.texto = "";
		
	}
	
	/**
	 * Devuelve los movimientos realizados durante la ejecución del movimiento
	 * @return String
	 */
	public String pintaMovimientos(){
		
		return this.texto;
		
	}

}
