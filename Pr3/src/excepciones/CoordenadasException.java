package excepciones;

/**
 * Excepción para cuando no se introducen las coordenadas correctamente.
 */
public class CoordenadasException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public CoordenadasException(){
		super("No ha introducido bien las coordenadas.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public CoordenadasException(String s){
		super("No ha introducido bien las coordenadas. " + s);
	}
	
}
