package es.ucm.fdi.tp.basecode.practica5.connectn;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNMove;


@SuppressWarnings("serial")
/**
 * 
 * Un jugador para connectN.
 *
 */
public class ConnectNSwingPlayer extends Player {

	private int row;
	private int col;

	/**
	 * Constructor por defecto.
	 */
	public ConnectNSwingPlayer() {
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
	 * @return GameMove.
	 */
	protected GameMove createMove(int row, int col, Piece p) {
		return new ConnectNMove(row, col, p);
	}

}
