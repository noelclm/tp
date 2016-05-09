package es.ucm.fdi.tp.basecode.practica5.connectn;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
//import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.connectn.ConnectNFactory;
import es.ucm.fdi.tp.basecode.connectn.ConnectNRules;
/**
 * A factory for creating connect-N games. See {@link ConnectNRules} for the
 * description of the game.
 * 
 * 
 * <p>
 * Factoria para la creacion de juegos Connect-n. Vease {@link ConnectNRules}
 * para la descripcion del juego.
 */
public class ConnectNFactoryExt extends ConnectNFactory{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor por defecto.
	 */
	public ConnectNFactoryExt() {
		super();
	}
	/**
	 * Constructor parametrizado.
	 * @param dim Dimension del tablero.
	 */
	public ConnectNFactoryExt(int dim) {
		super(dim);
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new ConnectNSwingView(g, c, viewPiece, random, ai);
			}
			
		});

	}
	
}
