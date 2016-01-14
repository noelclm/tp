package controlador;

import comandos.*;
import excepciones.MundoException;

/**
 * Clase que comprueba los comandos.
 */
public class ParserComandos {
		
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	static Comando[] array = {new Iniciar(), new Cargar(), new Guardar(), 
							  new Jugar(), new Paso(), new CrearCelula(), 
							  new EliminarCelula(), new Vaciar(), 
							  new Ayuda(), new Salir()};
	

	/**
	 * Recoge todas las ayudas de los comandos y lo devuelve.
	 * @return String
	 */
	static public String AyudaComandos(){
		
		String ayuda = "POSIBLES COMANDOS:" + LINE_SEPARATOR;
		
		for(Comando c:array){
			ayuda += c.textoAyuda();
		}
		
		return  ayuda;
		
	}
	
	/**
	 * Comprueba a que comando corresponde la cadena introducida por el usuario y devuelve el comando.
	 * @param cadenas Cadena introducida por el usuario.
	 * @return Comando
	 * @throws CoordenadasException 
	 */
	static public Comando parseaComando(String[ ] cadenas) throws MundoException {
		
		Comando comando = null;
		
		for(Comando c:array){
			if(comando == null)
				comando=c.parsea(cadenas);	
		}
		
		return comando;
		
	}


}
