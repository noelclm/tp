package controlador.mundo.superficie;

/**
 * Clase para sacar las posiciones adyacentes de una posicion.
 */
public class Posicion {
	
	private int x;
	private int y;
	
	/**
	 * Constructor.
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 */
	public Posicion(int x, int y){
		
		this.x = x; //filas
		this.y = y; //columnas
		
	}
	
	/**
	 * Devuelve la cantidad de posiciones adyacentes de una posicion.
	 * @param maxFila Filas totales del tablero.
	 * @param maxColumna Columnas totales del tablero.
	 * @return int
	 */
	public int numPosiciones(int maxFila, int maxColumna){
		
		if ((this.x==0 && (this.y==0 || this.y==maxColumna)) || (this.y==0 && this.x==maxFila) || (this.y==maxColumna && this.x==maxFila))
			return 3;
		else if((this.x>0 && this.x<maxFila && (this.y==0 || this.y==maxColumna))||(this.y>0 && this.y<maxColumna && (this.x==0 || this.x==maxFila)))
			return 5;
		else
			return 8;
		
	}

	/**
	 * Devuelve una lista de posiciones adyacentes de una posicion.
	 * @param maxFila Filas totales del tablero.
	 * @param maxColumna Columnas totales del tablero.
	 * @return Posicion[]
	 */
	public Posicion[] adyacencia(int maxFila, int maxColumna){

		Posicion[] pos = new Posicion[this.numPosiciones(maxFila, maxColumna)];
		int i = 0;
		for (int x=this.x-1; x<=this.x+1; x++){
			for (int y=this.y-1; y<=this.y+1; y++){
				if(x>=0 && y>=0 && x<maxFila && y<maxColumna && (x!=this.x || y!=this.y)){
					pos[i] = new Posicion(x,y);
					i++;
				}
			}
		}
		
		return pos;
			
	}
	
	/**
	 * Devuelve la coordenada X.
	 * @return int
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Devuelve la coordenada Y.
	 * @return int
	 */
	public int getY(){
		return this.y;
	}
	
}
