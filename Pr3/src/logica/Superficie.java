package logica;

import java.io.PrintWriter;

import excepciones.FicheroErroneoException;
import excepciones.MundoException;


/**
 * Clase que gestiona el tablero de juego.
 */
public class Superficie {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int filas;
	private int columnas;
	private Celula[][] superficie;
	
	/**
	 * Constructor parametrizado.
	 * @param numFilas Número de filas de la superficie.
	 * @param numColumnas Número de columnas de la superficie.
	 */
	public Superficie(int numFilas, int numColumnas){
		
		this.filas = numFilas;
		this.columnas = numColumnas;
		this.superficie = new Celula[numFilas][numColumnas];
		
		for (int f=0; f<numFilas; f++){
			for (int c=0; c<numColumnas; c++){
				
				this.superficie[f][c]= null;
				
			}
		}
		
	}
	
	/**
	 * Inicia la superficie con un numero de células que le entra. Devuelve un boolean dependiendo si ha podido o no.
	 * @param numCelulas Número de celulas con el que se inicializa la superficie.
	 * @return boolean
	 */
	public boolean iniciarSuperficie (int numCelulas){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulas <= this.filas*this.columnas){
			
			while (numCelulasPuestas<numCelulas){
				
				int fila = (int)(Math.random()*this.filas);
				int columna = (int)(Math.random()*this.columnas);
				int tipo = (int)(Math.random()*2);
				
				Casilla casilla = new Casilla(fila,columna);
				
				if (tipo==0){
					
					if(this.crearCelulaSimple(casilla))
						numCelulasPuestas++;
					
				}else{
					
					if(this.crearCelulaCompleja(casilla))
						numCelulasPuestas++;
					
				}
				
			}
				
			return true;
			
		}else
			return false;
		
	}
	
	/**
	 * Inicia la superficie con un numero de células que le entra. Devuelve un boolean dependiendo si ha podido o no.
	 * @param numCelulasSimples Número de células simples con el que se inicializa la superficie.
	 * @param numCelulasComplejas Número de células complejas con el que se inicializa la superficie.
	 * @return boolean
	 */
	public boolean inicializaSuperficie (int numCelulasSimples, int numCelulasComplejas){
		
		this.vaciar();
		
		int numCelulasSimplesPuestas = 0;
		int numCelulasComplejasPuestas = 0;
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulasSimples+numCelulasComplejas <= this.filas*this.columnas){
			
			//Pone las celulas simples
			while (numCelulasSimplesPuestas<numCelulasSimples){
				
				int fila = (int)(Math.random()*this.filas);
				int columna = (int)(Math.random()*this.columnas);

				Casilla casilla = new Casilla(fila,columna);
				
				if(this.crearCelulaSimple(casilla))
					numCelulasSimplesPuestas++;

			}
			
			// Pone las celulas complejas
			while (numCelulasComplejasPuestas<numCelulasComplejas){
				
				int fila = (int)(Math.random()*this.filas);
				int columna = (int)(Math.random()*this.columnas);

				Casilla casilla = new Casilla(fila,columna);
				
				if(this.crearCelulaCompleja(casilla))
					numCelulasComplejasPuestas++;

			}
				
			return true;
			
		}else
			return false;
		
	}
	
	/**
	 * Reproduce los movimientos de las células sobre el tablero y devuelve en un String los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		StringBuilder texto = new StringBuilder();
		
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];

		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				posicionesPasadas[f][c] = false;
			} 
		} 

		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				// Comprobamos si hemos pasado ya por esa casilla
				if(posicionesPasadas[f][c]==false){
					
					posicionesPasadas[f][c] = true;
					
					// Comprobamos que la casilla no esta vacia
					if(this.superficie[f][c]!=null){ 
						
						Casilla casillaInicial = new Casilla(f,c);
						Casilla casillaFinal = this.superficie[f][c].ejecutaMovimiento(casillaInicial, texto, this); 
						
						if (casillaFinal != casillaInicial && casillaFinal != null)
							posicionesPasadas[casillaFinal.getFila()][casillaFinal.getColumna()] = true;

					} // if(this.superficie[f][c]!=null)
					
				} // if(posicionesPasadas[f][c] == false)	
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return texto.toString();

	}
	
	/**
	 * Crea una célula simple en una posición de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posición del tablero.
	 * @return boolean
	 */
	public boolean crearCelulaSimple (Casilla casilla){
		
		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		
		if (fila>=0 && fila<this.filas && columna>=0 && columna<this.columnas){
			if (this.superficie[fila][columna]==null){
				this.superficie[fila][columna] = new CelulaSimple();	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Crea una célula compleja en una posición de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posición del tablero.
	 * @return boolean
	 */
	public boolean crearCelulaCompleja (Casilla casilla){

		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		
		if (fila>=0 && fila<this.filas && columna>=0 && columna<this.columnas){
			if (this.superficie[fila][columna]==null){
				this.superficie[fila][columna] = new CelulaCompleja();	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Elimina una célula del tablero. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posición del tablero.
	 * @return boolean
	 */
	public boolean eliminarCelula (Casilla casilla){
		
		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		
		if (fila>=0 && fila<this.filas && columna>=0 && columna<this.columnas){
			if (this.superficie[fila][columna]!=null){
				this.superficie[fila][columna]=null;
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Vacia el tablero entero.
	 */
	public void vaciar(){
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				this.superficie[f][c]= null;
			}
		}
		
	}
	
	/**
	 * Devuelve la cantidad de filas de la superficie.
	 * @return int
	 */
	public int getFilas(){
		return this.filas;
	}
	
	/**
	 * Devuelve la cantidad de columnas de la superficie
	 * @return int
	 */
	public int getColumnas(){
		return this.columnas;
	}
	
	/**
	 * Comprueba que la casilla esta vacía.
	 * @param casilla Posición del tablero.
	 * @return boolean
	 */
	public boolean comprobarCasilla(Casilla casilla){
		
		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		if (this.superficie[fila][columna]==null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * Mueve una célula de una casilla a otra en el tablero.
	 * @param casillaInicial Posición inicial del tablero.
	 * @param casillaFinal Posición final del tablero.
	 */
	public void moverCelula(Casilla casillaInicial, Casilla casillaFinal){

		this.superficie[casillaFinal.getFila()][casillaFinal.getColumna()] = this.superficie[casillaInicial.getFila()][casillaInicial.getColumna()];
		this.superficie[casillaInicial.getFila()][casillaInicial.getColumna()] = null;
		
	}
	
	/**
	 * Devuelve si la célula se puede comer.
	 * @param casilla Posición del tablero.
	 * @return boolean
	 */
	public boolean esComestible(Casilla casilla){

		return this.superficie[casilla.getFila()][casilla.getColumna()].esComestible();
		
	}

	/**
	 * Saca la cantidad de posiciones vacías que tiene una posición concreta.
	 * @param posicionesAdyacentes Array de posiciones adyacentes.
	 * @param numPosiciones Número de posiciones adyancentes
	 * @return int
	 */
	public int cantidadPosicionesAdyacentesVacias(Posicion[] posicionesAdyacentes, int numPosiciones){
		
		int num = 0;
		
		for(int i=0; i<numPosiciones; i++){
			int fila = posicionesAdyacentes[i].getX();
			int columna = posicionesAdyacentes[i].getY();
			if(superficie[fila][columna]==null)
				num++;
		}
		
		return num;
		
	}
	
	/**
	 * Saca las posiciones vacías que tiene una posición concreta.
	 * @param posicionesAdyacentes Array de posiciones adyacentes.
	 * @param numPosiciones Número de posiciones adyancentes.
	 * @param numPosicionesVacias Número de posiciones adyancentes vacías.
	 * @return Posicion[]
	 */
	public Posicion[] posicionesAdyacentesVacias(Posicion[] posicionesAdyacentes, int numPosiciones, int numPosicionesVacias){
		
		int num = 0;
		Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
		for(int i=0; i<numPosiciones; i++){
			int fila = posicionesAdyacentes[i].getX();
			int columna = posicionesAdyacentes[i].getY();
			if(superficie[fila][columna]==null){
				posicionesVacias[num] = new Posicion(fila,columna);
				num++;
			}
		}
		
		return posicionesVacias;
		
	}
	
	/**
	 * Carga una célula simple de un fichero.
	 * @param casilla Posición del tablero.
	 * @param pasosDados Pasos que lleva dados la célula.
	 * @param pasosSinMover Pasos sin mover de la célula.
	 * @param numLinea Número de linea del fichero.
	 * @return boolean
	 * @throws MundoException Excepción de la que heredan las demás excepciones.
	 */
	public boolean cargarCelulaSimple(Casilla casilla, int pasosDados, int pasosSinMover, int numLinea) throws MundoException{
		
		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		
		if (fila>=0 && fila<this.filas && columna>=0 && columna<this.columnas){
			if(this.comprobarCasilla(casilla)){
				throw new FicheroErroneoException("Hay una célula repetida en la linea " + numLinea);
			}
			this.superficie[fila][columna] = new CelulaSimple(pasosDados, pasosSinMover);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Carga una célula compleja de un fichero.
	 * @param casilla Posición del tablero.
	 * @param vecesComido Número de veces que ha comido la célula.
	 * @param numLinea Número de linea del fichero.
	 * @return boolean
	 * @throws MundoException Excepción de la que heredan las demás excepciones.
	 */
	public boolean cargarCelulaCompleja(Casilla casilla, int vecesComido, int numLinea) throws MundoException{
		
		int fila = casilla.getFila();
		int columna = casilla.getColumna();
		
		if (fila>=0 && fila<this.filas && columna>=0 && columna<this.columnas){
			if(this.comprobarCasilla(casilla)){
				throw new FicheroErroneoException("Hay una célula repetida en la linea " + numLinea);
			}
			this.superficie[fila][columna] = new CelulaCompleja(vecesComido);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Guarda en un PrintWriter los datos de la superficie.
	 * @param pw Entra un PrintWrite para escribir en el fichero.
	 */
	public void guardar(PrintWriter pw) {
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				if (this.superficie[f][c]!= null){
					pw.print(f + " " + c + " ");
					this.superficie[f][c].guardar(pw);
				}
			}
		}
		
		
	}
	
	/**
	 * Genera el tablero en un String y lo devuelve para imprimirlo por pantalla.
	 * @return String
	 */
	public String toString(){
		
		String str = "";
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(this.superficie[f][c]!= null)
					str = str+this.superficie[f][c].toString();
				else
					str = str+" - ";
					
			}
			str = str+LINE_SEPARATOR;
		}
		
		return str;
		
	}
	
}
