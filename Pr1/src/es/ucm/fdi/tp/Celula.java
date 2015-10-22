package es.ucm.fdi.tp;

/**
 * Representa una celula.
 */
public class Celula {

	private int pasosSinMover;
	private int pasosDados;
	private final int MAX_PASOS_SIN_MOVER;
	private final int PASOS_REPRODUCCION;
	
	/**
	 * Constructor por defecto.
	 * @param pasosSinMover
	 * @param pasosReproduccion
	 */
	public Celula(int pasosSinMover, int pasosReproduccion){
		
		this.MAX_PASOS_SIN_MOVER = pasosSinMover;
		this.PASOS_REPRODUCCION = pasosReproduccion;
		this.pasosSinMover = 0;
		this.pasosDados = 0;
		
	}
	
	/**
	 * Metodo que suma uno al numero de pasos dados.
	 */
	public void sumPasosDados(){
		
		this.pasosSinMover++;
		
	}
	
	/**
	 * Metodo que suma uno al numero de pasos sin mover.
	 */
	public void sumPasosSinMover(){
		
		this.pasosDados++;
		
	}
	
	/**
	 * Metodo que devuelve el numero de passos que ha dado la celula.
	 * @return Numero de pasos dados.
	 */
	public int getPasosDados(){
		
		return this.pasosDados;
		
	}
	
	/**
	 * Metodo que devuelve el numero de pasos que lleva sin moverse la celula.
	 * @return Numero de pasos sin mover.
	 */
	public int getPasosSinMover(){
		
		return this.pasosSinMover;
		
	}
	
	/**
	 * Metodo que devuelve el numero de pasos que queda para que la celula se reproduzca.
	 * @return Numero de pasos que queda para reproducirse.
	 */
	public int getQuedaParaReproducirse(){
		
		return this.PASOS_REPRODUCCION-this.pasosDados;
		
	}
	
	/**
	 * Metodo que devuelve el numero de pasos que queda para que la celula muera.
	 * @return Numero de pasos que queda para morir.
	 */
	public int getPasosParaMorir(){
		
		return this.MAX_PASOS_SIN_MOVER-this.pasosSinMover;
		
	}
	
	/**
	 * Metodo que devuelve false si ha llegado al limite de pasos sin mover o true si no.
	 * @return False si ha llegado al limite de pasos sin mover.
	 */
	public boolean limitePasosSinMover(){
		
		if (this.pasosSinMover == this.MAX_PASOS_SIN_MOVER)
			
			return false;
		
		else 
			
			return true;
		
	}
	
	/**
	 * Metodo que devuelve true si tiene que reproducirse o false si no.
	 * @return True si tiene que reproducirse.
	 */
	public boolean limitePasosDados(){
		
		if (this.pasosDados == this.PASOS_REPRODUCCION)
			
			return true;
		
		else
			
			return false;
		
	}

}
