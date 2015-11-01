package controlador.mundo;

import controlador.mundo.superficie.Superficie;

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
	
	/**
	 * Crea una celula en las coordenadas.
	 */
	public boolean crearCelula(int f, int c){
		
		if(superficie.crearCelula(f,c,3,1)) // TODO Mirar de donde se sacan los datos de maxPasosSinMover, pasosReproduccion
			return true;
		else
			return false;

	}
	
	/**
	 * Devuelve la superficie en un string para dibujarlo.
	 */
	public String imprimirSuperficie(){
		
		return superficie.toString();
		
	}

}
