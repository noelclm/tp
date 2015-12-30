package comandos;

import controlador.Comando;
import controlador.Controlador;

/**
 * Comando Vaciar - Vacía el tablero.
 */
public class Vaciar extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	/**
	 * Constructor por defecto.
	 */
	public Vaciar() {}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.vaciar() + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("vaciar") && cadenaComando.length == 1){
			Comando comando = new Vaciar();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " VACIAR: crea un mundo vacío" + LINE_SEPARATOR;
		
	}

}
