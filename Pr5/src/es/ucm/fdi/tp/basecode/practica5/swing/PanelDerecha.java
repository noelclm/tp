package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PanelDerecha extends JPanel{
	
	//private List<Piece> pieces;
	
	private JTextArea status;
	private JTable information;
	private Tabla tabla;
	private JPanel statusPanel;
	private JPanel playerPanel;
	private JPanel piecePanel;
	private JPanel playerModePanel;
	private JPanel automaticPanel;
	private JPanel abajoPanel;
	private JButton color;
	private JButton set;
	private JButton random;
	private JButton inteligente;
	private JButton quit;
	private JButton restart;
	private JComboBox combo1;
	private JComboBox combo2;
	private JComboBox combo3;
	private List<Piece> pieces;
	
	public enum PlayerMode {
		MANUAL("Manual"), RANDOM("Random"), AI("Automatics");

		//private String id;
		private String desc;

		PlayerMode(String desc) {
			//this.id = id;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return desc;
		}
	}

	
	public PanelDerecha(){
		
		this.setLayout(new GridLayout(6, 1));
		this.status=new JTextArea("status");
		this.statusPanel=new JPanel();
		statusPanel.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		statusPanel.add(status);
		
		this.playerPanel=new JPanel();
		playerPanel.setBorder(BorderFactory.createTitledBorder("Player Information"));
		this.tabla =new Tabla();
		this.information=new JTable (tabla);
		playerPanel.add(information);
		
		this.piecePanel=new JPanel();
		piecePanel.setBorder(BorderFactory.createTitledBorder("Piece Colors"));
		piecePanel.setLayout(new FlowLayout());
		this.playerModePanel=new JPanel();
		playerModePanel.setBorder(BorderFactory.createTitledBorder("Player Modes"));
		playerModePanel.setLayout(new FlowLayout());
		this.automaticPanel=new JPanel();
		automaticPanel.setBorder(BorderFactory.createTitledBorder("Automatic Moves"));
		automaticPanel.setLayout(new FlowLayout());
		this.abajoPanel=new JPanel();
		abajoPanel.setLayout(new FlowLayout());
		this.combo1=new JComboBox();
			
		piecePanel.add(combo1);
		this.combo3=new JComboBox();
		playerModePanel.add(combo3);
		this.combo2=new JComboBox(PlayerMode.values());
		playerModePanel.add(combo2);
		this.color=new JButton("Choose Color");
		piecePanel.add(color);
		this.set=new JButton("Set");
		playerModePanel.add(set);
		this.random=new JButton("Random");
		automaticPanel.add(random);
		this.inteligente=new JButton("Intelligent");
		automaticPanel.add(inteligente);
		this.quit=new JButton("Quit");
		abajoPanel.add(quit);
		this.restart=new JButton("Restart");
		abajoPanel.add(restart);
		

		
	//	for (Pieces p:pieces){
		//	pieceColor.put(p.colorsItem.next());
			//playerColor(B.addItem(p));
		//}
		
		this.add(statusPanel);
		this.add(playerPanel);
		this.add(piecePanel);
		this.add(playerModePanel);
		this.add(automaticPanel);
		this.add(abajoPanel);
	}
private class Tabla extends AbstractTableModel{
	private String[]columnas={"Player","Mode", "#Pieces"};
	
	@Override
	public String getColumnName(int index){
		return columnas[1];
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}

public void ponerPiezas (List<Piece> pieces){
	this.pieces = pieces;
	for (Piece p: this.pieces){
		combo1.addItem(p);
		combo3.addItem(p);
	}
}
}
