package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

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

	protected Controller ctrl;
	protected Observable<GameObserver> game;
	
	private Piece localPiece;
	private Piece turn;
	private List<Piece> pieces;

	private Board board;
	private Map<Piece, Color> pieceColors;
	private Map<Piece, PlayerMode> playerTypes;
	private PanelDerecha pd;

	Iterator<Color> colorsIter;
	
	public enum PlayerMode {
		MANUAL("Manual"), RANDOM("Random"), AI("Automatics");

		private String desc;

		PlayerMode(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return desc;
		}
	}

	/*
	 * private Player randomPlayer;
	 * private Player aiPlayer;
	 * */
	
	/*
	 * 
	 * private Map<>
	 * */
	
	private JPanel boardPanel;
	private JPanel toolBarPanel;
	private JTextArea statusArea;
	
	
	protected Piece getTurn() {
		return turn;
	}

	final protected List<Piece> getPieces() {
		return pieces;
	}
	
	protected Board getBoard() {
		return board;
	}
	

	final protected void setBoardArea(JComponent c) {
		//boardPanel.add(c, BorderLayout.CENTER);
	}
	
	final protected void addToCtrlArea(JComponent c) {
		//toolBarPanel.add(c);
	}

	final protected void addContentToStatusArea(String msg) {
		statusArea.append("* " + msg + "\n");
	}

	
	protected abstract void initBoardGui();

	protected abstract void activateBoard();

	protected abstract void deActivateBoard();


	public SwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer, Player aiPlayer) {

		this.ctrl = c;
		this.game = g;
		
		colorsIter = new Iterator<Color>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public Color next() {
				return null;
			}
		};

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				game.addObserver(SwingView.this);
			}
			
		});

		initGUI();
		
	}

	
	private void initGUI() {
		
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		this.setMinimumSize(new Dimension(780, 600));
		
		this.setContentPane(mainPanel);

		// board panel
		//boardPanel = new JPanel(new BorderLayout());
		PanelIzquierda pi = new PanelIzquierda(5);
		this.pd = new PanelDerecha();
		mainPanel.add(pi, BorderLayout.CENTER);
		mainPanel.add(pd, BorderLayout.EAST);
		//mainPanel.add(boardPanel, BorderLayout.CENTER);
			
		initBoardGui();

		// tool bar panel
		//toolBarPanel = new JPanel();
		//toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
			
		//mainPanel.add(toolBarPanel, BorderLayout.LINE_END);
			
		initCtrlPanel();

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
			
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}

	
	protected void initCtrlPanel() {
		
		JPanel p = new JPanel(new BorderLayout());
		p.setPreferredSize(new Dimension(100, 150));
		p.setBorder(BorderFactory.createTitledBorder("Status Messages"));

		statusArea = new JTextArea(5, 10);
		statusArea.setEditable(true);

		JScrollPane statusAreaScroll = new JScrollPane(statusArea);
		
		p.add(statusAreaScroll, BorderLayout.CENTER);
			
		addToCtrlArea(p);

		JPanel buttons = new JPanel(new FlowLayout());
		addQuitButton(buttons);
		addRestartButton(buttons);
		addToCtrlArea(buttons);
	}
	
	final protected void addPlayers(){
		JComboBox players = new JComboBox();
		
		
	}	
	
	final protected void addQuitButton(JPanel panelBotones){
		
		JButton quitButton = new JButton("Quit");
		
		quitButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				quit();
			}
		});
		
		panelBotones.add(quitButton);
	}
	
	final protected void addRestartButton(JPanel panelBotones){
		
		JButton restartButton = new JButton("Restart");
		
		restartButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				try{
					ctrl.restart();
				}
				catch(GameError _e){
					
				}
			}
		});
		
		panelBotones.add(restartButton);
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
	

	/** GAME OBSERVER CALLBACKS **/

	@Override
	public void onGameStart(Board roBoard, String gameDesc, List<Piece> roPieces,Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){handGameStart(roBoard,gameDesc,roPieces,turn);}
			});
	}

	private void handGameStart(Board roBoard, String gameDesc, List<Piece> roPieces,Piece turn) {
		
		this.setTitle(gameDesc);
	
		this.pieceColors = new HashMap<>();
		this.playerTypes = new HashMap<>();
		this.board = roBoard;
		this.pieces = roPieces;
		this.turn = turn;
		
		for(Piece p : pieces) {
			if(pieceColors.get(p) == null) {
				this.pieceColors.put(p, colorsIter.next());
				this.playerTypes.put(p, PlayerMode.MANUAL);
			}
			//this.pieceColorComboBox.addItem(p);
		}
		
		this.pd.ponerPiezas(this.pieces);
		// TODO Preguntar
		
			//turn.equals(this.localPiece)?:
			
		
		
			
		
		
	}

	@Override
	public void onGameOver(final Board board, final State state, final Piece winner) {
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {

	}

	@Override
	public void onChangeTurn(Board board, final Piece turn) {

	}

	@Override
	public void onError(String msg) {

	}

}
