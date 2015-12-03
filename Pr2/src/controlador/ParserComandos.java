package controlador;

public class ParserComandos {
		
	// Para que el salto de linea salga bien en windows y linux.
	private static final String LINE_SEPARATOR = System.lineSeparator();
		
	static Comando[] array = {new Iniciar(), new Paso(), new CrearCelulaSimple(), 
							  new CrearCelulaCompleja(), new EliminarCelula(),
							  new Vaciar(), new Ayuda(), new Salir()};
	

	/**
	 * 
	 * @return
	 */
	static public String AyudaComandos(){
		
		String aux = "POSIBLES COMANDOS:" + LINE_SEPARATOR;
		
		for(Comando c:array){
			aux += c.textoAyuda();
		}
		
		return  aux;
		
	}
	
	static public Comando parseaComando(String[ ] cadenas){
		
		Comando aux = null;
		
		for(Comando c:array){
			if(aux == null)
				aux=c.parsea(cadenas);	
		}
		
		return aux;
		
	}


}
