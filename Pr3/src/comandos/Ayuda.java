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

package comandos;

import controlador.Comando;
import controlador.Controlador;
import controlador.ParserComandos;

/**
 * Comando Ayuda - Muestra los posibles comandos.
 */
public class Ayuda extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Ayuda(){}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return ParserComandos.AyudaComandos();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("ayuda") && cadenaComando.length == 1){
			
			Comando comando = new Ayuda();
				
			return comando;
		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " AYUDA: muestra esta ayuda" + LINE_SEPARATOR;
		
	}

}
