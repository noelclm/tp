package excepciones;

/**
 * Excepcion para cuando hay algun error de sintaxis en el fichero.
 */
public class FicheroErroneoException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public FicheroErroneoException(){
		super("El fichero contiene errores en la sintaxis.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public FicheroErroneoException(String s){
		super("El fichero contiene errores en la sintaxis. " + s);
	}
	
}
