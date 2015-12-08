package logica;


/**
 * Clase encargada de comprobar si se ha realizado la accion y construir los textos que se mostraran.
 */
public class Mundo {

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
		this.simulacionTerminada=false;
		
	}
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public String iniciar (){
		
		if(this.superficie.iniciarSuperficie(NUM_CELULAS,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION,MAX_COMER))
			return "Iniciando simulación";
		
		else
			return "No se ha podido inicializar la simulación";
		
	}
	
	/**
	 * Llama a paso y devuelve el resultado de los pasos realizados.
	 * @return String
	 */
	public String evoluciona(){
		
		return this.superficie.evoluciona(MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION,MAX_COMER);
			
	}
	
	
	/**
	 * Llama a crear celula simple. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public String crearCelulaSimple(Casilla casilla){
		
		if(this.superficie.crearCelulaSimple(casilla ,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	
	/**
	 * Llama a crear celula compleja. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public String crearCelulaCompleja(Casilla casilla){
		
		if(this.superficie.crearCelulaCompleja(casilla ,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION,MAX_COMER)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	
	/**
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public String eliminarCelula (Casilla casilla){
		
		if (this.superficie.eliminarCelula(casilla))
			return "Se ha eliminado la célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "No hay ninguna célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
		
		
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
	 * Cambia simulacionTerminada a true para que el juego termine y devuelve un texto.
	 * @return String
	 */
	public String salir(){
		
		this.simulacionTerminada = true;
		return "Fin de la simulacion.....";
		
	}
	
	/**
	 * Comprueba si tiene que seguir la simulación y lo de vuelve false si continúa.
	 * @return boolean
	 */
	public boolean sigueSimulacion(){
		
		return this.simulacionTerminada;
		
	}
	
	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
}
