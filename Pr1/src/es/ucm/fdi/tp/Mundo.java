package es.ucm.fdi.tp;

/**
 * Clase encargada de gestionar los movimientos.
 */
public class Mundo {
	
	private Superficie superficie;
	
	/**
	 * Constructor por defecto.
	 */
	public Mundo(int nf, int nc){
		this.superficie =new Superficie(nf,nc);
	}
	
	/**
	 * Recorre la superficie y ejecuta los pasos acorde a las reglas del juego.
	 */
	public void evoluciona(){
		
	}

}
