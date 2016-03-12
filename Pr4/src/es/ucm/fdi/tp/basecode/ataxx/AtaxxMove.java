package es.ucm.fdi.tp.basecode.ataxx;


import java.util.List;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A Class representing a move for Ataxx.
 * 
 * <p>
 * Clase para representar un movimiento del juego ataxx.
 * 
 */
public class AtaxxMove extends GameMove {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int rowI;

	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int colI;
	
	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int rowF;

	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int colF;

	/**
	 * This constructor should be used ONLY to get an instance of
	 * {@link AtaxxMove} to generate game moves from strings by calling
	 * {@link #fromString(String)}
	 * 
	 * <p>
	 * Solo se debe usar este constructor para obtener objetos de
	 * {@link AtaxxMove} para generar movimientos a partir de strings usando
	 * el metodo {@link #fromString(String)}
	 * 
	 */

	public AtaxxMove() {
	}
	
	/**
	 * Constructs a move for placing a piece of the type referenced by {@code p}
	 * at position ({@code rowI},{@code colI},{@code rowF},{@code colF}).
	 * 
	 * <p>
	 * Construye un movimiento para colocar una ficha del tipo referenciado por
	 * {@code p} en la posicion ({@code rowI},{@code colI},{@code rowF},{@code colF}).
	 * 
	 * @param p
	 * 			  A piece to be place at ({@code rowF},{@code colF}).
	 *            <p>
	 *            Ficha a colocar en ({@code rowF},{@code colF}).
	 */
	
	public AtaxxMove(Piece p) {
		super(p);
		this.rowI = -1;
		this.colI = -1;
		this.rowF = -1;
		this.colF = -1;
	}

	
	/**
	 * Constructs a move for placing a piece of the type referenced by {@code p}
	 * at position ({@code rowI},{@code colI},{@code rowF},{@code colF}).
	 * 
	 * <p>
	 * Construye un movimiento para colocar una ficha del tipo referenciado por
	 * {@code p} en la posicion ({@code rowI},{@code colI},{@code rowF},{@code colF}).
	 * 
	 * @param rowI
	 *            Number of initial row .
	 *            <p>
	 *            Numero de fila inicial.
	 * @param colI
	 * 			  Number of initial column .
	 *            <p>
	 *            Numero de columna inicial.
	 * @param rowF
	 * 			  Number of final row .
	 *            <p>
	 *            Numero de fila final.
	 * @param colF
	 *  		  Number of final column .
	 *            <p>
	 *            Numero de columna final.
	 * @param p
	 * 			  A piece to be place at ({@code rowF},{@code colF}).
	 *            <p>
	 *            Ficha a colocar en ({@code rowF},{@code colF}).
	 */
	
	public AtaxxMove(int rowI, int colI, int rowF, int colF, Piece p) {
		super(p);
		this.rowI = rowI;
		this.colI = colI;
		this.rowF = rowF;
		this.colF = colF;
	}
	
	/**
	 * Transforma las piezas de alrededor al tipo de pieza que es movido
	 * @param rowF
	 *           Number of final row .
	 *            <p>
	 *            Numero de fila final.
	 * @param colF
	 * 	          Number of final column .
	 *            <p>
	 *            Numero de columna final.
	 * @param board tablero.
	 * @param pieces Piezas del tablero.
	 */
	private void adyacencia(int rowF, int colF,Board board,List<Piece> pieces){
		
		Piece obs = new Piece("*");
		
		for (int x=this.rowF-1; x<=this.rowF+1; x++){
			for (int y=this.colF-1; y<=this.colF+1; y++){
				if(x>=0 && y>=0 && x<board.getRows() && y<board.getCols() && (x!=this.rowF || y!=this.colF)){
					if ((board.getPosition(x, y) != null)&&(!board.getPosition(x, y).equals(getPiece()))&&(!board.getPosition(x, y).equals(obs))){	
						board.setPieceCount(board.getPosition(x, y), board.getPieceCount(board.getPosition(x, y))-1);
						board.setPosition(x, y, getPiece());
						board.setPieceCount(getPiece(), board.getPieceCount(getPiece())+1);
					}
						
				}
			}
		}
			
		
	}
	

