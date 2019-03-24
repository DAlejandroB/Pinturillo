package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		this.setSize(500,300);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbcFrame = new GridBagConstraints();
		friendsPanel = new JPanel();
		friendsPanel.setBackground(Color.LIGHT_GRAY);
		friendsPanel.setPreferredSize(new Dimension(250,200));
		friendsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray),
				"Amigos actuales", 
				0, 1, new Font("Italic", 2, 15), new Color(0, 193, 9)));
		friendsUserList = new JComboBox<String>();
		friendsUserList.addActionListener(actionListener);
		friendsUserList.setActionCommand("amigo_seleccionado");
		this.fillFriendsLists(friendsName);
		friendsPanel.add(friendsUserList);
		gbcFrame.gridx=0;
		gbcFrame.gridy=0;
		this.add(friendsPanel, gbcFrame);
		
		friendSettingsPanel = new JPanel(new BorderLayout());
		friendSettingsPanel.setBackground(Color.LIGHT_GRAY);
		friendInfoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbcPanel = new GridBagConstraints();
		txtFriendInfo = new JTextPane();
		txtFriendInfo.setEditable(false);
		txtFriendInfo.setPreferredSize(new Dimension(200,150));
		gbcPanel.gridx=0;
		gbcPanel.gridy=0;
		friendInfoPanel.add(txtFriendInfo, gbcPanel);
		
		lblfriendsNumber = new JLabel("No. total de amigos:  "+friendsName.getSize());
		gbcPanel.gridy=1;
		friendInfoPanel.add(lblfriendsNumber, gbcPanel);
		
		friendSettingsPanel.add(friendInfoPanel, BorderLayout.NORTH);
		
		buttonsPanel = new JPanel(new GridBagLayout());
		btnDeleteFriend = new JButton("Eliminar amigo");
		btnDeleteFriend.addActionListener(actionListener);
		btnDeleteFriend.setActionCommand("borrar_amigo");
		gbcPanel.gridx=0;
		gbcPanel.gridy=0;
		buttonsPanel.add(btnDeleteFriend, gbcPanel);
		
		btnAddFriend = new JButton("Añadir Nuevo Amigo");
		btnAddFriend.addActionListener(actionListener);
		btnAddFriend.setActionCommand("añadir_amigo");
		gbcPanel.gridy=1;
		buttonsPanel.add(btnAddFriend, gbcPanel);
		friendSettingsPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		gbcFrame.gridx=1;
		this.add(friendSettingsPanel, gbcFrame);
		this.setVisible(true);
		this.setResizable(false);
		this.center();
		
	}
	
	/**
	 * Get the nickName of the selected friend in the comboBox
	 * @return A string with a nickName
	 */
	public String getSelectedFriend() {
		return this.friendsUserList.getSelectedItem().toString();
	}
	
	/**
	 * Creates a confirmDialog with a message
	 * @param message the message to ask in the dialog
	 * @return 1 if the answer is 'OK', 2 in another case
	 */
	public int deleteFriendResponse() {
		String message = "Esta seguro que desea eliminar a "+this.friendsUserList.getSelectedItem().toString()+
				" de su lista de amigos?";
		return JOptionPane.showConfirmDialog(this, message)==JOptionPane.OK_OPTION?1:2;
	}
	
	public void setFriendInfo(String nickName, long globalScore, String status) {
		String info = "NickName: "+nickName+"\n";
			    info+="GlobalScore: "+globalScore+"\n";
			    info+="Status: "+status;
		this.txtFriendInfo.setText(info);
	}
	
	/**
	 * Método que centra el frame 
	 */
	private void center() {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEdge = ( screen.width - getWidth( ) ) / 2;
        int yEdge = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEdge, yEdge );
    }
	
	public void fillFriendsLists(LinkedList<String> friendsName) {
		if(!friendsName.isEmpty()) {
			Node<String> aux = friendsName.getHead();
			while(aux!=null) {
				friendsUserList.addItem(aux.getInfo());
				aux = aux.getNext();
			}
		}
	}
	
}
