package controlador;

import logica.Mundo;

public class Vaciar extends Comando {
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	public Vaciar(){}
		
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.vaciar();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("vaciar")){
			Comando comando = new Vaciar();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " VACIAR: crea un mundo vac�o" + LINE_SEPARATOR;
		
	}

}
