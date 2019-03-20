package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PinturilloProfileFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileMenuBar menuBar;
	private JPanel userInfoPanel, buttonsPanel;
	private JLabel lblNickName, lblUserNick, lblGlobalScore, lblUserScore, lblIcon;
	private JButton btnPublicRoom, btnPrivateRoom, btnCreatePrivateRoom;
	
	public PinturilloProfileFrame(ActionListener actionListener, String nickName,
			long globalScore, String iconPath) {
		super("Pinturillo Social");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,400);
		this.setLayout(new BorderLayout());
		menuBar = new ProfileMenuBar(actionListener);
		this.setJMenuBar(menuBar);
		userInfoPanel = new JPanel(new GridBagLayout());
		userInfoPanel.setBackground(Color.ORANGE);
		userInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				"Información del jugador", 
				0, 1, new Font("Italic", 2, 15), Color.BLUE));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		lblNickName = new JLabel("NickName:   ");
		gbc.gridx=0;
		gbc.gridy=0;
		userInfoPanel.add(lblNickName, gbc);
		
		lblUserNick = new JLabel(nickName+"     ");
		gbc.gridx=1;
		userInfoPanel.add(lblUserNick, gbc);
		
		lblIcon = new JLabel(new ImageIcon(iconPath));
		lblIcon.setPreferredSize(new Dimension(100, 100));
		gbc.weighty=2;
		gbc.gridx=3;
		userInfoPanel.add(lblIcon, gbc);
		
		lblGlobalScore = new JLabel("<html>Puntuación <br> global:</html>");
		gbc.weighty=1;
		gbc.gridx=0;
		gbc.gridy=1;
		userInfoPanel.add(lblGlobalScore, gbc);
		
		lblUserScore = new JLabel(String.valueOf(globalScore));
		gbc.gridx=1;
		userInfoPanel.add(lblUserScore, gbc);
		this.add(userInfoPanel, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel(new GridBagLayout());
		buttonsPanel.setBackground(new Color(254, 254, 112));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPublicRoom = new JButton("<html>Ingresar <br> a sala <br> pública </html>");
		btnPublicRoom.addActionListener(actionListener);
		btnPublicRoom.setActionCommand("crear_sala_publica");
		btnPublicRoom.setBackground(new Color(207, 65, 40));
		btnPublicRoom.setFont(new Font("Italic", 20, 20));
		btnPublicRoom.setPreferredSize(new Dimension(155,150));
		gbc.gridx=0;
		gbc.gridy=0;
		buttonsPanel.add(btnPublicRoom, gbc);
		
		btnPrivateRoom = new JButton("<html>Ingresar <br> a sala <br> privada </html>");
		btnPrivateRoom.addActionListener(actionListener);
		btnPrivateRoom.setActionCommand("crear_sala_publica");
		btnPrivateRoom.setBackground(new Color(207, 111, 40));
		btnPrivateRoom.setFont(new Font("Italic", 20, 20));
		btnPrivateRoom.setPreferredSize(new Dimension(155,150));
		gbc.gridx=1;
		buttonsPanel.add(btnPrivateRoom, gbc);
		
		btnCreatePrivateRoom = new JButton("<html>Crear <br> sala <br> privada </html>");
		btnCreatePrivateRoom.addActionListener(actionListener);
		btnCreatePrivateRoom.setActionCommand("crear_sala_privada");
		btnCreatePrivateRoom.setBackground(new Color(207, 111, 118));
		btnCreatePrivateRoom.setFont(new Font("Italic", 20, 20));
		btnCreatePrivateRoom.setPreferredSize(new Dimension(154,150));
		gbc.gridx=3;
		buttonsPanel.add(btnCreatePrivateRoom, gbc);
		
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.center();
		this.setVisible(true);
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
}
