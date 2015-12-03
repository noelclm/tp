package controlador;

import logica.Mundo;

public class Salir extends Comando {

	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	public Salir(){}

	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.salir();
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("salir")){
			Comando comando = new Salir();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " SALIR: cierra la aplicación" + LINE_SEPARATOR;
		
	}

}
