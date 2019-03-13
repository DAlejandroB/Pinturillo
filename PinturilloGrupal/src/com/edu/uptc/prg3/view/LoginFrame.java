package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.edu.uptc.prg3.controller.Control;

public class LoginFrame extends JFrame{
	private TitleImagePanel panelImg;
	private JPanel panelLoginInf;
	private JPanel panelOptions;
	private JLabel lblNickName, lblPassword, lblCreateAccount;
	private JTextField txtNickName, txtPassword;
	private JButton btnEnter, btnInfo, btnHelp, btnCreateAccount;
	
	public LoginFrame(Control control) {
		super("Pinturillo Inicio de Sesion");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,400);
		this.setLayout(new BorderLayout());
		panelImg = new TitleImagePanel();
		this.add(panelImg, BorderLayout.NORTH);
		
		panelLoginInf = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		lblNickName = new JLabel("Nombre de usuario");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelLoginInf.add(lblNickName, gbc);
		
		txtNickName = new JTextField();
		txtNickName.setPreferredSize(new Dimension(150,50));
		gbc.gridx=1;
		panelLoginInf.add(txtNickName, gbc);
		
		lblPassword = new JLabel("Contraseña");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelLoginInf.add(lblPassword, gbc);
		
		txtPassword = new JTextField();
		txtPassword.setPreferredSize(new Dimension(100,50));
		gbc.gridx=1;
		panelLoginInf.add(txtPassword, gbc);
		
		btnEnter = new JButton("Iniciar Sesion");
		btnEnter.addActionListener(control);
		btnEnter.setActionCommand("iniciar_sesion");
		gbc.gridy = 2;
		panelLoginInf.add(btnEnter, gbc);
		this.add(panelLoginInf, BorderLayout.CENTER);
		
		panelOptions = new JPanel(new GridBagLayout());
		ImageIcon icon = new ImageIcon("data/Info_Icon.png");
		btnInfo = new JButton(icon);
		btnInfo.addActionListener(control);
		btnInfo.setActionCommand("dar_informacion");		
		gbc.gridx=0;
		gbc.gridy=0;
		panelOptions.add(btnInfo, gbc);
		
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
