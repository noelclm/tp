package logica;

public class Casilla {

		private int f;
		private int c;
		
		public Casilla (int f, int c){
			this.f = f;
			this.c = c;
		}
		
		public int getFila(){
			return this.f;
		}
		
		public int getColumna(){
			return this.c;
		}
}
