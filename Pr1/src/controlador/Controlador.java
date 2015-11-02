package controlador;

import java.util.*;
import controlador.mundo.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	private static int filasTablero = 5;
	private static int columnasTablero = 5;
	private Mundo mundo =new Mundo(filasTablero,columnasTablero);
	//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s= new Scanner(System.in);
	
	/**
	 * Pide el comando al usuario y ejecuta la accion.
	 */
	public void simula(){

	
		boolean exit = false;
		
		while(!exit){
			
			System.out.println("Introduce un comando\n");
			
			//String linea = s.next().toLowerCase().trim();
			//Lee una linea por teclado
			String linea = s.nextLine().toLowerCase();
			//Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");
			
			//try{
				
				if (palabras[0].equals("paso")){
					
					mundo.evoluciona();
					mundo.imprimirSuperficie();
					
				}
				
				else if (palabras[0].equals("iniciar")){
					
					mundo.iniciar();
					mundo.imprimirSuperficie();
					
				}
				
				else if (palabras[0].equals("crearcelula") || (palabras[0].equals("crear") && palabras[1].equals("celula"))){
					
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
						mundo.crearCelula(f,c);
						mundo.imprimirSuperficie();
						
					}
					
				}
				
				else if (palabras[0].equals("eliminarcelula") || (palabras[0].equals("eliminar") && palabras[1].equals("celula"))){
					
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
						mundo.eliminarCelula(f, c);
						mundo.imprimirSuperficie();
						
					}
					
				}
				
				else if (palabras[0].equals("ayuda")){
					
					mundo.interpreterHelp();
					
				}
				
				else if (palabras[0].equals("vaciar")){
					
					mundo.vaciar();
					mundo.imprimirSuperficie();
					
				}
				
				else if (palabras[0].equals("salir")){
					
					System.out.println("Fin de la simulacion.....");
					exit = true;
					
				}
				
				else
					System.out.println("No has escrito un comando correcto");
			
			//} catch (NumberFormatException nfe){
			//	System.out.println("No ha introducido bien las coordenadas.");
			//}
			
		}//fin while
		
		
		/*	try (int s.nextInt());
					
		}
		catch(){
			System.out.println("error");
			System.out.println();
		}*/
		
	}

}
