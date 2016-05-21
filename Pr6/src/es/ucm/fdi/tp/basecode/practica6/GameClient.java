package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.control.commands.PlayCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.QuitCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.RestartCommand;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica6.response.Response;

/**
 * 
 * Clase para el ciente.
 *
 */
public class GameClient extends Controller implements Observable<GameObserver>, GameObserver{
	
	private String host;
	private int port;
	private List<GameObserver> observers;
	private Piece localPiece;
	private GameFactory gameFactory;
	private Connection connectioToServer;
	private boolean gameOver;
	
	/**
	 * Constructor parametrizado.
	 * @param host nombre o ip del servidor.
	 * @param port numero de puerto.
	 * @throws Exception
	 */
	public GameClient(String host, int port)throws Exception{
		super(null,null);
		this.host=host;
		this.port=port;
		this.observers = new ArrayList<GameObserver>();
		connect();
	}
	

	/**
	 * Hace la conexion al servidor.
	 * @throws Exception
	 */
	private void connect() throws Exception {
		
		this.connectioToServer= new Connection(new Socket(host,port));
		this.connectioToServer.sendObject("Connect");
		Object response = this.connectioToServer.getObject();
		
		if(response instanceof Exception){
			throw (Exception) response;
		}
		try{
			this.gameFactory=(GameFactory) this.connectioToServer.getObject();
			this.localPiece=(Piece) this.connectioToServer.getObject();
			
		}catch(Exception e){throw new GameError("Unknown server response:" + e.getMessage());}
	}

	/**
	 * Devuelve gameFactory
	 * @return GameFactory
	 */
	public GameFactory getGameFactory() {
		
		return this.gameFactory;
	}

	/**
	 * Devuelve la pieza.
	 * @return Piece
	 */
	public Piece getPlayerPiece() {
		
		return this.localPiece;
	}

	@Override
	public void start() {
		
		this.observers.add(this);
		
		this.gameOver = false;
		
		while (!gameOver){
			try{
				Response res= (Response) this.connectioToServer.getObject();
				for (GameObserver o: observers){
					res.run(o);
					
				}
			}catch (ClassNotFoundException | IOException e){}
		}
	}
	@Override
	public void stop() {
		forwardCommand(new QuitCommand());
	}
	
	@Override
	public void restart() {
		forwardCommand(new RestartCommand());
	}
	
	@Override
	public void makeMove(Player p) {
		forwardCommand(new PlayCommand(p));
	}
	
	private void forwardCommand(Command cmd) {
		
		try{
			if(!this.gameOver)
				this.connectioToServer.sendObject(cmd);
		}catch (IOException e){}
		
	}

	@Override
	public void addObserver(GameObserver o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(GameObserver o) {
		this.observers.remove(o);
	}


	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		
	}


	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		this.gameOver = true;
	}


	@Override
	public void onMoveStart(Board board, Piece turn) {}


	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {}


	@Override
	public void onChangeTurn(Board board, Piece turn) {}


	@Override
	public void onError(String msg) {}
	
}
