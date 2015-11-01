package controlador.mundo;

import controlador.mundo.superficie.Superficie;

/**
 * Clase encargada de gestionar los movimientos.
 */
public class Mundo {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static int numCelulas = 4;
	
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
		
		boolean[][] tablero = new boolean[superficie.getFilas()][superficie.getColumnas()];
		
		for (int i=0; i<superficie.getFilas(); i++){
			for (int j=0; j<superficie.getColumnas(); j++){
				
				tablero[i][j] = false;
				
			}
		}
		
		for (int i=0; i<superficie.getFilas(); i++){
			for (int j=0; j<superficie.getColumnas(); j++){
				
				if(tablero[i][j] == false){
					int[] t = new int[2];
					t = superficie.moverCelula(i,j);
					tablero[i][j] = true;
					tablero[t[0]][t[1]] = true;
				}
			}
		}
		
		
		
	}
	
	/**
	 * Llama a crear celula he imprime por pantalla si ha podido.
	 */
	public void crearCelula(int f, int c){
		
		if(superficie.crearCelula(f,c,3,1)) // TODO Mirar de donde se sacan los datos de maxPasosSinMover, pasosReproduccion
			System.out.println("Creamos nueva celula en la posición: ("+f+","+c+")");
			
		else
			System.out.println("Imposible crear una nueva celula, posicion ocupada");

	}
	public void eliminarCelula (int f,int c){
		
		if (superficie.eliminarCelula(f,c))
			System.out.println("Se ha eliminado la celula en la posicion: ("+f+","+c+")");
			
		else
			System.out.println("No hay ninguna celula en la posicion: ("+f+","+c+")");
		
		
	}
	public void vaciar(){
		
		System.out.println("Vaciando la superficie....");
		superficie.vaciar();
		
	}
	
	public void iniciar (){
		
		superficie.vaciar();
		
		for(int i = 0; i < numCelulas; i++){
			int f = (int)(Math.random()*superficie.getFilas());
			int c = (int)(Math.random()*superficie.getColumnas());
			while(!superficie.crearCelula(f+1, c+1, 3, 1)){
				f = (int)(Math.random()*superficie.getFilas());
				c = (int)(Math.random()*superficie.getColumnas());
			}
		}
		System.out.println ("Iniciando simulación");
		
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
