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
	public void evoluciona(){
		
		boolean[][] tablero = new boolean[superficie.getFilas()][superficie.getColumnas()];
		
		for (int f=0; f<superficie.getFilas(); f++){
			for (int c=0; c<superficie.getColumnas(); c++){
				
				tablero[f][c] = false;
				
			}
		}
		
		for (int f=0; f<this.superficie.getFilas(); f++){
			for (int c=0; c<this.superficie.getColumnas(); c++){
				
				if(tablero[f][c] == false){
					int[] t = new int[2];
					t = this.sacarDireccion(f,c);
					if(t!=null){
						if(this.superficie.moverCelula(f,c,t[0],t[1])){
							this.superficie.sumarPaso(t[0],t[1]);
							tablero[f][c] = true;
							tablero[t[0]][t[1]] = true;
						}
						else{
							this.superficie.sumarPasoSinMover(f,c);
							tablero[f][c] = true;
						}
					}
				}
			}
		}
			
	}
	
	/**
	 * Saca las coordenadas a donde se mueve la celula
	 * @return array con las cordenadas de donde se va a mover la celula.
	 */
	public int[] sacarDireccion(int f,int c){
		
		if (this.superficie.existeCelula(f,c)){
			
			boolean salir = false;
			int f2 = 0;
			int c2 = 0;
			int[] t = new int [2];
		
			while(!salir){
				
				int dir = (int)(Math.random()*7);
				
				if(dir == 0){ // Arriba Izquierda
					f2 = f-1;
					c2 = c-1;
				}else if(dir == 1){ // Arriba
					f2 = f-1;
					c2 = c;
				}else if(dir == 2){ // Arriba Derecha
					f2 = f-1;
					c2 = c+1;
				}else if(dir == 3){ // Izquierda
					f2 = f;
					c2 = c-1;
				}else if(dir == 4){ // Derecha
					f2 = f;
					c2 = c+1;
				}else if(dir == 5){ // Abajo Izquierda
					f2 = f+1;
					c2 = c-1;
				}else if(dir == 6){ // Abajo
					f2 = f+1;
					c2 = c;
				}else if(dir == 7){ // Abajo Izquierda
					f2 = f+1;
					c2 = c+1;
				}
				
				if((f2>=0 && f2<this.superficie.getFilas())&&(c2>=0 && c2<this.superficie.getColumnas())){
					salir = true;
					t[0] = f2;
					t[1] = c2;
				}		
			}
			return t;
		}
		return null;
		
	}
	
	/**
	 * Llama a crear celula he imprime por pantalla si ha podido.
	 */
	public void crearCelula(int f, int c){
		
		if(superficie.crearCelula(f,c,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
			System.out.println("Creamos nueva célula en la posición: ("+f+","+c+")");
			
		else
			System.out.println("Imposible crear una nueva célula, posición ocupada");

	}
	
	/**
	 * Elimina una celula.
	 * @param f fila.
	 * @param c columna.
	 */
	public void eliminarCelula (int f,int c){
		
		if (superficie.eliminarCelula(f,c))
			System.out.println("Se ha eliminado la célula en la posición: ("+f+","+c+")");
			
		else
			System.out.println("No hay ninguna célula en la posición: ("+f+","+c+")");
		
		
	}
	
	/**
	 * Vacia la superficie del tablero.
	 */
	public void vaciar(){
		
		System.out.println("Vaciando la superficie....");
		superficie.vaciar();
		
	}
	
	/**
	 * Inicia el tablero.
	 */
	public void iniciar (){
		
		superficie.vaciar();
		
		for(int i = 0; i < NUM_CELULAS; i++){
			int f = (int)(Math.random()*superficie.getFilas());
			int c = (int)(Math.random()*superficie.getColumnas());
			while(!superficie.crearCelula(f+1, c+1, MAX_PASOS_SIN_MOVER, PASOS_REPRODUCCION)){
				f = (int)(Math.random()*superficie.getFilas());
				c = (int)(Math.random()*superficie.getColumnas());
			}
		}
		System.out.println ("Iniciando simulación");
		
	}
	
	/**
	 * Imprime el tablero.
	 */
	public void imprimirSuperficie(){
		
		System.out.println(superficie.toString());
		
	}
	
	/**
	 * Imprime la ayuda.
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
