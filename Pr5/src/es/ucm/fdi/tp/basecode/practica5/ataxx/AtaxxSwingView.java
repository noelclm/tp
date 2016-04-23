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
	protected void handleMouseClick(int row, int col, int mouseButton) {
		if(getInPlay()){
			if(getLocalPiece() != null){ // Si esta el modo multiventana
				if(getLocalPiece().getId().equalsIgnoreCase(getTurn().getId())){ // Si es el turno del jugador
					if(count == 0){ // Si es la primera vez que pincha
						if(getPiece(row, col) != null && getTurn().getId().equalsIgnoreCase(getPiece(row, col).getId())){
							rowI = row;
							colI = col;
							count++;
						}else{
							addStatusMessages("(" + row + "," + col + ") it's not your piece.");
						}
					}else{ // Si es la segunda vez
						if(getPiece(row, col) != null){
							addStatusMessages("(" + row + "," + col + ") is already occupied!");
						}else{
							count = 0;
							player.setMoveValue(rowI, colI, row, col);
						    decideMakeManualMove(player);
						    this.redrawBoard();
						}
					}
				}else{ // Si no es el turno
					addStatusMessages("Out of turn.");
				}
			}else{ // Si esta el modo una ventana
				if(count == 0){ // Si es la primera vez que pincha
					if(getPiece(row, col) != null && getTurn().getId().equalsIgnoreCase(getPiece(row, col).getId())){
						rowI = row;
						colI = col;
						count++;
					}else{
						addStatusMessages("(" + row + "," + col + ") it's not your piece.");
					}
				}else{ // Si es la segunda vez
					if(getPiece(row, col) != null){
						addStatusMessages("(" + row + "," + col + ") is already occupied!");
					}else{
						count = 0;
						player.setMoveValue(rowI, colI, row, col);
					    decideMakeManualMove(player);
					    this.redrawBoard();
					}
			    	
				}
			}
		}
	}

	private void decideMakeManualMove(AtaxxSwingPlayer player2) {
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
