package controlador;

import java.util.*;
import logica.Casilla;
import logica.Mundo;
import mundos.MundoComplejo;
import mundos.MundoSimple;
import java.io.*;
import excepciones.ArchivoNoEncontradoException;
import excepciones.FalloIOException;
import excepciones.FicheroErroneoException;
import excepciones.FormatoNoValidoException;
import excepciones.MundoException;
import excepciones.SinMundoException;

/**
 * Clase encargada de pedir los comandos al usuario.
 */
public class Controlador {

	private Mundo mundo = null;
	private boolean simulacionTerminada;
	// Clase que nos permite obtener datos desde el teclado (Deriva de java.util)
	Scanner s = new Scanner(System.in);

	/**
	 * Pide el comando al usuario y ejecuta la accion.
	 */
	public void simula() {

		this.mundo = null;
		this.simulacionTerminada = false;

		while (!this.simulacionTerminada) {
			try {
				System.out.println("Introduce un comando:");
	
				// Lee una linea por teclado, quita los espacios del final y del principio y pone todo a minusculas
				String linea = s.nextLine().toLowerCase().trim();
				// Separa la linea en un array identificando los espacios
				String[] palabras = linea.split(" ");

					Comando comando = ParserComandos.parseaComando(palabras);
	
					if (comando != null) {
						System.out.println(comando.ejecuta(this));
					} else {
						System.out.println("No has escrito un comando correcto.");
					}
	
			} catch (MundoException me) {
				System.err.println(me.getMessage());
			} 	
	
		}// fin while
		
	}

	
	/**
	 * Carga un mundo de un fichero.
	 * @param nombreFichero Nombre del fichero dado por el usuario.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String cargar(String nombreFichero) throws MundoException {
		
		FileReader fr = null;
		BufferedReader b = null;
		String texto;
		
		try{
			
			Mundo auxMundo = null;
			fr = new FileReader("src/"+nombreFichero);
			b = new BufferedReader(fr);
			String linea1 = b.readLine();
			String linea2 = b.readLine();
			String linea3 = b.readLine();
			
			int filas = Integer.parseInt(linea2);
			int columnas = Integer.parseInt(linea3);
			
			if(linea1.equals("simple")){
				auxMundo = new MundoSimple(filas,columnas);
			}else if(linea1.equals("complejo")){
				auxMundo = new MundoComplejo(filas,columnas);
			}else{
				b.close();
				fr.close();
				throw new FicheroErroneoException("En la linea 1.");
			}
	
			if(auxMundo.cargar(b)){
				this.mundo = auxMundo;
				texto = "Fichero cargado correctamente";
			}else{
				texto = "No se ha podido cargar el fichero";
			}
		
		}catch (FileNotFoundException fnfe) {
			throw new ArchivoNoEncontradoException();
		}catch (IOException e) {
			throw new FalloIOException("En Controlador");
		}catch(NumberFormatException nfe){
			throw new FormatoNoValidoException("en la linea 2 o 3.");
		}finally{
		
			try {
				b.close();
				fr.close();
			} catch (IOException e) {
				throw new FalloIOException("Al cerrar el fichero");
			}
		}
		
		return texto;
		
	}

	/**
	 * Guarda un mundo en un fichero.
	 * @param nombreFichero Nombre del fichero dado por el usuario.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String guardar(String nombreFichero) throws MundoException {
		
		if(this.mundo == null){
			throw new SinMundoException();
		}
		
		FileWriter fw = null;
        PrintWriter pw = null;
		String texto;
		
        try
        {
        	fw = new FileWriter("src/" + nombreFichero);
            pw = new PrintWriter(fw);
 
            if(this.mundo.guardar(pw)){
				texto = "Fichero guardado correctamente";
			}else{
				texto = "No se ha podido guardar el fichero";
			}
 
        }catch (FileNotFoundException fnfe) {
			throw new ArchivoNoEncontradoException();
		}catch (IOException e) {
			throw new FalloIOException("En Controlador");
		}finally {
			try {
				pw.close();
				fw.close();
			} catch (IOException e) {
				throw new FalloIOException("Al cerrar el fichero");
			}
        }
		
		return texto;
	}

	
	/**
	 * Te permite jugar en un mundo simple o en un mundo complejo.
	 * @param mundo Mundo que va a cargar.
	 * @return String
	 */
	public String jugar(Mundo mundo) {
		this.mundo = mundo;
		return this.mundo.inicializaMundo();
	}
	
	/**
	 * Reinicia el tablero. Devuelve si ha podido a no en una cadena de caracteres.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String iniciar() throws MundoException {
		if(this.mundo == null){
			throw new SinMundoException();
		}
		return this.mundo.iniciar();
	}

	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String evoluciona() throws MundoException {
		if(this.mundo == null){
			throw new SinMundoException();
		}
		return this.mundo.evoluciona();
	}

	/**
	 * Llama a crear celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posicion del tablero.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String crearCelula(Casilla casilla) throws MundoException {
		if(this.mundo == null){
			throw new SinMundoException();
		}
		return this.mundo.crearCelula(casilla);
	}

	/**
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posicion del tablero.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String eliminarCelula(Casilla casilla) throws MundoException {
		if(this.mundo == null){
			throw new SinMundoException();
		}
		return this.mundo.eliminarCelula(casilla);
	}

	/**
	 * Vacia la superficie del tablero.
	 * @return String
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public String vaciar() throws MundoException {
		if(this.mundo == null){
			throw new SinMundoException();
		}
		return this.mundo.vaciar();
	}

	/**
	 * Cambia simulacionTerminada a true para que el juego termine y devuelve un texto.
	 * @return String
	 */
	public String salir() {
		this.simulacionTerminada = true;
		return "Fin de la simulación.....";
	}

	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString() {
		return mundo.toString();
	}

}
