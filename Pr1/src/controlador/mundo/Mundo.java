package controlador.mundo;

import controlador.mundo.superficie.Superficie;

/**
 * Clase encargada de gestionar los movimientos.
 */
public class Mundo {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
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
	public void evoluciona(){
		
	}
	
	/**
	 * Llama a crear celula he imprime por pantalla si ha podido.
	 */
	public void crearCelula(int f, int c){
		
		if(superficie.crearCelula(f,c,3,1)) // TODO Mirar de donde se sacan los datos de maxPasosSinMover, pasosReproduccion
			System.out.println("Creamos nueva celula en la posición: ("+f+","+c+")");
			
		else
			System.out.println("No se ha podido crear la celula");

	}
	
	/**
	 * Devuelve la superficie en un string para dibujarlo.
	 */
	public void imprimirSuperficie(){
		
		System.out.println(superficie.toString());
		
	}
	
	/**
	 * Devuelve la ayuda.
	 * @return String con la ayuda.
	 */
	public void interpreterHelp(){
		System.out.println("POSIBLES COMANDOS:" + LINE_SEPARATOR + 
				" PASO: realiza un paso en la simulacion" + LINE_SEPARATOR + 
				" AYUDA: muestra esta ayuda" + LINE_SEPARATOR + 
				" SALIR: cierra la aplicación" + LINE_SEPARATOR + 
				" INICIAR: inicia una nueva simulación" + LINE_SEPARATOR + 
				" VACIAR: crea un mundo vacío" + LINE_SEPARATOR + 
				" CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible" + LINE_SEPARATOR + 
				" ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible" + LINE_SEPARATOR) ;
	}

}
