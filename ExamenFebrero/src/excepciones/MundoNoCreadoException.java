package excepciones;

public class MundoNoCreadoException extends MundoException{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public MundoNoCreadoException(){
		super("El mundo aun no ha sido creado.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public MundoNoCreadoException(String s){
		super("El mundo aun no ha sido creado. "+s);
	}

}
