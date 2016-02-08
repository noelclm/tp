/*
	Practica 3: Interfaces, Ficheros y Excepciones
    Copyright (C) 2016  Noel Clemente / Estefania Ortega

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

import java.io.BufferedReader;
import java.io.PrintWriter;

import excepciones.MundoException;

/**
 * Clase abstracta encargada de comprobar si se ha realizado la accion y construir los textos que se mostraran.
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
	 * @param nf Numero de filas.
	 * @param nc Numero de columnas.
	 */
	public Mundo(int nf, int nc){
		
		this.filas = nf;
		this.columnas = nc;
		this.superficie =new Superficie(nf,nc);
		
	}
	
	/**
	 * Llama a crear celula de cada mundo. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public abstract String crearCelula(Casilla casilla);
	
	/**
	 * Inicia el tablero. Devuelve si ha podido a no en una cadena de caracteres. 
	 * @return String
	 */
	public abstract String inicializaMundo ();
	
	/**
	 * Carga un mundo de un fichero.
	 * @param b Es un BufferedReader de donde lee.
	 * @return boolean
	 * @throws MundoException Excepcion de la que heredan las demas excepciones.
	 */
	public abstract boolean cargar(BufferedReader b) throws MundoException;
	
	/**
	 * Guarda un mundo en un fichero.
	 * @param pw Es un PrintWriter en donde escribe.
	 * @return boolean
	 */
	public abstract boolean guardar (PrintWriter pw);
	
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
	 * Elimina una celula. Devuelve si ha podido a no en una cadena de caracteres.
	 * @param casilla Posicion del tablero.
	 * @return String
	 */
	public String eliminarCelula (Casilla casilla) {
		
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
	 * Devuelve un texto de finalizar simulacion.
	 * @return String
	 */
	public String salir(){
		
		return "Fin de la simulación.....";

	}
	
	/**
	 * Devuelve el tablero.
	 * @return String
	 */
	public String toString(){
		
		return this.superficie.toString();
		
	}
	
	
}
