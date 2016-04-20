package es.ucm.fdi.tp.basecode.practica5.swing;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelIzquierda extends JPanel{
	
	public PanelIzquierda (int dim){
		this.setLayout(new GridLayout(dim,dim));
			for (int i = 0; i < dim; i++){
				for(int j = 0; j < dim; j++)
			{
				 JButton lab = new JButton();
				 this.add(lab);
			}
		}
		this.setVisible(true);
		
		
	}

}
