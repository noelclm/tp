package controlador;

import logica.Mundo;

/**
 * Clase abstracta pura de la que heredan los comandos.
 */
public abstract class Comando {
	
	/**
	 * Ejecuta la acción y devuelve un texto con lo que ha hecho.
	 * @param mundo
	 * @return String
	 */
	public abstract String ejecuta(Mundo mundo);
	
	/**
	 * Comprueba el comando y devuelve el comando si es el correcto.
	 * @param cadenaComando Texto introducido por el usuario.
	 * @return Comando
	 */
	public abstract Comando parsea(String[ ] cadenaComando);
	
	/**
	 * Devuelve la ayuda del comando.
	 * @return String 
	 */
	public abstract String textoAyuda();
}
