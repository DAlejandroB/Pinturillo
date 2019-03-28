package com.edu.uptc.prg3.view.dialogs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.edu.uptc.prg3.view.InviteListPanel;
import com.edu.uptc.prg3.view.PrivateLobbyFrame;
import com.edu.uptc.structure.LinkedList;

public class InviteListDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InviteListPanel listPanel;
	
	public InviteListDialog(PrivateLobbyFrame principal, 
			ActionListener actionListener, LinkedList<String> friendsNames) {
		super(principal, true);
		listPanel = new InviteListPanel(this, actionListener, friendsNames);
		this.setTitle("Invitaciones a sala privada");
		this.center();
		this.setResizable(false);
		this.pack();
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
	
	/**
	 * Calls the getSelectedFriend in the listPanel class
	 * @return A string with the selected item
	 */
	public String getSelectedFriend() {
		return this.listPanel.getSelectedFriend();
	}
	
	/**
	 * Calls the refreshList of the listPanel class
	 * @param friendsNames a linkedlist with the NickNames of the local user friends
	 */
	public void refreshList(LinkedList<String> friendsNames) {
		this.listPanel.refreshList(friendsNames);
	}
}
