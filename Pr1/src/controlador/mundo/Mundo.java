package controlador.mundo;

import controlador.mundo.superficie.Superficie;

/**
 * Clase encargada de gestionar los movimientos.
 */
public class Mundo {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private final int NUM_CELULAS = 3;
	private final int MAX_PASOS_SIN_MOVER = 4;
	private final int PASOS_REPRODUCCION = 4;
	
	private Superficie superficie;
	
	/**
	 * Constructor por defecto.
	 */
	public Mundo(int nf, int nc){
		
		this.superficie =new Superficie(nf,nc);
		
	}
	
	/**
	 * Inicia el tablero.
	 */
	public String iniciar (){
		
		if(this.superficie.iniciarSuperficie(NUM_CELULAS,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION))
			return "Iniciando simulaci�n";
		
		else
			return "No se ha podido inicializar la simulaci�n";
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String evoluciona(){
		
		if (this.superficie.paso(MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)){
			return "ha evolucionado";
		}
		else{
			return "ha fallado"; 
		}
			
	}
	
	
	/**
	 * Llama a crear celula.
	 * @param f
	 * @param c
	 * @return
	 */
	public String crearCelula(int f, int c){
		
		if(this.superficie.crearCelula(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			return "Creamos nueva c�lula en la posici�n: ("+f+","+c+")";
			
		else
			return "Imposible crear una nueva c�lula en ("+f+","+c+"), posici�n ocupada";

	}
	
	/**
	 * Elimina una celula.
	 * @param f fila.
	 * @param c columna
	 * @return
	 */
	public String eliminarCelula (int f,int c){
		
		if (this.superficie.eliminarCelula(f,c))
			return "Se ha eliminado la c�lula en la posici�n: ("+f+","+c+")";
			
		else
			return "No hay ninguna c�lula en la posici�n: ("+f+","+c+")";
		
		
	}
	
	/**
	 * Vacia la superficie del tablero.
	 * @return
	 */
	public String vaciar(){
		
		this.superficie.vaciar();
		return "Vaciando la superficie....";
		
	}
	
	/**
	 * Devuelve la ayuda.
	 * @return String
	 */
	public String ayuda(){
		
		return  "POSIBLES COMANDOS:" + LINE_SEPARATOR + 
				" INICIAR: inicia una nueva simulaci�n" + LINE_SEPARATOR +
				" PASO: realiza un paso en la simulacion" + LINE_SEPARATOR + 
				" CREARCELULA F C: crea una nueva celula en la posici�n (f,c) si es posible" + LINE_SEPARATOR +
				" ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible" + LINE_SEPARATOR +
				" VACIAR: crea un mundo vac�o" + LINE_SEPARATOR + 
				" AYUDA: muestra esta ayuda" + LINE_SEPARATOR + 
				" SALIR: cierra la aplicaci�n" + LINE_SEPARATOR ;	
		
	}
	
	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	

}
