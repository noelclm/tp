package es.ucm.fdi.tp.basecode.practica5.attt;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;

@SuppressWarnings("serial")
public class AdvancedTTTSwingPlayer extends Player {

	private int rowI;
	private int colI;
	private int row;
	private int col;

	public AdvancedTTTSwingPlayer() {
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(rowI, colI, row, col, p);
	}

	public void setMoveValue(int rowI, int colI , int row, int col) {
		this.rowI = rowI;
		this.colI = colI;
		this.row = row;
		this.col = col;
	}
	
	protected GameMove createMove(int rowI, int colI,int row, int col, Piece p) {
		return new AdvancedTTTMove(rowI, colI, row, col, p);
	}
	
}
