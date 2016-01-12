package logica;

public class MundoSimple extends Mundo{
	
	private int celulasSimples;
	
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

}
