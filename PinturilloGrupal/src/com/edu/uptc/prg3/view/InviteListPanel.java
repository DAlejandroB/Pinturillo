package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.edu.uptc.prg3.view.dialogs.InviteListDialog;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class InviteListPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblInviteFriends;
	private JComboBox<String> friendsList;
	private JButton btnInvite, btnClose;
	@SuppressWarnings("unused")
	private InviteListDialog dialog;
	
	public InviteListPanel(InviteListDialog dialog, ActionListener actionListener, 
					LinkedList<String> friendsNames) {
		this.dialog = dialog;
		this.setBackground(new Color(196, 249, 65));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		lblInviteFriends = new JLabel("Invitar amigos");
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(lblInviteFriends, gbc);
		
		friendsList = new JComboBox<>();
		this.fillList(friendsNames);
		gbc.gridx=1;
		this.add(friendsList, gbc);
		
		btnInvite = new JButton("Invitar");
		btnInvite.addActionListener(actionListener);
		btnInvite.setActionCommand("invitar_amigo");
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(btnInvite, gbc);
		
		btnClose = new JButton("Cerrar");
		btnClose.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();				
			}
		});
		gbc.gridx=1;
		this.add(btnClose, gbc);
		
		this.setVisible(true);
		this.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
	}
	
	/**
	 * Gets the element (friend) selected in the friendsList
	 * @return a string with the selected item info
	 */
	public String getSelectedFriend() {
		return this.friendsList.getSelectedItem().toString();
	}
	
	/**
	 * Updates the info of the friendsList with a new friends list
	 * @param friendsNames the names of the local player's friends
	 */
	public void refreshList(LinkedList<String> friendsNames) {
		this.friendsList.removeAllItems();
		Node<String> aux = friendsNames.getHead();
		while(aux!=null) {
			this.friendsList.addItem(aux.getInfo());
			aux = aux.getNext();
		}
	}
	
	/**
	 * Fills the friends list with all the friends in the list of the player´s list
	 * @param friendsNames
	 */
	private void fillList(LinkedList<String> friendsNames) {
		Node<String> aux = friendsNames.getHead();
		while(aux!=null) {
			this.friendsList.addItem(aux.getInfo());
			aux = aux.getNext();
		}
	}
}
