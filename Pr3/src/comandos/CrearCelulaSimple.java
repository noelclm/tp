package comandos;

import controlador.Comando;
import logica.Casilla;
import logica.Mundo;

/**
 * Comando CrearCelulaSimple - Crea una célula simple en el tablero.
 */
public class CrearCelulaSimple extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;
	
	/**
	 * Constructor por defecto.
	 */
	public CrearCelulaSimple() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde se quiere crear la célula.
	 */
	public CrearCelulaSimple(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.crearCelulaSimple(this.casilla) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("crearcelulasimple") && cadenaComando.length == 3){
			
			int f = Integer.parseInt(cadenaComando[1]); 
			int c = Integer.parseInt(cadenaComando[2]);
			
			Casilla casilla = new Casilla(f,c);
			
			Comando comando = new CrearCelulaSimple(casilla);
				
			return comando;
			
		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULASIMPLE F C: crea una nueva célula simple en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}
	


}