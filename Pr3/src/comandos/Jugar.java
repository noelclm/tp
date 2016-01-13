package comandos;
import controlador.Comando;
import controlador.Controlador;
import logica.Mundo;
import logica.MundoSimple;
import logica.MundoComplejo;

public class Jugar extends Comando{
	
	private Mundo mundo;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public Jugar() {
		
		this.mundo = null;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param mundo Mundo que se crea.
	 */
	public Jugar(Mundo mundo) {
		
		this.mundo = mundo;	
		
	}
	
	@Override
	public String ejecuta(Controlador controlador) {
		
		return controlador.jugar(this.mundo) + LINE_SEPARATOR + controlador.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("simple") && cadenaComando.length == 5){
			int filas = Integer.parseInt(cadenaComando[2]); 
			int columnas = Integer.parseInt(cadenaComando[3]);
			int celulasSimples = Integer.parseInt(cadenaComando[4]); 
			Mundo mundo = new MundoSimple(filas,columnas,celulasSimples);
			Comando comando = new Jugar(mundo);
			return comando;
		}else if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("complejo") && cadenaComando.length == 6){
			int filas = Integer.parseInt(cadenaComando[2]); 
			int columnas = Integer.parseInt(cadenaComando[3]);
			int celulasSimples = Integer.parseInt(cadenaComando[4]); 
			int celulasComplejas = Integer.parseInt(cadenaComando[5]);
			Mundo mundo = new MundoComplejo(filas,columnas,celulasSimples,celulasComplejas);
			Comando comando = new Jugar(mundo);
			return comando;
		}else{
			return null;
		}
		
	}

	@Override
	public String textoAyuda() {
		
		return " JUGAR SIMPLE/COMPLEJO m n s c: Permite cambiar de un juego a otro" + LINE_SEPARATOR ;
		
	}

}
