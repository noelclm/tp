package logica;

public class CelulaSimple extends Celula{

	public CelulaSimple(int pasosSinMover, int pasosReproduccion) {
		super(pasosSinMover, pasosReproduccion);
		this.esComestible = true;
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
