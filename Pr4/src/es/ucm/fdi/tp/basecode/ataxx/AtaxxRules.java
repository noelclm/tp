package es.ucm.fdi.tp.basecode.ataxx;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;

/**
 * Rules for ConnectN game.
 * <ul>
 * <li>The game is played on an NxN board (with N>=3).</li>
 * <li>The number of players is between 2 and 4.</li>
 * <li>The player turn in the given order, each placing a piece on an empty
 * cell. The winner is the one who construct a line (horizontal, vertical or
 * diagonal) with N consecutive pieces of the same type.</li>
 * </ul>
 * 
 * <p>
 * Reglas del juego ConnectN.
 * <ul>
 * <li>El juego se juega en un tablero NxN (con N>=3).</li>
 * <li>El numero de jugadores esta entre 2 y 4.</li>
 * <li>Los jugadores juegan en el orden proporcionado, cada uno colocando una
 * ficha en una casilla vacia. El ganador es el que consigua construir una linea
 * (horizontal, vertical o diagonal) de N fichas consecutivas del mismo tipo.
 * </li>
 * </ul>
 *
 */
public class AtaxxRules implements GameRules {

	// This object is returned by gameOver to indicate that the game is not
	// over. Just to avoid creating it multiple times, etc.
	//
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);

	private int dim;
	private int numeroJugadores;

	public AtaxxRules(int dim,int n) {
		if (n<2 || n>4){
			throw new GameError("El n�mero de jugadores es 2,3 o 4");
		}
		if (dim < 5) {
			throw new GameError("Dimension must be at least 5: " + dim);
		} 
		else if (dim%2==0){
			throw new GameError("La dimensi�n debe ser impar");	
			
		}else {
			this.dim = dim;
			this.numeroJugadores=n;
		}
	}

	@Override
	public String gameDesc() {
		return "Ajaxx " + dim + "x" + dim;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		
		FiniteRectBoard f = new FiniteRectBoard(dim, dim);
		
		return dameTablero(f,pieces);
				
	}

	@Override
	public Piece initialPlayer(Board board, List<Piece> playersPieces) {
		return playersPieces.get(0);
	}

	@Override
	public int minPlayers() {
		return 2;
	}

	@Override
	public int maxPlayers() {
		return 4;
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> playersPieces, Piece lastPlayer) {
		int j;
		Piece p;

		// check rows & cols
		for (int i = 0; i < dim; i++) {
			// row i
			p = board.getPosition(i, 0);
			if (p != null) {
				j = 1;
				while (j < dim && board.getPosition(i, j) == p)
					j++;
				if (j == dim)
					return new Pair<State, Piece>(State.Won, p);
			}

			// col i
			p = board.getPosition(0, i);
			if (p != null) {
				j = 1;
				while (j < dim && board.getPosition(j, i) == p)
					j++;
				if (j == dim)
					return new Pair<State, Piece>(State.Won, p);
			}
		}

		// diagonal 1 - left-up to right-bottom
		p = board.getPosition(0, 0);
		if (p != null) {
			j = 1;
			while (j < dim && board.getPosition(j, j) == p) {
				j++;
			}
			if (j == dim) {
				return new Pair<State, Piece>(State.Won, p);
			}
		}

		// diagonal 2 - left-bottom to right-up
		p = board.getPosition(dim - 1, 0);
		if (p != null) {
			j = 1;
			while (j < dim && board.getPosition(dim - j - 1, j) == p) {
				j++;
			}
			if (j == dim) {
				return new Pair<State, Piece>(State.Won, p);
			}
		}

		if (board.isFull()) {
			return new Pair<State, Piece>(State.Draw, null);
		}

		return gameInPlayResult;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> playersPieces, Piece lastPlayer) {
		List<Piece> pieces = playersPieces;
		int i = pieces.indexOf(lastPlayer);
		return pieces.get((i + 1) % pieces.size());
	}

	@Override
	public double evaluate(Board board, List<Piece> playersPieces, Piece turn) {
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces, Piece turn) {
		List<GameMove> moves = new ArrayList<GameMove>();

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				if (board.getPosition(i, j) == null) {
					moves.add(new AtaxxMove(i, j, turn));
				}
			}
		}
		return moves;
	}
	/**
	 * Crea el tablero inicial
	 */
	public Board dameTablero(Board board, List<Piece> piece){

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				if (this.numeroJugadores==2 || this.numeroJugadores==3 || this.numeroJugadores==4){
					if ( i== 0 && j==0 || i==board.getRows()-1 && j==board.getCols()-1){
						// TODO crear pieza posicion i,j
						Piece x= new Piece ("X");
						board.setPosition(i, j, x);
					}

					if (i==0 && j==board.getCols()-1 || i==board.getRows()-1 && j==0){
						// TODO crear pieza posicion i,j
						Piece x= new Piece ("O");
						board.setPosition(i, j, x);
					}
					if(this.numeroJugadores==3 || this.numeroJugadores==4){
						if (i==(board.getRows()-1)/2 && (j==0 || j==board.getCols()-1)) {
							Piece x= new Piece ("R");
							board.setPosition(i, j, x);
							
						}
						
						
					}
					if(this.numeroJugadores==4){
						if (j==(board.getCols()-1)/2 && (i==0 || i==board.getRows()-1)) {
							Piece x= new Piece ("B");
							board.setPosition(i, j, x);
						
					}
				}
				
				}	
				
			}
		}
		return board;
	}
	}


