package controlador.mundo.superficie;

import controlador.mundo.superficie.celula.Celula;

/**
 * Representa la zona donde trascurre el juego.
 */
public class Superficie {
	
	//Para que el salto de linea salga bien en windows y linux.
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
	
	public int getFilas(){
		
		return this.filas;
	
	}
	
	public int getColumnas(){
		
		return this.columnas;
	
	}
	
	public boolean eliminarCelula (int f, int c){
		if (this.superficie[f-1][c-1]==null)
			return false;
	
		else {
			this.superficie[f-1][c-1]=null;
			return true;
		}
	}
	
	public void vaciar(){
		
		for (int i=0; i<this.filas; i++){
			for (int j=0; j<this.columnas; j++){
				
				this.superficie[i][j]= null;
			}
		}
	}
	
	public int[] moverCelula(int f, int c){
		
		int[] t = new int[2];
		
		if (this.superficie[f][c] != null){
			int f2 = (int)(Math.random()*this.getFilas()-1);
			int c2 = (int)(Math.random()*this.getColumnas()-1);
	
			if(this.superficie[f2][c2]==null){
				t[0] = f2;
				t[1] = c2;
				this.superficie[f2][c2] = this.superficie[f][c];
				this.superficie[f][c] = null;
			}
			
		}
		
		return t;
		
		
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

}
