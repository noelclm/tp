package logica;

/**
 * Clase que gestiona una celula simple que hereda de Celula.
 */
public class CelulaSimple extends Celula{

	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int pasosSinMover;
	private int pasosDados;
	private int MAX_PASOS_SIN_MOVER;
	private int PASOS_REPRODUCCION;
	
	/**
	 * Constructor parametrizado.
	 * @param pasosSinMover Pasos que puede estar sin moverse.
	 * @param pasosReproduccion Pasos que tiene que dar para reproducirse.
	 */
	public CelulaSimple(int pasosSinMover, int pasosReproduccion) {
		
		super();
		this.MAX_PASOS_SIN_MOVER = pasosSinMover;
		this.PASOS_REPRODUCCION = pasosReproduccion;
		this.pasosSinMover = 0;
		this.pasosDados = 0;
		this.esComestible = true;
		
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie) {
		
		Casilla casillaFinal = null;
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		this.texto = "";
		
		Posicion posicionInicial = new Posicion(f,c);
		
		// Miramos si tiene posiciones vacias adyacentes
		int numPosiciones = posicionInicial.numPosiciones(filas-1, columnas-1);
		Posicion[] posicionesAdyacentes = new Posicion[numPosiciones];
		posicionesAdyacentes = posicionInicial.adyacencia(filas, columnas);
		
		int numPosicionesVacias = superficie.cantidadPosicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones);
		
		// Si se puede mover
		if(numPosicionesVacias>0){ 
			
			Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
			posicionesVacias = superficie.posicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
			
			// Cogemos una posicion aleatoria de las posiciones adyacentes vacias
			int numAleatorio = (int)(Math.random()*numPosicionesVacias-1);
			int f2 = posicionesVacias[numAleatorio].getX();
			int c2 = posicionesVacias[numAleatorio].getY();
			
			casillaFinal = new Casilla(f2,c2);
			this.texto = this.texto + "->Célula simple en ("+f+","+c+") se mueve a ("+f2+","+c2+")"+LINE_SEPARATOR;
			superficie.moverCelula(casillaInicial, casillaFinal);
			
			// Miramos si tiene que reproducirse
			if (this.reproducirse()){
				this.reiniciaPasosReproduccion();
				superficie.crearCelulaSimple(casillaInicial, this.MAX_PASOS_SIN_MOVER, this.PASOS_REPRODUCCION);
				this.texto = this.texto + "->Nace una nueva célula simple en ("+f+","+c+") cuyo padre ha sido ("+f2+","+c2+")"+LINE_SEPARATOR;
			}else
				this.sumPasosDados();
			
			
				
		}else{ // Si no se puede mover
			
			// Comprobamos que si ha llegado al limite de pasos sin moverse
			if (this.muertePorInactividad())
				this.texto = this.texto + "->Muere la célula simple de la casilla en ("+f+","+c+")  por inactividad"+LINE_SEPARATOR;
				
			// Comprobamos si ha llegado al limite de pasos que tiene que dar para reproducirse
			else if (this.reproducirse())
				this.texto = this.texto + "->Muere la célula simple de la casilla en ("+f+","+c+")  por no poder reproducirse"+LINE_SEPARATOR;
			
			else{ 
				this.texto = this.texto + "->La célula simple en ("+f+","+c+") no se ha podido mover"+LINE_SEPARATOR;
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
	 * Devuelve un string para pintar la célula simple en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " X ";
		
	}
	
	/**
	 * Suma uno al número de pasos dados.
	 */
	private void sumPasosDados(){
		
		this.pasosDados++;
		
	}
	
	/**
	 * Metodo que reinicia los pasos dados.
	 */
	private void reiniciaPasosReproduccion(){
		
		this.pasosDados = 0;
		
	}	
	
	/**
	 * Suma uno al número de pasos sin mover.
	 */
	private void sumPasosSinMover(){
		
		this.pasosSinMover++;
		
	}
	
	/**
	 * Metodo que devuelve true si ha llegado al límite de pasos sin mover o false si no.
	 * @return boolean
	 */
	private boolean muertePorInactividad(){
		
		if (this.pasosSinMover >= this.MAX_PASOS_SIN_MOVER)
			return true;
		
		else 
			return false;
		
	}
	
	/**
	 * Metodo que devuelve true si tiene que reproducirse o false si no.
	 * @return boolean
	 */
	private boolean reproducirse(){
			
		if (this.pasosDados == this.PASOS_REPRODUCCION)
			return true;
		
		else
			return false;
		
	}

}
