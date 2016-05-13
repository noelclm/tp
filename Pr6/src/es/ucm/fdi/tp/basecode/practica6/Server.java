package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Server {
	private volatile boolean stopped;
	private volatile ServerSocket server;
	private boolean consoleMode;
	private JTextArea infoArea;
	
	public Server(){
		
	}

	private void startServer() throws IOException  {
		ServerSocket server = new ServerSocket (2020); //Creo un servidor que escucha en el puerto 2020.
		stopped=false;
		
		while(!stopped){
			try{
				Socket s= server.accept(); //Cuando alguien se conecta devuelve un socket para enviar y recibir datos a través de el.
				log("Type a command (status or exit): ");
				handleRequest(s); //maneja la petición.
			}catch (IOException | ClassNotFoundException e){}
			if (!stopped){
				Throwable e = null;
				log("error while waiting for a connection: " + e.getMessage());
			}
				
		}
		server.close(); //cierra el servidor.
		
	}
	private void log(String msg) {
		// TODO Auto-generated method stub
		if (consoleMode) {
			System.out.println("SERVER: " + msg);
			System.out.flush();
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					infoArea.append(msg);
				}
			});
		}
	}
	public void handleRequest(Socket s) throws IOException, ClassNotFoundException{
		// TODO Auto-generated method stub
		int i=0;
		//PrintStream out = new PrintStream(s.getOutputStream()); //Enviar datos al cliente.
		//Scanner in = new Scanner (s.getInputStream()); //leer los datos enviados desde el cliente
		ObjectOutputStream out = new ObjectOutputStream (s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream (s.getInputStream());
		do{
			i = ((MyNumber)in.readObject()).getValue(); // leer el entero enviado por el cliente
				
				if (i!=-1){
					out.writeObject(new MyNumber (2*i));
					out.flush(); //con flush y reset nos aseguramos que se envia inmediatamente
					out.reset();
				}
			//System.out.println("Recevied:" + i);
			//out.println(2 + "*" + i + "=" + (2*i)); //enviarle al cliente.
		} while (i!=-1);
		
		s.close();
	}
	private void handleRequestInThread(Socket s){
		new Thread(){//Crea una hebra.
			public void run(){
				try{
					handleRequest(s);
				}catch (IOException | ClassNotFoundException e){
					
				} 
			}
		}.start();
	}
	public void launchServer(boolean cosoleMode) throws IOException{
		this.consoleMode = consoleMode;
		startServerInAThread(); //Comienza el servidor en una hebra.
		control();
	}
	private void control() throws IOException{
		// TODO Auto-generated method stub
		Scanner in = new Scanner (System.in);
		
		while (!stopped){
			System.out.println("");
			log("Type a command (status or exit): ");
			String cmd = in.nextLine();
			
			switch (cmd){
			case "status":
				
				break;
			case "stop":
				stopped=true;
				server.close();
				break;
			default:
				break;
			}
			
				
		}
		
	}
	private void startServerInAThread() {
		// TODO Auto-generated method stub
		new Thread(){//Crea una hebra.
			public void run(){
				try{
					startServer();
				}catch (IOException e){
					
				}
			}
		}.start();
		
	}

}
