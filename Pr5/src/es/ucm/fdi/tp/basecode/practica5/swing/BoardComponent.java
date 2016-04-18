package es.ucm.fdi.tp.basecode.practica5.swing;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;



public class BoardComponent extends JComponent implements GameObserver  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _CELL_HEIGHT = 50;
	private int _CELL_WIDTH = 50;
	protected Observable<GameObserver> game;

	private int rows;
	private int cols;
	private Color[][] board;

	public BoardComponent(Observable<GameObserver> g, int rows, int cols) {
		this.game=g;
		
		SwingUtilities.invokeLater(new Runnable() {
				
			@Override
			public void run() {
				game.addObserver(BoardComponent.this);
			}
			
		});

		initBoard(rows, cols);
		initGUI();
	}

	private void initBoard(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		board = new Color[rows][cols];
		PanelIzquierda piz=new PanelIzquierda(rows);
		this.add(piz);
		/*for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (Math.random() > 0.5)
				 // board[i][j] = Color.BLUE;
					board[i][j] = new Color(138, 43, 226);
				else
				 // board[i][j] = Color.RED;
		            board[i][j] = Color.WHITE;*/
	}

	
	private void initGUI() {

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse Exited Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse Entered Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Clicked:: " + "(" + e.getX() + "," + e.getY() + ")");
			}
			
		});
		
		this.setSize(new Dimension(rows * _CELL_HEIGHT, cols * _CELL_WIDTH));
		
		repaint();
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		_CELL_WIDTH  = this.getWidth()  / cols;
		_CELL_HEIGHT = this.getHeight() / rows;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++);
				//drawCell(i, j, g);
		
	}

	private void drawCell(int row, int col, Graphics g) {
		
		int x = col * _CELL_WIDTH;
		int y = row * _CELL_HEIGHT;

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 2, y + 2, _CELL_WIDTH - 4, _CELL_HEIGHT - 4);

		g.setColor(board[row][col]);
		g.fillOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);

		g.setColor(Color.black);
		g.drawOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);

	}

	public void setBoardSize(int rows, int cols) {
		initBoard(rows, cols);
		repaint();
	}

	protected boolean isPlayerPiece(Piece p) {
		// TODO Auto-generated method stub
		return false;
	}

	protected Color getPieceColor(Piece p) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void mouseClicked(int row, int col, int mouseButton) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
	//	chageBoard(board);
		
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}


}
