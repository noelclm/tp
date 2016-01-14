package excepciones;

public class MundoException extends NoelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MundoException(){
		super("No ha introducido bien las coordenadas.");
	}
	
	public MundoException(String s){
		super("No ha introducido bien las coordenadas. " + s);
	}
	
}
