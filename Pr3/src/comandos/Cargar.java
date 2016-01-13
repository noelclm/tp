package comandos;

import java.io.FileNotFoundException;
import java.io.IOException;

import controlador.Comando;
import controlador.Controlador;

public class Cargar extends Comando{
	private String nombreFichero;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Cargar() {
		this.nombreFichero = null;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param String Nombre del fichero.
	 */
	public Cargar(String nomFichero) {
		this.nombreFichero = nomFichero;
	}
	
	@Override
	public String ejecuta(Controlador controlador) throws FileNotFoundException, IOException {
		
		return controlador.cargar(this.nombreFichero) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("cargar") && cadenaComando.length == 2){
			Comando comando = new Cargar(cadenaComando[1]);
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " CARGAR NOMFICH: Carga un fichero con la partida actual " + LINE_SEPARATOR ;
		
	}

}
