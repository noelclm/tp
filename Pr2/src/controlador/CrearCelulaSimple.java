package controlador;

import logica.Mundo;

public class CrearCelulaSimple extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private int f;
	private int c;

	public CrearCelulaSimple() {
		this.f = 0;
		this.c = 0;
	}
	
	public CrearCelulaSimple(int f, int c) {
		this.f = f;
		this.c = c;
	}

	@Override
	public void ejecuta(Mundo mundo) {
		
		System.out.println(mundo.crearCelulaSimple(f,c));
		System.out.println(mundo.toString());
		
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
			
			Comando comando = new CrearCelulaSimple(f,c);
				
			return comando;
			
		}
		return null;
	}

	@Override
	public String textoAyuda() {
		
		return " CREARCELULASIMPLE F C: crea una nueva celula simple en la posición (f,c) si es posible" + LINE_SEPARATOR ;
		
	}

}
