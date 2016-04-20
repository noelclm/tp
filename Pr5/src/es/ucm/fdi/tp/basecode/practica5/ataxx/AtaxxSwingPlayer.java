package es.ucm.fdi.tp.basecode.practica5.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.ataxx.AtaxxMove;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxSwingPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int col;
	private int row2;
	private int col2;

	public AtaxxSwingPlayer() {
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(row, col, row2, col2, p);
	}

	public void setMoveValue(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	protected GameMove createMove(int row, int col,int row2, int col2, Piece p) {
		return new AtaxxMove(row, col, row2, col2, p);
	}
	
}
