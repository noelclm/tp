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
 * Clase que gestiona una célula compleja que hereda de Celula.
 */
public class CelulaCompleja extends Celula {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int comidas;
	private final int MAX_COMER;

	/**
	 * Constructor parametrizado
	 * @param pasosSinMover Pasos que puede estar sin moverse.
	 * @param pasosReproduccion Pasos que tiene que dar para reproducirse.
	 * @param max_comer Máximas células que puede comer.
	 */
	public CelulaCompleja(int max_comer) {
		
		super();
		this.esComestible = false;
		this.MAX_COMER = max_comer;
		this.comidas=0;
		
	}

	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, Superficie superficie) {
		
		Casilla casillaFinal = null;
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int f = casillaInicial.getFila();
		int c = casillaInicial.getColumna();
		int f2=(int)(Math.random()*filas-1);
		int c2=(int)(Math.random()*columnas-1);
		
		// Si se puede mover
		if(!superficie.comprobarCasilla(f2, c2) || superficie.esComestible(f2, c2)){
			
			casillaFinal = new Casilla(f2,c2);
			
			// Si come
			if(superficie.comprobarCasilla(f2, c2) && superficie.esComestible(f2, c2)){
				
				this.comidas++;
				this.texto = "->Célula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --COME--"+LINE_SEPARATOR;

				// Si muere por comer mucho
				if (this.MAX_COMER == this.comidas){
					this.texto = this.texto + "->Explota la célula compleja en ("+f2+","+c2+")"+LINE_SEPARATOR;
					superficie.eliminarCelula(casillaFinal); // Eliminamos la celula que se va a comer
					casillaFinal = null; // Ponemos como que no puede moverse para que se elimine la celula
				}else
					superficie.moverCelula(casillaInicial, casillaFinal);
		
			}else{ // Si no come
				this.texto = "->Célula compleja en ("+f+","+c+") se mueve a ("+f2+","+c2+") --NO COME--"+LINE_SEPARATOR;
				superficie.moverCelula(casillaInicial, casillaFinal);
			}

		}else{ // Si no se puede mover

			this.texto = "->La célula compleja en ("+f+","+c+") no se ha podido mover"+LINE_SEPARATOR;
			casillaFinal = casillaInicial;

		} 
		
		return casillaFinal;
		
	}

	@Override
	public boolean esComestible() {
		
		return this.esComestible;
		
	}

	/**
	 * Devuelve un string para pintar la célula compleja en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " * ";
		
	}

}
