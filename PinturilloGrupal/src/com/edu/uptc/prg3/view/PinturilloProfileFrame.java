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
import com.edu.uptc.prg3.view.dialogs.ModifyInfoDialog;
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
	private ModifyInfoDialog modInfoDialog;
	
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
		
	/**
	 * Creates an user friends frame 
	 * @param actionListener an actionListener object
	 * @param userNick a string with the nickName of the local player
	 * @param friendsName a linked list with all the nickNames of the local player
	 */
	public void createUserFriendsFrame(ActionListener actionListener, String userNick, LinkedList<String> friendsName) {
		this.userFriendsFrame = new UserFriendsFrame(actionListener, userNick, friendsName);
	}
	
	/**
	 * Shows an error dialog with a personalized message
	 * @param message the message to be showed
	 */
	public void printErrorMessagge(String message) {
		JOptionPane.showMessageDialog( this, message, "error", JOptionPane.ERROR_MESSAGE );
	}
	
	/**
	 * Shows an information dialog with a personalized message
	 * @param message the message to be showed
	 */
	public void printInfoMessage(String message) {
		JOptionPane.showMessageDialog( this, message, "info", JOptionPane.INFORMATION_MESSAGE );
	}
	
	/**
	 * Sets the info of the selected friend in the list
	 * @param nickName the nickName of the selected friend
	 * @param globalScore the globalScore of the selected friend
	 * @param status the current status of the selected friend
	 */
	public void setFriendInfo(String nickName, long globalScore, String status) {
		if(userFriendsFrame!=null)
			this.userFriendsFrame.setFriendInfo(nickName, globalScore, status);
	}
	
	/**
	 * Creates a question Dialog with a message
	 * @param message the message to ask in the dialog
	 * @return 1 if the answer is 'OK', 2 in another case
	 */
	private int generateQuestionDialog(String message) {
		return JOptionPane.showConfirmDialog(this, message)==JOptionPane.OK_OPTION?1:2;
	}
	
	/**
	 * Calls the generateQuestionDialog of this class, and lets confirm the delete account option
	 * @return 1 = OK, 2 = NO
	 */
	public int generateDeleteAccountDialog() {
		return this.generateQuestionDialog("Esta seguro que desea eliminar esta cuenta?"
				+ "\nLos cambios no podrán ser revertidos");
	}
	
	/**
	 * Shows a question dialog for the close sesion option
	 * @return 1 = OK, 2 = NO
	 */
	public int generateCloseSesionDialog() {
		return this.generateQuestionDialog("Esta seguro que desea cerrar la sesión actual?");
	}
	
	/**
	 * Calls the delete friend response method of the user friends frame
	 * @return 1 = delete friend / 2 = don´t delete it
	 */
	public int deleteFriendResponse() {
		return this.userFriendsFrame.deleteFriendResponse();
	}
	
	/**
	 * Calls the addNewFriend method of the user friends frame
	 * @return the nickName of the friend to be added
	 */
	public String addNewFriend() {
		return this.userFriendsFrame.addNewFriend();
	}
	
	/**
	 * Calls the generateFriendNotFoundMessage to the userFriendsFrame class
	 */
	public void generateFriendNotFoundMessage() {
		this.userFriendsFrame.generateFriendNotFoundMessage();
	}
	
	/**
	 * Creates a public lobby frame 
	 * @param actionListener an actionListener object
	 * @param currentSeconds the current wait seconds of the lobby
	 * @param roomPlayers The players that log in the lobby
	 */
	public void createPublicLobbyFrame(ActionListener actionListener, int currentSeconds, LinkedList<String> roomPlayers) {
<<<<<<< HEAD
		this.publicLobby = new PublicLobbyFrame(actionListener, currentSeconds, roomPlayers);		
=======
		this.publicLobby = new PublicLobbyFrame(actionListener, currentSeconds, roomPlayers);	
	}
	public void updateLobbyTime(int seconds) {
		publicLobby.updateTime(seconds);
>>>>>>> branch 'master' of https://github.com/DAlejandroB/Pinturillo.git
	}
	/**
	 * Calls the generateScoreTable method of the gameFrame
	 * @param nickNames a linkedlist of the nickNames of the round players
	 * @param scores a linkedlist of the scores of the round players
	 */
	public void generateScoreTable(LinkedList<String> nickNames, LinkedList<Integer> scores) {
		this.gameFrame.generateScoreTable(nickNames, scores);
	}
	
	/**
	 * Creates a frame for the pinturillo game
	 * @param actionListener an actionListener object
	 * @param isDrawer indicates if the player is the drawer
	 */
	public void createGameFrame(ActionListener actionListener, boolean isDrawer) {
		this.gameFrame = new GameFrame(actionListener, isDrawer);
	}
	
	/**
	 * Calls the getSelectedFriend method of the userFriendsFrame
	 * @return a string with the selected friend nickName / or " " if isn´t selected
	 */
	public String getSelectedFriend() {
		if(userFriendsFrame==null) 
			return " ";
		return this.userFriendsFrame.getSelectedFriend();
	}
	
	/**
	 * Creates a dialog that lets modify the player information
	 * @param actionListener an actionListener object
	 * @param nickName the nickName of the local player
	 * @param iconPath the icon path of the local player
	 */
	public void createModInfoDialog(ActionListener actionListener, String nickName, String iconPath) {
		this.modInfoDialog = new ModifyInfoDialog(this, actionListener, nickName, iconPath);
		this.modInfoDialog.setVisible(true);
	}
	
	/**
	 * Gets the entered data in the new account dialog. Then, saves the data in a string array 
	 * with the next order:
	 * 1. NickName 2. Password 3. Icon path
	 * @return a string array with the new user information
	 */
	public String[] getModAccountData() {
		String data[] = null;
		if(this.modInfoDialog!=null) {
			String password = this.modInfoDialog.getPassword();
			if(!password.equals("contraseña_no_valida")&&!password.equals("")) {
				String nickName = this.modInfoDialog.getNickName();
				String iconPath = this.modInfoDialog.getIconPath();
				if(!nickName.equals("")) {
					data = new String[3];
					data[0] = nickName;
					data[1] = password;
					data[2] = iconPath;
				}
			}
		}
		modInfoDialog.dispose();
		return data;
	}
	
	/**
	 * Calls the selectAWord method of the gameFrame
	 * @param word1 the first word
	 * @param word2 the second word
	 * @param word3 the third word
	 * @return the selected word
	 */
	public String selectAWord(String word1, String word2, String word3) {
		return this.gameFrame.selectAWord(word1, word2, word3);
	}
	
	/**
	 * Show a dialog to confirm that a player wants to join to a private game room
	 * @return 1 if the answer is OK, 2 in another case
	 */
	public int generateInvitationToPrivateRoomDialog() {
		return this.generateQuestionDialog("Has sido invitado a una sala privada.\nDeseas entrar?");
	}
	
	/**
	 * Creates a dialog, for number input for a new private room id
	 * @return the id of the created private room
	 */
	public long createPrivateRoom() {
		long idPrivateRoom = -1;
		String answer = JOptionPane.showInputDialog("Ingrese un id para la sala privada: ");	
		if(answer!="") {
			try {
				idPrivateRoom = Long.valueOf(answer);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Solo números son válidos");
			}			
		}else JOptionPane.showMessageDialog(this, "Campo vacío");
		return idPrivateRoom;
	}
	
	/**
	 * Show a dialog with a room not found message
	 */
	public void roomNotFoundDialog() {
		JOptionPane.showMessageDialog(this, "Error 404: Sala no encontrada");
	}
	
	/**
	 * Shows a dialog to enter the id of a private room, for join into
	 * @return the private room id
	 */
	public long enterToPrivateRoom() {
		long idPrivateRoom = -1;
		String answer = JOptionPane.showInputDialog("Ingrese el id de la sala privada: ");
		if(answer!="") {
			try {
				idPrivateRoom = Long.valueOf(answer);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Solo números son válidos");
			}			
		}else JOptionPane.showMessageDialog(this, "Campo vacío");
		return idPrivateRoom;
	}
	
	/**
	 * Closes the modify information dialog
	 */
	public void closeModDialog() {
		this.modInfoDialog.dispose();
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
