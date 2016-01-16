package excepciones;

public class FicheroErroneoException extends MundoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FicheroErroneoException(){
		super("El fichero contiene errores en la sintaxis.");
	}
	
	public FicheroErroneoException(String s){
		super("El fichero contiene errores en la sintaxis. " + s);
	}
	
}
