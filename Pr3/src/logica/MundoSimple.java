package logica;

import java.io.BufferedReader;
import java.io.IOException;

import excepciones.FalloIOException;
import excepciones.MundoException;

public class MundoSimple extends Mundo{
	
	private int celulasSimples;
	
	public MundoSimple(){
		
		super(5,5);
		this.celulasSimples = 3;
		
	}
	
	public MundoSimple(int filas,int columnas,int celulasSimples){
		
		super(filas,columnas);
		this.celulasSimples = celulasSimples;
		
	}
		
	@Override
	public String inicializaMundo() {
		
		if(this.superficie.inicializaSuperficie(this.celulasSimples, 0)){
			return "Mundo simple inicializado";
		}else{
			return "No se ha podido inicializar";
		}
		
	}
	
	@Override
	public String crearCelula(Casilla casilla){
		
		if(this.superficie.crearCelulaSimple(casilla)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	
	@Override
	public boolean cargar(BufferedReader b) throws MundoException{
		
		String cadena;
		
		try {
			while((cadena = b.readLine())!=null) {
			    System.out.println(cadena);
			}
		} catch (IOException e) {
			throw new FalloIOException("En MundoSimple");
		}
		
		return true;
	}

}
