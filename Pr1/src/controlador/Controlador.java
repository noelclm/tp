/*
	Practica 1: Simulacion de un Mundo Celular Simple
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
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static int filasTablero = 5;
	private static int columnasTablero = 5;
	private Mundo mundo = new Mundo(filasTablero,columnasTablero);
	//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s= new Scanner(System.in);
	
	/**
	 * Pide el comando al usuario y ejecuta la accion.
	 */
	public void simula(){

	
		boolean exit = false;
		
		while(!exit){
			
			System.out.println("Introduce un comando:"+ LINE_SEPARATOR);
			
			//Lee una linea por teclado
			String linea = s.nextLine().toLowerCase();
			//Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");
			
			try{
				
				if (palabras[0].equals("iniciar")){
					
					System.out.println(mundo.iniciar());
					System.out.println(mundo.toString());
					
				}else if (palabras[0].equals("paso")){
					
					System.out.println(mundo.evoluciona());
					System.out.println(mundo.toString());
					
				}else if (palabras[0].equals("crearcelula") || (palabras[0].equals("crear") && palabras[1].equals("celula"))){
					
					int num1 = 1;
					int num2 = 2;
					if(palabras[0].equals("crear")){
						num1 = 2;
						num2 = 3;
					}
					
					if(palabras.length <= num2)
						System.out.println("No ha introducido las coordenadas.");
					
					else{
						
						int f = Integer.parseInt(palabras[num1]); 
						int c = Integer.parseInt(palabras[num2]);
						System.out.println(mundo.crearCelula(f,c));
						System.out.println(mundo.toString());
						
					}
					
				}else if (palabras[0].equals("eliminarcelula") || (palabras[0].equals("eliminar") && palabras[1].equals("celula"))){
					
					int num1 = 1;
					int num2 = 2;
					if(palabras[0].equals("eliminar")){
						num1 = 2;
						num2 = 3;
					}
					
					if(palabras.length <= num2)
						System.out.println("No ha introducido las coordenadas.");
					
					else{
						
						int f = Integer.parseInt(palabras[num1]);
						int c = Integer.parseInt(palabras[num2]);
						System.out.println(mundo.eliminarCelula(f, c));
						System.out.println(mundo.toString());
						
					}
					
				}else if (palabras[0].equals("vaciar")){
					
					System.out.println(mundo.vaciar());
					System.out.println(mundo.toString());
					
				}else if (palabras[0].equals("ayuda")){
					
					System.out.println(mundo.ayuda());
					
				}else if (palabras[0].equals("salir")){
					
					System.out.println("Fin de la simulacion.....");
					exit = true;
					
				}else
					System.out.println("No has escrito un comando correcto.");
			
			} catch (NumberFormatException nfe){
				System.out.println("No ha introducido bien las coordenadas.");
			}
			
		}//fin while
		
	}

}
