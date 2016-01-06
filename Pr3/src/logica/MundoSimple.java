package logica;

public class MundoSimple extends Mundo{
		
		/**
		 * Constructor por defecto.
		 */
		public MundoSimple(){
			super();
		}
		
		/**
		 * Constructor parametrizado.
		 * @param nf Número de filas.
		 * @param nc Número de columnas.
		 */
		public MundoSimple(int nf, int nc){
			super(nf,nc);		
		}
		
		/**
		 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
		 * @return String
		 */
		public String iniciar(){
			
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

		@Override
		public String inicializaMundo() {
			// TODO Auto-generated method stub
			return null;
		}

}
