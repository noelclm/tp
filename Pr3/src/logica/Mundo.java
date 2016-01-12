package logica;

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
		
		this.columnas = nf;
		this.columnas = nc;
		this.superficie =new Superficie(nf,nc);
		this.inicializaMundo();
		
	}
	
	/**
	 * Llama a crear célula simple. Devuelve si ha podido a no en una cadena de caracteres. 
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
	 * Llama a crear célula simple. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	/*public String crearCelulaSimple(Casilla casilla){
		
		if(this.superficie.crearCelulaSimple(casilla)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	*/
	/**
	 * Llama a crear célula compleja. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	/*public String crearCelulaCompleja(Casilla casilla){
		
		if(this.superficie.crearCelulaCompleja(casilla)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}*/
	
	/**
	 * Elimina una célula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posición del tablero.
	 * @return String
	 */
	public String eliminarCelula (Casilla casilla){
		
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
	 * Cambia simulacionTerminada a true para que el juego termine y devuelve un texto.
	 * @return String
	 */
	public String salir(){
		
		return "Fin de la simulación.....";

	}
	
	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String cargar(String nombreFichero){
		return "fichero cargador";
	}
	
	/**
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public String guardar (String nombreFichero){
		return "fichero guardado";
	}

	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	
}
