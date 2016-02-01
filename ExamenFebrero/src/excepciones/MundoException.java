package excepciones;

/**
 * Excepcion de la que heredan las demas excepciones.
 */
public class MundoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public MundoException (){
		super();
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public MundoException (String s){
		super(s);
	}
	
}
