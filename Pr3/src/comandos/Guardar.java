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

package comandos;

import controlador.Comando;
import controlador.Controlador;
import excepciones.MundoException;
/**
 * Comando Guardar - Guarda el mundo en un fichero.
 */
public class Guardar extends Comando {
	private String nombreFichero;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Guardar() {
		this.nombreFichero = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param nomFichero Nombre del fichero.
	 */
	public Guardar(String nomFichero) {
		this.nombreFichero = nomFichero;
	}
	
	@Override
	public String ejecuta(Controlador controlador) throws MundoException {
		
		return controlador.guardar(this.nombreFichero) + LINE_SEPARATOR;
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("guardar") && cadenaComando.length == 2){
			Comando comando = new Guardar(cadenaComando[1]);
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " GUARDAR NOMFICH: guarda en el fichero la partida actual" + LINE_SEPARATOR ;
		
	}

}
