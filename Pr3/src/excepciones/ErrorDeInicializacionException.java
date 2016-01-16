package excepciones;

/**
 * Excepción para cuando se inicializa un tipo de mundo.
 */
public class ErrorDeInicializacionException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public ErrorDeInicializacionException(){
		super("No se ha podido inicializar.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public ErrorDeInicializacionException(String s){
		super("No se ha podido inicializar. " + s);
	}
}
