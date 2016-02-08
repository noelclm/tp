/*
	Practica 1: Simulacion de un Mundo Celular Simple
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
 * Clase que gestiona una celula.
 */
public class Celula {

	private int pasosSinMover;
	private int pasosDados;
	private final int MAX_PASOS_SIN_MOVER;
	private final int PASOS_REPRODUCCION;
	
	/**
	 * Constructor.
	 * @param pasosSinMover Numero de pasos sin mover que tiene la celula.
	 * @param pasosReproduccion Numero de pasos para que la celula se reproduzca.
	 */
	public Celula(int pasosSinMover, int pasosReproduccion){
		
		this.MAX_PASOS_SIN_MOVER = pasosSinMover;
		this.PASOS_REPRODUCCION = pasosReproduccion;
		this.pasosSinMover = 0;
		this.pasosDados = 0;
		
	}
	
	/**
	 * Suma uno al numero de pasos dados.
	 */
	public void sumPasosDados(){
		
		this.pasosDados++;
		
	}
	
	/**
	 * Suma uno al numero de pasos sin mover.
	 */
	public void sumPasosSinMover(){
		
		this.pasosSinMover++;
		
	}
	
	/**
	 * Devuelve el numero de pasos que ha dado la celula.
	 * @return int
	 */
	public int getPasosDados(){
		
		return this.pasosDados;
		
	}
	
	/**
	 * Devuelve el numero de pasos que lleva sin moverse la celula.
	 * @return int
	 */
	public int getPasosSinMover(){
		
		return this.pasosSinMover;
		
	}
	
	/**
	 * Devuelve el numero de pasos que queda para que la celula se reproduzca.
	 * @return int
	 */
	public int getQuedaParaReproducirse(){
		
		return this.PASOS_REPRODUCCION-this.pasosDados;
		
	}
	
	/**
	 * Devuelve el numero de pasos que queda para que la celula muera.
	 * @return int
	 */
	public int getPasosParaMorir(){
		
		return this.MAX_PASOS_SIN_MOVER-this.pasosSinMover;
		
	}
	
	/**
	 * Metodo que devuelve false si ha llegado al limite de pasos sin mover o true si no.
	 * @return boolean
	 */
	public boolean limitePasosSinMover(){
		
		if (this.pasosSinMover == this.MAX_PASOS_SIN_MOVER)
			return true;
		
		else 
			return false;
		
	}
	
	/**
	 * Metodo que devuelve true si tiene que reproducirse o false si no.
	 * @return boolean
	 */
	public boolean limitePasosDados(){
			
		if (this.pasosDados == this.PASOS_REPRODUCCION)
			return true;
		
		else
			return false;
		
	}
	
	/**
	 * Metodo que reinicia los pasos dados.
	 */
	public void reiniciaPasosReproduccion(){
		this.pasosDados = 0;
	}
	
	/**
	 * Devuelve un string para pintar la celula en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return "["+this.getPasosParaMorir()+"]-["+this.getQuedaParaReproducirse()+"]";
		
	}

}
