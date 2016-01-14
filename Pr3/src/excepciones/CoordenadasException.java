package excepciones;

public class CoordenadasException extends MundoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordenadasException(){
		super("No ha introducido bien las coordenadas.");
	}
	
	public CoordenadasException(String s){
		super("No ha introducido bien las coordenadas. " + s);
	}
	
}
