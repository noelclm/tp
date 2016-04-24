package es.ucm.fdi.tp.basecode.practica5.attt;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.attt.AdvancedTTTRules;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
/**
 * A factory for Advanced Tic-Tac-Toe. See {@link AdvancedTTTRules} for the game
 * rules.
 * 
 * <p>
 * Factoria para el juego Tic-Tac-Toe avanzado. Vease {@link AdvancedTTTRules}
 * para ver las reglas del juego.
 */
public class AdvancedTTTFactoryExt  extends AdvancedTTTFactory{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor por defecto.
	 */
	public AdvancedTTTFactoryExt() {
		super();
	}
	
	
	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new AdvancedTTTSwingView(g, c, viewPiece, random, ai);
			}
			
		});

	}
}
