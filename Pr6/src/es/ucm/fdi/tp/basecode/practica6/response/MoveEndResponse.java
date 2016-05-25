package es.ucm.fdi.tp.basecode.practica6.response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * 
 * Respuesta del final del movimiento.
 *
 */
public class MoveEndResponse implements Response {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private Piece turn;
	private Boolean success;
	
	/**
	 * Constructor parametrizado.
	 * @param board tablero.
	 * @param turn turno del jugador.
	 * @param success si funciona.
	 */
	public MoveEndResponse(Board board, Piece turn, Boolean success){
		this.board=board;
		this.turn=turn;
		this.success=success;
	}
	@Override
	public void run(GameObserver o) {
		o.onMoveEnd(board,turn,success); 

	}

}
