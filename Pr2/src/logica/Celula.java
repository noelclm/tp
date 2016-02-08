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
 * Clase que gestiona una celula.
 */
public abstract class Celula {

	protected boolean esComestible;
	protected String texto;
	
	/**
	 * Mira donde se puede mover la celula y devuelve la casilla.
	 * @param casillaInicial Posición del tablero.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public abstract Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public abstract boolean esComestible();
	
	/**
	 * Constructor por defecto.
	 */
	public Celula(){
		
		this.texto = "";
		
	}
	
	/**
	 * Devuelve los movimientos realizados durante la ejecución del movimiento
	 * @return String
	 */
	public String pintaMovimientos(){
		
		return this.texto;
		
	}

}
