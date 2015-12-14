package comandos;

import controlador.Comando;
import logica.Casilla;
import logica.Mundo;

/**
 * Comando EliminarCelula - Elimina una célula del tablero.
 */
public class EliminarCelula extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	private Casilla casilla;

	/**
	 * Constructor por defecto.
	 */
	public EliminarCelula() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde esta la célula que se quiere borrar.
	 */
	public EliminarCelula(Casilla casilla) {
		this.casilla = casilla;
	}
		
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.eliminarCelula(this.casilla) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {

		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length == 3){

			int f = Integer.parseInt(cadenaComando[1]); 
			int c = Integer.parseInt(cadenaComando[2]);
			
			Casilla casilla = new Casilla(f,c);

			Comando comando = new EliminarCelula(casilla);
			
			return comando;

		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " ELIMINARCELULA F C: elimina una célula de la posición (f,c) si es posible" + LINE_SEPARATOR;
		
	}

}
