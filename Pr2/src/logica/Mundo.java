/*
	Practica 2: Nuevas Celulas en Nuestro Mundo
    Copyright (C) 2015  Noel Clemente / Estefania Ortega

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package logica;

/**
 * Clase encargada de comprobar si se ha realizado la acción y construir los textos que se mostraran.
 */
public class Mundo {

	// Número de células que introduce al iniciar el tablero
	private final int NUM_CELULAS = 5;
	// Número de pasos que puede estar sin mover
	private final int MAX_PASOS_SIN_MOVER = 2;
	// Número de pasos para que se reproduzca
	private final int PASOS_REPRODUCCION = 4;
	// Número de células que puede comer una celula compleja
	private final int MAX_COMER = 2;
	// Número si hay que cerrar el juego o no
	private boolean simulacionTerminada; 
	
	private Superficie superficie;
	
	/**
	 * Constructor.
	 * @param nf Número de filas.
	 * @param nc Número de columnas.
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
		
		this.simulacionTerminada = true;
		return "Fin de la simulación.....";
		
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
