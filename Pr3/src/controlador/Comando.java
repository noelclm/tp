package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase abstracta pura de la que heredan los comandos.
 */
public abstract class Comando {
	
	/**
	 * Ejecuta la acción y devuelve un texto con lo que ha hecho.
	 * @param mundo Donde se realiza la acion.
	 * @return String
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public abstract String ejecuta(Controlador controlador) throws FileNotFoundException, IOException;
	
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
