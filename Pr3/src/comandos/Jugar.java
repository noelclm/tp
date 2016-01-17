package comandos;
import controlador.Comando;
import controlador.Controlador;
import excepciones.CoordenadasException;
import excepciones.ErrorDeInicializacionException;
import excepciones.MundoException;
import logica.Mundo;
import mundos.MundoComplejo;
import mundos.MundoSimple;
/**
 * Comando Jugar - Te permite jugar en un mundo simple o en un mundo complejo.
 */
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
	public Comando parsea(String[] cadenaComando) throws MundoException {
		try{
			if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("simple") && cadenaComando.length == 5){

				int filas = Integer.parseInt(cadenaComando[2]); 
				int columnas = Integer.parseInt(cadenaComando[3]);
				int celulasSimples = Integer.parseInt(cadenaComando[4]); 
				
				if(filas*columnas < celulasSimples){
					throw new ErrorDeInicializacionException();
				}
				
				Mundo mundo = new MundoSimple(filas,columnas,celulasSimples);
				Comando comando = new Jugar(mundo);
				return comando;
				
			}else if (cadenaComando[0].equals("jugar") && cadenaComando[1].equals("complejo") && cadenaComando.length == 6){
				
				int filas = Integer.parseInt(cadenaComando[2]); 
				int columnas = Integer.parseInt(cadenaComando[3]);
				int celulasSimples = Integer.parseInt(cadenaComando[4]); 
				int celulasComplejas = Integer.parseInt(cadenaComando[5]);
				
				if(filas*columnas < celulasSimples+celulasComplejas){
					throw new ErrorDeInicializacionException();
				}
				
				Mundo mundo = new MundoComplejo(filas,columnas,celulasSimples,celulasComplejas);
				Comando comando = new Jugar(mundo);
				return comando;
				
			}else{
				return null;
			}
		}catch(NumberFormatException nfe){
			throw new CoordenadasException();
		}
	}

	@Override
	public String textoAyuda() {
		
		return " JUGAR SIMPLE/COMPLEJO m n s c: Permite cambiar de un juego a otro" + LINE_SEPARATOR ;
		
	}

}
