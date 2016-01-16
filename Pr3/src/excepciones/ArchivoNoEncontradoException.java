package excepciones;

/**
 * Excepción para cuando no encuentra un fichero.
 */
public class ArchivoNoEncontradoException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public ArchivoNoEncontradoException(){
		super("No se ha encontrado el fichero especificado.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public ArchivoNoEncontradoException(String s){
		super("No se ha encontrado el fichero especificado. " + s);
	}
}
