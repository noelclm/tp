package comandos;

import controlador.Comando;
import controlador.Controlador;
import excepciones.FormatoNoValidoException;
import excepciones.MundoException;
import logica.Casilla;

/**
 * Comando CrearCelulaSimple - Crea una célula simple en el tablero.
 */
public class CrearCelula extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;
	
	/**
	 * Constructor por defecto.
	 */
	public CrearCelula() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde se quiere crear la célula.
	 */
	public CrearCelula(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.crearCelula(this.casilla) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws MundoException {
		
		if (cadenaComando[0].equals("crearcelula") && cadenaComando.length == 3){
			try{
				int fila = Integer.parseInt(cadenaComando[1]); 
				int columna = Integer.parseInt(cadenaComando[2]);
				
				Casilla casilla = new Casilla(fila,columna);
				
				Comando comando = new CrearCelula(casilla);
					
				return comando;
			}catch(NumberFormatException nfe){
				throw new FormatoNoValidoException("en las coordenadas.");
			}

		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULA F C: crea una nueva célula en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}
	


}
