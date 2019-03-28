package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class PublicLobbyFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPlayers;
	private JTextPane txtNickPlayers;
	private JLabel lblInfoCounter;
	private JButton btnLeftRoom;
	
	public PublicLobbyFrame(ActionListener actionListener, int timeSeconds, LinkedList<String> roomPlayers) {
		super("Sala de espera (pública)");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,300);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.GREEN);
		GridBagConstraints gbc = new GridBagConstraints();
		this.lblPlayers = new JLabel("Jugadores de la sala: ");
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(lblPlayers, gbc);
		
		this.txtNickPlayers = new JTextPane();
		txtNickPlayers.setEditable(false);
		txtNickPlayers.setPreferredSize(new Dimension(200, 150));
		txtNickPlayers.setBackground(new Color(114, 220, 140));
		this.fillTextPane(roomPlayers);
		gbc.gridy=1;
		this.add(txtNickPlayers, gbc);
						
		lblInfoCounter = new JLabel("<html>La partida se iniciará en: "+timeSeconds
				+" segundos,<br> o cuando se complete el cupo máximo<html>");
		lblInfoCounter.setBackground(Color.CYAN);
		lblInfoCounter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),
				"Segundos para iniciar la partida", 
				0, 1, new Font("Italic", 2, 15), Color.BLACK));
		gbc.gridy=2;
		this.add(lblInfoCounter, gbc);
		
		this.btnLeftRoom = new JButton("Abandonar Sala");
		btnLeftRoom.addActionListener(actionListener);
		btnLeftRoom.setActionCommand("abandonar_sala_publica");
		btnLeftRoom.setBackground(new Color(255, 111, 111));
		gbc.gridx=1;
		gbc.gridy=3;
		this.add(btnLeftRoom, gbc);
		
		this.pack();
		this.center();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Fills the textPane with the nickNames of the players in the room
	 * @param list a linked list with the nickNames of some players
	 */
	private void fillTextPane(LinkedList<String> list) {
		String info = "";
		Node<String> aux = list.getHead();
		while(aux!=null) {
			info+="- "+aux.getInfo()+"\n";
			aux = aux.getNext();
		}
		if(list.getSize()<8) {
			for (int i = 0; i < (8-list.getSize()); i++) 
				info+="- \n";		
		}
		this.txtNickPlayers.setText(info);
	}
	
	/**
	 * Update the remaining time that is showed in the lobby frame
	 * @param currentTime the current seconds of the public room
	 */
	public void updateTime(int currentTime) {
		lblInfoCounter.setText(/*"La partida se iniciará en: "*/""+currentTime
			/*	+" segundos, o cuando se complete el cupo máximo"*/);
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
