package controlador;

import logica.Mundo;

/**
 * 
 */
public abstract class Comando {
	/**
	 * 
	 * @param mundo
	 * @return String
	 */
	public abstract String ejecuta(Mundo mundo);
	/**
	 * 
	 * @param cadenaComando
	 * @return Comando
	 */
	public abstract Comando parsea(String[ ] cadenaComando);
	/**
	 * 
	 * @return String
	 */
	public abstract String textoAyuda();
}
