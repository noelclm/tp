package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;

/**
 * 
 * Crea la ventana y los componentes.
 *
 */
@SuppressWarnings("serial")
public abstract class SwingView extends JFrame implements GameObserver {

	/**
	 * 
	 * Enumerado del modo de jugador.
	 *
	 */
	public enum PlayerMode {
		MANUAL("Manual"), RANDOM("Random"), AI("Automatics");

		private String desc;

		PlayerMode( String desc) {
		
			this.desc = desc;
		}

		@Override
		public String toString() {
			return desc;
		}
	}
	
	private Observable<GameObserver> game;
	private Controller ctrl;
	private Piece localPiece;
	private Player ramdomPlayer;
	private Player aiPlayer;
	private Map<Piece, Color> pieceColors;
	private Map<Piece, PlayerMode> playerTypes;
	private Board board;
	private List<Piece> pieces;
	private Piece turn;
	boolean inPlay;
	Iterator<Color> colorsIter;
	
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rigthPanel;
	
	private JPanel statusPanel;
	private JTextArea statusMessages;

	private JPanel playerPanel;
	private TableModel playerInformationTable;
	private JTable table;
	
	private JPanel pieceColorPanel;
	private JComboBox<Piece> pieceColorCombo;
	private JButton pieceColorButton;
	
	private JPanel playerModesPanel;
	private JComboBox<Piece> playerModesCombo;
	private JComboBox<PlayerMode> typeModesCombo;
	private JButton playerModesButton;
	
	private JPanel automaticMovesPanel;
	private JButton randomAutomaticMovesButton;
	private JButton inteligenteAutomaticMovesButton;
	
	private JPanel quitRestarPanel;
	private JButton quitButton;
	private JButton restartButton;
	

	/**
	 * Constructor parametrizado.
	 * @param g observable.
	 * @param c controller.
	 * @param localPiece pieza del jugador multiventana.
	 * @param randPlayer jugador random.
	 * @param aiPlayer jugador IA.
	 */
	public SwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		
		this.game = g;
		this.ctrl = c;
		this.localPiece = localPiece;
		this.ramdomPlayer = randPlayer;
		this.aiPlayer = aiPlayer;
		this.pieceColors = new HashMap<Piece, Color>();
		this.playerTypes = new HashMap<Piece, PlayerMode>();
		this.inPlay = false;
		
