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

// TODO Nuevo comando guardar nomFich: permite almacenar en un fichero de texto, de nombre nomFich, la configuración del juego actual.
// TODO Nuevo comando cargar nomFich: carga como juego actual, el almacenado en el fichero de texto nomFich
/*
 * complejo
 * 57
 * 0 0 compleja 0
 * 0 1 compleja 1
 * 0 2 compleja 1
 * 1 0 simple 1 1
 * 1 1 simple 1 0
 * 2 2 compleja 0
 * 3 0 simple 1 0
 */
// TODO Desaparecen los comandos crearcelulasimple y crearcelulacompleja, y aparece un único comando crearcelula f c, depende el tipo de juego luego pide que se indique el tipo de celula
// TODO Nuevo comando jugar simple N M S: inicia la simulación del juego simple, con una superficie de N filas y M columnas y con S células simples colocadas de forma aleatoria.
// TODO Nuevo comando jugar complejo N M S C: es similar al comando anterior, donde C representa el número de células complejas que debe contener el mundo complejo.
// TODO Control de excepciones
/*
 * FileNotFoundException o IOException
 * FormatoNumericoIncorrecto: se produce cuando se espera un número pero se teclean
 * caracteres no numéricos. Por ejemplo en el comando crearCelula a 2 se lanzaría una
 * excepción de este tipo.
 * PalabraIncorrecta: cuando leemos de un fichero y encontramos palabras que no se
 * corresponden con el formato estipulado para el fichero. Por ejemplo si la primera
 * línea del fichero no es simple o complejo, entonces debe saltar una excepción, y dejar
 * el juego estable.
 * IndicesFueraDeRango: cuando intentamos acceder a posiciones de la superficie que no
 * existen.
 * ErrorDeInicializacion: si se intenta jugar a un juego donde la superficie no tiene capacidad para almacenar el número de células iniciales.
 */
// TODO Clases
/*
Controlador: incorpora un nuevo atributo boolean simulacionTerminada que controla el
fin de la simulación. El método realizaSimulacion() es similar al de la Práctica 2, pero
incorpora tratamiento de excepciones. Por otro lado, puesto que tenemos dos mundos
(el simple y el complejo) y podemos cambiar de uno a otro a través del comando jugar,
los comandos se ejecutarán sobre el controlador, y no sobre el mundo como ocurría en
la Práctica 2. Por lo tanto las llamadas al método ejecuta de los comandos se realizará
pasando como parámetro al controlador, es decir comando.ejecuta(this). Además como
ahora los comandos interactuan con el controlador, dentro de esta clase necesitarás
métodos para manipularlos. Por ejemplo, el método ejecuta(Controlador controlador)
implementado en el comando Paso realizará una llamada a controlador.daUnPaso() y,
el método daUnPaso en controlador contendrá la instrucción mundo.evoluciona(). El
mismo proceso debes realizarlo con todos los comandos.
Con respecto a los ficheros, el controlador contendrá el método void cargar(String
nombreFichero), que utilizará la primera línea del mismo para crear un mundo simple
o complejo por defecto, y llamar a continuación al método cargar del mundo.
4 Práctica 3: Interfaces, Ficheros y Excepciones

Comando: es una interfaz similar a la Práctica 2, salvo por el método ejecuta, que
recibe como parámetro un controlador. Esta interfaz es implementada por cada uno
de los comandos disponibles. Para el caso del comando Jugar, la clase contendrá un
atributo privado Mundo mundo, que se inicializará en el método parsea dependiendo
de los parámetros del comando. Por ejemplo si el usuario teclea jugar simple 3 4 2,
el método parsea devolverá new MundoSimple(3,4,2). De forma similar, el método
ejecuta de la clase Jugar contendrá una llamada a controlador.juega(this.mundo). Los
comandos Cargar y Guardar tendrán un atributo privado String nombreFichero para
almacenar el nombre del fichero.

Mundo: es una clase abstracta que contiene, igual que en la Práctica 2, un atributo del
tipo Superficie. Ahora además contendrá dos atributos para almacenar el número de
filas y columnas del juego. La constructora por defecto de Mundo inicializa el número
de filas y columnas a 0, y la superficie a null. Por el contrario, la constructora con argumentos Mundo(int filas, int columnas) inicializará los atributos asociados al número
de filas y columnas de acuerdo a los parámetros de entrada, y creará una superficie
de ese tamaño, llamando a continuación a un método abstracto inicializaMundo() que
tendrán que implementar las clases que heredan de Mundo. El método evoluciona()
de esta clase es igual al de la Práctica 2, salvo el tratamiento de excepciones. Además
la clase contendrá métodos para guardar y cargar un mundo en un fichero de texto.
De la clase Mundo heredan las clases MundoSimple y MundoComplejo, que no son abstractas y por lo tanto implementan, entre otros, el método inicializaMundo(). Observa
también que el método para guardar el mundo en un fichero, necesita escribir si se
está en presencia de un mundo simple o complejo, y por lo tanto esta información
sólo puede escribirse en las clase concretas.

Superficie: similar a la Práctica 2, salvo por el tratamiento de excepciones y los
métodos para guardar y cargar una superficie.

Celula: ahora pasa a ser una interfaz, que además de los métodos que contenía en la
Práctica 2, añade dos nuevos métodos abstractos para guardar y cargar en un fichero.
Esta interfaz es implementada por las clases CelulaSimple y CelulaCompleja.
*/