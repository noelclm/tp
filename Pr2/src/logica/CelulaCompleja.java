package logica;

/**
 * 
 */
public class CelulaCompleja extends Celula {
	
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
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie) {
		// TODO Movimiento celula compleja
		return null;
	}

	@Override
	public boolean esComestible() {
		return this.esComestible;
	}

}
