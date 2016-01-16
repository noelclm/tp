package controlador;

import java.util.*;
import logica.Casilla;
import logica.Mundo;
import logica.MundoComplejo;
import logica.MundoSimple;
import java.io.*;
import excepciones.ArchivoNoEncontradoException;
import excepciones.FalloIOException;
import excepciones.FicheroErroneoException;
import excepciones.MundoException;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {

	private Mundo mundo = null;
	private boolean simulacionTerminada;
	// Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s = new Scanner(System.in);

	/**
	 * Pide el comando al usuario y ejecuta la acción.
	 */
	public void simula() {

		this.mundo = new MundoSimple();
		this.simulacionTerminada = false;

		while (!this.simulacionTerminada) {

			System.out.println("Introduce un comando:");

			// Lee una linea por teclado
			String linea = s.nextLine().toLowerCase().trim();
			// Separa la linea en un array identificando los espacios
			String[] palabras = linea.split(" ");

			try {

				Comando comando = ParserComandos.parseaComando(palabras);

				if (comando != null) {
					System.out.println(comando.ejecuta(this));
				} else {
					System.out.println("No has escrito un comando correcto.");
				}

			} catch (MundoException ne) {
				System.err.println(ne.getMessage());
			} 

		}// fin while

	}

	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de
	 * caracteres.
	 * 
	 * @return String
	 */
	public String iniciar() {
		return this.mundo.iniciar();
	}

	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * 
	 * @return String
	 */
	public String evoluciona() {
		return this.mundo.evoluciona();
	}

	/**
	 * Llama a crear célula simple. Devuelve si ha podido a no en una cadena de
	 * caracteres.
	 * 
	 * @param casilla
	 *            Posición del tablero.
	 * @return String
	 */
	public String crearCelula(Casilla casilla) {
		return this.mundo.crearCelula(casilla);
	}

	/**
	 * Elimina una célula. Devuelve si ha podido a no en una cadena de
	 * caracteres.
	 * 
	 * @param casilla
	 *            Posición del tablero.
	 * @return String
	 */
	public String eliminarCelula(Casilla casilla) {
		return this.mundo.eliminarCelula(casilla);
	}

	/**
	 * Vacía la superficie del tablero.
	 * 
	 * @return String
	 */
	public String vaciar() {
		return this.mundo.vaciar();
	}

	/**
	 * Cambia simulacionTerminada a true para que el juego termine y devuelve un
	 * texto.
	 * 
	 * @return String
	 */
	public String salir() {
		this.simulacionTerminada = true;
		return this.mundo.salir();
	}

	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String cargar(String nombreFichero) throws MundoException {

		try{
			Mundo auxMundo = null;
			FileReader fr = new FileReader("src/"+nombreFichero);
			BufferedReader b = new BufferedReader(fr);
			String linea = b.readLine();
			
			if(linea == "simple"){
				auxMundo = new MundoSimple();
			}else if(linea == "complejo"){
				auxMundo = new MundoComplejo();
			}else{
				b.close();
				fr.close();
				throw new FicheroErroneoException("En la linea 1.");
			}
	
			if(auxMundo.cargar(b)){
				this.mundo = auxMundo;
				b.close();
				fr.close();
				return "Fichero cargado correctamente";
			}else{
				b.close();
				fr.close();
				
				return "No se ha podido cargar el fichero";
			}
			
			
		
		}catch (FileNotFoundException fnfe) {
			throw new ArchivoNoEncontradoException();
		} catch (IOException e) {
			throw new FalloIOException("En Controlador");
		}
		

	}

	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String guardar(String nombreFichero) {
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
	public String jugar(Mundo mundo) {
		this.mundo = mundo;
		return this.mundo.inicializaMundo();
	}

	/**
	 * Devuelve el tablero.
	 * 
	 * @return String
	 */
	public String toString() {
		return mundo.toString();
	}

}
