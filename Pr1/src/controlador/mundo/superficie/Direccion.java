package controlador.mundo.superficie;

public class Direccion {

	/*
	public int numPosiciones(int f, int c, int maxf,int maxc){
		
		if ((f==0 && (c==0 || c==maxc)) || (c==0 && f==maxf) || (c==maxc && f==maxf)){
			return 3;
		}
		else if( ((f==1 || f==maxf )&& c>=1 && c<maxc) || (f>=1 && f<maxf && (c==0 || c==maxc)) )
			return 5;
		else
			return 8;
		
	}
	*/
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
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f-1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.NORESTE;
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f-1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.NOROESTE;
		}else if (f>0 && f<maxf && c==0){
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f+1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.SURESTE;
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f-1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.NORESTE;
		}else if (f>0 && f<maxf && c==maxc){
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f+1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.SUROESTE;
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f-1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.NOROESTE;
		}else{
			if(posicionesOcupadas[f-1][c]==false)
				puntos[puntos.length] = PuntosCardinales.NORTE;
			if(posicionesOcupadas[f+1][c]==false)
				puntos[puntos.length] = PuntosCardinales.SUR;
			if(posicionesOcupadas[f][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.ESTE;
			if(posicionesOcupadas[f][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.OESTE;
			if(posicionesOcupadas[f-1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.NORESTE;
			if(posicionesOcupadas[f+1][c+1]==false)
				puntos[puntos.length] = PuntosCardinales.SURESTE;
			if(posicionesOcupadas[f-1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.NOROESTE;
			if(posicionesOcupadas[f+1][c-1]==false)
				puntos[puntos.length] = PuntosCardinales.SUROESTE;
		}
		
		return puntos;
			
	}
}
