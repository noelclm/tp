package celulas;

import java.io.PrintWriter;

import logica.Casilla;
import logica.Celula;
import logica.Posicion;
import mundos.MundoSimple;

public class CelulaBomba implements Celula{

	//Para que el salto de linea salga bien en windows y linux.
		private static final String LINE_SEPARATOR = System.lineSeparator();

		/**
		 * Constructor por defecto
		 */
		public CelulaBomba() {}

		@Override
		public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, MundoSimple superficie) {

			int filaCasillaInicial = casillaInicial.getFila();
			int columnaCasillaInicial = casillaInicial.getColumna();
			int filas = superficie.getFilas();
			int columnas = superficie.getColumnas();
			
			
			Posicion posicionInicial = new Posicion(filaCasillaInicial,columnaCasillaInicial);

			
			// Miramos si tiene posiciones adyacentes y cuales son
			int numPosiciones = posicionInicial.numPosiciones(filas-1, columnas-1);
			Posicion[] posicionesAdyacentes = posicionInicial.adyacencia(filas, columnas);

			// Miramos si tiene posiciones vacias adyacentes
			int numPosicionesOcupadas = superficie.cantidadPosicionesAdyacentesOcupadas(posicionesAdyacentes,numPosiciones);
			if(numPosicionesOcupadas>0){
				
				Posicion[] posicionesOcupadas = superficie.posicionesAdyacentesOcupadas(posicionesAdyacentes,numPosiciones,numPosicionesOcupadas);
				
				for(int i=0; i<posicionesOcupadas.length; i++){
					int fila = posicionesOcupadas[i].getX();
					int columna = posicionesOcupadas[i].getY();
					Casilla casilla = new Casilla(fila,columna);
					superficie.eliminarCelula(casilla);
					texto.append("->Exploto en ("+fila+","+columna+") una celula por la bomba("+filaCasillaInicial+","+columnaCasillaInicial+")"+LINE_SEPARATOR);
				}
			
			}
			else{
				texto.append("->No exploto ninguna celula la bomba ("+filaCasillaInicial+","+columnaCasillaInicial+")"+LINE_SEPARATOR);
			}
			return casillaInicial;
			
		}

		@Override
		public boolean esComestible() {
			
			return false;
			
		}
		
		@Override
		public void guardar (PrintWriter pw){
			pw.println("bomba");
		}

		/**
		 * Devuelve un string para pintar la celula compleja en el tablero.
		 * @return String
		 */
		public String toString(){
			
			return " B ";
			
		}
}
