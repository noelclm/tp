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
	@SuppressWarnings("null") // Quita los warning que avisa de que puede devolver null
	public PuntosCardinales[] sacarDireccion(int f,int c, boolean[][] posicionesOcupadas){
		
		int maxf = posicionesOcupadas.length-1;
		int maxc = posicionesOcupadas[0].length-1;
		PuntosCardinales[] puntos = null;
		
		
		if (f==0 && c==0){
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f+1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.SURESTE;
		}else if (f==0 && c==maxc){
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f+1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.SUROESTE;
		}else if (c==0 && f==maxf){
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f-1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.NORESTE;
		}else if (c==maxc && f==maxf){
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f-1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.NOROESTE;
		}else if (c>0 && c<maxc && f==0){
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f+1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.SURESTE;
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f+1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.SUROESTE;
		}else if (c>0 && c<maxc && f==maxf){
			puntos[0] = PuntosCardinales.ESTE;
			puntos[1] = PuntosCardinales.NORTE;
			puntos[2] = PuntosCardinales.NORESTE;
			puntos[3] = PuntosCardinales.OESTE;
			puntos[4] = PuntosCardinales.NOROESTE;
		}else if (f>0 && f<maxf && c==0){
			puntos[0] = PuntosCardinales.ESTE;
			puntos[1] = PuntosCardinales.SUR;
			puntos[2] = PuntosCardinales.SURESTE;
			puntos[3] = PuntosCardinales.NORTE;
			puntos[4] = PuntosCardinales.NORESTE;
		}else if (f>0 && f<maxf && c==maxc){
			puntos[0] = PuntosCardinales.OESTE;
			puntos[1] = PuntosCardinales.SUR;
			puntos[2] = PuntosCardinales.SUROESTE;
			puntos[3] = PuntosCardinales.NORTE;
			puntos[4] = PuntosCardinales.NOROESTE;
		}else{
			puntos[0] = PuntosCardinales.NORTE;
			puntos[1] = PuntosCardinales.SUR;
			puntos[2] = PuntosCardinales.ESTE;
			puntos[3] = PuntosCardinales.OESTE;
			puntos[4] = PuntosCardinales.NORESTE;
			puntos[4] = PuntosCardinales.SURESTE;
			puntos[4] = PuntosCardinales.NOROESTE;
			puntos[4] = PuntosCardinales.SUROESTE;
		}
		
		
		return puntos;
		
		
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
		
	}
}
