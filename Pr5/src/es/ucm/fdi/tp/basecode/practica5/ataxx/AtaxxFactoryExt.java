package es.ucm.fdi.tp.basecode.practica5.ataxx;

import es.ucm.fdi.tp.basecode.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxFactoryExt extends AtaxxFactory{
	
	public AtaxxFactoryExt() {
		super();
	}

	public AtaxxFactoryExt(int dimRows) {
		super(dimRows);
	}
	public AtaxxFactoryExt(int dimRows,int obstacles) {
		super(dimRows,obstacles);
	}


	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		throw new UnsupportedOperationException("There is no swing view");
	}

}
