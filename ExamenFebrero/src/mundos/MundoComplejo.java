package mundos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import logica.Casilla;
import excepciones.FalloIOException;
import excepciones.FicheroErroneoException;
import excepciones.IndicesFueraDeRango;
import excepciones.MundoException;

/**
 * Clase que hereda de Mundo que crea un mundo con celulas complejas y simples.
 */
public class MundoComplejo extends MundoSimple{

	private int celulasComplejas;
	
	
	/**
	 * Constructor parametrizado.
	 * @param filas Filas totales del tablero.
	 * @param columnas Columnas totales del tablero. 
	 * @param celulasSimples Celulas simples totales del tablero.
	 * @param celulasComplejas Celulas complejas totales del tablero.
	 * @param celulasBomba Celulas bomba totales del tablero.
	 */
	public MundoComplejo(int filas,int columnas,int celulasSimples,int celulasComplejas, int celulasBomba){
		
		super(filas,columnas,celulasSimples,celulasBomba);
		this.celulasComplejas = celulasComplejas;
		this.inicializaMundo();
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param filas Filas totales del tablero.
	 * @param columnas Columnas totales del tablero.
	 */
	public MundoComplejo(int filas,int columnas){
		
		super(filas,columnas);
		this.celulasComplejas = 0;
		this.inicializaMundo();
		
	}

	@Override
	public String inicializaMundo() {
		
		if(this.inicializaSuperficie(this.celulasSimples, this.celulasComplejas,this.celulasBomba)){
			return "Mundo complejo inicializado";
		}else{
			return "No se ha podido inicializar";
		}
		
	}
	
	@Override
	public String crearCelula(Casilla casilla){

		System.out.println("Indica el tipo de celula (simple/compleja/bomba):");
		
		//Lee una linea por teclado
		String linea = s.nextLine().toLowerCase().trim();
		//Separa la linea en un array identificando los espacios
		String[] palabra = linea.split(" ");
		
		if(palabra.length == 1 && palabra[0].equals("simple")){
			
			if(this.crearCelulaSimple(casilla)) 
				return "Creamos nueva celula en la posicion: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva celula en ("+casilla.getFila()+","+casilla.getColumna()+"), posicion ocupada";
			
		}else if(palabra.length == 1 && palabra[0].equals("compleja")){
			
			if(this.crearCelulaCompleja(casilla)) 
				return "Creamos nueva celula en la posicion: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva celula en ("+casilla.getFila()+","+casilla.getColumna()+"), posicion ocupada";
			
		}else if(palabra.length == 1 && palabra[0].equals("bomba")){
			
			if(this.crearCelulaBomba(casilla)) 
				return "Creamos nueva celula en la posicion: ("+casilla.getFila()+","+casilla.getColumna()+")";
				
			else
				return "Imposible crear una nueva celula en ("+casilla.getFila()+","+casilla.getColumna()+"), posicion ocupada";
			
		}else{
			
			return "No se reconoce el comando";
			
		}


	}
	
	/**
	 * Inicia la superficie con un numero de celulas que le entra. Devuelve un boolean dependiendo si ha podido o no.
	 * @param numCelulas Numero de celulas con el que se inicializa la superficie.
	 * @return boolean
	 */
	protected boolean iniciarSuperficie (int numCelulas){
		
		this.vaciar();
		
		int numCelulasPuestas = 0;
		
		// Comprueba que hay suficientes celdas para las celulas
		if (numCelulas <= this.filas*this.columnas){
			
			while (numCelulasPuestas<numCelulas){
				
				int fila = (int)(Math.random()*this.filas);
				int columna = (int)(Math.random()*this.columnas);
				int tipo = (int)(Math.random()*3);
				
				Casilla casilla = new Casilla(fila,columna);
				
				if (tipo==0){
					
					if(this.crearCelulaSimple(casilla))
						numCelulasPuestas++;
					
				}else if (tipo==1){
					
					if(this.crearCelulaCompleja(casilla))
						numCelulasPuestas++;
					
				}else{
					
					if(this.crearCelulaBomba(casilla))
						numCelulasPuestas++;
					
				}
				
			}
				
			return true;
			
		}else
			return false;
		
	}
	
	@Override
	public boolean cargar(BufferedReader b) throws MundoException{
		
		String cadena;
		int numLinea = 4;
		int numCelulas = 0;
		
		
		try {
			while((cadena = b.readLine())!=null) {
				String[] palabras = cadena.split(" ");
				
				if(palabras.length == 5 && palabras[2].equals("simple")){
					int f = Integer.parseInt(palabras[0]);
					int c = Integer.parseInt(palabras[1]);
					Casilla casilla = new Casilla (f,c);
					int pasosDados = Integer.parseInt(palabras[3]);
					int pasosSinMover = Integer.parseInt(palabras[4]);
					
					if(numCelulas <= this.filas*this.columnas){
						if(this.cargarCelulaSimple(casilla,pasosDados,pasosSinMover,numLinea)){
							numCelulas++;
						}else{
							throw new IndicesFueraDeRango("Posicion de la celula fuera del tablero, en la linea " + numLinea);
						}
					}else{
						throw new IndicesFueraDeRango("Hay mas celulas que casillas ");
					}
				
					
				}else if(palabras.length ==4 && palabras[2].equals("compleja")){
					int f = Integer.parseInt(palabras[0]);
					int c = Integer.parseInt(palabras[1]);
					Casilla casilla = new Casilla (f,c);
					int vecesComido = Integer.parseInt(palabras[3]);
					
					if(numCelulas <= this.filas*this.columnas){
						if(this.cargarCelulaCompleja(casilla,vecesComido,numLinea)){
							numCelulas++;
						}else{
							throw new IndicesFueraDeRango("Posicion de la celula fuera del tablero, en la linea " + numLinea);
						}
					}else{
						throw new IndicesFueraDeRango("Hay mas celulas que casillas ");
					}
				
					
					
				}else if(palabras.length ==3 && palabras[2].equals("bomba")){
					int f = Integer.parseInt(palabras[0]);
					int c = Integer.parseInt(palabras[1]);
					Casilla casilla = new Casilla (f,c);
					
					if(numCelulas <= this.filas*this.columnas){
						if(this.cargarCelulaBomba(casilla,numLinea)){
							numCelulas++;
						}else{
							throw new IndicesFueraDeRango("Posicion de la celula fuera del tablero, en la linea " + numLinea);
						}
					}else{
						throw new IndicesFueraDeRango("Hay mas celulas que casillas ");
					}
				
					
					
				}else{
					throw new FicheroErroneoException("En la linea " + numLinea);
				}
				numLinea++;
				}
			    
			
		} catch (IOException e) {
			throw new FalloIOException("En MundoComplejo");
		}
		
		return true;
	}
	
	@Override
	public boolean guardar (PrintWriter pw){
			
		pw.println("complejo");
		pw.println(this.filas);
		pw.println(this.columnas);
		for (int f=0; f<this.filas; f++){
			for (int c=0; c<this.columnas; c++){
				if (this.superficie[f][c]!= null){
					pw.print(f + " " + c + " ");
					this.superficie[f][c].guardar(pw);
				}
			}
		}

		return true;
			
	}
	
	
}
