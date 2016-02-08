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

package mundos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import logica.Casilla;
import logica.Mundo;
import excepciones.FalloIOException;
import excepciones.FicheroErroneoException;
import excepciones.FormatoNoValidoException;
import excepciones.IndicesFueraDeRango;
import excepciones.MundoException;

/**
 * Clase que hereda de Mundo que crea un mundo con celulas simples.
 */
public class MundoSimple extends Mundo{
	
	private int celulasSimples;
	
	/**
	 * Constructor por defecto.
	 */
	public MundoSimple(){
		
		super(5,5);
		this.celulasSimples = 3;
		this.inicializaMundo();
		
	}
	
	/**
	 * Constructor parametrizado
	 * @param filas Filas totales del tablero.
	 * @param columnas Columnas totales del tablero.
	 * @param celulasSimples Celulas simples totales del tablero.
	 */
	public MundoSimple(int filas,int columnas,int celulasSimples){
		
		super(filas,columnas);
		this.celulasSimples = celulasSimples;
		this.inicializaMundo();
		
	}
	
	/**
	 * Constructor parametrizado
	 * @param filas Filas totales del tablero.
	 * @param columnas Columnas totales del tablero.
	 */
	public MundoSimple(int filas,int columnas){
		
		super(filas,columnas);
		this.celulasSimples = 0;
		this.inicializaMundo();
		
	}

	@Override
	public String inicializaMundo() {
		
		if(this.superficie.inicializaSuperficie(this.celulasSimples, 0)){
			return "Mundo simple inicializado";
		}else{
			return "No se ha podido inicializar";
		}
		
	}
	
	@Override
	public String crearCelula(Casilla casilla){
		
		if(this.superficie.crearCelulaSimple(casilla)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	
	@Override
	public boolean cargar(BufferedReader b) throws MundoException{
		
		String cadena;
		int numLinea = 4;
		int numCelulas = 0;
		
		try {
			while((cadena = b.readLine())!=null) {
				String[] palabras = cadena.split(" ");
				
				if(palabras.length == 5 && palabras[2].equals("simple")){
					
					int f = Integer.parseInt(palabras[0]);
					int c = Integer.parseInt(palabras[1]);
					Casilla casilla = new Casilla (f,c);
					int pasosDados = Integer.parseInt(palabras[3]);
					int pasosSinMover = Integer.parseInt(palabras[4]);
					
					if(numCelulas <= this.filas*this.columnas){
						if(this.superficie.cargarCelulaSimple(casilla,pasosDados,pasosSinMover,numLinea)){
							numCelulas++;
						}else{
							throw new IndicesFueraDeRango("Posición de la celula fuera del tablero, en la linea " + numLinea);
						}
					}else{
						throw new IndicesFueraDeRango("Hay más células que casillas ");
					}
					
				}else{
					throw new FicheroErroneoException("En la linea " + numLinea);
				}
				numLinea++;
			}
		}catch (IOException e) {
			throw new FalloIOException("En MundoSimple");
		}catch (NumberFormatException nfe){
			throw new FormatoNoValidoException("En la linea " + numLinea);
		}
		
		return true;
	}
	
	@Override
	public boolean guardar (PrintWriter pw){
		
		pw.println("simple");
		pw.println(this.filas);
		pw.println(this.columnas);
		this.superficie.guardar(pw);

		return true;
		
	}

}