		this.colorsIter = Utils.colorsGenerator();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				game.addObserver(SwingView.this);
			}
			
		});

		initGUI();

	}
	
	/**
	 * Inicio de la interfaz grafica.
	 */
	private void initGUI() { 
		
		mainPanel = new JPanel( new BorderLayout());
		leftPanel = new JPanel(new BorderLayout());
		rigthPanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rigthPanel.setLayout( new BoxLayout(rigthPanel, BoxLayout.Y_AXIS));
		
		statusMessagesPanel();
		playerInformationPanel();
		pieceColorPanel();
		playerModesPanel();
		AutomaticMovesPanel();
		quitRestartPanel();
		
		addStatusMessages("Welcome");
		
		mainPanel.add(leftPanel, BorderLayout.CENTER);
		mainPanel.add(rigthPanel, BorderLayout.LINE_END);
		this.getContentPane().add(mainPanel);
		initBoardGui(); 
		this.setMinimumSize(new Dimension(800, 600));
		this.setVisible(true);
		this.pack();
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				quit();
			}
		});
	}
	/**
	 * Mostrar la ventana de confirmacion de salir.
	 */
	final protected void quit() {
		
		int n = JOptionPane.showOptionDialog(new JFrame(), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			
			try {
				ctrl.stop();
			} catch (GameError _e) {
			}
			
			setVisible(false);
			dispose();
			System.exit(0);
			
		}
		
	}
	
	// --------------------------------------------------------------------------------
	

	/** GAME OBSERVER CALLBACKS **/

	@Override
	public void onGameStart(Board roBoard, String gameDesc, List<Piece> roPieces,Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){handleGameStart(roBoard,gameDesc,roPieces,turn);}
		});
	}

	/**
	 * Se encarga de empezar el juego.
	 */
	private void handleGameStart(Board roBoard, String gameDesc, List<Piece> roPieces,Piece turn) {
		
		this.pieceColors = new HashMap<>();
		this.playerTypes = new HashMap<>();
		this.board = roBoard;
		this.pieces = roPieces;
		this.turn = turn;
		this.inPlay = true;

		pieceColorCombo.removeAllItems();
		
		for(Piece p : pieces) {
			if(pieceColors.get(p) == null) {
				setPieceColor(p, colorsIter.next());
			}
		}
		
		if (localPiece == null){
			for(Piece p : pieces) {
				this.pieceColorCombo.addItem(p);
			}
		}else{	
			this.pieceColorCombo.addItem(localPiece);
		}
		
		playerModesCombo.removeAllItems();
		if (localPiece == null){
			for (Piece p : pieces) {
				if(playerTypes.get(p) == null){
					playerTypes.put(p, PlayerMode.MANUAL);
					this.playerModesCombo.addItem(p);
				}
			}
		}else{
			if(playerTypes.get(localPiece) == null){
				playerTypes.put(localPiece, PlayerMode.MANUAL);
				this.playerModesCombo.addItem(localPiece);
			}
		}
		
		disableView();
		handleChangeTurn(board,turn);
		this.setTitle("Board Games: " + gameDesc + (localPiece == null ?
				"" : " (" + localPiece + ")"));
		
		this.redrawBoard();
		playerInformationTable.refresh();
		
	}

	@Override
	public void onGameOver(final Board board, final State state, final Piece winner) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleGameOver(board, state, winner);}
		});
	}
	
	/**
	 * Se encarga de terminar el juego.
	 * @param board tablero.
	 * @param state estado del ganador.
	 * @param winner pieza que ha ganado.
	 */
	protected void handleGameOver(Board board, State state, Piece winner) {
		this.board = board;
		addStatusMessages("Game Over!!");
		this.redrawBoard();
		addStatusMessages("Game Status: " + state);
		if (state == State.Won) {
			addStatusMessages("Winner: " + winner);
		}
		inPlay = false;
		deActivateBoard();
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
	}

	@Override
	public void onChangeTurn(Board board, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleChangeTurn(board, turn);}
		});
	}
	
	/**
	 * Se encarga de cambiar el turno.
	 * @param board2 tablero.
	 * @param turn2 turno.
	 */
	protected void handleChangeTurn(Board board2, Piece turn2) {
		this.board = board2;
		this.turn = turn2;
		
		if(this.localPiece != null){
			if(this.turn.getId().equalsIgnoreCase(this.localPiece.getId())){
				randomAutomaticMovesButton.setEnabled(true);
				inteligenteAutomaticMovesButton.setEnabled(true);
				activateBoard();
			}else{
				randomAutomaticMovesButton.setEnabled(false);
				inteligenteAutomaticMovesButton.setEnabled(false);
				deActivateBoard();
			}
		}
		
		this.redrawBoard();
		addStatusMessages("Turn for " + turn);
		if(this.playerTypes.get(this.turn) != null && this.playerTypes.get(this.turn).equals(PlayerMode.AI)){
			move(aiPlayer);
		}
		if(this.playerTypes.get(this.turn) != null && this.playerTypes.get(this.turn).equals(PlayerMode.RANDOM)){
			move(ramdomPlayer);
		}	
		
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {addStatusMessages(msg);}
		});
	}


	// --------------------------------------------------------------------------------
		
	/**
	 * Inicia el tablero.
	 */
	protected abstract void initBoardGui();
	/**
	 * Activa el tablero.
	 */
	protected abstract void activateBoard();
	/**
	 * Desactiva el tablero.
	 */
	protected abstract void deActivateBoard();
	/**
	 * Repinta el tablero.
	 */
	protected abstract void redrawBoard();
	
	/**
	 * Devuelve la pieza local.
	 * @return Piece.
	 */
	final protected Piece getLocalPiece() { 
		return localPiece; 
	}
	
	/**
	 * Devuelve el tablero.
	 * @return Board.
	 */
	final protected Board getBoard() { 
		return board; 
	}
	
	/**
	 * Devuelve el turno.
	 * @return Piece.
	 */
	final protected Piece getTurn() { 
		return turn; 
	}
	
	/**
	 * Devuelve si esta iniciado el juego o no.
	 * @return boolean.
	 */
	final protected boolean getInPlay() { 
		return inPlay; 
	}
	
	/**
	 * Devuelve la pieza en una posiciion.
	 * @param x coordenada fila.
	 * @param y coordenada columna.
	 * @return Piece.
	 */
	final protected Piece getPiece(int x, int y) { 
		return board.getPosition(x, y); 
	}
	
	/**
	 * Devuelve el color de la pieza.
	 * @param p pieza del jugador.
	 * @return Color.
	 */
	final protected Color getPieceColor(Piece p) { 
		return pieceColors.get(p);
	}
	
	/**
	 * Devuelve la lista de piezas.
	 * @return List<Piece>
	 */
	final protected List<Piece> getPieces() { 
		return pieces; 
	}
	
	/**
	 * Pone el color a la pieza.
	 * @param p pieza.
	 * @param c color.
	 * @return Color
	 */
	final protected Color setPieceColor(Piece p, Color c) { 
		return pieceColors.put(p,c); 
	}
	
	/**
	 * Añade al panel izquierdo un JComponent.
	 * @param c JComponent.
	 */
	final protected void setBoardArea(JComponent c) { 
		leftPanel.add(c,BorderLayout.CENTER);
	}
	
	/**
	 * Desactivar la vista del panel izquierdo.
	 */
	protected void disableView() {
		this.leftPanel.setEnabled(false);
	}
	/**
	 * Activar la vista del panel izquierdo.
	 */
	protected void enableView() {
		this.leftPanel.setEnabled(true);
	}
	/**
	 * Añade un mensaje al panel del estado.
	 * @param msg mensaje.
	 */
	final protected void addStatusMessages(String msg) { 
		statusMessages.append(msg + '\n');
	}
	
	/**
	 * Ejecuta el movimiento.
	 * @param player jugador.
	 */
	protected void move(Player player) {
		ctrl.makeMove(player);
		playerInformationTable.refresh();
	}
	
	// --------------------------------------------------------------------------------
	
	/**
	 * Crea el panel del estado.
	 */
	private void statusMessagesPanel() {
		statusPanel = new JPanel( new BorderLayout());
		statusPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Status Messages"));
		statusMessages= new JTextArea();
		statusMessages.setEditable(false);
		statusMessages.setLineWrap(true);
		statusMessages.setWrapStyleWord(true);
		JScrollPane jp = new JScrollPane(statusMessages, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		statusPanel.add(jp);
		statusPanel.setPreferredSize(new Dimension(100,100));
		rigthPanel.add(statusPanel);
	}
	
	/**
	 * Crea el panel de la informacion del jugador.
	 */
	final protected void playerInformationPanel() {
		playerPanel = new JPanel( new BorderLayout());
		playerPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Player Information"));
		
		playerInformationTable = new TableModel();
		table = new JTable(playerInformationTable){

			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col){
				Component comp =  super.prepareRenderer(renderer, row, col);
				Color c = pieceColors.get(pieces.get(row));
				comp.setBackground(c);
				return  comp;
			}
		};
		
		JScrollPane jp = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		playerPanel.setPreferredSize(new Dimension(100,100));
		playerPanel.add(jp);
		rigthPanel.add(playerPanel);		
		
	}
	
	/**
	 * Crea el panel de elegir color.
	 */
	private void pieceColorPanel() {
		pieceColorPanel = new JPanel ();
		pieceColorPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Piece Color"));
		pieceColorCombo = new JComboBox<Piece>();

		pieceColorButton = new JButton("Choose Color");
		pieceColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ choose(); }
		});

		pieceColorPanel.add(pieceColorCombo);
		pieceColorPanel.add(pieceColorButton);
		rigthPanel.add(pieceColorPanel);
		
	}
	
	/**
	 * Accion del boton de cambiar el color.
	 */
	protected void choose() {
		Piece p = (Piece) this.pieceColorCombo.getSelectedItem();
		ColorChooser c = new ColorChooser(new JFrame(), "Select color", pieceColors.get(p));
		Color color = c.getColor();
		if ( color != null ) {
			pieceColors.put(p,color);
			playerInformationTable.refresh();
		}
		this.redrawBoard();
	}
	
	/**
	 * Crea el panel del modo del jugador.
	 */
	private void playerModesPanel() {
		playerModesPanel = new JPanel ();
		
		playerModesPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Player Modes"));
		playerModesCombo = new JComboBox<Piece>();
		typeModesCombo = new JComboBox<PlayerMode>();
		playerModesButton = new JButton("Set");
		playerModesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ set();}
		});
		this.typeModesCombo.addItem(PlayerMode.MANUAL);
		this.typeModesCombo.addItem(PlayerMode.RANDOM);
		this.typeModesCombo.addItem(PlayerMode.AI);
		playerModesPanel.add(playerModesCombo);
		playerModesPanel.add(typeModesCombo);
		
		playerModesPanel.add(playerModesButton);
		rigthPanel.add(playerModesPanel);
	}
	
	/**
	 * Accion de cabiar modo de jugador.
	 */
	protected void set() {
		Piece p = (Piece) playerModesCombo.getSelectedItem();
		PlayerMode pm = (PlayerMode) typeModesCombo.getSelectedItem();
		playerTypes.put(p,pm);
		playerInformationTable.refresh();
		
		if(p.getId().equalsIgnoreCase(turn.getId())){
			if(playerTypes.get(this.turn).equals(PlayerMode.RANDOM)){
				move(ramdomPlayer);
			}
			if(playerTypes.get(this.turn).equals(PlayerMode.AI)){
				move(aiPlayer);
			}
		}
	}
	
	/**
	 * Crea el panel de los movimientos automaticos.
	 */
	private void AutomaticMovesPanel() {
		automaticMovesPanel = new JPanel();
		automaticMovesPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Automatic Moves"));
		randomAutomaticMovesButton = new JButton("Random");
		randomAutomaticMovesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {move(ramdomPlayer);}
		});
		inteligenteAutomaticMovesButton = new JButton("Intelligent");
		inteligenteAutomaticMovesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){move(aiPlayer);}
		});
		automaticMovesPanel.add(randomAutomaticMovesButton);
		automaticMovesPanel.add(inteligenteAutomaticMovesButton);
		rigthPanel.add(automaticMovesPanel);
	}
	
	/**
	 * Crea el paner de salir y reiniciar.
	 */
	private void quitRestartPanel() {
		quitRestarPanel = new JPanel ();
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {quit();}
		});
		restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ ctrl.restart();
			playerInformationTable.refresh();
			addStatusMessages("--Restarted--");}
		});

		quitRestarPanel.add(quitButton);
		quitRestarPanel.add(restartButton);
		rigthPanel.add(quitRestarPanel);
	}
	
	
	// --------------------------------------------------------------------------------
	
	/**
	 * Clase para la tabla de jugadores.
	 *
	 *
	 */
	private class TableModel extends AbstractTableModel{
		
		private static final long serialVersionUID = 1L;
		
		private String[] columsNames = new String[] {"Player","Mode","Pieces"};
		
		@Override
		public String getColumnName(int column){
			return columsNames[column];
			
		}
		
		@Override
		public int getRowCount() {
			return pieces == null ? 0 : pieces.size();
		}

		@Override
		public int getColumnCount() {
			return columsNames.length;
		}

		public void refresh() {
			fireTableStructureChanged();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return pieces.get(rowIndex);
			case 1:
				return playerTypes.get(pieces.get(rowIndex));
			case 2:
				return board.getPieceCount(pieces.get(rowIndex));
			default:
				break;
			}
			return columnIndex;
		}
		
			
	}
	

}
