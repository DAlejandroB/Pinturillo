package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class PrivateLobbyFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel lblPlayers;
	private JTextPane txtNickPlayers;
	private JButton btnLeftRoom, btnInvite;
	
	public PrivateLobbyFrame(ActionListener actionListener, LinkedList<String> roomPlayers, boolean isTheLeader) {
		super("Sala de espera (privada)");
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
		
		this.generateInviteButton(isTheLeader, gbc, actionListener);
		
		this.btnLeftRoom = new JButton("Abandonar Sala");
		btnLeftRoom.addActionListener(actionListener);
		btnLeftRoom.setActionCommand("abandonar_sala_privada");
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
	 * Generates the invite button only if the player is the room leader
	 * @param isTheLeader indicates if the player is the leader
	 * @param gbc a GridBagConstraints object
	 * @param actionListener an ActionListener object
	 */
	private void generateInviteButton(boolean isTheLeader, GridBagConstraints gbc, ActionListener actionListener) {
		if(isTheLeader) {
			gbc.gridy=2;
			this.btnInvite = new JButton("Invitar amigos");
			btnInvite.addActionListener(actionListener);
			btnInvite.setActionCommand("invitar_amigos");
			this.add(btnInvite, gbc);
		}
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
	 * Centers the frame
	 */
	private void center() {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEdge = ( screen.width - getWidth( ) ) / 2;
        int yEdge = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEdge, yEdge );
    }
}
