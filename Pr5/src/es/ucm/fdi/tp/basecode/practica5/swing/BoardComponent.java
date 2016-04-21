package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public abstract class BoardComponent extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Board board;
	private int _CELL_HEIGHT = 50;
	private int _CELL_WIDTH = 50;
	private int rows;
	private int cols;
	

	protected abstract Color getPieceColor(Piece p);
	protected abstract boolean isPlayerPiece(Piece p);
	protected abstract void mouseClicked(int row, int col, int mouseButton);
	
	
	public void redraw(Board b){
		this.board = b;
		this.cols = b.getCols();
		this.rows = b.getRows();
		repaint();
	}

	public BoardComponent() {
		initGUI();
	}

	private void initGUI() {

		addMouseListener(new MouseAdapter() {

			//controlar que el pulsar y soltar sean los mismos: mouse Release y pressed
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Mouse Released: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("Mouse Pressed: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				BoardComponent.this.mouseClicked(e.getY()/_CELL_HEIGHT, e.getX()/_CELL_WIDTH, e.getButton());
			} 
		});
		this.setSize(new Dimension(rows * _CELL_HEIGHT, cols * _CELL_WIDTH));
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ( board == null ) {
			return;
		}
		_CELL_WIDTH = this.getWidth() / cols;
		_CELL_HEIGHT = this.getHeight() / rows;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				drawCell(i, j, g);
	}

	private void drawCell(int row, int col, Graphics g) {
		int x = col * _CELL_WIDTH;
		int y = row * _CELL_HEIGHT;

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 2, y + 2, _CELL_WIDTH - 4, _CELL_HEIGHT - 4);

		if(board.getPosition(row, col) != null) {
			Piece p = board.getPosition(row, col);
			g.setColor(getPieceColor(p));
			g.fillOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
		}
	}
}
