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
	// Numero de celulas que puede comer una celula compleja
	private final int MAX_COMER = 4;
	// Marca si hay que cerrar el juego o no
	private boolean simulacionTerminada; 
	
	private Superficie superficie;
	
	/**
	 * Constructor.
	 * @param nf Numero de filas.
	 * @param nc Numero de columnas.
	 */
	public Mundo(int nf, int nc){
		
		this.superficie =new Superficie(nf,nc);
		this.simulacionTerminada=true;
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public String iniciar (){
		
		if(this.superficie.iniciarSuperficie(NUM_CELULAS,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION))
			return "Iniciando simulaci�n";
		
		else
			return "No se ha podido inicializar la simulaci�n";
		
	}
	
	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		return this.superficie.evoluciona(MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION);
			
	}
	
	
	/**
	 * Llama a crear celula simple. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param f fila.
	 * @param c columna.
	 * @return String
	 */
	public String crearCelulaSimple(int f, int c){
		
		if(this.superficie.crearCelulaSimple(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			return "Creamos nueva c�lula en la posici�n: ("+f+","+c+")";
			
		else
			return "Imposible crear una nueva c�lula en ("+f+","+c+"), posici�n ocupada";

	}
	
	/**
	 * Llama a crear celula compleja. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param f fila.
	 * @param c columna.
	 * @return String
	 */
	public String crearCelulaCompleja(int f, int c){
		
		if(this.superficie.crearCelulaCompleja(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION,MAX_COMER)) 
			return "Creamos nueva c�lula en la posici�n: ("+f+","+c+")";
			
		else
			return "Imposible crear una nueva c�lula en ("+f+","+c+"), posici�n ocupada";

	}
	
	/**
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param f fila.
	 * @param c columna.
	 * @return String
	 */
	public String eliminarCelula (int f,int c){
		
		if (this.superficie.eliminarCelula(f,c))
			return "Se ha eliminado la c�lula en la posici�n: ("+f+","+c+")";
			
		else
			return "No hay ninguna c�lula en la posici�n: ("+f+","+c+")";
		
		
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
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean sigueSimulacion(){
		
		return this.simulacionTerminada;
		
	}
	
}