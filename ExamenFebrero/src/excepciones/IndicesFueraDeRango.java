package excepciones;

/**
 * Excepcion para cuando los indices se salen fuera del tablero.
 */
public class IndicesFueraDeRango extends MundoException{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public IndicesFueraDeRango(){
		super("Fuera de rango.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public IndicesFueraDeRango(String s){
		super(s);
	}
	

}
