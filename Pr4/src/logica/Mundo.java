package logica;

import java.io.BufferedReader;
import java.io.PrintWriter;

import excepciones.MundoException;

/**
 * Clase abstracta encargada de comprobar si se ha realizado la accion y construir los textos que se mostraran.
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
	 * @param nf Numero de filas.
	 * @param nc Numero de columnas.
	 */
	public Mundo(int nf, int nc){
		
		this.filas = nf;
		this.columnas = nc;
		this.superficie =new Superficie(nf,nc);
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public abstract String inicializaMundo ();
	
	/**
	 * Carga un mundo de un fichero.
	 * @param b Es un BufferedReader de donde lee.
	 * @return boolean
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public abstract boolean cargar(BufferedReader b) throws MundoException;
	
	/**
	 * Guarda un mundo en un fichero.
	 * @param pw Es un PrintWriter en donde escribe.
	 * @return boolean
	 */
	public abstract boolean guardar (PrintWriter pw);
	
	/**
	 * Reinicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public abstract String iniciar();
	
	/**
	 * Llama a crear celula de cada mundo. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public abstract String crearCelula(Casilla casilla);
	
	/**
	 * Llama a evoluciona y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		return this.superficie.evoluciona();
			
	}
		
	/**
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posicion del tablero.
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
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	
}
