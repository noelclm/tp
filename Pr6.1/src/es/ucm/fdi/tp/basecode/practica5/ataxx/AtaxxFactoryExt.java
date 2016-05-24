package es.ucm.fdi.tp.basecode.practica5.ataxx;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica4.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.basecode.practica4.ataxx.AtaxxRules;
/**
 * A factory for creating ataxx games. See {@link AtaxxRules} for the
 * description of the game.
 * 
 * 
 * <p>
 * Factoria para la creacion de juegos Ataxx. Vease {@link AtaxxRules}
 * para la descripcion del juego.
 */
public class AtaxxFactoryExt extends AtaxxFactory{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto.
	 */
	public AtaxxFactoryExt() {
		super();
	}
	/**
	 * Constructor parametrizado.
	 * @param dim Dimension del tablero.
	 */
	public AtaxxFactoryExt(int dim) {
		super(dim);
	}
	/**
	 * Constructor parametrizado.
	 * @param dim Dimension del tablero.
	 * @param obstacles Obstaculos iniciales del tablero.
	 */
	public AtaxxFactoryExt(int dim, int obstacles) {
		super(dim,obstacles);
	}

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
			Player random, Player ai) {
		try{
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					new AtaxxSwingView(g, c, viewPiece, random, ai);
				}
				
			});
		}catch (InvocationTargetException | InterruptedException e){
			throw new GameError("Ha fallado");
		}

	}

}
