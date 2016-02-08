/*
	Practica 3: Interfaces, Ficheros y Excepciones
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
 * Clase para sacar las posiciones adyacentes de una posicion.
 */
public class Posicion {
	
	private int x; // Filas
	private int y; // Columnas
	
	/**
	 * Constructor.
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 */
	public Posicion(int x, int y){
		
		this.x = x; 
		this.y = y; 
		
	}
	
	/**
	 * Devuelve la cantidad de posiciones adyacentes de una posicion.
	 * @param maxFila Filas totales del tablero.
	 * @param maxColumna Columnas totales del tablero.
	 * @return int
	 */
	public int numPosiciones(int maxFila, int maxColumna){
		
		if ((this.x==0 && (this.y==0 || this.y==maxColumna)) || (this.y==0 && this.x==maxFila) || (this.y==maxColumna && this.x==maxFila))
			return 3;
		else if((this.x>0 && this.x<maxFila && (this.y==0 || this.y==maxColumna))||(this.y>0 && this.y<maxColumna && (this.x==0 || this.x==maxFila)))
			return 5;
		else
			return 8;
		
	}

	/**
	 * Devuelve una lista de posiciones adyacentes de una posicion.
	 * @param maxFila Filas totales del tablero.
	 * @param maxColumna Columnas totales del tablero.
	 * @return Posicion[]
	 */
	public Posicion[] adyacencia(int maxFila, int maxColumna){

		Posicion[] pos = new Posicion[this.numPosiciones(maxFila, maxColumna)];
		int i = 0;
		for (int x=this.x-1; x<=this.x+1; x++){
			for (int y=this.y-1; y<=this.y+1; y++){
				if(x>=0 && y>=0 && x<maxFila && y<maxColumna && (x!=this.x || y!=this.y)){
					pos[i] = new Posicion(x,y);
					i++;
				}
			}
		}
		
		return pos;
			
	}
	
	/**
	 * Devuelve la coordenada X.
	 * @return int
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Devuelve la coordenada Y.
	 * @return int
	 */
	public int getY(){
		return this.y;
	}
	
}
