package excepciones;

/**
 * Excepcion para cuando el formato numerico no es valido.
 */
public class FormatoNoValidoException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public FormatoNoValidoException(){
		super("Formato numérico incorrecto.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public FormatoNoValidoException(String s){
		super("Formato numérico incorrecto, " + s);
	}
	
}


