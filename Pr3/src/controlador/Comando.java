package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;

import excepciones.CoordenadasException;

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
	 * @throws CoordenadasException 
	 */
	public abstract Comando parsea(String[ ] cadenaComando) throws CoordenadasException;
	
	/**
	 * Devuelve la ayuda del comando.
	 * @return String 
	 */
	public abstract String textoAyuda();
}
