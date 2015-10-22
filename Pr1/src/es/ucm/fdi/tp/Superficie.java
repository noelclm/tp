package es.ucm.fdi.tp;

/**
 * Representa la zona donde trascurre el juego.
 */
public class Superficie {
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int filas;
	private int columnas;
	private Celula[][] superficie;
	
	/**
	 * Crea la superficie.
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
	 * Crea una celula en una posicion de la superficie.
	 * @return True si se puede crear y false si no.
	 */
	public boolean crearCelula (int f, int c, int maxPasosSinMover, int pasosReproduccion){
		
		if (this.superficie[f-1][c-1]==null){
			
			this.superficie[f-1][c-1] = new Celula(maxPasosSinMover,pasosReproduccion);	
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * Devuelve un string con la superficie para imprimirla por pantalla.
	 * @return String con la superficie.
	 */
	public String toString(){
		
		String str = "";
		for (int i=0; i<this.filas; i++){
			for (int j=0; j<this.columnas; j++){
				if(this.superficie[i][j]!= null){
					if(j==this.columnas-1)
						str = str+this.superficie[i][j].getPasosParaMorir()+"-"+this.superficie[i][j].getQuedaParaReproducirse();
					else
						str = str+this.superficie[i][j].getPasosParaMorir()+"-"+this.superficie[i][j].getQuedaParaReproducirse()+" ";
				}
				else{
					if(j==this.columnas-1)
						str = str+" - ";
					else
						str = str+" -  ";
				}
					
			}
			str = str+LINE_SEPARATOR;
		}
		
		return str;
		
	}

}
