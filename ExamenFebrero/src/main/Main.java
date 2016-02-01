package main;

import controlador.Controlador;

/**
 * @author Noel Clemente Montero
 * He quitado la clase superficie, por lo que todos los metodos de superficie pasan a mundoSimple, donde los metodos que simplemente
 * llamaban a otros los he eliminado para reducir el codigo. Los metodos antiguos de superficie que he dejado estan porque dependiendo del tipo 
 * de mundo que use funcionan de una forma u otra.
 * Al quitar la clase mundo he metido todos sus metodos en mundoSimple para que mundoComplejo, al heredar, tambien pueda usarlos.
 * Al cambiar los mundos en el controlador y en los comandos de jugar y cargar tambien cambio para que use mundoSimple.
 * Al meter una nueva celula he tenido que modificar los mundos para que aparezca la nueva celula, y puedan usarlos.
 * Las clases que no se usan las he borrado para evitar problemas de compilacion
 */

public class Main {
	
	public static void main (String[] args) {
		
		Controlador c= new Controlador();
		c.simula();
		
	}

}
