package comandos;
import controlador.Comando;
import logica.Mundo;

public class Jugar extends Comando{
	private Mundo mundo;
	
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Jugar() {}
	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.jugar() + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("iniciar") && cadenaComando.length == 1){
			Comando comando = new Jugar();
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " JUGAR: Permite cambiar de un juego a otro" + LINE_SEPARATOR ;
		
	}

}

	


