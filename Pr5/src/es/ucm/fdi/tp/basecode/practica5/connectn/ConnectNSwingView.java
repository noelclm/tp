package es.ucm.fdi.tp.basecode.practica5.connectn;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica5.swing.FiniteRectBoardSwingView;


@SuppressWarnings("serial")
public class ConnectNSwingView extends FiniteRectBoardSwingView {

	private ConnectNSwingPlayer player;

	public ConnectNSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new ConnectNSwingPlayer();
		
	}

	@Override
	protected void handleMouseClick(int row, int col, int clickCount, int mouseButton) {
	 // do nothing if the board is not active	
		player.setMoveValue(row, col);
	 // decideMakeManualMove(player);
	}

	@Override
	protected void activateBoard() {
		// - declare the board active, so handleMouseClick accepts moves
		// - add corresponding message to the status messages indicating
		//   what to do for making a move, etc.
	}

	@Override
	protected void deActivateBoard() {
		// declare the board inactive, so handleMouseClick rejects moves
	}

}
