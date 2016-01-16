package excepciones;

public class FormatoNoValidoException extends MundoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormatoNoValidoException(){
		super("Formato numérico incorrecto.");
	}
	
	public FormatoNoValidoException(String s){
		super("Formato numérico incorrecto, " + s);
	}
	
}


