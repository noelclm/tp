package excepciones;

public class ArchivoNoEncontradoException extends MundoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArchivoNoEncontradoException(){
		super("No se ha encontrado el fichero especificado.");
	}
	
	public ArchivoNoEncontradoException(String s){
		super("No se ha encontrado el fichero especificado. " + s);
	}
}
