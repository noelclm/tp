package main;
import controlador.Controlador;

/**
 * PRACTICA 3 - Interfaces, Ficheros y Excepciones.
 * @author Noel Clemente Montero
 * @author Estefanía Ortega Ávila
 */

public class Main {
	
	public static void main (String[] args){
		
		Controlador c= new Controlador();
		c.simula();
		
	}

}

// TODO Preguntar al profesor
/*
 * Para que tiene un mundo el comando JugarSimple y JugarComplejo?
 * Como ponemos el cargar fichero en celula?
 * Como sabe el comando crear celula que tipo de juego se esta ejecutando?
 * Finalizar la ejecucion del juego, ahora pone que esta en el controlador, como sabe el controlador que comando se ha ejecutado
 * o como cambiamos la variable simulacionTerminada desde el mundo?
 * El comando iniciar desaparece?
 */