/*
	Practica 3: Interfaces, Ficheros y Excepciones
    Copyright (C) 2016  Noel Clemente / Estefania Ortega

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

package excepciones;

/**
 * Excepcion para cuando se inicializa un tipo de mundo.
 */
public class ErrorDeInicializacionException extends MundoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto
	 */
	public ErrorDeInicializacionException(){
		super("No se ha podido inicializar.");
	}
	
	/**
	 * Constructor parametrizado
	 * @param s String para añadir al texto.
	 */
	public ErrorDeInicializacionException(String s){
		super("No se ha podido inicializar. " + s);
	}
}
