package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

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
	
	//private Piece localPiece;
	private Piece turn;
	private List<Piece> pieces;

	private Board board;

	/*
	 * private Player randomPlayer;
	 * private Player aiPlayer;
	 * */
	
	/*
	 * private Map<Piece, Color> pieceColors;
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
		boardPanel.add(c, BorderLayout.CENTER);
	}
	
	final protected void addToCtrlArea(JComponent c) {
		toolBarPanel.add(c);
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

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				game.addObserver(SwingView.this);
			}
			
		});

		initGUI();
		
	}

	
	private void initGUI() {
		
		this.setTitle("Board Games");
		
			JPanel mainPanel = new JPanel(new BorderLayout());
		
		this.setContentPane(mainPanel);

			 // board panel
				boardPanel = new JPanel(new BorderLayout());
			
			mainPanel.add(boardPanel, BorderLayout.CENTER);
			
		initBoardGui();

			 // tool bar panel
				toolBarPanel = new JPanel();
				toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.Y_AXIS));
			
			mainPanel.add(toolBarPanel, BorderLayout.LINE_END);
			
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

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
	public void onGameStart(final Board board, final String gameDesc, final List<Piece> pieces, final Piece turn) {

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
