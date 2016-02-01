package logica;

import java.io.PrintWriter;

import mundos.MundoSimple;

/**
 * Interfaz que gestiona una celula.
 */
public interface Celula {
	
	/**
	 * Ejecuta el movimiento de la celula sobre el tablero y devuelve la casilla.
	 * @param casillaInicial Posicion del tablero.
	 * @param texto Texto que se muestra por pantalla al realizar la accion.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, MundoSimple superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public boolean esComestible();
	
	/**
	 * Guarda en un PrintWriter los datos de la celula.
	 * @param pw Entra un PrintWrite para escribir en el fichero.
	 */
	public void guardar(PrintWriter pw);
	
}
