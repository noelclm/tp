package logica;

public class MundoComplejo extends Mundo{
	
	private int celulasSimples;
	private int celulasComplejas;
	
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
		// TODO leer por teclado el tipo que es
		if(this.superficie.crearCelulaSimple(casilla)) 
			return "Creamos nueva célula en la posición: ("+casilla.getFila()+","+casilla.getColumna()+")";
			
		else
			return "Imposible crear una nueva célula en ("+casilla.getFila()+","+casilla.getColumna()+"), posición ocupada";

	}
	
}
