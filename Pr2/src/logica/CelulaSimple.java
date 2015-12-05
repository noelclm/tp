package logica;

/**
 * 
 */
public class CelulaSimple extends Celula{

	/**
	 * Constructor parametrizado.
	 * @param pasosSinMover Pasos que puede estar sin moverse.
	 * @param pasosReproduccion Pasos que tiene que dar para reproducirse.
	 */
	public CelulaSimple(int pasosSinMover, int pasosReproduccion) {
		super(pasosSinMover, pasosReproduccion);
		this.esComestible = true;
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie) {
		
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		
		Posicion posicionInicial = new Posicion(casillaInicial.getFila(),casillaInicial.getColumna());
		
		int numPosiciones = posicionInicial.numPosiciones(filas-1, columnas-1);
		Posicion[] posicionesAdyacentes = new Posicion[numPosiciones];
		posicionesAdyacentes = posicionInicial.adyacencia(filas, columnas);
		
		int numPosicionesVacias = superficie.cantidadPosicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones);
		
		if(numPosicionesVacias>0){ // Si se puede mover
			
			Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
			posicionesVacias = superficie.posicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
			
			int numAleatorio = (int)(Math.random()*numPosicionesVacias-1);
			int f2 = posicionesVacias[numAleatorio].getX();
			int c2 = posicionesVacias[numAleatorio].getY();
			
			Casilla casillaFinal = new Casilla(f2,c2);
			
			return casillaFinal;
				
		}else{ // Si no se puede mover
			
			return null;

		} 
		
	}

	@Override
	public boolean esComestible() {
		return this.esComestible;
	}
	/**
	 * Devuelve un string para pintar la celula simple en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " X ";
		
	}

}
