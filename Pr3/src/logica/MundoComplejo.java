package logica;

public class MundoComplejo {
	// Número de células que introduce al iniciar el tablero
		private final int NUM_CELULAS = 5;
		// Número de pasos que puede estar sin mover
		private final int MAX_PASOS_SIN_MOVER = 2;
		// Número de pasos para que se reproduzca
		private final int PASOS_REPRODUCCION = 4;
		// Número de células que puede comer una celula compleja
		private final int MAX_COMER = 2;
		
		private Superficie superficie;
		
		/**
		 * Constructor por defecto.
		 */
		public Mundo(){
			
			this.superficie =new Superficie(5,5);
			
		}
		
		/**
		 * Constructor parametrizado.
		 * @param nf Número de filas.
		 * @param nc Número de columnas.
		 */
		public Mundo(int nf, int nc){
			
			this.superficie =new Superficie(nf,nc);
			
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
		 * Llama a crear célula simple. Devuelve si ha podido a no en una cadena de caracteres. 
		 * @param casilla Posición del tablero.
		 * @return String
		 */
		public String crearCelulaSimple(Casilla casilla){
			
			if(this.superficie.crearCelulaSimple(casilla ,MAX_PASOS_SIN_MOVER,PASOS_REPRODUCCION)) 
				return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

		}
		
		/**
		 * Llama a crear célula compleja. Devuelve si ha podido a no en una cadena de caracteres. 
		 * @param casilla Posición del tablero.
		 * @return String
		 */
		public String crearCelulaCompleja(Casilla casilla){
			
			if(this.superficie.crearCelulaCompleja(casilla ,MAX_COMER)) 
				return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

		}
		
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
		 * 
		 * @param filas
		 * @param columnas
		 * @param celulasComplejas
		 * @param celulasSimples
		 * @return
		 */
		public String jugar(int filas, int columnas, int celulasComplejas, int celulasSimples){
			return "Cambiando de juego";
		}
		
		/**
		 * Devuelve el tablero.
		 * @return String
		 */
		public String toString(){
			
			return this.superficie.toString();
			
		}
}
