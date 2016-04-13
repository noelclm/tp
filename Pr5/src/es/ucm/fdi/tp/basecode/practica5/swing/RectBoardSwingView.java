package es.ucm.fdi.tp.basecode.practica5.swing;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class RectBoardSwingView extends SwingView {

	public RectBoardSwingView(Observable<GameObserver> g, Controller c,
			Piece localPiece, Player randomPlayer, Player aiPlayer) {
		super(g, c, localPiece, randomPlayer, aiPlayer);
		
	}

	@Override
	protected void initBoardGui() {
		
	}

	@Override
	protected void activateBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deActivateBoard() {
		// TODO Auto-generated method stub

	}

}
