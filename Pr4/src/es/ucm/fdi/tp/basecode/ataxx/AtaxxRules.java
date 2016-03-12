package es.ucm.fdi.tp.basecode.ataxx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
 * Rules for Ataxx game.
 * <ul>
 * <li>The game is played on an NxN board (with N>=5).</li>
 * <li>The number of players is between 2 and 4.</li>
 * <li>The player turn in the given order, each placing a piece on an empty
 * cell. The winner is the one who construct a line (horizontal, vertical or
 * diagonal) with N consecutive pieces of the same type.</li>
 * </ul>
 * 
 * <p>
 * Reglas del juego Ataxx.
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
	private int obstacle;
	/**
	 * Constructor parametrizado.
	 * @param dim Dimensión del tablero.
	 */
	public AtaxxRules(int dim) {
		
		if (dim < 5) {
			throw new GameError("Dimensión must be at least 5: " + dim);
		} 
		else if (dim%2==0){
			throw new GameError("The dimension must be odd");	
			
		}else {
			this.dim = dim;
			this.obstacle = 0;
		}
	}
/**
 * Constructor parametrizado.
 * @param dim Dimensión del tablero.
 * @param o Obstaculos del tablero.
 */
	public AtaxxRules(int dim,int o) {
		
		if(o>dim*dim){
			throw new GameError("Too many obstacles");
		}
		if (dim < 5) {
			throw new GameError("Dimensión must be at least 5: " + dim);
		} 
		else if (dim%2==0){
			throw new GameError("The dimension must be odd");	
			
		}else {
			this.obstacle = o;
			this.dim = dim;
		}
	}

	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
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
		
		
		List<GameMove> moves = validMoves(board, playersPieces, lastPlayer);
		
		
		
		//if (board.isFull()){
			
			ArrayList<Integer> totalPiezas = new ArrayList<Integer>();
			
			for (int i=0; i < playersPieces.size(); i++){

				totalPiezas.add(board.getPieceCount(playersPieces.get(i)));
			}
			
			int pos = 0;
			double valorMax=0;

			//int max =Integer.MIN_VALUE;
			//System.out.println(max);
			for (int j=0; j < totalPiezas.size(); j++){
				//if (totalPiezas.get(j)>max){
					//pos = j;
					//max=totalPiezas.get(j);
				if(totalPiezas.get(j)>valorMax){

					pos = j;
					valorMax = totalPiezas.get(j);
				}
			    //System.out.println(Collections.max(totalPiezas));
				//Integer m = Collections.max(totalPiezas);
				System.out.println( totalPiezas.size());
				System.out.println(valorMax);
				
				
			}
	
			
			Piece p = playersPieces.get(pos);
			//return new Pair<State, Piece>(State.Won, p);
		//}
		
		
		
		
		return gameInPlayResult;
		
		
		/*
		//int totalPiezas[] = new int[playersPieces.size()];
		ArrayList<Integer> totalPiezas = new ArrayList<Integer>();
		
		List<GameMove> moves = validMoves(board, playersPieces, lastPlayer);
		
		if (board.isFull()){
			for (int i=0; i < playersPieces.size(); i++){
				totalPiezas.add(board.getPieceCount(playersPieces.get(i)));
			}
			

			for (int j=0; j < totalPiezas.length; j++){
				totalPiezas[j]=board.getPieceCount(playersPieces.get(i));
			}
			
		}
		
		
		
		if (moves.isEmpty())
		if (board.isFull()||moves.isEmpty()){
			for (int i=0; i < playersPieces.size(); i++){
				totalPiezas[j]=board.getPieceCount(playersPieces.get(i));
				return new Pair<State, Piece>(State.Won, playersPieces.get(i));
			}
			
		}

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
*/

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

		// Recorremos las filas del tablero
		for (int rowI = 0; rowI < board.getRows(); rowI++) {
			//Recorremos las columnas del tablero
			for (int colI = 0; colI < board.getCols(); colI++) {
				// Si hay una pieza del tipo de jugador del turno	
				if(board.getPosition(rowI, colI) != null && board.getPosition(rowI, colI).equals(turn)) {
					
					// Recorremos las casillas adyacentes en busca de casillas vacias
					for (int rowF=rowI-2; rowF<=rowI+2; rowF++){
						for (int colF=colI-2; colF<=colI+2; colF++){
							
							// Comprobamos que este dentro del tablero y que no sea la propia casilla inicial
							if(rowF>=0 && colF>=0 && rowF<board.getRows() && colF<board.getCols() && (rowF!=rowI || colF!=colI)){
								
								// Si hay una casilla vacia guarda el movimiento
								if ((board.getPosition(rowF, colF) == null)){	
									moves.add(new AtaxxMove(rowI, colI, rowF, colF, turn));
								}	
								
							}
							
						}
					}
					
				}
			}
		}
		
		return moves;
		
	}
	/**
	 * Crea el tablero inicial
	 */
	public Board dameTablero(Board board, List<Piece> piece){

		for (int i = 0; i < piece.size(); i++) {
			
			if(i==0){
				board.setPosition(0, 0, piece.get(i));
				board.setPosition(board.getRows()-1, board.getCols()-1, piece.get(i));
				board.setPieceCount(piece.get(i), 2);
			}
			if(i==1){
				board.setPosition(0, board.getCols()-1, piece.get(i));
				board.setPosition(board.getRows()-1, 0, piece.get(i));
				board.setPieceCount(piece.get(i), 2);
			}
			if(i==2){
				board.setPosition((board.getRows()-1)/2, 0, piece.get(i));
				board.setPosition((board.getRows()-1)/2, board.getCols()-1, piece.get(i));
				board.setPieceCount(piece.get(i), 2);
			}
			if(i==3){
				board.setPosition(0, (board.getCols()-1)/2, piece.get(i));
				board.setPosition(board.getRows()-1, (board.getCols()-1)/2, piece.get(i));
				board.setPieceCount(piece.get(i), 2);
			}
		}
		
		if(obstacle>0){
			
			Piece obs = new Piece("*");
			int j = 0;
			while (j < obstacle ) {
				int x = (int)(Math.random()*board.getRows()-1);
				int y = (int)(Math.random()*board.getCols()-1);
				if(board.getPosition(x, y) == null){
					board.setPosition(x, y, obs);
					j++;
				}
			}
		}
		
		return board;
	}
}


