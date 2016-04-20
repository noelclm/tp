package es.ucm.fdi.tp.basecode.practica5.ataxx;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica5.ataxx.AtaxxSwingPlayer;
import es.ucm.fdi.tp.basecode.practica5.swing.RectBoardSwingView;

public class AtaxxSwingView extends RectBoardSwingView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AtaxxSwingPlayer player;
	private int count = 0;
	private int rowI;
	private int colI;

	public AtaxxSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new AtaxxSwingPlayer();
		
	}

	@Override
	protected void handleMouseClick(int mouseButton, int row, int col) {
	 // do nothing if the board is not active	
		System.out.println(mouseButton +" " +row +" " + col);
		if(count == 0){
			rowI = row;
			colI = col;
			count++;
		}else{
			player.setMoveValue(row, col);
		    decideMakeManualMove(player);
		    this.redrawBoard();
			count = 0;
		}
		
	}

	private void decideMakeManualMove(AtaxxSwingPlayer player2) {
		// TODO Auto-generated method stub
		
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
