package excepciones;

public class IndicesFueraDeRango extends MundoException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndicesFueraDeRango(){
		super("Fuera de rango.");
	}
	
	public IndicesFueraDeRango(String s){
		super(s);
	}
	

}
