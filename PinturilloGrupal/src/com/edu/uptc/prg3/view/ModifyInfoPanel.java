package com.edu.uptc.prg3.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.edu.uptc.prg3.view.dialogs.ModifyInfoDialog;

public class ModifyInfoPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblNickName, lblPassword, lblRPassword, lblIcon;
	private JTextField txtNick, txtPassword, txtRPassword;
	private JButton btnSelectIcon, btnModify, btnCancel;
	private JLabel imageIcon;
	private String iconPath;
	private ModifyInfoDialog dialog;
	
	public ModifyInfoPanel(ActionListener actionListener, ModifyInfoDialog dialog, String nickName, String iconPath) {
		this.dialog = dialog;
		this.setBackground(new Color(242,129,75));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		lblNickName = new JLabel("Nombre de Usuario");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(lblNickName, gbc);
		
		txtNick = new JTextField();
		txtNick.setPreferredSize(new Dimension(150,30));
		txtNick.setText(nickName);
		gbc.gridx=1;
		this.add(txtNick, gbc);
		
		lblPassword = new JLabel("Contraseņa");
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(lblPassword, gbc);
		
		txtPassword = new JTextField();
		txtPassword.setPreferredSize(new Dimension(150,30));
		gbc.gridx=1;
		this.add(txtPassword, gbc);
		
		lblRPassword = new JLabel("Repetir contraseņa");
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(lblRPassword, gbc);
		
		txtRPassword = new JTextField();
		txtRPassword.setPreferredSize(new Dimension(150,30));
		gbc.gridx=1;
		this.add(txtRPassword, gbc);
		
		this.iconPath = iconPath;
		
		lblIcon = new JLabel("Selecciona un icono");
		gbc.gridx=0;
		gbc.gridy=3;
		this.add(lblIcon, gbc);
		
		btnSelectIcon = new JButton("Iconos");
		btnSelectIcon.addActionListener(this);
		btnSelectIcon.setActionCommand("seleccion_icono");
		gbc.gridx=1;
		this.add(btnSelectIcon, gbc);
		
		imageIcon = new JLabel(new ImageIcon(iconPath));
		imageIcon.setPreferredSize(new Dimension(100,100));
		gbc.gridx=1;
		gbc.gridy=4;
		this.add(imageIcon, gbc);
		
		btnModify = new JButton("Modificar cuenta");
		btnModify.addActionListener(actionListener);
		btnModify.setActionCommand("modificar_cuenta");
		gbc.gridx=0;
		gbc.gridy=5;
		this.add(btnModify, gbc);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand("cancelar");
		gbc.gridx=1;
		this.add(btnCancel, gbc);	
		
		this.setVisible(true);
		this.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
	}
	
	
	/**
	 * Opens a JFileChooser object, and let chooses an icon of the icons Folder
	 * @return the selected icon path
	 */
	public String getPath() {
    	String path="";
    	JFileChooser fc = new JFileChooser(this.iconPath);
    	fc.setDialogTitle("Elegir icono de usuario");
    	int resultado = fc.showOpenDialog(this );
        if(resultado == JFileChooser.APPROVE_OPTION)
        path = fc.getSelectedFile().getAbsolutePath();
        return path;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("seleccion_icono")) {
			this.iconPath = this.getPath();
			this.imageIcon.setIcon((new ImageIcon(iconPath)));
			this.dialog.pack();
		}else if(e.getActionCommand().equals("cancelar")) {
			this.dialog.dispose();
		}		
	}

	public String getTxtNick() {
		return txtNick.getText();
	}


	public String getTxtPassword() {
		return txtPassword.getText();
	}


	public String getTxtRPassword() {
		return txtRPassword.getText();
	}


	public String getIconPath() {
		return iconPath;
	}
}
