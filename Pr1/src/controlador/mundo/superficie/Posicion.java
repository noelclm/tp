package controlador.mundo.superficie;

public class Posicion {
	
	public Posicion(){
		
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
	
	
	
}
