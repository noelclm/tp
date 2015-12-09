package logica;

/**
 * Clase que gestiona una celula.
 */
public abstract class Celula {

	private int pasosSinMover;
	private int pasosDados;
	private final int MAX_PASOS_SIN_MOVER;
	private final int PASOS_REPRODUCCION;
	protected boolean esComestible;
	
	/**
	 * Mira donde se puede mover la celula y devuelve la casilla.
	 * @param casillaInicial Posición del tablero.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public abstract Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public abstract boolean esComestible();
	
	/**
	 * Devuelve si muere por comer el maximo de celulas permitidas.
	 * @return boolean
	 */
	public abstract boolean muertePorComida();
	
	/**
	 * Constructor.
	 * @param pasosSinMover Numero de pasos sin mover que tiene la celula.
	 * @param pasosReproduccion Numero de pasos para que la celula se reproduzca.
	 */
	public Celula(int pasosSinMover, int pasosReproduccion){
		
		this.MAX_PASOS_SIN_MOVER = pasosSinMover;
		this.PASOS_REPRODUCCION = pasosReproduccion;
		this.pasosSinMover = 0;
		this.pasosDados = 0;
		
	}
	
	/**
	 * Suma uno al numero de pasos dados.
	 */
	public void sumPasosDados(){
		
		this.pasosDados++;
		
	}
	
	/**
	 * Metodo que reinicia los pasos dados.
	 */
	public void reiniciaPasosReproduccion(){
		
		this.pasosDados = 0;
		
	}	
	
	/**
	 * Suma uno al numero de pasos sin mover.
	 */
	public void sumPasosSinMover(){
		
		this.pasosSinMover++;
		
	}
	
	/**
	 * Metodo que devuelve true si ha llegado al limite de pasos sin mover o true si no.
	 * @return boolean
	 */
	public boolean muertePorInactividad(){
		
		if (this.pasosSinMover == this.MAX_PASOS_SIN_MOVER)
			return true;
		
		else 
			return false;
		
	}
	
	/**
	 * Metodo que devuelve true si tiene que reproducirse o false si no.
	 * @return boolean
	 */
	public boolean reproducirse(){
			
		if (this.pasosDados == this.PASOS_REPRODUCCION)
			return true;
		
		else
			return false;
		
	}

}
