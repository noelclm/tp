package logica;

/**
 * Clase que gestiona una celula.
 */
interface Celula {
	
	/**
	 * Ejecuta el movimiento de la celula sobre el tablero y devuelve la casilla.
	 * @param casillaInicial Posición del tablero.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public boolean esComestible();
	
}
