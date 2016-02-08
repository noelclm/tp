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

package comandos;

import controlador.Comando;
import logica.Casilla;
import logica.Mundo;

/**
 * Comando EliminarCelula - Elimina una célula del tablero.
 */
public class EliminarCelula extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	private Casilla casilla;

	/**
	 * Constructor por defecto.
	 */
	public EliminarCelula() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde esta la célula que se quiere borrar.
	 */
	public EliminarCelula(Casilla casilla) {
		this.casilla = casilla;
	}
		
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.eliminarCelula(this.casilla) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {

		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length == 3){

			int f = Integer.parseInt(cadenaComando[1]); 
			int c = Integer.parseInt(cadenaComando[2]);
			
			Casilla casilla = new Casilla(f,c);

			Comando comando = new EliminarCelula(casilla);
			
			return comando;

		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " ELIMINARCELULA F C: elimina una célula de la posición (f,c) si es posible" + LINE_SEPARATOR;
		
	}

}
