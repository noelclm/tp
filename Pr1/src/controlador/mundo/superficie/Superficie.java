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
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulas <= this.filas*this.columnas){
			
			while (numCelulasPuestas<numCelulas){
				int f = (int)(Math.random()*this.filas);
				int c = (int)(Math.random()*this.columnas);
				if(this.crearCelula(f, c, maxPasosSinMover, pasosReproduccion))
					numCelulasPuestas++;	
			}
			return true;
			
		}else
			return false;
	}
	
	/**
	 * 
	 * @param maxPasosSinMover
	 * @param pasosReproduccion
	 * @return boolean
	 */
	public String paso(int maxPasosSinMover, int pasosReproduccion){
		
		boolean[][] posicionesPasadas = new boolean[this.filas][this.columnas];

		// Inicia un tablero auxiliar para saber porque posiciones ha pasado ya
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				posicionesPasadas[f][c] = false;
				
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
		
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				
				if(posicionesPasadas[f][c]==false){
					
					if(this.superficie[f][c]!=null){
					
						Posicion posicionInicial = new Posicion(f,c);
						
						int numPosiciones = posicionInicial.numPosiciones(this.filas-1, this.columnas-1);
						Posicion[] posicionesAdyacentes = new Posicion[numPosiciones];
						posicionesAdyacentes = posicionInicial.adyacencia(this.filas, this.columnas);
						
						// TODO Elimina cuando quiere
						int numPosicionesVacias = cantidadPosicionesVacias(posicionesAdyacentes,numPosiciones);
						
						if(numPosicionesVacias>0){ // Si se puede mover
							
							Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
							posicionesVacias = posicionesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
							
							int numAleatorio = (int)(Math.random()*numPosicionesVacias-1);
							int f2 = posicionesVacias[numAleatorio].getX();
							int c2 = posicionesVacias[numAleatorio].getY();
							System.out.println("f2"+f2);
							System.out.println("c2"+c2);
							
							this.superficie[f2][c2] = this.superficie[f][c];
							this.superficie[f][c] = null;
							
							if (this.superficie[f2][c2].limitePasosDados()){
								this.crearCelula(f, c, maxPasosSinMover, pasosReproduccion);
								this.superficie[f2][c2].setPasosReproduccion();
							}else
								this.superficie[f2][c2].sumPasosDados();

							posicionesPasadas[f2][c2] = true;	
								
						} // if(numPosicionesVacias>0)
						else{ // Si no se puede mover
							
							this.superficie[f][c].sumPasosSinMover();
							if (this.superficie[f][c].limitePasosSinMover() || this.superficie[f][c].limitePasosDados())
								this.eliminarCelula(f, c);
							
						} // else
						
					} // if(this.superficie[f][c]!=null)
					
					posicionesPasadas[f][c] = true;
					
				} // if(posicionesPasadas[f][c] == false)						
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return "pasos que ha hecho";
		
	}
	
	/**
	 * Crea una celula en una posicion de la superficie.
	 * @return True si se puede crear y false si no.
	 */
	public boolean crearCelula (int f, int c, int maxPasosSinMover, int pasosReproduccion){

		if (f>=1 && f<=this.filas && c>=1 && c<=this.columnas){
			if (this.superficie[f][c]==null){
				this.superficie[f][c] = new Celula(maxPasosSinMover,pasosReproduccion);	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Elimina una celula del tablero.
	 * @param f fila.
	 * @param c columna.
	 * @return false si no puede borrar y true si la borra.
	 */
	public boolean eliminarCelula (int f, int c){
		
		if (f>=1 && f<=this.filas && c>=1 && c<=this.columnas){
			if (this.superficie[f][c]!=null){
				this.superficie[f][c]=null;
				return true;
			}
		}
		
		return false;
		
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

	/**
	 * 
	 * @return
	 */
	private int cantidadPosicionesVacias(Posicion[] posicionesAdyacentes, int numPosiciones){
		
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
	private Posicion[] posicionesVacias(Posicion[] posicionesAdyacentes, int numPosiciones, int numPosicionesVacias){
		
		int num = 0;
		Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
		// TODO devuelve alguna ocupada
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
	
}
