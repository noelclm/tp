package controlador.mundo.superficie;

public class Posicion {
	
	int x;
	int y;
	
	public Posicion(int x, int y){
		
		this.x = x;
		this.y = y;
		
	}
	
	
	public int numPosiciones(int f, int c, int maxf,int maxc){
		
		if ((f==0 && (c==0 || c==maxc)) || (c==0 && f==maxf) || (c==maxc && f==maxf)){
			return 3;
		}
		else if( ((f==1 || f==maxf )&& c>=1 && c<maxc) || (f>=1 && f<maxf && (c==0 || c==maxc)) )
			return 5;
		else
			return 8;
		
	}

	/**
	 * Saca las coordenadas a donde se mueve la celula
	 * @return enumerado con la direccion de donde se va a mover la celula.
	 */
	@SuppressWarnings("null")
	public Posicion[] adyacencia(int maxFila, int maxColumna){

		Posicion[] pos = null;
		
		for (int x=this.x-1; x<=this.x+1; x++){
			for (int y=this.y-1; y<=this.y+1; y++){
				if(x>=0 && y>=0 && x<maxFila && y<maxColumna){
					pos[pos.length].x = x;
					pos[pos.length].y = y;
				}
			}
		}
		
		return pos;
			
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
