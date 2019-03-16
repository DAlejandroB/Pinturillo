package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.edu.uptc.prg3.controller.Control;

public class LoginFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TitleImagePanel panelImg;
	private JPanel panelLoginInf;
	private JPanel panelOptions;
	private JLabel lblNickName, lblPassword, lblCreateAccount;
	private JTextField txtNickName;
	private JPasswordField txtPassword;
	private JButton btnEnter, btnInfo, btnHelp, btnCreateAccount;
	
	public LoginFrame(ActionListener actionListener) {
		super("Pinturillo Inicio de Sesion");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500,400);
		this.setLayout(new BorderLayout());
		panelImg = new TitleImagePanel();
		this.add(panelImg, BorderLayout.NORTH);
		
		panelLoginInf = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelLoginInf.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				"Ingreso de información de la cuenta", 
				0, 1, new Font("Italic", 2, 15), Color.BLACK));
		this.add(panelLoginInf, BorderLayout.CENTER);		
		lblNickName = new JLabel("Nombre de usuario");
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelLoginInf.add(lblNickName, gbc);
		
		txtNickName = new JTextField();
		txtNickName.setPreferredSize(new Dimension(150,30));
		gbc.gridx=1;
		panelLoginInf.add(txtNickName, gbc);
		
		lblPassword = new JLabel("Contraseña");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelLoginInf.add(lblPassword, gbc);
		
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(150,30));	
		gbc.gridx=1;
		panelLoginInf.add(txtPassword, gbc);
		
		btnEnter = new JButton("Iniciar Sesion");
		btnEnter.addActionListener(actionListener);
		btnEnter.setActionCommand("iniciar_sesion");
		btnEnter.setBackground(new Color(247, 98, 85));
		gbc.gridy = 2;
		panelLoginInf.add(btnEnter, gbc);
		panelLoginInf.setBackground(Color.LIGHT_GRAY);
		
		panelOptions = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon("data/Info_Icon.png");
		btnInfo = new JButton(icon);
		btnInfo.addActionListener(actionListener);
		btnInfo.setActionCommand("dar_informacion");
		btnInfo.setPreferredSize(new Dimension(48,48));
		panelOptions.add(btnInfo, BorderLayout.EAST);
		
		
		lblCreateAccount = new JLabel("No tienes una cuenta?");
		lblCreateAccount.setHorizontalAlignment(JLabel.CENTER);
		panelOptions.add(lblCreateAccount, BorderLayout.NORTH);
		
		btnCreateAccount = new JButton("Crear Cuenta");
		btnCreateAccount.addActionListener(actionListener);
		btnCreateAccount.setActionCommand("crear_cuenta");
		btnCreateAccount.setBackground(new Color(247, 98, 85));
		panelOptions.add(btnCreateAccount, BorderLayout.CENTER);
		
		icon = new ImageIcon("data/Help_Icon.png");
		btnHelp = new JButton(icon);
		btnHelp.addActionListener(actionListener);
		btnHelp.setActionCommand("ayuda_juego");
		btnHelp.setPreferredSize(new Dimension(48,48));
		panelOptions.add(btnHelp, BorderLayout.WEST);
		this.add(panelOptions, BorderLayout.SOUTH);
		
		this.center();
		this.setVisible(true);
	}	
	
	public void printMessagge(String message) {
		JOptionPane.showMessageDialog( this, message, "error", JOptionPane.ERROR_MESSAGE );
	}
	
	public String getTxtNickName() {
		return txtNickName.getText();
	}

	public String getTxtPassword() {
		return txtPassword.getText();
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
