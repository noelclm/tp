package logica;

public class CelulaCompleja extends Celula {
	
	private final int MAX_COMER;

	public CelulaCompleja(int pasosSinMover, int pasosReproduccion, int max_comer) {
		super(pasosSinMover, pasosReproduccion);
		this.esComestible = false;
		this.MAX_COMER = max_comer;
	}

	@Override
	public Posicion ejecutaMovimiento(int f, int c, Superficie superficie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esComestible() {
		return this.esComestible;
	}

}
