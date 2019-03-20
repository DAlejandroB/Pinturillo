package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class UserFriendsFrame extends JFrame{
	private JPanel friendsPanel, friendSettingsPanel, friendInfoPanel, buttonsPanel;
	private JComboBox<String> friendsUserList;
	private JTextPane txtFriendInfo;
	private JButton btnDeleteFriend, btnAddFriend;
	private JLabel lblfriendsNumber;
	
	public UserFriendsFrame(ActionListener actionListener, String userNick, 
			LinkedList<String> friendsName) throws HeadlessException {
		super("Amigos de "+userNick);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,400);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbcFrame = new GridBagConstraints();
		friendsPanel = new JPanel();
		friendsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray),
				"Amigos actuales", 
				0, 1, new Font("Italic", 2, 15), Color.GREEN));
		friendsUserList = new JComboBox<String>();
		friendsUserList.addActionListener(actionListener);
		friendsUserList.setActionCommand("amigo_seleccionado");
		friendsPanel.add(friendsUserList);
		gbcFrame.gridx=0;
		gbcFrame.gridy=0;
		this.add(friendsPanel, gbcFrame);
		
		friendSettingsPanel = new JPanel(new GridBagLayout());
		
		
		friendInfoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanel = new GridBagConstraints();
		txtFriendInfo = new JTextPane();
		txtFriendInfo.setEditable(false);
		txtFriendInfo.setPreferredSize(new Dimension(200,150));
		gbcPanel.gridx=0;
		gbcPanel.gridy=0;
		friendInfoPanel.add(txtFriendInfo, gbcPanel);
		
		lblfriendsNumber = new JLabel("No. total de amigos: "+friendsName.getSize());
		gbcPanel.gridy=1;
		friendInfoPanel.add(lblfriendsNumber, gbcPanel);
		
		gbcFrame.gridx=1;
		this.add(friendInfoPanel, gbcFrame);
		
		buttonsPanel = new JPanel(new GridBagLayout());
		btnDeleteFriend = new JButton("Eliminar amigo");
		btnDeleteFriend.addActionListener(actionListener);
		btnDeleteFriend.setActionCommand("borrar_amigo");
		
		
		
	}
	
	public void fillFriendsLists(LinkedList<String> onlineUsers, LinkedList<String> offlineUsers) {
		if(!onlineUsers.isEmpty()) {
			Node<String> aux = onlineUsers.getHead();
			while(aux!=null) {
				friendsUserList.addItem(aux.getInfo());
				aux = aux.getNext();
			}
		}
		if(!offlineUsers.isEmpty()) {
			Node<String> aux = offlineUsers.getHead();
			while(aux!=null) {
				friendsUserList.addItem(aux.getInfo());
				aux = aux.getNext();
			}
		}
	}
	
}
