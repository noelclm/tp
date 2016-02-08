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

import java.io.PrintWriter;

/**
 * Interfaz que gestiona una celula.
 */
public interface Celula {
	
	/**
	 * Ejecuta el movimiento de la celula sobre el tablero y devuelve la casilla.
	 * @param casillaInicial Posicion del tablero.
	 * @param texto Texto que se muestra por pantalla al realizar la accion.
	 * @param superficie Tablero de juego.
	 * @return Casilla
	 */
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, Superficie superficie);
	
	/**
	 * Comprueba si la celula se puede comer.
	 * @return boolean
	 */
	public boolean esComestible();
	
	/**
	 * Guarda en un PrintWriter los datos de la celula.
	 * @param pw Entra un PrintWrite para escribir en el fichero.
	 */
	public void guardar(PrintWriter pw);
	
}
