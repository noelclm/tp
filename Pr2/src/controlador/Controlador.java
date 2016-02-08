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

import java.util.*;

import logica.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	private static int filasTablero = 5;
	private static int columnasTablero = 5;
	private Mundo mundo = new Mundo(filasTablero,columnasTablero);
	//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s= new Scanner(System.in);
	
	/**
	 * Pide el comando al usuario y ejecuta la acción.
	 */
	public void simula(){
	
		while(!this.mundo.sigueSimulacion()){
			
			System.out.println("Introduce un comando:");
			
			//Lee una linea por teclado
			String linea = s.nextLine().toLowerCase().trim();
			//Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");

			try{
				
				Comando comando = ParserComandos.parseaComando(palabras);
			
				if(comando != null){
					System.out.println(comando.ejecuta(mundo));
				}else{
					System.out.println("No has escrito un comando correcto.");
				}
				
			} catch (NumberFormatException nfe){
				
				System.out.println("No ha introducido bien las coordenadas.");
				
			}
			
		}//fin while
		
	}

}
