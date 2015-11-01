package controlador;

import java.util.*;

import controlador.mundo.Mundo;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private Mundo mundo =new Mundo(5,5);
	
	//Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s= new Scanner(System.in);
	
	/**
	 * Pide el comando al usuario y ejecuta la accion.
	 */
	public void simula(){
			
		
		String str="";
		
		while(!str.toLowerCase().equals("salir")){
			
			System.out.println("Introduce un comando\n");
			
			str=s.next().toLowerCase().trim();	
			
			if (str.equals("paso")){
				System.out.println("Has escrito paso");
			}
			
			else if (str.equals("iniciar")){
				System.out.println("Has escrito iniciar");
			}
			
			else if (str.equals("crearcelula")){
				
				if(mundo.crearCelula(2,3)){
					System.out.println("Creamos nueva celula en la posición: ("+2+","+3+")");
					
				}else{
					System.out.println("No se ha podido crear la celula");
				}
				
				System.out.println(mundo.imprimirSuperficie());
			}
			
			else if (str.equals("eliminarcelula")){
				System.out.println("Has escrito eliminar celula");
			}
			
			else if (str.equals("ayuda")){
				System.out.println(interpreterHelp());
			}
			
			else if (str.equals("vaciar")){
				System.out.println("Vaciando la superficie....");
			}
			
			else if (str.equals("salir")){
				System.out.println("Fin de la simulacion.....");
			}
			
			else
				System.out.println("No has escrito un comando correcto");
			
		}//fin while
			
		/*	try (int s.nextInt());
					
		}
		catch(){
			System.out.println("error");
			System.out.println();
		}*/
	}

	/**
	 * Devuelve la ayuda.
	 * @return String con la ayuda.
	 */
	public String interpreterHelp(){
		return	"POSIBLES COMANDOS:" + LINE_SEPARATOR + 
				" PASO: realiza un paso en la simulacion" + LINE_SEPARATOR + 
				" AYUDA: muestra esta ayuda" + LINE_SEPARATOR + 
				" SALIR: cierra la aplicación" + LINE_SEPARATOR + 
				" INICIAR: inicia una nueva simulación" + LINE_SEPARATOR + 
				" VACIAR: crea un mundo vacío" + LINE_SEPARATOR + 
				" CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible" + LINE_SEPARATOR + 
				" ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible" + LINE_SEPARATOR ;
	}

}
