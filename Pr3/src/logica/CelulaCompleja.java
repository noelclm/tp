package logica;

/**
 * Clase que gestiona una célula compleja que hereda de Celula.
 */
public class CelulaCompleja extends Celula {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int comidas;
	private final int MAX_COMER = 2;

	/**
	 * Constructor por defecto
	 */
	public CelulaCompleja() {

		this.esComestible = false;
		this.comidas=0;
		
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie) {

		int filaCasillaInicial = casillaInicial.getFila();
		int columnaCasillaInicial = casillaInicial.getColumna();
		int filaCasillaFinal = (int)(Math.random()*superficie.getFilas()-1);
		int columnaCasillaFinal = (int)(Math.random()*superficie.getColumnas()-1);
		Casilla casillaFinal = new Casilla(filaCasillaFinal,columnaCasillaFinal);
		
		// Si se puede mover
		if(!superficie.comprobarCasilla(casillaFinal) || superficie.esComestible(casillaFinal)){

			// Si come
			if(superficie.comprobarCasilla(casillaFinal) && superficie.esComestible(casillaFinal)){
				
				superficie.moverCelula(casillaInicial, casillaFinal);
				this.comidas++;
				texto.append("->Célula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") se mueve a ("+filaCasillaFinal+","+columnaCasillaFinal+") --COME--"+LINE_SEPARATOR);

				// Si muere por comer mucho
				if (this.MAX_COMER == this.comidas){
					texto.append("->Explota la célula compleja en ("+filaCasillaFinal+","+columnaCasillaFinal+")"+LINE_SEPARATOR);
					superficie.eliminarCelula(casillaFinal); 
				}
					
			}else{ // Si no come
				texto.append("->Célula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") se mueve a ("+filaCasillaFinal+","+columnaCasillaFinal+") --NO COME--"+LINE_SEPARATOR);
				superficie.moverCelula(casillaInicial, casillaFinal);
			}

		}else{ // Si no se puede mover

			texto.append("->La célula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") no se ha podido mover"+LINE_SEPARATOR);
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
