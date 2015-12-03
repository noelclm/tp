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
	
		while(!this.mundo.sigueSimulacion()){
			
			System.out.println("Introduce un comando:"+ LINE_SEPARATOR);
			
			//Lee una linea por teclado
			String linea = s.nextLine().toLowerCase();
			//Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");
			
			try{
				
				Comando comando = ParserComandos.parseaComando(palabras);
			
				if(comando != null){
					System.out.println(comando.ejecuta(mundo));
					System.out.println(mundo.toString());
				}else{
					System.out.println("No has escrito un comando correcto.");
				}
				
			} catch (NumberFormatException nfe){
				
				System.out.println("No ha introducido bien las coordenadas.");
				
			}
			
		}//fin while
		
	}

}
