package es.ucm.fdi.tp.basecode.practica4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A random player for Ataxx.
 * 
 * <p>
 * Un jugador aleatorio para Ataxx.
 *
 */
public class AtaxxRandomPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		if (board.isFull()) {
			throw new GameError("The board is full, cannot make a random move!!");
		}
		
		List<GameMove> moves = rules.validMoves(board, pieces, p);
		
		int move = Utils.randomInt(moves.size());
		
		return moves.get(move);
	
	}

	/**
	 * Creates the actual move to be returned by the player. Separating this
	 * method from {@link #requestMove(Piece, Board, List, GameRules)} allows us
	 * to reuse it for other similar games by overriding this method.
	 * 
	 * <p>
	 * Crea el movimiento concreto que sera devuelto por el jugador. Se separa
	 * este metodo de {@link #requestMove(Piece, Board, List, GameRules)} para
	 * permitir la reutilizacion de esta clase en otros juegos similares,
	 * sobrescribiendo este metodo.
	 * @param rowI
	 *            Number of initial row .
	 *            <p>
	 *            Numero de fila inicial.
	 * @param colI
	 * 			  Number of initial column .
	 *            <p>
	 *            Numero de columna inicial.
	 * @param rowF
	 * 			  Number of final row .
	 *            <p>
	 *            Numero de fila final.
	 * @param colF
	 *  		  Number of final column .
	 *            <p>
	 *            Numero de columna final.
	 * @param p 
	 * 			  Turno actual.
	 * 			  <p>
	 *            Current turn.	
	 * @return GameMove
	 */
	protected GameMove createMove(int rowI, int colI, int rowF, int colF, Piece p) {
		return new AtaxxMove(rowI, colI, rowF, colF, p);
	}

}


