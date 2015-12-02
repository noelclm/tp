package controlador;

public class ParserComandos {
		
	static Comando[] array = {new Paso(),new Iniciar(),new CrearCelulaSimple(), 
								new CrearCelulaCompleja(),new EliminarCelula(),
								new Ayuda(), new Vaciar(), new Salir()};
	

	/**
	 * 
	 * @return
	 */
	static public String AyudaComandos(){
		String aux = "";
		for(Comando c:array){
			aux += c.textoAyuda();
			
		}
		
		return  aux;
				/*"POSIBLES COMANDOS:" + LINE_SEPARATOR + 
				" INICIAR: inicia una nueva simulaci�n" + LINE_SEPARATOR +
				" PASO: realiza un paso en la simulaci�n" + LINE_SEPARATOR + 
				" CREARCELULA F C: crea una nueva celula en la posici�n (f,c) si es posible" + LINE_SEPARATOR +
				" ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible" + LINE_SEPARATOR +
				" VACIAR: crea un mundo vac�o" + LINE_SEPARATOR + 
				" AYUDA: muestra esta ayuda" + LINE_SEPARATOR + 
				" SALIR: cierra la aplicaci�n" + LINE_SEPARATOR ;*/
		
	}
	
	static public Comando parseaComando(String[ ] cadenas){
		
		Comando aux = null;
		
		for(Comando c:array){
			aux=c.parsea(cadenas);	
		}
		
		return aux;
		
	}


}
