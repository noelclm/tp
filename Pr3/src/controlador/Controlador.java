package controlador;

import java.util.*;
import logica.Casilla;
import logica.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	private Mundo mundo = null;
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
					System.out.println(comando.ejecuta(this));
				}else{
					System.out.println("No has escrito un comando correcto.");
				}
				
			} catch (NumberFormatException nfe){
				System.out.println("No ha introducido bien las coordenadas.");
			}
			
		}//fin while
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public String iniciar (){
		return mundo.iniciar();
	}
	
	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		return mundo.evoluciona();
	}
		
	/**
	 * Llama a crear célula simple. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public String crearCelulaSimple(Casilla casilla){
		return mundo.crearCelulaSimple(casilla) ;
	}
	
	/**
	 * Llama a crear célula compleja. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public String crearCelulaCompleja(Casilla casilla){
		return mundo.crearCelulaCompleja(casilla);
	}
	
	/**
	 * Elimina una célula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public String eliminarCelula (Casilla casilla){
		return mundo.eliminarCelula(casilla);
	}
	
	/**
	 * Vacía la superficie del tablero.
	 * @return String
	 */
	public String vaciar(){
		return mundo.vaciar();
	}
	
	/**
	 * Cambia simulacionTerminada a true para que el juego termine y devuelve un texto.
	 * @return String
	 */
	public String salir(){
		
		this.simulacionTerminada = true;
		return mundo.salir();
		
	}
	
	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String cargar(String nombreFichero){
		return mundo.cargar(nombreFichero);
		
	}
	
	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String guardar (String nombreFichero){
		return mundo.guardar(nombreFichero);
	}
	
	/**
	 * 
	 * @param filas
	 * @param columnas
	 * @param celulasComplejas
	 * @param celulasSimples
	 * @return
	 */
	public String jugar(int filas, int columnas, int celulasComplejas, int celulasSimples){
		return mundo.jugar(filas, columnas, celulasComplejas, celulasSimples);
	}
	
	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		return mundo.toString();
	}


}
