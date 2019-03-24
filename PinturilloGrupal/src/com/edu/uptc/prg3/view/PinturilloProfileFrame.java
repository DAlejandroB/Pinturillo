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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.edu.uptc.structure.LinkedList;

public class PinturilloProfileFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileMenuBar menuBar;
	private JPanel userInfoPanel, buttonsPanel;
	private JLabel lblNickName, lblUserNick, lblGlobalScore, lblUserScore, lblIcon;
	private JButton btnPublicRoom, btnPrivateRoom, btnCreatePrivateRoom;
	private UserFriendsFrame userFriendsFrame;
	private PublicLobbyFrame publicLobby;
	//private PrivateLobby privateLobby;
	private GameFrame gameFrame;
	
	public PinturilloProfileFrame(ActionListener actionListener, String nickName,
			long globalScore, String iconPath) {
		super("Pinturillo Social");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,450);
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
		lblNickName.setFont(new Font(Font.SANS_SERIF, 20, 20));
		gbc.gridx=0;
		gbc.gridy=0;
		userInfoPanel.add(lblNickName, gbc);
		
		lblUserNick = new JLabel(nickName+"     ");
		lblUserNick.setFont(new Font(Font.SANS_SERIF, 20, 20));
		gbc.gridx=1;
		userInfoPanel.add(lblUserNick, gbc);
		
		lblIcon = new JLabel(new ImageIcon(iconPath));
		lblIcon.setPreferredSize(new Dimension(100, 100));
		gbc.weighty=2;
		gbc.gridx=3;
		userInfoPanel.add(lblIcon, gbc);
		
		lblGlobalScore = new JLabel("<html>Puntuación <br> global:</html>");
		lblGlobalScore.setFont(new Font(Font.SANS_SERIF, 20, 20));
		gbc.weighty=1;
		gbc.gridx=0;
		gbc.gridy=1;
		userInfoPanel.add(lblGlobalScore, gbc);
		
		lblUserScore = new JLabel(String.valueOf(globalScore));
		lblUserScore.setFont(new Font(Font.SANS_SERIF, 20, 20));
		gbc.gridx=1;
		userInfoPanel.add(lblUserScore, gbc);
		this.add(userInfoPanel, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel(new GridBagLayout());
		buttonsPanel.setBackground(new Color(254, 254, 112));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPublicRoom = new JButton("<html>Ingresar <br> a sala <br> pública </html>");
		btnPublicRoom.addActionListener(actionListener);
		btnPublicRoom.setActionCommand("entrar_sala_publica");
		btnPublicRoom.setBackground(new Color(207, 65, 40));
		btnPublicRoom.setFont(new Font("Italic", 20, 20));
		btnPublicRoom.setPreferredSize(new Dimension(155,150));
		gbc.gridx=0;
		gbc.gridy=0;
		buttonsPanel.add(btnPublicRoom, gbc);
		
		btnPrivateRoom = new JButton("<html>Ingresar <br> a sala <br> privada </html>");
		btnPrivateRoom.addActionListener(actionListener);
		btnPrivateRoom.setActionCommand("entrar_sala_privada");
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
		this.setResizable(false);
		this.setVisible(true);
	}
		
	public void createUserFriendsFrame(ActionListener actionListener, String userNick, LinkedList<String> friendsName) {
		this.userFriendsFrame = new UserFriendsFrame(actionListener, userNick, friendsName);
	}
	
	public void setFriendInfo(String nickName, long globalScore, String status) {
		this.userFriendsFrame.setFriendInfo(nickName, globalScore, status);
	}
	
	/**
	 * Creates a confirmDialog with a message
	 * @param message the message to ask in the dialog
	 * @return 1 if the answer is 'OK', 2 in another case
	 */
	public int generateQuestionDialog(String message) {
		return JOptionPane.showConfirmDialog(this, message)==JOptionPane.OK_OPTION?1:2;
	}
	
	public void createPublicLobbyFrame(ActionListener actionListener, int currentSeconds, LinkedList<String> roomPlayers) {
		this.publicLobby = new PublicLobbyFrame(actionListener, currentSeconds, roomPlayers);
		
	}
	
	public void generateScoreTable(LinkedList<String> nickNames, LinkedList<Integer> scores) {
		this.gameFrame.generateScoreTable(nickNames, scores);
	}
	
	public void createGameFrame(ActionListener actionListener, boolean isDrawer) {
		this.gameFrame = new GameFrame(actionListener, isDrawer);
	}
	
	public String getSelectedFriend() {
		return this.userFriendsFrame.getSelectedFriend();
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
