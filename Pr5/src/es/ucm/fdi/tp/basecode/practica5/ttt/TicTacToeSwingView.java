package es.ucm.fdi.tp.basecode.practica5.ttt;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica5.swing.RectBoardSwingView;

public class TicTacToeSwingView extends RectBoardSwingView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TicTacToeSwingPlayer player;

	public TicTacToeSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new TicTacToeSwingPlayer();
		
	}

	@Override
	protected void handleMouseClick(int row, int col, int mouseButton) {
		player.setMoveValue(row, col);
	    decideMakeManualMove(player);
	    this.redrawBoard();
	}

	private void decideMakeManualMove(TicTacToeSwingPlayer player2) {
		this.move(player2);
	}

	@Override
	protected void activateBoard() {
		// - declare the board active, so handleMouseClick accepts moves
		// - add corresponding message to the status messages indicating
		//   what to do for making a move, etc.
		this.enableView();
	}

	@Override
	protected void deActivateBoard() {
		// declare the board inactive, so handleMouseClick rejects moves
		this.disableView();
	}
}
