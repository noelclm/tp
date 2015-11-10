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
		
		if (this.superficie[f][c]==null){
			
			this.superficie[f][c] = new Celula(maxPasosSinMover,pasosReproduccion);	
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @param f
	 * @param c
	 * @return
	 */
	public boolean estasPariendo (int f, int c){

		return this.superficie[f][c].limitePasosDados();
		
	}
	
	public void reiniciarPasosReproduccion(int f, int c){
		this.superficie[f][c].setPasosReproduccion();
	}
	
	public boolean sinActividad(int f, int c){
		return this.superficie[f][c].limitePasosSinMover();
	}
	
	/**
	 * Devuelve el numero de filas totales del tablero.
	 * @return filas.
	 */
	public int getFilas(){
		
		return this.filas;
	
	}
	
	/**
	 * Devuelve el numero de columnas totales del tablero.
	 * @return columnnas.
	 */
	public int getColumnas(){
		
		return this.columnas;
	
	}
	
	/**
	 * Elimina una celula del tablero.
	 * @param f fila.
	 * @param c columna.
	 * @return false si no puede borrar y true si la borra.
	 */
	public boolean eliminarCelula (int f, int c){
		if (this.superficie[f][c]==null)
			return false;
	
		else {
			this.superficie[f][c]=null;
			return true;
		}
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
	 * Mira si hay una celula en esa posicion del tablero.
	 * @param f
	 * @param c
	 * @return
	 */
	public boolean existeCelula(int f, int c){
		
		if (this.superficie[f][c] != null)
			return true;
		else
			return false;
		
	}
	
	/**
	 * 
	 * @param f
	 *  @param c
	 */
	public void sumarPaso(int f, int c){
		this.superficie[f][c].sumPasosDados();
	}
	
	/**
	 * 
	 * @param f
	 * @param c
	 */
	public void sumarPasoSinMover(int f, int c){
		this.superficie[f][c].sumPasosSinMover();
	}

	public boolean paso(){
		
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];
		boolean[][] posicionesCelulas = new boolean[this.filas][this.columnas];
		Direccion d = new Direccion();
		PuntosCardinales pc = null;
		
		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				posicionesPasadas[f][c] = false;
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
		
		// Inicia un tablero auxiliar para saber donde hay celulas
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(this.superficie[f][c]!=null)
					posicionesCelulas[f][c] = true;
				else
					posicionesCelulas[f][c] = false;
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(posicionesPasadas[f][c] == false){
					
					pc = d.sacarDireccion(f,c,posicionesCelulas);
					
					if(pc==PuntosCardinales.SINDIRECCION){
					
						this.sumarPasoSinMover(f,c);
						if (!this.sinActividad(f,c) || this.estasPariendo(f,c)){
							this.eliminarCelula(f, c);
							posicionesCelulas[f][c] = false;
						} // if (!this.sinActividad(f,c) || this.estasPariendo(f,c))
						posicionesPasadas[f][c] = true;
					
					} // if(direccion==Direccion.SINDIRECCION)
					
					/*
			
						if(this.moverCelula(f,c,t[0],t[1])){
							if (this.estasPariendo(t[0], t[1])){
								this.crearCelula(f, c, MAX_PASOS_SIN_MOVER, PASOS_REPRODUCCION);
								this.reiniciarPasosReproduccion(t[0],t[1]);
							}else
								this.sumarPaso(t[0],t[1]);
							posicionesPasadas[f][c] = true;
							posicionesPasadas[t[0]][t[1]] = true;
	
						} // if(this.moverCelula(f,c,t[0],t[1]))
*/
				} // if(posicionesPasadas[f][c] == false)
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
			
		return true;
	}

	
	/**
	 * Mueve una celula.
	 * @param f fila de la celula que se quiere mover.
	 * @param c columna de la celula que se quiere mover.
	 * @return array con las cordenadas de donde se ha movido la celula.
	 */
	public boolean moverCelula(int f, int c, int f2, int c2){
		
		/*
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
		*/
		
	
		
		if(this.superficie[f2][c2]==null){
			this.superficie[f2][c2] = this.superficie[f][c];
			this.superficie[f][c] = null;
			return true;
		}
		else
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
