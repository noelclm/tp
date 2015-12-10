package logica;

/**
 * Clase que gestiona una celula compleja que hereda de Celula.
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
		
		Casilla casillaFinal = null;
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		int f2=(int)(Math.random()*filas-1);
		int c2=(int)(Math.random()*columnas-1);
		this.texto = "";
		
		// Si se puede mover
		if(!superficie.comprobarCasilla(f2, c2) || superficie.esComestible(f2, c2)){
			
			casillaFinal = new Casilla(f2,c2);
			
			// Si come
			if(superficie.comprobarCasilla(f2, c2) && superficie.esComestible(f2, c2)){
				
				this.comidas++;
				this.texto = this.texto + "->Celula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --COME--"+LINE_SEPARATOR;

				// Si muere por comer mucho
				if (this.MAX_COMER == this.comidas){
					this.texto = this.texto + "->Explota la celula compleja en ("+f2+","+c2+")"+LINE_SEPARATOR;
					superficie.eliminarCelula(casillaFinal); // Eliminamos la celula que se va a comer
					casillaFinal = null; // Ponemos como que no puede moverse para que se elimine la celula
				}else
					superficie.moverCelula(casillaInicial, casillaFinal);
		
			}else{ // Si no come
				this.texto = this.texto + "->Celula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --NO COME--"+LINE_SEPARATOR;
				superficie.moverCelula(casillaInicial, casillaFinal);
			}
			
			// Miramos si tiene que reproducirse
			if (this.reproducirse() && this.MAX_COMER != this.comidas){
				this.reiniciaPasosReproduccion();
				superficie.crearCelulaCompleja(casillaInicial, this.MAX_PASOS_SIN_MOVER, this.PASOS_REPRODUCCION, this.MAX_COMER);
				this.texto = this.texto + "->Nace una nueva celula compleja en ("+f+","+c+") cuyo padre ha sido ("+f2+","+c2+")"+LINE_SEPARATOR;
			}else
				this.sumPasosDados();

		}else{ // Si no se puede mover
			
			// Comprobamos que si ha llegado al limite de pasos sin moverse
			if (this.muertePorInactividad())
				this.texto = this.texto + "->Muere la celula compleja de la casilla en ("+f+","+c+") por inactividad"+LINE_SEPARATOR;
				
			// Comprobamos si ha llegado al limite de pasos que tiene que dar para reproducirse
			else if (this.reproducirse())
				this.texto = this.texto + "->Muere la celula compleja de la casilla en ("+f+","+c+") por no poder reproducirse"+LINE_SEPARATOR;
			
			else{ 
				this.texto = this.texto + "->La celula compleja en ("+f+","+c+") no se ha podido mover"+LINE_SEPARATOR;
				casillaFinal = casillaInicial;
			}

			this.sumPasosSinMover();

		} 
		
		return casillaFinal;
		
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
