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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.edu.uptc.prg3.view.dialogs.CreateAccountDialog;

public class LoginFrame extends JFrame implements ActionListener{
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
	private CreateAccountDialog dialog;
	private PinturilloProfileFrame profileFrame;
	
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
		panelLoginInf.setBackground(new Color(23, 157, 166));
		
		panelOptions = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon("data/Info_Icon.png");
		btnInfo = new JButton(icon);
		btnInfo.addActionListener(this);
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
		btnHelp.addActionListener(this);
		btnHelp.setActionCommand("ayuda_juego");
		btnHelp.setPreferredSize(new Dimension(48,48));
		panelOptions.add(btnHelp, BorderLayout.WEST);
		this.add(panelOptions, BorderLayout.SOUTH);
		
		this.center();
		this.setResizable(false);
		this.setVisible(true);
	}	
	
	public void printMessagge(String message) {
		JOptionPane.showMessageDialog( this, message, "error", JOptionPane.ERROR_MESSAGE );
	}
	
	public String getTxtNickName() {
		return txtNickName.getText();
	}
	
	/**
	 * Obtiene el texto escrito en el campo de contraseña.  
	 * Luego elimina el contenido del componente por seguridad
	 * Nota: No se usa getText() para objetos JPasswordField, es obsoleto.
	 * @return un string con la contraseña ingresada 
	 */
	public String getTxtPassword() {
		String pass = String.valueOf(txtPassword.getPassword());
		txtPassword.setText("");
		return pass;
	}
	
	public void closeCreateAccountDialog() {
		if(this.dialog!=null) this.dialog.dispose();
	}
	
	public void createProfileFrame(ActionListener actionListener, String nickName, long globalScore, String iconPath) {
		this.profileFrame = new PinturilloProfileFrame(actionListener, nickName, globalScore, iconPath);
	}
	
	public void createAccountDialog(ActionListener actionListener) {
		this.dialog = new CreateAccountDialog(this, actionListener);
		this.dialog.setVisible(true);
	}
	
	/**
	 * Método que obtiene los datos ingresados en el dialogo de nueva cuenta.
	 * Almacena los datos en un arreglo de strings con el siguiente orden: 
	 * 1. NickName 2. Password 3. Ruta del icono
	 * @return un arreglo de strings con la informacion del nuevo usuario.
	 */
	public String[] getNewAccountData() {
		String data[] = null;
		if(this.dialog!=null) {
			String password = this.dialog.getPassword();
			if(!password.equals("contraseña_no_valida")&&!password.equals("")) {
				String nickName = this.dialog.getNickName();
				String iconPath = this.dialog.getIconPath();
				if(!nickName.equals("")) {
					data = new String[3];
					data[0] = nickName;
					data[1] = password;
					data[2] = iconPath;
				}
			}
		}
		return data;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("dar_informacion")) {
			System.out.println("info");
		}else if(e.getActionCommand().equals("ayuda_juego")) {
			System.out.println("ayuda");
		}
	}
	
}
