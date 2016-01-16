package excepciones;

/**
 * Excepción para cuando sale algún error de escritura o lectura en disco.
 */
public class FalloIOException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public FalloIOException(){
		super("Error en el IO.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public FalloIOException(String s){
		super("Error en el IO. " + s);
	}
}
