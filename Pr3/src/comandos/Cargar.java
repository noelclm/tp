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
 * Comando Cargar - Carga un fichero.
 */
public class Cargar extends Comando{
	private String nombreFichero;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Cargar() {
		this.nombreFichero = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param nomFichero Nombre del fichero.
	 */
	public Cargar(String nomFichero) {
		this.nombreFichero = nomFichero;
	}
	
	@Override
	public String ejecuta(Controlador controlador) throws MundoException {
		
		return controlador.cargar(this.nombreFichero) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("cargar") && cadenaComando.length == 2){
			Comando comando = new Cargar(cadenaComando[1]);
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " CARGAR NOMFICH: Carga un fichero con la partida actual " + LINE_SEPARATOR ;
		
	}

}
