package es.ucm.fdi.tp.basecode.practica5.connectn;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica5.swing.RectBoardSwingView;


/**
 * 
 * Vista para el connectN.
 *
 */
public class ConnectNSwingView extends RectBoardSwingView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectNSwingPlayer player;
	/**
	 * Constructor parametrizado.
	 * @param g observable
	 * @param c controller
	 * @param localPiece pieza de la multiventana
	 * @param randomPlayer jugador ramdon
	 * @param autoPlayer jugador IA.
	 */
	public ConnectNSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		player = new ConnectNSwingPlayer();
		
	}

	@Override
	protected void handleMouseClick(int row, int col, int mouseButton) {
		
		if(getInPlay()){
			
			if(getLocalPiece() != null){ // Si esta el modo multiventana
				
				if(getLocalPiece().getId().equalsIgnoreCase(getTurn().getId())){
					if(getPiece(row, col) != null){
						addStatusMessages("(" + row + "," + col + ") is already occupied!");
					}else{
						player.setMoveValue(row, col);
					    decideMakeManualMove(player);
					    this.redrawBoard();
					}
				}else{ // Si no es el turno
					addStatusMessages("Out of turn.");
				}
				
			}else{ // Si esta el modo una ventana
				
				if(getPiece(row, col) != null){
					addStatusMessages("(" + row + "," + col + ") is already occupied!");
				}else{
					player.setMoveValue(row, col);
				    decideMakeManualMove(player);
				    this.redrawBoard();
				}
				
			}
		}
		
	}

	/**
	 * Hace el movimiento manual del jugador.
	 * @param player2 jugador.
	 */
	private void decideMakeManualMove(ConnectNSwingPlayer player2) {
		this.move(player2);
	}

	@Override
	protected void activateBoard() {
		// - declare the board active, so handleMouseClick accepts moves
		// - add corresponding message to the status messages indicating
		//   what to do for making a move, etc.
		this.enableView();
	}

	@Override
	protected void deActivateBoard() {
		// declare the board inactive, so handleMouseClick rejects moves
		this.disableView();
	}

}
