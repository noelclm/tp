package controlador;

import java.util.*;

import logica.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	private Mundo mundo = new Mundo();
	private boolean simulacionTerminada;
	//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s= new Scanner(System.in);
	
	/**
	 * Pide el comando al usuario y ejecuta la acción.
	 */
	public void simula(){
	
		this.simulacionTerminada = false;
		
		while(!this.simulacionTerminada){
			
			System.out.println("Introduce un comando:");
			
			//Lee una linea por teclado
			String linea = s.nextLine().toLowerCase().trim();
			//Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");

			try{
				
				Comando comando = ParserComandos.parseaComando(palabras);
			
				if(comando != null){
					System.out.println(comando.ejecuta(mundo));
					// TODO Poner que pueda terminar
				}else{
					System.out.println("No has escrito un comando correcto.");
				}
				
			} catch (NumberFormatException nfe){
				
				System.out.println("No ha introducido bien las coordenadas.");
				
			}
			
		}//fin while
		
	}

}
