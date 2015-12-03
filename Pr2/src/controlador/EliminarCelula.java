package controlador;

import logica.Casilla;
import logica.Mundo;

public class EliminarCelula extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	private Casilla casilla;

	public EliminarCelula() {
		this.casilla = null;
	}
	
	public EliminarCelula(Casilla casilla) {
		this.casilla = casilla;
	}
		
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.eliminarCelula(this.casilla);
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("eliminarcelula") || (cadenaComando[0].equals("eliminar") && cadenaComando[1].equals("celula"))){
			
			int num1 = 1;
			int num2 = 2;
			if(cadenaComando[0].equals("eliminar")){
				num1 = 2;
				num2 = 3;
			}
			
			if(cadenaComando.length <= num2)
				System.out.println("No ha introducido las coordenadas.");
			
			else{
				
				int f = Integer.parseInt(cadenaComando[num1]); 
				int c = Integer.parseInt(cadenaComando[num2]);
				
				Casilla casilla = new Casilla(f,c);

				Comando comando = new EliminarCelula(casilla);
				
				return comando;
				
			}
		}
		return null;
	}

	@Override
	public String textoAyuda() {
		
		return " ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible" + LINE_SEPARATOR;
		
	}

}
