package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;

public class Connection {
	
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public Connection(Socket s) throws IOException{
		this.s = s;
		this.out = new ObjectOutputStream( s.getOutputStream() );
		this.in = new ObjectInputStream( s.getInputStream() );
	}
	
	// TODO Implementar trhows y javadoc
	public void sendObject(Object r) throws IOException {
		out.writeObject(r);
		out.flush();
		out.reset();
	}
	// TODO Implementar trhows y javadoc
	public Object getObject() throws ClassNotFoundException, IOException {
		return in.readObject();
	}
	
	// TODO Implementar trhows y javadoc
	public void stop() throws IOException {
		s.close();
	}


}