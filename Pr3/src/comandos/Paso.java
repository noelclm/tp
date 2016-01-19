package comandos;

import controlador.Comando;
import controlador.Controlador;

/**
 * Comando Paso - Realiza un movimiento en todas las celulas del tablero.
 */
public class Paso extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * Constructor por defecto.
	 */
	public Paso() {}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.evoluciona() + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("paso") && cadenaComando.length == 1){
			Comando comando = new Paso();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " PASO: realiza un paso en la simulación" + LINE_SEPARATOR;
		
	}

}
