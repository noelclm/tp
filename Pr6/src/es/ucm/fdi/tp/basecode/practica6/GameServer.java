package es.ucm.fdi.tp.basecode.practica6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica6.response.ChangeTurnResponse;
import es.ucm.fdi.tp.basecode.practica6.response.ErrorResponse;
import es.ucm.fdi.tp.basecode.practica6.response.GameOverResponse;
import es.ucm.fdi.tp.basecode.practica6.response.GameStartResponse;
import es.ucm.fdi.tp.basecode.practica6.response.MoveEndResponse;
import es.ucm.fdi.tp.basecode.practica6.response.MoveStartResponse;
import es.ucm.fdi.tp.basecode.practica6.response.Response;

public class GameServer extends Controller implements GameObserver{
	private int port;
	private int numPlayers;
	private int numOfConnectedPlayers;
	private GameFactory gameFactory;
	private List<Connection> clients;
	volatile private ServerSocket server;
	volatile private boolean stopped;
	volatile private boolean gameOver;
	
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JPanel infoPanel;
	private JButton quitButton;
	private JTextArea infoArea;
	
	public GameServer(GameFactory gameFactory, List<Piece> pieces, int port) {
		
		super(new Game(gameFactory.gameRules()), pieces);
		
		this.port = port;
		this.numOfConnectedPlayers = 0;
		this.numPlayers = pieces.size();
		this.gameFactory = gameFactory;
		this.gameOver = false;
		this.clients = new ArrayList<Connection>();
		
		game.addObserver(this);
		
	}
	


	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		log("empieza");
		try{
			forwardNotification(new GameStartResponse(board,gameDesc,pieces,turn));
		}catch (IOException e){log("Fallo");}
		
		
	}


	@Override
	public void onGameOver(Board board, State state, Piece winner) {
	
		try{
			forwardNotification(new GameOverResponse(board,state,winner));
		}catch (IOException e){}
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		
		try{
			forwardNotification(new MoveStartResponse(board,turn));
		}catch (IOException e){}
		
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		
		try{
			forwardNotification(new MoveEndResponse(board,turn,success));
		}catch (IOException e){}
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		
		try{
			forwardNotification(new ChangeTurnResponse(board,turn));
		}catch (IOException e){}
		
	}

	@Override
	public void onError(String msg) {
		
		try{
			forwardNotification(new ErrorResponse(msg));
		}catch (IOException e){}
		
		
	}
	
	private void forwardNotification(Response r) throws IOException {
		
		for (int i=0;i<this.clients.size();i++){
			this.clients.get(i).sendObject(r);
		}
		
	}
	
	@Override
	public synchronized void makeMove(Player player) {
		try { super.makeMove(player); } catch (GameError e) { }
	}
	
	@Override
	public synchronized void stop() {
		try { super.stop(); } catch (GameError e) { }
	}
	
	@Override
	public synchronized void restart() {
		try { super.restart(); } catch (GameError e) { }
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
				log("Waiting for a connection.");
				Socket s= server.accept(); //Cuando alguien se conecta devuelve un socket para enviar y recibir datos a través de el.
				handleRequestInAThread(s); //maneja la petición.
			} catch (IOException | ClassNotFoundException e) {
				if (!this.stopped) {
					log("error while waiting for a connection: " + e.getMessage());
				}
			}
		}
		
		
		
	}
	
	public void handleRequestInAThread(Socket s) throws IOException, ClassNotFoundException{
		
		// Crea una nueva hebra para el cliente
		new Thread() {
			@Override
			public void run() {
				try {
					handleRequest(s);
				} catch (IOException | ClassNotFoundException e) {
				}
			}
		}.start();
		
	}
	
	private void handleRequest(Socket s) throws IOException,ClassNotFoundException {
		//Mandar datos del juego al cliente
		try{
			Connection c = new Connection(s);
			Object clientRequest = c.getObject();
			if(!(clientRequest instanceof String)&& !((String)clientRequest).equalsIgnoreCase("Connect")){
				c.sendObject(new GameError("Invalid Request"));	
				c.stop();
				return;
			}
		
			// Si no hay hueco en el servidor no se conecta
			if(this.numPlayers == this.numOfConnectedPlayers){
				c.sendObject(new GameError("Server is full"));	
				c.stop();
				return;
			}
			c.sendObject("OK");
			c.sendObject(this.gameFactory);
			c.sendObject(this.pieces.get(numOfConnectedPlayers));
			this.clients.add(c);
			this.numOfConnectedPlayers++;
			
			startClientListener(c); //Crea una hebra que esta escuchando al cliente
			
			if(this.numPlayers == this.numOfConnectedPlayers){
				game.start(pieces);
			}
			
			
			log("Se a conectado el jugador "+this.pieces.get(numOfConnectedPlayers-1));
				
		}catch (IOException | ClassNotFoundException e){}
		
	}
	
	private void startClientListener(Connection c) {
		this.gameOver=false;
		Thread t= new Thread(new Runnable(){
			@Override
			public void run() {
				while(!stopped && !gameOver){
					try{
						Command cmd;
						cmd = (Command) c.getObject();//Recibir un objeto del cliente y hacer un casting a Command.
						cmd.execute(GameServer.this);
					}catch (IOException | ClassNotFoundException e){
						if (!stopped && !gameOver){ //Si hay un error y se esta ejecutando el juego se para.
							game.stop();
						} else{
							try {
								closeServer();
							} catch (IOException e1) {}
							
						}
						
					}
				}
			}
			
		});
		t.start();
		
	}
	
	private void closeServer() throws IOException{
		this.stopped = true;
		if(this.numOfConnectedPlayers > 0){
			game.stop();
			for (int i=0;i<this.clients.size();i++){
				this.clients.get(i).stop();
			}
		}
		this.server.close(); //cierra el servidor.
		
	}


	
	//+++++++++++++++++++++++++++++++++++
	// 				  GUI
	//+++++++++++++++++++++++++++++++++++
	

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
		
		this.mainPanel = new JPanel ();
		this.buttonPanel = new JPanel ();
		this.infoPanel = new JPanel (new BorderLayout());

		// create text area for printing messages
		this.infoArea= new JTextArea();
		this.infoArea.setEditable(false);
		this.infoArea.setLineWrap(true); //Salto de linea en cualquier parte de la palabra.
		this.infoArea.setWrapStyleWord(true); //Salto de lineas buscando espacios entre palabras.
		JScrollPane jp = new JScrollPane(this.infoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.infoPanel.add(jp);
		this.infoPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Info Messages"));
		this.infoPanel.setPreferredSize(new Dimension(600,500));
		// quit button
		this.quitButton = new JButton("Stop Sever");
		this.quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {close();}
		});
		this.buttonPanel.add(this.quitButton);
		
		this.mainPanel.add(this.infoPanel, BorderLayout.CENTER);
		this.mainPanel.add(this.buttonPanel, BorderLayout.LINE_END);
		
		window.add(mainPanel);
		window.setPreferredSize(new Dimension(800, 600) ); 
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	
	/**
	 * Mostrar la ventana de confirmacion de salir.
	 * @throws IOException 
	 */
	final protected void close(){
		
		int n = JOptionPane.showOptionDialog(new JFrame(), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			
			try {
				closeServer(); //cierra el servidor.
			} catch (GameError | IOException _e) {
			}

			System.exit(0);
			
		}
		
	}
	
	private void log(String msg) {
		
		// show the message in infoArea, use invokeLater!!
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				infoArea.append(msg + '\n');
			}
		});
		
	}
	
}
