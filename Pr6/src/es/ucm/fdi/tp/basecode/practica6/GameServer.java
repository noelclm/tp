package es.ucm.fdi.tp.basecode.practica6;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class GameServer extends Controller implements GameObserver{
	private int port;
	private int numPlayers;
	private int numOfConnectedPlayers;
	private GameFactory gameFactory;
	private List<Connection> clients;
	volatile private ServerSocket server;
	volatile private boolean stopped;
	volatile private boolean gameOver;
	private boolean consoleMode;
	
	private JPanel panel;
	private JButton quitButton;
	private JButton restartButton;
	private JTextArea infoArea;
	
	public GameServer(GameFactory gameFactory, List<Piece> pieces, int port) {
		
		super(new Game(gameFactory.gameRules()), pieces);
		
		this.port = port;
		this.numOfConnectedPlayers = 0;
		this.numPlayers = pieces.size();
		this.gameFactory = gameFactory;
		this.gameOver = false;
		
		game.addObserver(this);
		
	}
	
	
	
	
	
	

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public synchronized void makeMove(Player player) {
		try { super.makeMove(player); } catch (GameError e) { }
	}
	
	@Override
	public synchronized void stop() {
		// TODO implementar stop
		//try { super.stop(player); } catch (GameError e) { }
	}
	
	@Override
	public synchronized void restart() {
		// TODO implementar restart
		//try { super.restart(player); } catch (GameError e) { }
	}
	
	@Override
	public void start(){
		controlGUI();
		try{
			startServer();
		}catch(IOException e){
			
		}
		
	}
	
	private void startServer() throws IOException {
		
		this.server = new ServerSocket(port);
		this.stopped = false;
		
		while (!this.stopped) {
			try {
				Socket s= server.accept(); //Cuando alguien se conecta devuelve un socket para enviar y recibir datos a través de el.
				log("Type a command (status or exit): ");
				handleRequest(s); //maneja la petición.
			} catch (IOException | ClassNotFoundException e) {
				if (!this.stopped) {
					log("error while waiting for a connection: " + e.getMessage());
				}
			}
		}
		
		this.server.close(); //cierra el servidor.
		
	}
	
	public void handleRequest(Socket s) throws IOException, ClassNotFoundException{
		// TODO Mirar esto
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

	private void controlGUI() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() { constructGUI(); }
			});
		} catch (InvocationTargetException | InterruptedException e) {
			throw new GameError("Something went wrong when constructing the GUI");
		}
	}
	
	private void constructGUI() {
		JFrame window = new JFrame("Game Server");

		// create text area for printing messages
		this.infoArea= new JTextArea();
		this.infoArea.setEditable(false);
		this.infoArea.setLineWrap(true);
		this.infoArea.setWrapStyleWord(true);
		JScrollPane jp = new JScrollPane(this.infoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(jp);
		// quit button
		this.quitButton = new JButton("Stop Sever");
		this.quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {stopped = true;}
		});
		window.add(this.quitButton);
		
		window.setPreferredSize(new Dimension(800, 600) ); 
		// TODO para que se pueda cerrar mediante la X
		//window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	
	private void log(String msg) {
		// show the message in infoArea, use invokeLater!!
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
	
	public void launchServer(boolean cosoleMode) throws IOException{
		this.consoleMode = consoleMode;
		startServerInAThread(); //Comienza el servidor en una hebra.
		//control();
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
