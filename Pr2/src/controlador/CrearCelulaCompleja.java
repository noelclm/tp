package controlador;

import logica.Casilla;
import logica.Mundo;

/**
 * Comando CrearCelulaCompleja - Crea una celula compleja en el tablero
 */
public class CrearCelulaCompleja extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;

	/**
	 * Constructor por defecto
	 */
	public CrearCelulaCompleja() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado
	 * @param casilla Casilla del tablero donde se quiere crear la celula
	 */
	public CrearCelulaCompleja(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.crearCelulaSimple(this.casilla) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("crearcelulacompleja") || (cadenaComando[0].equals("crear") && cadenaComando[1].equals("celula") && cadenaComando[2].equals("compleja") )){
			
			int num1 = 1;
			int num2 = 2;
			if(cadenaComando[0].equals("crear")){
				num1 = 3;
				num2 = 4;
			}
			
			int f = Integer.parseInt(cadenaComando[num1]); 
			int c = Integer.parseInt(cadenaComando[num2]);
			
			Casilla casilla = new Casilla(f,c);
			
			Comando comando = new CrearCelulaCompleja(casilla);
				
			return comando;
			
		}
		return null;
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULACOMPLEJA F C: crea una nueva celula compleja en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}

}
