package comandos;

import controlador.Comando;
import logica.Casilla;
import logica.Mundo;

/**
 * Comando CrearCelulaCompleja - Crea una célula compleja en el tablero.
 */
public class CrearCelulaCompleja extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;

	/**
	 * Constructor por defecto.
	 */
	public CrearCelulaCompleja() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde se quiere crear la célula.
	 */
	public CrearCelulaCompleja(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.crearCelulaCompleja(this.casilla) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("crearcelulacompleja") && cadenaComando.length == 3){
			
			int f = Integer.parseInt(cadenaComando[1]); 
			int c = Integer.parseInt(cadenaComando[2]);
			
			Casilla casilla = new Casilla(f,c);
			
			Comando comando = new CrearCelulaCompleja(casilla);
				
			return comando;
			
		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULACOMPLEJA F C: crea una nueva célula compleja en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}

}
