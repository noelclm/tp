package logica;


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
	 * Constructor.
	 * @param nf N�mero de filas de la superficie.
	 * @param nc N�mero de columnas de la superficie.
	 */
	public Superficie(int nf, int nc){
		
		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[nf][nc];
		
		for (int i=0; i<nf; i++){
			for (int j=0; j<nc; j++){
				
				this.superficie[i][j]= null;
				
			}
		}
		
	}
	
	/**
	 * Inicia la superficie con un numero de celululas que le entra. Devuelve un boolean dependiendo si ha podido o no.
	 * @param numCelulas Numero de celulas con el que se inicializa la superficie.
	 * @param maxPasosSinMover Numero de pasos sin mover que tiene la celula.
	 * @param pasosReproduccion Numero de pasos para que la celula se reproduzca.
	 * @param maxComer Numero de veces que puede comer una celula compleja.
	 * @return boolean
	 */
	public boolean iniciarSuperficie (int numCelulas, int maxPasosSinMover, int pasosReproduccion, int maxComer){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulas <= this.filas*this.columnas){
			
			while (numCelulasPuestas<numCelulas){
				
				int f = (int)(Math.random()*this.filas);
				int c = (int)(Math.random()*this.columnas);
				int tipo = (int)(Math.random()*2);
				
				Casilla casilla = new Casilla(f,c);
				
				if (tipo==0){
				//switch (tipo) {
					//case 0:
						if(this.crearCelulaSimple(casilla, maxPasosSinMover, pasosReproduccion))
							numCelulasPuestas++;
						//break;
						}
						
				else
					//case 1:
						if(this.crearCelulaCompleja(casilla, maxPasosSinMover, pasosReproduccion, maxComer))
							numCelulasPuestas++;
						//break;
				
			}
				
			return true;
			
		}else
			return false;
		
	}
	
	/**
	 * Reproduce los movimientos de las celulas sobre el tablero y devuelve en un String los pasos realizados.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @param maxComer Numero de veces que puede comer una celula compleja.
	 * @return String
	 */
	public String evoluciona(int maxPasosSinMover, int pasosReproduccion, int maxComer){
		
		String str = "";
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];

		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				posicionesPasadas[f][c] = false;
			} 
		} 
		
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(posicionesPasadas[f][c]==false){ // Comprobamos si hemos pasado ya por esa casilla
					
					posicionesPasadas[f][c] = true;
					
					if(this.superficie[f][c]!=null){ // Comprobamos que la casilla no esta vacia
						
						Casilla casillaInicial = new Casilla(f,c);
						Casilla casillaFinal = this.superficie[f][c].ejecutaMovimiento(casillaInicial, this); 

						if (casillaFinal != casillaInicial){
							if(casillaFinal != null){
								int f2 = casillaFinal.getFila();
								int c2 = casillaFinal.getColumna();
								str = str + this.superficie[f2][c2].pintaMovimientos();
								posicionesPasadas[f2][c2] = true;
							}else{
								str = str + this.superficie[f][c].pintaMovimientos();
								this.eliminarCelula(casillaInicial);
							}
						}else
							str = str + this.superficie[f][c].pintaMovimientos();

					} // if(this.superficie[f][c]!=null)
					
				} // if(posicionesPasadas[f][c] == false)	
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return str;

	}
	
	/**
	 * Crea una celula simple en una posicion de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posicion del tablero.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @return boolean
	 */
	public boolean crearCelulaSimple (Casilla casilla, int maxPasosSinMover, int pasosReproduccion){
		
		int f = casilla.getFila();
		int c = casilla.getColumna();
		
		if (f>=0 && f<this.filas && c>=0 && c<this.columnas){
			if (this.superficie[f][c]==null){
				this.superficie[f][c] = new CelulaSimple(maxPasosSinMover,pasosReproduccion);	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Crea una celula compleja en una posicion de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posicion del tablero.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @param maxComer Numero de veces que puede comer una celula compleja.
	 * @return boolean
	 */
	public boolean crearCelulaCompleja (Casilla casilla, int maxPasosSinMover, int pasosReproduccion, int maxComer){

		int f = casilla.getFila();
		int c = casilla.getColumna();
		
		if (f>=0 && f<this.filas && c>=0 && c<this.columnas){
			if (this.superficie[f][c]==null){
				this.superficie[f][c] = new CelulaCompleja(maxPasosSinMover,pasosReproduccion,maxComer);	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Elimina una celula del tablero. Devuelve un boolean dependiendo si ha podido o no.
	 * @param casilla Posicion del tablero.
	 * @return boolean
	 */
	public boolean eliminarCelula (Casilla casilla){
		
		int f = casilla.getFila();
		int c = casilla.getColumna();
		
		if (f>=0 && f<this.filas && c>=0 && c<this.columnas){
			if (this.superficie[f][c]!=null){
				this.superficie[f][c]=null;
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Vacia el tablero entero.
	 */
	public void vaciar(){
		
		for (int i=0; i<this.filas; i++){
			for (int j=0; j<this.columnas; j++){
				this.superficie[i][j]= null;
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
	 * Comprueba que la casilla esta vacia
	 * @param f Fila.
	 * @param c Columna.
	 * @return boolean
	 */
	public boolean comprobarCasilla(int f,int c){
		
		if (this.superficie[f][c]==null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * Mueve una celula de una casilla a otra en el tablero.
	 * @param casillaInicial Posicion inicial del tablero.
	 * @param casillaFinal Posicion final del tablero.
	 */
	public void moverCelula(Casilla casillaInicial, Casilla casillaFinal){
		
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		int f2 = casillaFinal.getFila();
		int c2 = casillaFinal.getColumna();
		
		this.superficie[f2][c2] = this.superficie[f][c];
		this.superficie[f][c] = null;
		
	}
	
	/**
	 * Devuelve si la celua se puede comer.
	 * @param f Fila.
	 * @param c Columna.
	 * @return boolean
	 */
	public boolean esComestible(int f, int c){
		
		return this.superficie[f][c].esComestible();
		
	}

	/**
	 * Saca la cantidad de posiciones vacias que tiene una posicion concreta.
	 * @param posicionesAdyacentes Array de posiciones adyacentes.
	 * @param numPosiciones Numero de posiciones adyancentes
	 * @return int
	 */
	public int cantidadPosicionesAdyacentesVacias(Posicion[] posicionesAdyacentes, int numPosiciones){
		
		int num = 0;
		
		for(int i=0; i<numPosiciones; i++){
			int f = posicionesAdyacentes[i].getX();
			int c = posicionesAdyacentes[i].getY();
			if(superficie[f][c]==null)
				num++;
		}
		
		return num;
		
	}
	
	/**
	 * Saca las posiciones vacias que tiene una posicion concreta.
	 * @param posicionesAdyacentes Array de posiciones adyacentes.
	 * @param numPosiciones Numero de posiciones adyancentes.
	 * @param numPosicionesVacias Numero de posiciones adyancentes vacias.
	 * @return Posicion[]
	 */
	public Posicion[] posicionesAdyacentesVacias(Posicion[] posicionesAdyacentes, int numPosiciones, int numPosicionesVacias){
		
		int num = 0;
		Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
		for(int i=0; i<numPosiciones; i++){
			int f = posicionesAdyacentes[i].getX();
			int c = posicionesAdyacentes[i].getY();
			if(superficie[f][c]==null){
				posicionesVacias[num] = new Posicion(f,c);
				num++;
			}
		}
		
		return posicionesVacias;
		
	}
	
	/**
	 * Genera el tablero en un String y lo devuelve para imprimirlo por pantalla.
	 * @return String
	 */
	public String toString(){
		
		String str = "";
		
		for (int i=0; i<this.filas; i++){
			for (int j=0; j<this.columnas; j++){
				
				if(this.superficie[i][j]!= null)
					str = str+this.superficie[i][j].toString();
				else
					str = str+" - ";
					
			}
			str = str+LINE_SEPARATOR;
		}
		
		return str;
		
	}
	
}
