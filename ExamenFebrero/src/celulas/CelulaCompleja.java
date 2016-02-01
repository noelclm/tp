package celulas;

import java.io.PrintWriter;

import logica.Casilla;
import logica.Celula;
import mundos.MundoSimple;

/**
 * Clase que gestiona una celula compleja que hereda de Celula.
 */
public class CelulaCompleja implements Celula {
	
	//Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private int comidas;
	private final int MAX_COMER = 2;

	/**
	 * Constructor por defecto
	 */
	public CelulaCompleja() {

		this.comidas=0;
		
	}
	
	/**
	 * Constructor parametrizado
	 * @param vecesComido Numero de veces que ha comido la celula.
	 */
	public CelulaCompleja(int vecesComido) {

		this.comidas=vecesComido;
		
	}
	@Override
	public Casilla ejecutaMovimiento(Casilla casillaInicial, StringBuilder texto, MundoSimple superficie) {

		int filaCasillaInicial = casillaInicial.getFila();
		int columnaCasillaInicial = casillaInicial.getColumna();
		int filaCasillaFinal = (int)(Math.random()*superficie.getFilas()-1);
		int columnaCasillaFinal = (int)(Math.random()*superficie.getColumnas()-1);
		Casilla casillaFinal = new Casilla(filaCasillaFinal,columnaCasillaFinal);
		
		// Si se puede mover
		if(!superficie.comprobarCasilla(casillaFinal) || superficie.esComestible(casillaFinal)){

			// Si come
			if(superficie.comprobarCasilla(casillaFinal) && superficie.esComestible(casillaFinal)){
				
				superficie.moverCelula(casillaInicial, casillaFinal);
				this.comidas++;
				texto.append("->Celula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") se mueve a ("+filaCasillaFinal+","+columnaCasillaFinal+") --COME--"+LINE_SEPARATOR);

				// Si muere por comer mucho
				if (this.MAX_COMER == this.comidas){
					texto.append("->Explota la célula compleja en ("+filaCasillaFinal+","+columnaCasillaFinal+")"+LINE_SEPARATOR);
					superficie.eliminarCelula(casillaFinal); 
				}
					
			}else{ // Si no come
				texto.append("->Celula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") se mueve a ("+filaCasillaFinal+","+columnaCasillaFinal+") --NO COME--"+LINE_SEPARATOR);
				superficie.moverCelula(casillaInicial, casillaFinal);
			}

		}else{ // Si no se puede mover

			texto.append("->La celula compleja en ("+filaCasillaInicial+","+columnaCasillaInicial+") no se ha podido mover"+LINE_SEPARATOR);
			casillaFinal = casillaInicial;

		} 
		
		return casillaFinal;
		
	}

	@Override
	public boolean esComestible() {
		
		return false;
		
	}
	
	@Override
	public void guardar (PrintWriter pw){
		pw.println("compleja " + this.comidas);
		
	}

	/**
	 * Devuelve un string para pintar la celula compleja en el tablero.
	 * @return String
	 */
	public String toString(){
		
		return " * ";
		
	}

}
