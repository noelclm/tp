package logica;

import java.io.BufferedReader;
import java.io.PrintWriter;

import excepciones.MundoException;

/**
 * Clase encargada de comprobar si se ha realizado la acción y construir los textos que se mostraran.
 */
public abstract class Mundo {

	protected int filas;
	protected int columnas;
	protected Superficie superficie;
	
	/**
	 * Constructor por defecto.
	 */
	public Mundo(){
		
		this.filas = 0;
		this.columnas = 0;
		this.superficie = null;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param nf Número de filas.
	 * @param nc Número de columnas.
	 */
	public Mundo(int nf, int nc){
		
		this.filas = nf;
		this.columnas = nc;
		this.superficie =new Superficie(nf,nc);
		
	}
	
	/**
	 * Llama a crear célula de cada mundo. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public abstract String crearCelula(Casilla casilla);
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public abstract String inicializaMundo ();
	
	/**
	 * 
	 * @param buffer
	 * @return
	 * @throws IOException 
	 */
	public abstract boolean cargar(BufferedReader b) throws MundoException;
	
	/**
	 * 
	 * @param nombreFichero
	 * @return
	 * @throws MundoException 
	 */
	public abstract boolean guardar (PrintWriter pw);
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public String iniciar(){
		
		if(this.superficie.iniciarSuperficie(5))
			return "Iniciando simulación";
		
		else
			return "No se ha podido inicializar la simulación";
		
	}
	
	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		return this.superficie.evoluciona();
			
	}
		
	/**
	 * Elimina una célula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public String eliminarCelula (Casilla casilla) {
		
		if (this.superficie.eliminarCelula(casilla))
			return "Se ha eliminado la célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
		
		else
			return "No hay ninguna célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
	}
	
	/**
	 * Vacía la superficie del tablero.
	 * @return String
	 */
	public String vaciar(){
		
		this.superficie.vaciar();
		return "Vaciando la superficie....";
		
	}
	
	/**
	 * Devuelve un texto de finalizar simulación.
	 * @return String
	 */
	public String salir(){
		
		return "Fin de la simulación.....";

	}
	
	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	
}
