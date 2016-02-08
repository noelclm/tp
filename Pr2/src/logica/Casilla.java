/*
	Practica 2: Nuevas Celulas en Nuestro Mundo
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
 * Clase que guarda las cordeenadas de una casilla del tablero.
 */
public class Casilla {

	private int f;
	private int c;
	
	/**
	 * Constructor parametrizado.
	 * @param f Fila.
	 * @param c Columna.
	 */
	public Casilla (int f, int c){
		this.f = f;
		this.c = c;
	}
	
	/**
	 * Devuelve la fila.
	 * @return int
	 */
	public int getFila(){
		return this.f;
	}
	
	/**
	 * Devuelve la columna.
	 * @return int
	 */
	public int getColumna(){
		return this.c;
	}
}
