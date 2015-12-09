package logica;

/**
 * Clase que gestiona una celula simple que hereda de Celula.
 */
public class CelulaSimple extends Celula{

	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
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
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		
		Posicion posicionInicial = new Posicion(f,c);
		
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
			
			this.texto = this.texto + "->Celula simple en ("+f+","+c+") se mueve a ("+f2+","+c2+")"+LINE_SEPARATOR;
			
			if (this.reproducirse()){
				this.reiniciaPasosReproduccion();
				this.texto = this.texto + "->Nace nueva celula en ("+f+"-"+c+") cuyo padre ha sido ("+f2+","+c2+")"+LINE_SEPARATOR;
			}else
				this.sumPasosDados();
			
			return casillaFinal;
				
		}else{ // Si no se puede mover
			
			// Comprobamos que si ha llegado al limite de pasos sin moverse
			if (this.muertePorInactividad())
				this.texto = this.texto + "->Muere la celula de la casilla "+f+"-"+c+" por inactividad"+LINE_SEPARATOR;
				
			// Comprobamos si ha llegado al limite de pasos que tiene que dar para reproducirse
			else if (this.reproducirse())
				this.texto = this.texto + "->Muere la celula de la casilla "+f+"-"+c+" por no poder reproducirse"+LINE_SEPARATOR;
			
			else 
				this.texto = this.texto + "->La celula "+f+"-"+c+" no se ha podido mover"+LINE_SEPARATOR;
				
			this.sumPasosSinMover();
				
			return null;

		} 
		
	}

	@Override
	public boolean esComestible() {
		
		return this.esComestible;
		
	}

	@Override
	public boolean muertePorComida() {
		
		return false;
	
	}
	
	/**
	 * Devuelve un string para pintar la celula simple en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " X ";
		
	}

}
