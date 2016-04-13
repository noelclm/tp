package es.ucm.fdi.tp.basecode.practica5.swing;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


@SuppressWarnings("serial")
public abstract class FiniteRectBoardSwingView extends SwingView {

	private BoardComponent boardComp;
	
	public FiniteRectBoardSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		
	}

	@Override
	protected void initBoardGui() {
		
		boardComp = new BoardComponent(7,7);
		setBoardArea(boardComp); // install the board in the view
		
	}

	protected abstract void handleMouseClick(int row, int col, int clickCount, int mouseButton);

}
