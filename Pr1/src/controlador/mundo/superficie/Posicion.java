package controlador.mundo.superficie;

/**
 * 
 * 
 *
 */
public class Posicion {
	
	private int x;
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Posicion(int x, int y){
		
		this.x = x; //filas
		this.y = y; //columnas
		
	}
	
	/**
	 * 
	 * @param maxFila
	 * @param maxColumna
	 * @return
	 */
	public int numPosiciones(int maxFila, int maxColumna){
		
		if ((this.x==0 && (this.y==0 || this.y==maxColumna)) || (this.y==0 && this.x==maxFila) || (this.y==maxColumna && this.x==maxFila)){
			return 3;
		}
		else if( ((this.x==1 || this.x==maxFila )&& this.y>=1 && this.y<maxColumna) || (this.x>=1 && this.x<maxFila && (this.y==0 || this.y==maxColumna)) )
			return 5;
		else
			return 8;
		
	}

	/**
	 * Saca las posiciones adyacentes de una posicion en un tablero
	 * @return Posicion[] con las posiciones adyacentes.
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
	 * 
	 * @return
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY(){
		return this.y;
	}
	
}
