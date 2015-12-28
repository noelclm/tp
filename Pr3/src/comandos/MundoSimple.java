package comandos;
import controlador.Comando;
import logica.Casilla;
import logica.Mundo;

public class MundoSimple extends Comando{
	
	private Mundo mundo;
	private int filas;
	private int columnas;
	private int celulasSimples;
	private int celulasComplejas;
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	/**
	 * Constructor por defecto.
	 */
	public MundoSimple() {
		
		this.filas = 0;
		this.columnas = 0;
		this.celulasComplejas = 0;
		this.celulasSimples = 0;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param filas Número de filas del tablero. 
	 * @param columnas Número de columnas del tablero. 
	 * @param celulasComplejas Número de celulas complejas para poner en el tablero. 
	 * @param celulasSimples Número de celulas simples para poner en el tablero. 
	 */
	public MundoSimple(int filas, int columnas, int celulasComplejas, int celulasSimples) {
		
		this.filas = filas;
		this.columnas = columnas;
		this.celulasComplejas = celulasComplejas;
		this.celulasSimples = celulasSimples;
		
	}
	
	@Override
	public String ejecuta(Mundo mundo) {
		
		return mundo.jugar(this.filas, this.columnas, this.celulasComplejas, this.celulasSimples) + LINE_SEPARATOR + mundo.toString();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		
		if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("simple") && cadenaComando.length == 5){
			int filas = Integer.parseInt(cadenaComando[2]); 
			int columnas = Integer.parseInt(cadenaComando[3]);
			int celulasSimples = Integer.parseInt(cadenaComando[4]); 
			Comando comando = new MundoSimple(filas,columnas,celulasSimples,0);
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

	


