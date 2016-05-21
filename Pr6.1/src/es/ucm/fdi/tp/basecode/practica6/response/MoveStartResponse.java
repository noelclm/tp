package es.ucm.fdi.tp.basecode.practica6.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * 
 * Respuesta del comienzo del movimiento.
 *
 */
public class MoveStartResponse implements Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private Piece turn;
	
	/**
	 * Constructor parametrizado.
	 * @param board tablero.
	 * @param turn turno del jugador.
	 */
	public MoveStartResponse(Board board, Piece turn){
		this.board=board;
		this.turn=turn;
	}

	@Override
	public void run(GameObserver o) {
		o.onMoveStart(board, turn); 
	}

}
