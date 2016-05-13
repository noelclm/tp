package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.model.GameError;

public class Client {

	public Client(){
		
	}
	
	public void sendToServer() throws IOException, ClassNotFoundException{
		
		Random r= new Random();
		
		Socket s=new Socket("localhost", 2020); //Conectar a localhost en el puerto 2020.
		
		ObjectOutputStream p = new ObjectOutputStream (s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream (s.getInputStream());
		//PrintStream p= new PrintStream(s.getOutputStream());//Para enviar datos al servidor.
		//Scanner in= new Scanner(s.getInputStream()); //leer datos.
		
		for (int i=0;i<10;i++){
			p.writeObject(new MyNumber (2*i));
			p.flush(); //con flush y reset nos aseguramos que se envia inmediatamente
			p.reset();
			MyNumber n= (MyNumber)in.readObject(); //Recibir un objeto.
			//p.println(r.nextInt(1000));//envia un entero.
			//p.flush(); //envio de datos inmediatamente.
			//System.out.println(in.nextLine());
			sleep(300);//detiene la ejecución del hilo.
		}
		//p.print(-1);
		p.writeObject(new MyNumber(-1));
		s.close();
	}

	private void sleep(int n) {
		// TODO Auto-generated method stub
	        try
	        {
	            Thread.sleep(n);
	        }catch(InterruptedException e){}
	    }
	
	/*private void handleRequest (Socket s){ // TODO Creo que esto va en el servidor
		try{
			Connection c = new Connection(s);
			
			Object clientRequest = c.getObject();
			if(!(clientRequest instanceof String)&& !((String)clientRequest).equalsIgnoreCase("Connect")){
				c.sendObject(new GameError("Invalid Request"));	
				c.stop();
				return;
		}
			else (numOfConnectedPlayers < pieces){
			
				numOfConnectedPlayers ++;
				c.sendObject ("Ok");
				c.sendObject (gameFactory);
				s.sendObject (pieces);
				clients.add(c);
			}
		
		}catch (IOException | ClassNotFoundException e){}
}*/
		
	


}
