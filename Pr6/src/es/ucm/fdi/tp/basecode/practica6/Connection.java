package es.ucm.fdi.tp.basecode.practica6;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public Connection(Socket s){
		this.s = s;
		this.out = new ObjectOutputStream( s.getOutputStream() );
		this.in = new ObjectInputStream( s.getInputStream() );
	}
	
	public void sendObject(Object r) throws  {
	out.writeObject(r);
	out.flush();
	out.reset();
	}
	public Object getObject() throws … {
	return in.readObject();
	}
	public void stop() throws … {
	s.close();
	}
}