	@Override
	public void execute(Board board, List<Piece> pieces) {
		if(rowI >= 0 && colI >= 0 && rowF >= 0 && colF >= 0){
			if (board.getPosition(rowI, colI) != null) {
				if(board.getPosition(rowI, colI).equals(getPiece())){
					if (board.getPosition(rowF, colF) == null) {
						int d = distance();
					
						if(d==1){
							board.setPosition(rowF, colF, getPiece());
							adyacencia(rowF, colF, board, pieces);
							board.setPieceCount(getPiece(), board.getPieceCount(getPiece())+1);
						}else if(d==2){
							
							board.setPosition(rowI, colI, null);
							board.setPosition(rowF, colF, getPiece());
							adyacencia(rowF, colF, board, pieces);
	
						}else {
							throw new GameError("position (" + rowF + "," + colF + ") is far!");
						}
						
					} else {
						throw new GameError("position (" + rowF + "," + colF + ") is already occupied!");
					}
				}else {
					throw new GameError("position (" + rowI + "," + colI + ") is another player!");
				}	
			}else {
				throw new GameError("position (" + rowI + "," + colI + ") is empty!");
			}
		}
	}
	
	/**
	 * Clacula la distancia que hay entre la casilla de origen y la casilla de destino del tablero.
	 * @return distancia.
	 */
	private int distance(){
		int d1 = Math.abs(this.rowI-this.rowF);
		int d2 = Math.abs(this.colI-this.colF);
		return Math.max(d1, d2);
	}
	
	
	/**
	 * This move can be constructed from a string of the form "row SPACE col"
	 * where row and col are integers representing a position.
	 * 
	 * <p>
	 * Se puede construir un movimiento desde un string de la forma
	 * "row SPACE col" donde row y col son enteros que representan una casilla.
	 */
	
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int rowI, colI, rowF, colF;
			rowI = Integer.parseInt(words[0]);
			colI = Integer.parseInt(words[1]);
			rowF = Integer.parseInt(words[2]);
			colF = Integer.parseInt(words[3]);
			return createMove(rowI, colI, rowF, colF, p);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	/**
	 * Creates a move that is called from {@link #fromString(Piece, String)}.
	 * Separating it from that method allows us to use this class for other
	 * similar games by overriding this method.
	 * 
	 * <p>
	 * Crea un nuevo movimiento con la misma ficha utilizada en el movimiento
	 * actual. Llamado desde {@link #fromString(Piece, String)}; se separa este
	 * metodo del anterior para permitir utilizar esta clase para otros juegos
	 * similares sobrescribiendo este metodo.
	 * 
	 * @param row
	 *            Row of the move being created.
	 *            <p>
	 *            Fila del nuevo movimiento.
	 * 
	 * @param col
	 *            Column of the move being created.
	 *            <p>
	 *            Columna del nuevo movimiento.
	 */
	/**
	 * Creates a move that is called from {@link #fromString(Piece, String)}.
	 * Separating it from that method allows us to use this class for other
	 * similar games by overriding this method.
	 * 
	 * <p>
	 * Crea un nuevo movimiento con la misma ficha utilizada en el movimiento
	 * actual. Llamado desde {@link #fromString(Piece, String)}; se separa este
	 * metodo del anterior para permitir utilizar esta clase para otros juegos
	 * similares sobrescribiendo este metodo.
	 * @param rowI Numero de fila inicial.
	 * @param colI Numero de columna inicial.
	 * @param rowF Numero de fila final.
	 * @param colF Numero de columna final.
	 * @param p Turno actual.
	 * @return Devuelve el movimiento que has metido por teclado.
	 */
	protected GameMove createMove(int rowI, int colI, int rowF, int colF, Piece p) {
		return new AtaxxMove(rowI, colI, rowF, colF, p);
	}

	@Override
	public String help() {
		return "Row and column for origin and for destination, separated by spaces (four numbers).";
	}

	@Override
	public String toString() {
		if (getPiece() == null) {
			return help();
		} else {
			return "Place a piece '" + getPiece() + "' at (" + rowI + "," + colI + ")";
		}
	}
}


