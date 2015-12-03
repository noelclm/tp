package controlador;

import logica.Casilla;
import logica.Mundo;

public class CrearCelulaSimple extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private Casilla casilla;

	public CrearCelulaSimple() {
		this.casilla = null;
	}
	
	public CrearCelulaSimple(Casilla casilla) {
		this.casilla = casilla;
	}

	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.crearCelulaSimple(this.casilla);
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		if (cadenaComando[0].equals("crearcelulasimple") || (cadenaComando[0].equals("crear") && cadenaComando[1].equals("celula") && cadenaComando[2].equals("simple") )){
			
			int num1 = 1;
			int num2 = 2;
			if(cadenaComando[0].equals("crear")){
				num1 = 3;
				num2 = 4;
			}
			
			int f = Integer.parseInt(cadenaComando[num1]); 
			int c = Integer.parseInt(cadenaComando[num2]);
			
			Casilla casilla = new Casilla(f,c);
			
			Comando comando = new CrearCelulaSimple(casilla);
				
			return comando;
			
		}
		return null;
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}

}
