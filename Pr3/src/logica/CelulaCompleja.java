package logica;

/**
 * Clase que gestiona una célula compleja que hereda de Celula.
 */
public class CelulaCompleja extends Celula {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int comidas;
	private final int MAX_COMER;

	/**
	 * Constructor parametrizado
	 * @param pasosSinMover Pasos que puede estar sin moverse.
	 * @param pasosReproduccion Pasos que tiene que dar para reproducirse.
	 * @param max_comer Máximas células que puede comer.
	 */
	public CelulaCompleja(int max_comer) {
		
		super();
		this.esComestible = false;
		this.MAX_COMER = max_comer;
		this.comidas=0;
		
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie) {
		
		Casilla casillaFinal = null;
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		int f2=(int)(Math.random()*filas-1);
		int c2=(int)(Math.random()*columnas-1);
		
		// Si se puede mover
		if(!superficie.comprobarCasilla(f2, c2) || superficie.esComestible(f2, c2)){
			
			casillaFinal = new Casilla(f2,c2);
			
			// Si come
			if(superficie.comprobarCasilla(f2, c2) && superficie.esComestible(f2, c2)){
				
				superficie.moverCelula(casillaInicial, casillaFinal);
				this.comidas++;
				texto.append("->Célula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --COME--"+LINE_SEPARATOR);

				// Si muere por comer mucho
				if (this.MAX_COMER == this.comidas){
					texto.append("->Explota la célula compleja en ("+f2+","+c2+")"+LINE_SEPARATOR);
					superficie.eliminarCelula(casillaFinal); 
				}
					
			}else{ // Si no come
				texto.append("->Célula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --NO COME--"+LINE_SEPARATOR);
				superficie.moverCelula(casillaInicial, casillaFinal);
			}

		}else{ // Si no se puede mover

			texto.append("->La célula compleja en ("+f+","+c+") no se ha podido mover"+LINE_SEPARATOR);
			casillaFinal = casillaInicial;

		} 
		
		return casillaFinal;
		
	}

	@Override
	public boolean esComestible() {
		
		return this.esComestible;
		
	}

	/**
	 * Devuelve un string para pintar la célula compleja en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " * ";
		
	}

}
