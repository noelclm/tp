package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.Color;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


public abstract class RectBoardSwingView extends SwingView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoardComponent boardComp;

	public RectBoardSwingView(Observable<GameObserver> g, Controller c,
			Piece localPiece, Player randomPlayer, Player aiPlayer) {
		super(g, c, localPiece, randomPlayer, aiPlayer);
		
	}

	@Override
	protected void initBoardGui() {
		boardComp = new BoardComponent(7,7){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void mouseClicked(int row, int col, int mouseButton) {
				// call handleMouseClick to let subclasses handle the event
			}
			@Override
			protected Color getPieceColor(Piece p) {
				return null;
				// get the color from the colours table, and if not
				// available (e.g., for obstacles) set it to have a color
			};
			@Override
			protected boolean isPlayerPiece(Piece p) {
				return rootPaneCheckingEnabled;
				// return true if p is a player piece, false if not (e.g, an obstacle)
			};
			
		};
		setBoardArea(boardComp); // Pone el tablero en la vista
	}

	@Override
	protected void activateBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deActivateBoard() {
		// TODO Auto-generated method stub

	}
	
	protected abstract void handleMouseClick(int row, int col, int clickCount, int mouseButton);

}
