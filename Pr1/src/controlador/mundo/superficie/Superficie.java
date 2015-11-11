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
	 * Inicia la superficie con un numero de celululas que le entra
	 * @param numCelulas Numero de celulas con el que se inicializa la superficie
	 * @param maxPasosSinMover Numero de pasos sin mover que tiene la celula
	 * @param pasosReproduccion Numero de pasos para que la celula se reproduzca 
	 * @return boolean
	 */
	public boolean iniciarSuperficie (int numCelulas, int maxPasosSinMover, int pasosReproduccion){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		
		// TODO Comprobar que hay suficientes celdas para las celulas
		
		while (numCelulasPuestas<numCelulas){
			int f = (int)(Math.random()*this.filas);
			int c = (int)(Math.random()*this.columnas);
			if(this.crearCelula(f, c, maxPasosSinMover, pasosReproduccion))
				numCelulasPuestas++;
		}
		
		return true;
		
	}
	
	/**
	 * 
	 * @param maxPasosSinMover
	 * @param pasosReproduccion
	 * @return
	 */
	public boolean paso(int maxPasosSinMover, int pasosReproduccion){
		
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];

		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				posicionesPasadas[f][c] = false;
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
		
		for (int f=0; f<this.filas-1; f++){
			for (int c=0; c<this.columnas-1; c++){
				
				if(posicionesPasadas[f][c]==false){
					
					if(this.superficie[f][c]!=null){
					
						Posicion posicionInicial = new Posicion(f,c);
						
						int numPosiciones = posicionInicial.numPosiciones(this.filas, this.columnas);
						Posicion[] posicionesAdyacentes = new Posicion[numPosiciones];
						posicionesAdyacentes = posicionInicial.adyacencia(this.filas, this.columnas);
						
						int numPosicionesVacias = cantidadPosicionesVacias(posicionesAdyacentes,numPosiciones);
						
						if(numPosicionesVacias>0){ // Si se puede mover
							
							Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
							posicionesVacias = posicionesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
							
							int numAleatorio = (int) Math.floor(Math.random()*numPosicionesVacias-1);
							int f2 = posicionesVacias[numAleatorio].getX();
							int c2 = posicionesVacias[numAleatorio].getY();
							
							if(this.moverCelula(f,c,f2,c2)){
								if (this.estasPariendo(f2, c2)){
									this.crearCelula(f, c, maxPasosSinMover, pasosReproduccion);
									this.reiniciarPasosReproduccion(t[0],t[1]);
								}else
									this.sumarPaso(f2,c2);
								posicionesPasadas[f][c] = true;
								posicionesPasadas[f2][c2] = true;
		
							} // if(this.moverCelula(f,c,t[0],t[1]))
							
						} // if(numPosicionesVacias>0)
						else{ // Si no se puede mover
							
							this.sumarPasoSinMover(f,c);
							if (!this.sinActividad(f,c) || this.estasPariendo(f,c)){
								this.eliminarCelula(f, c);
								posicionesCelulas[f][c] = false;
							} // if (!this.sinActividad(f,c) || this.estasPariendo(f,c))
							posicionesPasadas[f][c] = true;
							
						} // else
						
					} // if(this.superficie[f][c]!=null)
					
					posicionesPasadas[f][c] = true;
					
				} // if(posicionesPasadas[f][c] == false)						
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return true;
		
	}
	
	/**
	 * Crea una celula en una posicion de la superficie.
	 * @return True si se puede crear y false si no.
	 */
	public boolean crearCelula (int f, int c, int maxPasosSinMover, int pasosReproduccion){
		
		if (this.superficie[f][c]==null){
			this.superficie[f][c] = new Celula(maxPasosSinMover,pasosReproduccion);	
			return true;
			
		}else
			return false;
		
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
	
		else{
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
	 * 
	 * @return
	 */
	public int cantidadPosicionesVacias(Posicion[] posicionesAdyacentes, int numPosiciones){
		
		int num = 0;
		
		for(int i=0; i<numPosiciones; i++){
			int f = posicionesAdyacentes[i].getX();
			int c = posicionesAdyacentes[i].getY();
			if(this.superficie[f][c]==null)
				num++;
		}
		
		return num;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Posicion[] posicionesVacias(Posicion[] posicionesAdyacentes, int numPosiciones, int numPosicionesVacias){
		
		int num = 0;
		Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
		
		for(int i=0; i<numPosiciones; i++){
			int f = posicionesAdyacentes[i].getX();
			int c = posicionesAdyacentes[i].getY();
			if(this.superficie[f][c]==null){
				posicionesVacias[num] = posicionesAdyacentes[i];
				num++;
			}
		}
		
		return posicionesAdyacentes;
		
	}
	
	
	
	/**
	 * 
	 * @param f
	 * @param c
	 * @return
	 */
	/*
	public boolean estasPariendo (int f, int c){

		return this.superficie[f][c].limitePasosDados();
		
	}
	
	
	public void reiniciarPasosReproduccion(int f, int c){
		this.superficie[f][c].setPasosReproduccion();
	}
	
	public boolean sinActividad(int f, int c){
		return this.superficie[f][c].limitePasosSinMover();
	}
	*/
	
	
	
	
	
	
	/**
	 * Mira si hay una celula en esa posicion del tablero.
	 * @param f
	 * @param c
	 * @return
	 */
	/*public boolean existeCelula(int f, int c){
		
		if (this.superficie[f][c] != null)
			return true;
		else
			return false;
		
	}*/
	
	/**
	 * 
	 * @param f
	 * @param c
	 */
	/*public void sumarPaso(int f, int c){
		this.superficie[f][c].sumPasosDados();
	}*/
	
	/**
	 * 
	 * @param f
	 * @param c
	 */
	/*
	public void sumarPasoSinMover(int f, int c){
		this.superficie[f][c].sumPasosSinMover();
	}
*/
	
	
	/**
	 * 
	 * @param f
	 * @param c
	 * @param f2
	 * @param c2
	 * @return
	 */
	/*
	public boolean moverCelula(int f, int c, int f2, int c2){
		
		if(this.superficie[f2][c2]==null){
			this.superficie[f2][c2] = this.superficie[f][c];
			this.superficie[f][c] = null;
			return true;
		}
		else
			return false;
		
	}
	*/
			
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
