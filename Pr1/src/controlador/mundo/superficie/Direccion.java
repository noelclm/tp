package controlador.mundo.superficie;

public class Direccion {
	
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
	public PuntosCardinales sacarDireccion(int f,int c, boolean[][] posicionesCelulas){
		/*
		if (this.superficie.existeCelula(f,c)){
			
			boolean salir = false;
			int f2 = 0;
			int c2 = 0;
			int[] t = new int [2];
		
			while(!salir){
				
				int dir = (int)(Math.random()*7);
				
				if(dir == 0){ // Arriba Izquierda
					f2 = f-1;
					c2 = c-1;
				}else if(dir == 1){ // Arriba
					f2 = f-1;
					c2 = c;
				}else if(dir == 2){ // Arriba Derecha
					f2 = f-1;
					c2 = c+1;
				}else if(dir == 3){ // Izquierda
					f2 = f;
					c2 = c-1;
				}else if(dir == 4){ // Derecha
					f2 = f;
					c2 = c+1;
				}else if(dir == 5){ // Abajo Izquierda
					f2 = f+1;
					c2 = c-1;
				}else if(dir == 6){ // Abajo
					f2 = f+1;
					c2 = c;
				}else if(dir == 7){ // Abajo Izquierda
					f2 = f+1;
					c2 = c+1;
				}
				
				if((f2>=0 && f2<this.superficie.getFilas())&&(c2>=0 && c2<this.superficie.getColumnas())){
					salir = true;
					t[0] = f2;
					t[1] = c2;
				}		
			}
			return t;
		}*/
		return PuntosCardinales.SINDIRECCION;
		
	}
}
