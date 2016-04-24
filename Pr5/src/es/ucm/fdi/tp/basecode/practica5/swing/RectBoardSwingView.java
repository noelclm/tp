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
		
		boardComp = new BoardComponent(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void mouseClicked(int row, int col, int mouseButton) {
				handleMouseClick(row,col,mouseButton);
			}
			
			@Override
			protected Color getPieceColor(Piece p) {
				
				if(RectBoardSwingView.this.getPieceColor(p) == null)
					return Color.BLACK; 
				else
					return RectBoardSwingView.this.getPieceColor(p);
				
			}
			
			@Override
			protected boolean isPlayerPiece(Piece p) {
				// return true if p is a player piece, false if not (e.g, an obstacle)
				if(p.getId().equalsIgnoreCase("*"))
					return false;
				return true;
				
			};
			
		};
		setBoardArea(boardComp); // Pone el tablero en la vista
		
	}
	
	
	@Override
	protected void redrawBoard() {
		boardComp.redraw(this.getBoard());
	}
	
	protected abstract void handleMouseClick(int row, int col, int mouseButton);

}
