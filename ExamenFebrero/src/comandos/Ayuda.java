package comandos;

import controlador.Comando;
import controlador.Controlador;
import controlador.ParserComandos;

/**
 * Comando Ayuda - Muestra los posibles comandos.
 */
public class Ayuda extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Ayuda(){}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return ParserComandos.AyudaComandos();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("ayuda") && cadenaComando.length == 1){
			
			Comando comando = new Ayuda();
				
			return comando;
		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " AYUDA: muestra esta ayuda" + LINE_SEPARATOR;
		
	}

}
