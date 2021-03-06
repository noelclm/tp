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
import excepciones.FormatoNoValidoException;
import excepciones.MundoException;
import logica.Casilla;

/**
 * Comando CrearCelula - Crea una celula en el tablero.
 */
public class CrearCelula extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;
	
	/**
	 * Constructor por defecto.
	 */
	public CrearCelula() {
		this.casilla = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param casilla Casilla del tablero donde se quiere crear la celula.
	 */
	public CrearCelula(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.crearCelula(this.casilla) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws MundoException {
		
		if (cadenaComando[0].equals("crearcelula") && cadenaComando.length == 3){
			try{
				int fila = Integer.parseInt(cadenaComando[1]); 
				int columna = Integer.parseInt(cadenaComando[2]);
				
				Casilla casilla = new Casilla(fila,columna);
				
				Comando comando = new CrearCelula(casilla);
					
				return comando;
			}catch(NumberFormatException nfe){
				throw new FormatoNoValidoException("en las coordenadas.");
			}

		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULA F C: crea una nueva célula en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}
	


}
