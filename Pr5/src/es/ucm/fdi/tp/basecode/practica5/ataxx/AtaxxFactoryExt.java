package es.ucm.fdi.tp.basecode.practica5.ataxx;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxFactoryExt extends AtaxxFactory{
	
	private static final long serialVersionUID = 1L;

	public AtaxxFactoryExt() {
		super();
	}
	
	public AtaxxFactoryExt(int dim) {
		super(dim);
	}
	
	public AtaxxFactoryExt(int dim, int obstacles) {
		super(dim,obstacles);
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GameObserver o = new AtaxxSwingView(g, c, viewPiece, random, ai);
				g.addObserver(o);
			}
			
		});

	}

}
