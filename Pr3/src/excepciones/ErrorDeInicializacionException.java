package excepciones;

public class ErrorDeInicializacionException extends MundoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorDeInicializacionException(){
		super("No se ha podido inicializar.");
	}
	
	public ErrorDeInicializacionException(String s){
		super("No se ha podido inicializar. " + s);
	}
}
