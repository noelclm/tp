package controlador;

import logica.Mundo;

public class Iniciar extends Comando{
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	public Iniciar(){}

	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.iniciar() + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("iniciar")){
			Comando comando = new Iniciar();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " INICIAR: inicia una nueva simulación" + LINE_SEPARATOR ;
		
	}

}
