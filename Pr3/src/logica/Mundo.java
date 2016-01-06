package logica;

/**
 * Clase encargada de comprobar si se ha realizado la acción y construir los textos que se mostraran.
 */
public abstract class Mundo {

	// Número de células que introduce al iniciar el tablero
	protected final int NUM_CELULAS = 5;
	// Número de pasos que puede estar sin mover
	protected final int MAX_PASOS_SIN_MOVER = 2;
	// Número de pasos para que se reproduzca
	protected final int PASOS_REPRODUCCION = 4;
	// Número de células que puede comer una celula compleja
	protected final int MAX_COMER = 2;
	
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
		
		this.columnas = nf;
		this.columnas = nc;
		this.superficie =new Superficie(nf,nc);
		this.inicializaMundo();
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public abstract String inicializaMundo ();
	
	
}
