/*
	Practica 1: Simulacion de un Mundo Celular Simple
    Copyright (C) 2015  Noel Clemente / Estefania Ortega

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package logica;


/**
 * Clase que gestiona el tablero de juego.
 */
public class Superficie {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int filas;
	private int columnas;
	private Celula[][] superficie;
	
	/**
	 * Constructor.
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
	 * Inicia la superficie con un numero de celululas que le entra. Devuelve un boolean dependiendo si ha podido o no.
	 * @param numCelulas Numero de celulas con el que se inicializa la superficie.
	 * @param maxPasosSinMover Numero de pasos sin mover que tiene la celula.
	 * @param pasosReproduccion Numero de pasos para que la celula se reproduzca.
	 * @return boolean
	 */
	public boolean iniciarSuperficie (int numCelulas, int maxPasosSinMover, int pasosReproduccion){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		/*int numCelulas2 = (int)(Math.random()*(this.filas*this.columnas));
		if (numCelulas2 == 0)
			numCelulas2 = 1;
		*/
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
	 * Reproduce los movimientos de las celulas sobre el tablero y devuelve en un String los pasos realizados.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @return String
	 */
	public String evoluciona(int maxPasosSinMover, int pasosReproduccion){
		
		String str = "";
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
						
						
						int numPosicionesVacias = cantidadPosicionesVacias(posicionesAdyacentes,numPosiciones);
						
						if(numPosicionesVacias>0){ // Si se puede mover
							
							Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
							posicionesVacias = posicionesVacias(posicionesAdyacentes,numPosiciones,numPosicionesVacias);
							
							int numAleatorio = (int)(Math.random()*numPosicionesVacias-1);
							int f2 = posicionesVacias[numAleatorio].getX();
							int c2 = posicionesVacias[numAleatorio].getY();
							
							this.superficie[f2][c2] = this.superficie[f][c];
							this.eliminarCelula(f, c);
							
							
							str = str+"->Movimiento de ("+f+","+c+") a ("+f2+","+c2+")"+LINE_SEPARATOR;
							if (this.superficie[f2][c2].limitePasosDados()){
								if(this.crearCelula(f, c, maxPasosSinMover, pasosReproduccion))
									str = str+"->Nace nueva celula en ("+f+"-"+c+") cuyo padre ha sido ("+f2+","+c2+")"+LINE_SEPARATOR;
								else 
									str = str+"->La celula ("+f2+"-"+c2+") ha dado un error al reproducirse"+LINE_SEPARATOR;
								this.superficie[f2][c2].reiniciaPasosReproduccion();
							}else{
								this.superficie[f2][c2].sumPasosDados();
							}
							
							posicionesPasadas[f2][c2] = true;	
								
						}else{ // Si no se puede mover
							
							this.superficie[f][c].sumPasosSinMover();
							if (this.superficie[f][c].limitePasosSinMover()){
								this.eliminarCelula(f, c);
								str = str+"->Muere la celula de la casilla "+f+"-"+c+" por inactividad"+LINE_SEPARATOR;
							}else if (this.superficie[f][c].limitePasosDados()){
								this.eliminarCelula(f, c);
								str = str+"->Muere la celula de la casilla "+f+"-"+c+" por no poder reproducirse"+LINE_SEPARATOR;
							}
							else
								str = str+"->La celula "+f+"-"+c+" no se ha podido mover"+LINE_SEPARATOR;
							
						} // else
						
					} // if(this.superficie[f][c]!=null)
					
					posicionesPasadas[f][c] = true;
					
				} // if(posicionesPasadas[f][c] == false)						
			} // for (int c=0; c<this.columnas; c++)
		} // for (int f=0; f<this.filas; f++)
	
		return str;
		
	}
	
	/**
	 * Crea una celula en una posicion de la superficie. Devuelve un boolean dependiendo si ha podido o no.
	 * @param f fila.
	 * @param c columna.
	 * @param maxPasosSinMover Numero de pasos que puede estar sin mover.
	 * @param pasosReproduccion Numero de pasos para que se reproduzca.
	 * @return boolean
	 */
	public boolean crearCelula (int f, int c, int maxPasosSinMover, int pasosReproduccion){

		if (f>=0 && f<this.filas && c>=0 && c<this.columnas){
			if (this.superficie[f][c]==null){
				this.superficie[f][c] = new Celula(maxPasosSinMover,pasosReproduccion);	
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Elimina una celula del tablero. Devuelve un boolean dependiendo si ha podido o no.
	 * @param f fila.
	 * @param c columna.
	 * @return boolean
	 */
	public boolean eliminarCelula (int f, int c){
		
		if (f>=0 && f<this.filas && c>=0 && c<this.columnas){
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
	 * Genera el tablero en un String y lo devuelve imprimirla por pantalla.
	 * @return String
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
	 * Metodo privado que saca la cantidad de posiciones vacias que tiene una posicion concreta.
	 * @return int
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
	 * Metodo privado que saca las posiciones vacias que tiene una posicion concreta.
	 * @return Posicion[]
	 */
	private Posicion[] posicionesVacias(Posicion[] posicionesAdyacentes, int numPosiciones, int numPosicionesVacias){
		
		int num = 0;
		Posicion[] posicionesVacias = new Posicion[numPosicionesVacias];
		for(int i=0; i<numPosiciones; i++){
			int f = posicionesAdyacentes[i].getX();
			int c = posicionesAdyacentes[i].getY();
			if(this.superficie[f][c]==null){
				posicionesVacias[num] = new Posicion(f,c);
				num++;
			}
		}
		
		return posicionesVacias;
		
	}
	
}

