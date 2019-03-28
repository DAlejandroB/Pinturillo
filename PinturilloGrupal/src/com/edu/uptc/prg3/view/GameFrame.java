package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.edu.uptc.prg3.view.dialogs.ScoresTableDialog;
import com.edu.uptc.structure.LinkedList;

public class GameFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private DrawAreaPanel drawAreaPanel;
	private ChatPanel chatPanel;
	
	public GameFrame(ActionListener actionListener, boolean isDrawer) {
		super("Pinturillo!");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
					Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setLayout(new BorderLayout());
		this.drawAreaPanel = new DrawAreaPanel(isDrawer);
		this.add(drawAreaPanel, BorderLayout.WEST);
		
		this.chatPanel = new ChatPanel(actionListener, isDrawer);
		this.add(chatPanel, BorderLayout.EAST);
		this.setVisible(true);
		this.setResizable(false);	
	}
	
	/**
	 * Generates a scores table of all the scores of the players in the room
	 * @param nickNames all the nickNames of the players in the room
	 * @param scores all the scores of the players in the room
	 */
	public void generateScoreTable(LinkedList<String> nickNames, LinkedList<Integer> scores) {
		new ScoresTableDialog(this, nickNames, scores);
	}
	
	/**
	 * Puts three words in a dialog, and lets choose one of them to the drawer
	 * @param word1 first word to choose
	 * @param word2 second word to choose
	 * @param word3 third word to choose
	 * @return the selected word
	 */
	public String selectAWord(String word1, String word2, String word3) {
		String selectedWord = "";
		String[] options = {word1, word2, word3};
		int answer = JOptionPane.showInternalOptionDialog(this.getContentPane(), "Selecciona una palabra", "Selección de palabra", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "");
		if(answer==0) {
			selectedWord = word1;
		}else if(answer==1) {
			selectedWord = word2;
		}else selectedWord = word3;
		return selectedWord;
	}
	
	/**
	 * Calls the flipDrawer method of the drawAreaPanel, and flips the status of the local player
	 * @param isDrawer indicates if the local player is the drawer
	 */
	public void flipDrawer(boolean isDrawer) {
		this.drawAreaPanel.flipDrawer(isDrawer);
	}
}
