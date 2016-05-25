package es.ucm.fdi.tp.basecode.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ucm.fdi.tp.basecode.bgame.control.AIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.ConsolePlayer;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.DummyAIPlayer;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.AIAlgorithm;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.views.GenericConsoleView;

/**
 * A factory for creating ataxx games. See {@link AtaxxRules} for the
 * description of the game.
 * 
 * 
 * <p>
 * Factoria para la creacion de juegos Ataxx. Vease {@link AtaxxRules}
 * para la descripcion del juego.
 */
public class AtaxxFactory implements GameFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dim;
	private int obstacles;
		
	/**
	 * Constructor por defecto.
	 */
	public AtaxxFactory() {
		this.dim=7;
		this.obstacles = 0;
	}
	
	/**
	 * Constructor parametrizado.
	 * @param dim Dimensión del tablero.
	 */
	public AtaxxFactory(int dim) {
		
		if (dim < 5) {
			throw new GameError("Dimension must be at least 5: " + dim);
		} else if (dim%2==0) {
			throw new GameError("The dimension has to be odd");
		}else {
			this.dim = dim;
			this.obstacles = 0;
		}
	}
	
	/**
	 * Constructor parametrizado.
	 * @param dim Dimensión del tablero.
	 * @param o Obstaculos del tablero.
	 */
	public AtaxxFactory(int dim, int o ) {
		
		if(o>dim*dim-8){
			throw new GameError("Too many obstacles");
		}
		if (dim < 5) {
			throw new GameError("Dimension must be at least 5: " + dim);
		} else if (dim%2==0) {
			throw new GameError("The dimension has to be odd");	
		}
		else {
			this.obstacles = o;
			this.dim = dim;
		}
	}
	
	@Override
	public GameRules gameRules() {
		return new AtaxxRules(dim,obstacles);
	}

	@Override
	public Player createConsolePlayer() {
		ArrayList<GameMove> possibleMoves = new ArrayList<GameMove>();
		possibleMoves.add(new AtaxxMove());
		return new ConsolePlayer(new Scanner(System.in), possibleMoves);
	}

	@Override
	public Player createRandomPlayer() {
		return new AtaxxRandomPlayer();
	}

	@Override
	public Player createAIPlayer(AIAlgorithm alg) {
		if ( alg != null ) {
			return new AIPlayer(alg);
		} else {
			return new DummyAIPlayer(createRandomPlayer(), 1000);
		}
	}

	/**
	 * By default, we have two players, X and O.
	 * <p>
	 * Por defecto, hay dos jugadores, X y O.
	 */
	@Override
	public List<Piece> createDefaultPieces() {
		List<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Piece("X"));
		pieces.add(new Piece("O"));
		//pieces.add(new Piece("R"));
		//pieces.add(new Piece("T"));
		return pieces;
	}

	@Override
	public void createConsoleView(Observable<GameObserver> g, Controller c) {
		new GenericConsoleView(g, c);
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		throw new UnsupportedOperationException("There is no swing view");
	}

}

