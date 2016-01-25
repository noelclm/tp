package excepciones;

/**
 * Excepcion para cuando no se ha creado un mundo y se trata de usar.
 */
public class SinMundoException extends MundoException{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public SinMundoException(){
		super("Mundo no creado.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public SinMundoException(String s){
		super("Mundo no creado. " + s);
	}
	
}
