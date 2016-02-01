package comandos;

import controlador.Comando;
import controlador.Controlador;

/**
 * Comando Salir - Termina la simulacion.
 */
public class Salir extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Salir() {}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.salir();
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("salir") && cadenaComando.length == 1){
			Comando comando = new Salir();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " SALIR: cierra la aplicacion" + LINE_SEPARATOR;
		
	}

}
