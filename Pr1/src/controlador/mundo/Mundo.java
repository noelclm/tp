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
	 * Recorre la superficie y ejecuta los pasos acorde a las reglas del juego.
	 */
	public String evoluciona(){
		
		if (this.superficie.paso()){
			return "ha evolucionado";
		}
		else{
			return "ha fallado"; 
		}
			
	}
	
	
	/**
	 * Llama a crear celula he imprime por pantalla si ha podido.
	 */
	public String crearCelula(int f, int c){
		
		if(this.superficie.crearCelula(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			return "Creamos nueva célula en la posición: ("+f+","+c+")";
			
		else
			return "Imposible crear una nueva célula, posición ocupada";

	}
	
	/**
	 * Elimina una celula.
	 * @param f fila.
	 * @param c columna.
	 */
	public String eliminarCelula (int f,int c){
		
		if (this.superficie.eliminarCelula(f,c))
			return "Se ha eliminado la célula en la posición: ("+f+","+c+")";
			
		else
			return "No hay ninguna célula en la posición: ("+f+","+c+")";
		
		
	}
	
	/**
	 * Vacia la superficie del tablero.
	 */
	public String vaciar(){
		
		this.superficie.vaciar();
		return "Vaciando la superficie....";
		
	}
	
	/**
	 * Inicia el tablero.
	 */
	public String iniciar (){
		
		this.superficie.vaciar();
		
		for(int i = 0; i < NUM_CELULAS; i++){
			int f = (int)(Math.random()*this.superficie.getFilas());
			int c = (int)(Math.random()*this.superficie.getColumnas());
			while(!this.superficie.crearCelula(f, c, MAX_PASOS_SIN_MOVER, PASOS_REPRODUCCION)){
				f = (int)(Math.random()*this.superficie.getFilas());
				c = (int)(Math.random()*this.superficie.getColumnas());
			}
		}
		return "Iniciando simulación";
		
	}
	
	/**
	 * Imprime el tablero.
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	/**
	 * Imprime la ayuda.
	 */
	public String ayuda(){
		return  "POSIBLES COMANDOS:" + LINE_SEPARATOR + 
				" PASO: realiza un paso en la simulacion" + LINE_SEPARATOR + 
				" AYUDA: muestra esta ayuda" + LINE_SEPARATOR + 
				" SALIR: cierra la aplicación" + LINE_SEPARATOR + 
				" INICIAR: inicia una nueva simulación" + LINE_SEPARATOR + 
				" VACIAR: crea un mundo vacío" + LINE_SEPARATOR + 
				" CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible" + LINE_SEPARATOR + 
				" ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible" + LINE_SEPARATOR ;
	}

}
