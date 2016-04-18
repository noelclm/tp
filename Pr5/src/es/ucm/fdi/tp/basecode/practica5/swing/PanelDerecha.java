package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class PanelDerecha extends JPanel{
	
	private JTextArea status;
	private JTable information;
	private Tabla tabla;
	private JPanel statusPanel;
	private JPanel playerPanel;
	private JPanel piecePanel;
	private JPanel playerModePanel;
	private JPanel automaticPanel;
	private JComboBox combo1;
	
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
	//	for (Pieces p:pieces){
		//	pieceColor.put(p.colorsItem.next());
			//playerColor(B.addItem(p));
		//}
		
		this.add(statusPanel);
		this.add(playerPanel);
		this.add(piecePanel);
		this.add(playerModePanel);
		this.add(automaticPanel);
	}
private class Tabla extends AbstractTableModel{
	private String[]columnas={"Player","Mode", "#Pieces"};
	
	public String getColumnName(int index){
		return columnas[index];
		
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
}
