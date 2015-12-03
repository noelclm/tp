package controlador;

import logica.Mundo;

public class Ayuda extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	public Ayuda(){}
	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return ParserComandos.AyudaComandos();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("ayuda")){
			
			Comando comando = new Ayuda();
				
			return comando;
		}
		
		return null;
		
	}

	@Override
	public String textoAyuda() {
		
		return " AYUDA: muestra esta ayuda" + LINE_SEPARATOR;
		
	}

}
