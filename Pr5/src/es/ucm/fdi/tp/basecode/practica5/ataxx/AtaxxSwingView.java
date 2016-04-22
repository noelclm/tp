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
	 // do nothing if the board is not active
		if(getLocalPiece() != null){ // Si esta el modo multiventana
			if(getLocalPiece().getId().equalsIgnoreCase(getTurn().getId())){ // Si es el turno del jugador
				if(count == 0){ // Si es la primera vez que pincha
					if(getPiece(row, col) != null && getTurn().getId().equalsIgnoreCase(getPiece(row, col).getId())){
						rowI = row;
						colI = col;
						count++;
					}else{
						addStatusMessages("It's not your piece.");
					}
				}else{ // Si es la segunda vez
					count = 0;
					player.setMoveValue(rowI, colI, row, col);
				    decideMakeManualMove(player);
				    this.redrawBoard();
				}
			}else{ // Si no es el turno
				addStatusMessages("Out of turn.");
			}
		}else{
			if(count == 0){
				if(getPiece(row, col) != null && getTurn().getId().equalsIgnoreCase(getPiece(row, col).getId())){
					rowI = row;
					colI = col;
					count++;
				}else{
					addStatusMessages("It's not your piece.");
				}
			}else{
		    	count = 0;
				player.setMoveValue(rowI, colI, row, col);
			    decideMakeManualMove(player);
			    this.redrawBoard();
			}
		}
	}

	private void decideMakeManualMove(AtaxxSwingPlayer player2) {
		this.move(player2);
	}

	
}
