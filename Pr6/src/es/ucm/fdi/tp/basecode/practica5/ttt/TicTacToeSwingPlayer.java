package es.ucm.fdi.tp.basecode.practica5.ttt;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * 
 * Jugador para ttt.
 *
 */
public class TicTacToeSwingPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;

	/**
	 * Constructor por defecto.
	 */
	public TicTacToeSwingPlayer() {
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(row, col, p);
	}

	/**
	 * Guarda las coordenadas al jugador.
	 * @param row coordenada de fila final.
	 * @param col coordenada de columna final.
	 */
	public void setMoveValue(int row, int col) {
		this.row = row;
		this.col = col;
	}
	/**
	 * Crea un movimiento.
	 * @param row coordenada de fila final.
	 * @param col coordenada de columna final.
	 * @param p pieza del jugador.
	 * @return null.
	 */
	protected GameMove createMove(int row, int col, Piece p) {
		return null;//new TicTacToeMove(row, col, p);
	}
}
