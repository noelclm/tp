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
import excepciones.CoordenadasException;
import excepciones.ErrorDeInicializacionException;
import excepciones.MundoException;
import logica.Mundo;
import mundos.MundoComplejo;
import mundos.MundoSimple;
/**
 * Comando Jugar - Te permite jugar en un mundo simple o en un mundo complejo.
 */
public class Jugar extends Comando{
	
	private Mundo mundo;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Jugar() {
		
		this.mundo = null;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param mundo Mundo que se crea.
	 */
	public Jugar(Mundo mundo) {
		
		this.mundo = mundo;	
		
	}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.jugar(this.mundo) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) throws MundoException {
		try{
			if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("simple") && cadenaComando.length == 5){

				int filas = Integer.parseInt(cadenaComando[2]); 
				int columnas = Integer.parseInt(cadenaComando[3]);
				int celulasSimples = Integer.parseInt(cadenaComando[4]); 
				
				if(filas*columnas < celulasSimples){
					throw new ErrorDeInicializacionException();
				}
				
				Mundo mundo = new MundoSimple(filas,columnas,celulasSimples);
				Comando comando = new Jugar(mundo);
				return comando;
				
			}else if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("complejo") && cadenaComando.length == 6){
				
				int filas = Integer.parseInt(cadenaComando[2]); 
				int columnas = Integer.parseInt(cadenaComando[3]);
				int celulasSimples = Integer.parseInt(cadenaComando[4]); 
				int celulasComplejas = Integer.parseInt(cadenaComando[5]);
				
				if(filas*columnas < celulasSimples+celulasComplejas){
					throw new ErrorDeInicializacionException();
				}
				
				Mundo mundo = new MundoComplejo(filas,columnas,celulasSimples,celulasComplejas);
				Comando comando = new Jugar(mundo);
				return comando;
				
			}else{
				return null;
			}
		}catch(NumberFormatException nfe){
			throw new CoordenadasException();
		}
	}

	@Override
	public String textoAyuda() {
		
		return " JUGAR SIMPLE/COMPLEJO m n s c: Permite cambiar de un juego a otro" + LINE_SEPARATOR ;
		
	}

}
