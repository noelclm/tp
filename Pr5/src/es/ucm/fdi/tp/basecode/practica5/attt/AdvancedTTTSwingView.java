package es.ucm.fdi.tp.basecode.practica5.attt;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica5.swing.RectBoardSwingView;

public class AdvancedTTTSwingView extends RectBoardSwingView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdvancedTTTSwingPlayer player;
	private int count = 0;
	private int rowI;
	private int colI;

	public AdvancedTTTSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new AdvancedTTTSwingPlayer();
		
	}

	@Override
	protected void handleMouseClick(int row, int col, int mouseButton) {
		if(getInPlay()){
			if(getLocalPiece() != null){ // Si esta el modo multiventana
				if(getLocalPiece().getId().equalsIgnoreCase(getTurn().getId())){ // Si es el turno del jugador
					if(count == 0){
						rowI = row;
						colI = col;
						count++;
					}else{
				    	count = 0;
						player.setMoveValue(rowI, colI, row, col);
					    decideMakeManualMove(player);
					    this.redrawBoard();
					}
				}else{ // Si no es el turno
					addStatusMessages("Out of turn.");
				}
			}else{ // Si esta el modo una ventana
				if(count == 0){
					rowI = row;
					colI = col;
					count++;
				}else{
			    	count = 0;
					player.setMoveValue(rowI, colI, row, col);
				    decideMakeManualMove(player);
				    this.redrawBoard();
				}
			}
		}
	}

	private void decideMakeManualMove(AdvancedTTTSwingPlayer player2) {
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
