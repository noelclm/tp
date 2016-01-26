package logica;

import java.io.PrintWriter;

/**
 * Interfaz que gestiona una celula.
 */
public interface Celula {
	
	/**
	 * Guarda en un PrintWriter los datos de la celula.
	 * @param pw Entra un PrintWrite para escribir en el fichero.
	 */
	public void guardar(PrintWriter pw);
	
	/**
	 * Ejecuta el movimiento de la celula sobre el tablero y devuelve la casilla.
	 * @param casillaInicial Posicion del tablero.
	 * @param texto Texto que se muestra por pantalla al realizar la accion.
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
