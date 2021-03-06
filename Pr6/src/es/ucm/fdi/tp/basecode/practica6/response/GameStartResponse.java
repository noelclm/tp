package es.ucm.fdi.tp.basecode.practica6.response;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.practica6.response.Response;

/**
 * 
 * Respuesta de GameStart.
 *
 */
public class GameStartResponse implements Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private String gameDesc;
	private List<Piece> pieces;
	private Piece turn;
	
	/**
	 * Constructor parametrizado.
	 * @param board tablero.
	 * @param gameDesc descripcion del juego.
	 * @param pieces piezas del tablero.
	 * @param turn turno del jugador.
	 */
	public GameStartResponse(Board board, String gameDesc, List<Piece> pieces, Piece turn) { 
		this.board = board; 
		this.gameDesc = gameDesc; 
		this.pieces = pieces; 
		this.turn = turn;
	 }
	@Override
	public void run(GameObserver o) { 
		o.onGameStart(board, gameDesc, pieces, turn); 
	}

	
	
}
