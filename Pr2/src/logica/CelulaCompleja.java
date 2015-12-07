package logica;

/**
 * 
 */
public class CelulaCompleja extends Celula {
	private int comidas;
	private final int MAX_COMER;

	/**
	 * Constructor parametrizado
	 * @param pasosSinMover Pasos que puede estar sin moverse.
	 * @param pasosReproduccion Pasos que tiene que dar para reproducirse.
	 * @param max_comer Maximas celulas que puede comer.
	 */
	public CelulaCompleja(int pasosSinMover, int pasosReproduccion, int max_comer) {
		super(pasosSinMover, pasosReproduccion);
		this.esComestible = false;
		this.MAX_COMER = max_comer;
		this.comidas=0;
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie) {
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		Casilla casilla = null;
		
		int f=(int)(Math.random()*filas);
		int c=(int)(Math.random()*columnas);
		
		if(!superficie.comprobarCasilla(f, c)|| superficie.esComestible(f, c))
			casilla  = new Casilla(f,c);
			
		return casilla;
			
		
	}
	public void sumComidas(){
		this.comidas++;
	
	}
	public boolean muertePorComida(){
		if(this.MAX_COMER==this.comidas){
			return true;
		}
		else
			return false;
		
		
			
	}

	@Override
	public boolean esComestible() {
		return this.esComestible;
	}
	
	/**
	 * Devuelve un string para pintar la celula compleja en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " * ";
		
	}

}
