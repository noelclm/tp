package excepciones;

public class FalloIOException extends MundoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FalloIOException(){
		super("Error en el IO.");
	}
	
	public FalloIOException(String s){
		super("Error en el IO. " + s);
	}
}
