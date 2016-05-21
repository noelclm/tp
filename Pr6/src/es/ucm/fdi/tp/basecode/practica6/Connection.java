package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * 
 * Clase que realiza la conexion entre cliente y servidor.
 *
 */
public class Connection {
	
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	/**
	 * Costructor parametrizado
	 * @param s socket.
	 * @throws IOException
	 */
	public Connection(Socket s) throws IOException{
		this.s = s;
		this.out = new ObjectOutputStream( s.getOutputStream() );
		this.in = new ObjectInputStream( s.getInputStream() );
	}
	
	/**
	 * Envia un objeto.
	 * @param r objeto.
	 * @throws IOException
	 */
	public void sendObject(Object r) throws IOException {
		out.writeObject(r);
		out.flush();
		out.reset();
	}
	/**
	 * Devuelve un objeto.
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object getObject() throws ClassNotFoundException, IOException {
		return in.readObject();
	}
	
	/**
	 * Cierra la conexion.
	 */
	public void stop() throws IOException {
		s.close();
	}


}