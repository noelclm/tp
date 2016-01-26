package celulas;

import java.io.PrintWriter;

import logica.Casilla;
import logica.Celula;
import logica.Posicion;
import logica.Superficie;

/**
 * Clase que gestiona una celula simple que hereda de Celula.
 */
public class CelulaSimple implements Celula{

	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int pasosSinMover;
	private int pasosDados;
	private final int MAX_PASOS_SIN_MOVER = 2;
	private final int PASOS_REPRODUCCION = 3;
	
	/**
	 * Constructor por defecto.
	 */
	public CelulaSimple() {
		
		this.pasosSinMover = 0;
		this.pasosDados = 0;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param pasosDados Pasos dados por la celula.
	 * @param pasosSinMover Pasos sin mover por la celula.
	 */
	public CelulaSimple(int pasosDados, int pasosSinMover) {
		
		this.pasosSinMover = pasosSinMover;
		this.pasosDados = pasosDados;
		
	}

	@Override
	public void guardar (PrintWriter pw){
		pw.println("simple " + this.pasosDados + " " + this.pasosSinMover);
		
	}

	
	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie) {
		
		Casilla casillaFinal = null;
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int filaCasillaInicial = casillaInicial.getFila();
		int columnaCasillaInicial = casillaInicial.getColumna();
		
		Posicion posicionInicial = new Posicion(filaCasillaInicial,columnaCasillaInicial);
		
		// Miramos si tiene posiciones adyacentes y cuales son
		int numPosiciones = posicionInicial.numPosiciones(filas-1, columnas-1);
		Posicion[] posicionesAdyacentes = posicionInicial.adyacencia(filas, columnas);
		
		// Miramos si tiene posiciones vacias adyacentes
		int numPosicionesVacias = superficie.cantidadPosicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones);
		
		// Si se puede mover
		if(numPosicionesVacias>0){ 
			
			// Miramos cuales son las posiciones vacias adyacentes
			Posicion[] posicionesVacias = superficie.posicionesAdyacentesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
			
			// Cogemos una posicion aleatoria de las posiciones adyacentes vacias
			int numAleatorio = (int)(Math.random()*numPosicionesVacias-1);
			int filaCasillaFinal = posicionesVacias[numAleatorio].getX();
			int columnaCasillaFinal = posicionesVacias[numAleatorio].getY();
			
			//Movemos la celula a la casilla seleccionada
			casillaFinal = new Casilla(filaCasillaFinal,columnaCasillaFinal);
			texto.append("->Célula simple en ("+filaCasillaInicial+","+columnaCasillaInicial+") se mueve a ("+filaCasillaFinal+","+columnaCasillaFinal+")"+LINE_SEPARATOR);
			superficie.moverCelula(casillaInicial, casillaFinal);
			
			// Miramos si tiene que reproducirse
			if (this.reproducirse()){
				this.reiniciaPasosReproduccion();
				superficie.crearCelulaSimple(casillaInicial);
				texto.append("->Nace una nueva célula simple en ("+filaCasillaInicial+","+columnaCasillaInicial+") cuyo padre ha sido ("+filaCasillaFinal+","+columnaCasillaFinal+")"+LINE_SEPARATOR);
			}else
				this.sumPasosDados();
			
		}else{ // Si no se puede mover
			
			// Comprobamos que si ha llegado al limite de pasos sin moverse
			if (this.muertePorInactividad()){
				texto.append("->Muere la célula simple de la casilla en ("+filaCasillaInicial+","+columnaCasillaInicial+")  por inactividad"+LINE_SEPARATOR);
				superficie.eliminarCelula(casillaInicial);
			}
			// Comprobamos si ha llegado al limite de pasos que tiene que dar para reproducirse
			else if (this.reproducirse()){
				texto.append("->Muere la célula simple de la casilla en ("+filaCasillaInicial+","+columnaCasillaInicial+")  por no poder reproducirse"+LINE_SEPARATOR);
				superficie.eliminarCelula(casillaInicial);
			}
			else{ 
				texto.append("->La célula simple en ("+filaCasillaInicial+","+columnaCasillaInicial+") no se ha podido mover"+LINE_SEPARATOR);
				casillaFinal = casillaInicial;
				this.sumPasosSinMover();
			}

		} 
		
		return casillaFinal;
		
	}

	@Override
	public boolean esComestible() {
		return true;
	}
	
	/**
	 * Devuelve un string para pintar la celula simple en el tablero.
	 * @return String
	 */
	public String toString(){
		return " X ";
	}
	
	/**
	 * Suma uno al numero de pasos dados.
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
	 * Suma uno al numero de pasos sin mover.
	 */
	private void sumPasosSinMover(){
		this.pasosSinMover++;
	}
	
	/**
	 * Metodo que devuelve true si ha llegado al limite de pasos sin mover o false si no.
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
