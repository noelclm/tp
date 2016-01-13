package main;
import java.io.IOException;

import controlador.Controlador;

/**
 * PRACTICA 3 - Interfaces, Ficheros y Excepciones.
 * @author Noel Clemente Montero
 * @author Estefanía Ortega Ávila
 */

public class Main {
	
	public static void main (String[] args) throws IOException{
		
		Controlador c= new Controlador();
		c.simula();
		
	}

}
