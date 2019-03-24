package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.edu.uptc.prg3.view.dialogs.ScoresTableDialog;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class ScoresTablePanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable scoreTable;
	private JButton btnExit;
	private ScoresTableDialog scoresTableDialog;
	
	public ScoresTablePanel(LinkedList<String> nickNames, LinkedList<Integer> scores, ScoresTableDialog scoresTableDialog) {
		this.scoresTableDialog = scoresTableDialog;
		this.setLayout(new BorderLayout());
		
		this.scoreTable = new JTable();
		this.fillTable(nickNames, scores);
		this.add(scoreTable, BorderLayout.NORTH);
		
		btnExit = new JButton("Cerrar");
		btnExit.addActionListener(this);
		btnExit.setActionCommand("cerrar");
		this.add(btnExit, BorderLayout.SOUTH);
	}
	
	private void fillTable(LinkedList<String> nickNames, LinkedList<Integer> scores) {
		DefaultTableModel tableModel = (DefaultTableModel) this.scoreTable.getModel();
		Object titleRow[] = {"Jugadores", "Puntuaciones"};
		tableModel.addRow(titleRow);
		
		Node<String> auxNick = nickNames.getHead();
		Node<Integer> auxScore = scores.getHead();
		while(auxNick!=null) {
			Object newRow[] = {auxNick.getInfo(), auxScore.getInfo()};
			tableModel.addRow(newRow);
			auxNick = auxNick.getNext();
			auxScore = auxScore.getNext();
		}
//		this.scoreTable.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.scoresTableDialog.dispose();
	}
}
