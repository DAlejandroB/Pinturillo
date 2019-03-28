package com.edu.uptc.prg3.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import com.edu.uptc.prg3.view.GameFrame;
import com.edu.uptc.prg3.view.ScoresTablePanel;
import com.edu.uptc.structure.LinkedList;

public class ScoresTableDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private ScoresTablePanel tablePanel;
	
	public ScoresTableDialog(GameFrame principal, LinkedList<String> nickNames, LinkedList<Integer> scores) {
		super(principal, true);
		this.tablePanel = new ScoresTablePanel(nickNames, scores, this);
		this.add(tablePanel, BorderLayout.CENTER);
		this.setTitle("Tabla de puntuaciones");
		this.center();
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Centers the frame
	 */
	private void center() {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEdge = ( screen.width - getWidth( ) ) / 2;
        int yEdge = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEdge, yEdge );
    }
}
