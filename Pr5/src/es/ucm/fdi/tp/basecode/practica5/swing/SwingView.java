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


@SuppressWarnings("serial")
public abstract class SwingView extends JFrame implements GameObserver {

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
			this.pieceColorCombo.addItem(p);
		}
		

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
		this.setTitle(gameDesc);
		this.redrawBoard();
		
	}

	@Override
	public void onGameOver(final Board board, final State state, final Piece winner) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleGameOver(board, state, winner);}
		});
	}
	
	protected void handleGameOver(Board board, State state, Piece winner) {
		this.board = board;
		addStatusMessages("Game Over!!");
		this.redrawBoard();
		addStatusMessages("Game Status: " + state);
		if (state == State.Won) {
			addStatusMessages("Winner: " + winner);
		}
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleMoveStart(board, turn);}
		});
	}
	
	protected void handleMoveStart(Board board2, Piece turn2) {
		//TODO implementar
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleMoveEnd(board, turn, success);}
		});
	}
	
	protected void handleMoveEnd(Board board2, Piece turn2, boolean success) {
		//TODO implementar
	}

	@Override
	public void onChangeTurn(Board board, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {handleChangeTurn(board, turn);}
		});
	}
	
	protected void handleChangeTurn(Board board2, Piece turn2) {
		this.board = board2;
		this.turn = turn2;
		
		this.redrawBoard();
		addStatusMessages("Turn for " + turn);
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {addStatusMessages(msg);}
		});
	}


	// --------------------------------------------------------------------------------
		
	
	protected abstract void initBoardGui();
	protected abstract void activateBoard();
	protected abstract void deActivateBoard();
	protected abstract void redrawBoard();
	

	final protected Board getBoard() { 
		return board; 
	}
	
	final protected Piece getTurn() { 
		return turn; 
	}
	
	final protected Color getPieceColor(Piece p) { 
		return pieceColors.get(p);
	}
	
	final protected List<Piece> getPieces() { 
		return pieces; 
	}
	
	final protected Color setPieceColor(Piece p, Color c) { 
		return pieceColors.put(p,c); 
	}
	
	final protected void setBoardArea(JComponent c) { 
		leftPanel.add(c,BorderLayout.CENTER);
	}
	
	protected void disableView() {
		this.leftPanel.setEnabled(false);
	}
	
	protected void enableView() {
		this.leftPanel.setEnabled(true);
	}
	
	final protected void addStatusMessages(String msg) { 
		statusMessages.append(msg + '\n');
	}
	
	// --------------------------------------------------------------------------------
	
		
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
	
	protected void set() {
		Piece p = (Piece) playerModesCombo.getSelectedItem();
		PlayerMode pm = (PlayerMode) typeModesCombo.getSelectedItem();
		playerTypes.put(p,pm);
		playerInformationTable.refresh();
	}
	
	private void AutomaticMovesPanel() {
		automaticMovesPanel = new JPanel();
		automaticMovesPanel.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLACK), "Automatic Moves"));
		randomAutomaticMovesButton = new JButton("Random");
		randomAutomaticMovesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {ctrl.makeMove(ramdomPlayer);}
		});
		inteligenteAutomaticMovesButton = new JButton("Intelligent");
		inteligenteAutomaticMovesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ ctrl.makeMove(aiPlayer);}
		});
		automaticMovesPanel.add(randomAutomaticMovesButton);
		automaticMovesPanel.add(inteligenteAutomaticMovesButton);
		rigthPanel.add(automaticMovesPanel);
	}
	
	
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
