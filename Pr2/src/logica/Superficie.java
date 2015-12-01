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
	 * @param nf Numero de filas de la superficie.
	 * @param nc Numero de columnas de la superficie.
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
	 * @return boolean
	 */
	public boolean iniciarSuperficie (int numCelulas, int maxPasosSinMover, int pasosReproduccion){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulas <= this.filas*this.columnas){
			
			while (numCelulasPuestas<numCelulas){
				int f = (int)(Math.random()*this.filas);
				int c = (int)(Math.random()*this.columnas);
				if(this.crearCelulaSimple(f, c, maxPasosSinMover, pasosReproduccion))
					numCelulasPuestas++;	
			}
			return true;
			
		}else
			return false;
	}
	
	/**
	 * Reproduce los movimientos de las celulas sobre el tablero y devuelve en un String los pasos realizados.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @return String
	 */
	public String evoluciona(int maxPasosSinMover, int pasosReproduccion){
		
		String str = "";
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];

		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				posicionesPasadas[f][c] = false;
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
		
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(posicionesPasadas[f][c]==false){
					
					if(this.superficie[f][c]!=null){
						
						this.superficie[f][c].ejecutaMovimiento(f, c, this); // TODO Devuelve la posicion
						
						
					} // if(this.superficie[f][c]!=null)
					
					posicionesPasadas[f][c] = true;
					
				} // if(posicionesPasadas[f][c] == false)						
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return str;
		
	}
	
	/**
	 * Crea una celula simple en una posicion de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param f fila.
	 * @param c columna.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @return boolean
	 */
	public boolean crearCelulaSimple (int f, int c, int maxPasosSinMover, int pasosReproduccion){

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
	 * @param f fila.
	 * @param c columna.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @param pasosReproduccion Numero de celulas que puede comer.
	 * @return boolean
	 */
	public boolean crearCelulaCompleja (int f, int c, int maxPasosSinMover, int pasosReproduccion, int maxComer){

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
	 * @param f fila.
	 * @param c columna.
	 * @return boolean
	 */
	public boolean eliminarCelula (int f, int c){
		
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
	 * Genera el tablero en un String y lo devuelve imprimirla por pantalla.
	 * @return String
	 */
	public String toString(){
		
		String str = "";
		for (int i=0; i<this.filas; i++){
			for (int j=0; j<this.columnas; j++){
				if(this.superficie[i][j]!= null){
					if(j==this.columnas-1)
						str = str+this.superficie[i][j].toString();
					else
						str = str+this.superficie[i][j].toString()+" ";
				}
				else{
					if(j==this.columnas-1)
						str = str+"   -   ";
					else
						str = str+"   -    ";
				}
					
			}
			str = str+LINE_SEPARATOR;
		}
		
		return str;
		
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
	 * Saca la cantidad de posiciones vacias que tiene una posicion concreta.
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
	
}

