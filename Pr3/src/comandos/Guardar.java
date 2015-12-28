package comandos;

import controlador.Comando;
import logica.Mundo;

public class Guardar extends Comando {
	private String nombreFichero;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Guardar() {
	this.nombreFichero = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param String Nombre del fichero.
	 */
	public Guardar(String nomFichero) {
		this.nombreFichero = nomFichero;
	}
	

	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.guardar(this.nombreFichero) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("guardar") && cadenaComando.length == 2){
			Comando comando = new Guardar(cadenaComando[1]);
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " GUARDAR NOMFICH: guarda en el fichero la partida actual" + LINE_SEPARATOR ;
		
	}

}



