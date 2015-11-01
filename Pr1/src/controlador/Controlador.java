package controlador;

import java.util.*;

import controlador.mundo.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	private Mundo mundo =new Mundo(5,5);
	
	/**
	 * Pide el comando al usuario y ejecuta la accion.
	 */
	public void simula(){
			
		//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
		Scanner s= new Scanner(System.in);
		
		boolean exit = false;
		
		while(!exit){
			
			System.out.println("Introduce un comando\n");
			
			//String linea = s.next().toLowerCase().trim();
			String linea = s.nextLine();
			
			String[] palabras = linea.split(" ");
			
			
			if (palabras[0].equals("paso")){
				System.out.println("Has escrito paso");
			}
			
			else if (palabras[0].equals("iniciar")){
				System.out.println("Has escrito iniciar");
			}
			
			else if (palabras[0].equals("crearcelula")){
				// TODO si mete letras en vez de numeros falla
				int f = Integer.parseInt(palabras[1]); 
				int c = Integer.parseInt(palabras[2]);
				mundo.crearCelula(f,c);
				mundo.imprimirSuperficie();
			}
			
			else if (palabras[0].equals("eliminarcelula")){
				System.out.println("Has escrito eliminar celula");
			}
			
			else if (palabras[0].equals("ayuda")){
				mundo.interpreterHelp();
			}
			
			else if (palabras[0].equals("vaciar")){
				System.out.println("Vaciando la superficie....");
			}
			
			else if (palabras[0].equals("salir")){
				System.out.println("Fin de la simulacion.....");
				exit = true;
			}
			
			else
				System.out.println("No has escrito un comando correcto");
			
		}//fin while
			
		s.close();
		
		/*	try (int s.nextInt());
					
		}
		catch(){
			System.out.println("error");
			System.out.println();
		}*/
		
	}

}
