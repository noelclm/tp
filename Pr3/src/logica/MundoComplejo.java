package logica;

import java.util.Scanner;

public class MundoComplejo extends Mundo{
	
	private int celulasSimples;
	private int celulasComplejas;
	Scanner s= new Scanner(System.in);
	
	public MundoComplejo(int filas,int columnas,int celulasSimples,int celulasComplejas){
		
		super(filas,columnas);
		this.celulasSimples = celulasSimples;
		this.celulasComplejas = celulasComplejas;
		
	}

	@Override
	public String inicializaMundo() {
		
		if(this.superficie.inicializaSuperficie(this.celulasSimples, this.celulasComplejas)){
			return "Mundo complejo inicializado";
		}else{
			return "No se ha podido inicializar";
		}
		
	}
	
	@Override
	public String crearCelula(Casilla casilla){

		System.out.println("Indica el tipo de celula (simple/compleja):");
		
		//Lee una linea por teclado
		String linea = s.nextLine().toLowerCase().trim();
		//Separa la linea en un array identificando los espacios
		String[] palabra = linea.split(" ");
		
		if(palabra.length == 1 && palabra[0].equals("simple")){
			
			if(this.superficie.crearCelulaSimple(casilla)) 
				return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";
			
		}else if(palabra.length == 1 && palabra[0].equals("compleja")){
			
			if(this.superficie.crearCelulaCompleja(casilla)) 
				return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";
			
		}else{
			
			return "No se reconoce el comando";
			
		}


	}
	
}
