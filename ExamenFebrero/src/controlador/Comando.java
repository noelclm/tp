package controlador;

import excepciones.MundoException;

/**
 * Clase abstracta pura de la que heredan los comandos.
 */
public abstract class Comando {
	
	/**
	 * Ejecuta la accion y devuelve un texto con lo que ha hecho.
	 * @param controlador Donde se realiza la accion.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public abstract String ejecuta(Controlador controlador) throws MundoException;
	
	/**
	 * Comprueba el comando y devuelve el comando si es el correcto.
	 * @param cadenaComando Texto introducido por el usuario.
	 * @return Comando
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public abstract Comando parsea(String[ ] cadenaComando) throws MundoException;
	
	/**
	 * Devuelve la ayuda del comando.
	 * @return String 
	 */
	public abstract String textoAyuda();
}
