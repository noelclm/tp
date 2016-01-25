package comandos;

import controlador.Comando;
import controlador.Controlador;
import excepciones.FormatoNoValidoException;
import excepciones.MundoException;
import logica.Casilla;

/**
 * Comando EliminarCelula - Elimina una celula del tablero.
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
	 * @param casilla Casilla del tablero donde esta la celula que se quiere borrar.
	 */
	public EliminarCelula(Casilla casilla) {
		this.casilla = casilla;
	}
		
	@Override
	public String ejecuta(Controlador controlador) throws MundoException {
		
		return controlador.eliminarCelula(this.casilla) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws MundoException {

		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length == 3){
			try{
				int fila = Integer.parseInt(cadenaComando[1]); 
				int columna = Integer.parseInt(cadenaComando[2]);
				
				Casilla casilla = new Casilla(fila,columna);

				Comando comando = new EliminarCelula(casilla);
				
				return comando;
				
			}catch(NumberFormatException nfe){
				throw new FormatoNoValidoException("en las coordenadas.");
			}
			

		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " ELIMINARCELULA F C: elimina una célula de la posición (f,c) si es posible" + LINE_SEPARATOR;
		
	}

}
