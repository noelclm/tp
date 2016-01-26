package comandos;

import controlador.Comando;
import controlador.Controlador;
import excepciones.MundoException;

/**
 * Comando Iniciar - Inicia el tablero con varias celulas.
 */
public class Iniciar extends Comando{
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Iniciar() {}
	
	@Override
	public String ejecuta(Controlador controlador) throws MundoException {
		
		return controlador.iniciar() + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("iniciar") && cadenaComando.length == 1){
			Comando comando = new Iniciar();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " INICIAR: reinicia la simulacion simulación con un numero aleatoreo de celulas" + LINE_SEPARATOR ;
		
	}

}
