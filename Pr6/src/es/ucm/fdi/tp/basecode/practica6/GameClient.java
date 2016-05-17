package es.ucm.fdi.tp.basecode.practica6;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.control.commands.PlayCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.QuitCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.RestartCommand;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class GameClient extends Controller implements Observable<GameObserver> {
	
	private String host;
	private int port;
	private List<GameObserver> observers;
	private Piece localPiece;
	private GameFactory gameFactory;
	private Connection connectioToServer;
	private boolean gameOver;
	
	public GameClient(String host, int port)throws Exception{
		super(null,null);
		this.host=host;
		this.port=port;
		connect();
	}
	

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

	public GameFactory getGameFactoty() {
		
		return this.gameFactory;
	}

	public Piece getPlayerPiece() {
		
		return this.localPiece;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.observers.add((GameObserver) this);
		
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


	@Override
	public void addObserver(GameObserver o) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeObserver(GameObserver o) {
		// TODO Auto-generated method stub
		
	}
	private void forwardCommand(Command cmd) {
		try{
			if(!this.gameOver)
				this.connectioToServer.sendObject(cmd);
		}catch (IOException e){}
		
		
		
	}
	
}
