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

package controlador;

import comandos.*;

/**
 * Clase que comprueba los comandos.
 */
public class ParserComandos {
		
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	static Comando[] array = {new Iniciar(), new Paso(), new CrearCelulaSimple(), 
							  new CrearCelulaCompleja(), new EliminarCelula(),
							  new Vaciar(), new Ayuda(), new Salir()};
	

	/**
	 * Recoge todas las ayudas de los comandos y lo devuelve.
	 * @return String
	 */
	static public String AyudaComandos(){
		
		String aux = "POSIBLES COMANDOS:" + LINE_SEPARATOR;
		
		for(Comando c:array){
			aux += c.textoAyuda();
		}
		
		return  aux;
		
	}
	
	/**
	 * Comprueba a que comando corresponde la cadena introducida por el usuario y devuelve el comando.
	 * @param cadenas Cadena introducida por el usuario.
	 * @return Comando
	 */
	static public Comando parseaComando(String[ ] cadenas){
		
		Comando aux = null;
		
		for(Comando c:array){
			if(aux == null)
				aux=c.parsea(cadenas);	
		}
		
		return aux;
		
	}


}
