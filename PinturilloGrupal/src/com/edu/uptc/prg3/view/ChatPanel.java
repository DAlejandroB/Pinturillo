package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ChatPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isDrawer;
	private JTextPane txtChat;
	private JTextField txtEnterChat;
	private JButton btnSendMessage, btnReport, btnShowScores;
	private JPanel buttonPanel;
	
	public ChatPanel(ActionListener actionListener, boolean isDrawer) {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(104, 255, 248));
		this.isDrawer = isDrawer;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.REMAINDER;
		txtChat = new JTextPane();
		txtChat.setEditable(false);
		txtChat.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width/2)-70,
				Toolkit.getDefaultToolkit().getScreenSize().height-150));
		txtChat.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(txtChat, gbc);
		
		this.buttonPanel = new JPanel(new BorderLayout());
		this.generateCustomChat(gbc, actionListener);
				
		this.btnShowScores = new JButton("Puntuaciones");
		btnShowScores.addActionListener(actionListener);
		btnShowScores.setActionCommand("mostrar_puntuaciones");
		btnShowScores.setFont(new Font("Italic", 20, 20));
		btnShowScores.setPreferredSize(new Dimension(150, 50));
		buttonPanel.add(btnShowScores, BorderLayout.EAST);
		
		gbc.gridx=0;		
		gbc.gridy=2;
		this.add(buttonPanel, gbc);
	}
	
	/**
	 * Generates a text field for input comments, and put other components only if the local user,
	 * isn´t the drawer of the round
	 * @param gbc The gridBagConstraints of the chat panel
	 * @param actionListener an actionListener object for the created events
	 */
	private void generateCustomChat(GridBagConstraints gbc, ActionListener actionListener) {
		if(!isDrawer) {
			txtEnterChat = new JTextField();
			txtEnterChat.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width/2)-100, 40));
			txtEnterChat.addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent e) {
				}			
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						System.out.println("apretó enter");
						actionListener.actionPerformed(new ActionEvent(e, ERROR, "mensaje_enviado"));
					}
				}				
				@Override
				public void keyPressed(KeyEvent arg0) {					
				}
			});
			gbc.gridy=1;
			this.add(txtEnterChat, gbc);
			
			btnSendMessage = new JButton("Enviar");
			btnSendMessage.addActionListener(actionListener);
			btnSendMessage.setActionCommand("mensaje_enviado");
			gbc.gridx=1;
			this.add(btnSendMessage, gbc);
			
			btnReport = new JButton("Reportar dibujante");
			btnReport.addActionListener(actionListener);
			btnReport.setActionCommand("reportar");
			btnReport.setFont(new Font("Italic", 20, 20));
			btnReport.setPreferredSize(new Dimension(200, 50));
			buttonPanel.add(btnReport, BorderLayout.WEST);
		}
	}
		
	/**
	 * Reads the recently message entered in the chat
	 * @return a string with the readed message
	 */
	public String readMessage() {
		String message = this.txtEnterChat.getText();
		this.txtEnterChat.setText("");
		return message;
	}
	
	/**
	 * Adds a new message to the whole current chat
	 * @param info the new message to be written
	 */
	public void setMessage(String info) {
		this.txtChat.setText(txtChat.getText()+"\n"+info);
	}
}
