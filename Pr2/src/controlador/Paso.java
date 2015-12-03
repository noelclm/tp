package controlador;

import logica.Mundo;

public class Paso extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	public Paso(){}
	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.evoluciona() + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("paso")){
			Comando comando = new Paso();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " PASO: realiza un paso en la simulación" + LINE_SEPARATOR;
		
	}

}
