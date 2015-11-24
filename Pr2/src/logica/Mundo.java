package logica;


/**
 * Clase encargada de comprobar si se ha realizado la accion y construir los textos que se mostraran.
 */
public class Mundo {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	// Numero de celulas que introduce al iniciar el tablero
	private final int NUM_CELULAS = 3;
	// Numero de pasos que puede estar sin mover
	private final int MAX_PASOS_SIN_MOVER = 4;
	// Numero de pasos para que se reproduzca
	private final int PASOS_REPRODUCCION = 4;
	
	private Superficie superficie;
	
	/**
	 * Constructor.
	 * @param nf Numero de filas.
	 * @param nc Numero de columnas.
	 */
	public Mundo(int nf, int nc){
		
		this.superficie =new Superficie(nf,nc);
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public String iniciar (){
		
		if(this.superficie.iniciarSuperficie(NUM_CELULAS,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION))
			return "Iniciando simulación";
		
		else
			return "No se ha podido inicializar la simulación";
		
	}
	
	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		return this.superficie.evoluciona(MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION);
			
	}
	
	
	/**
	 * Llama a crear celula. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param f fila.
	 * @param c columna.
	 * @return String
	 */
	public String crearCelula(int f, int c){
		
		if(this.superficie.crearCelula(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			return "Creamos nueva célula en la posición: ("+f+","+c+")";
			
		else
			return "Imposible crear una nueva célula en ("+f+","+c+"), posición ocupada";

	}
	
	/**
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param f fila.
	 * @param c columna.
	 * @return String
	 */
	public String eliminarCelula (int f,int c){
		
		if (this.superficie.eliminarCelula(f,c))
			return "Se ha eliminado la célula en la posición: ("+f+","+c+")";
			
		else
			return "No hay ninguna célula en la posición: ("+f+","+c+")";
		
		
	}
	
	/**
	 * Vacia la superficie del tablero.
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
