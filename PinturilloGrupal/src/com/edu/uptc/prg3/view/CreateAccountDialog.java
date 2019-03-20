package com.edu.uptc.prg3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CreateAccountDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final String NO_VALID_PASSWORD = "contraseņa_no_valida";
	private CreateAccountPanel panel;
	
	public CreateAccountDialog(LoginFrame principal, ActionListener actionListener) {
		super(principal,true);
		this.panel = new CreateAccountPanel(actionListener, this);
		this.add(panel, BorderLayout.CENTER);
		this.setTitle("Nueva Cuenta");
		this.center();
		this.pack();
	}
	
	private void center() {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEdge = ( screen.width - getWidth( ) ) / 2;
        int yEdge = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEdge, yEdge );
    }
	
	public String getNickName() {
		String nickName = panel.getTxtNick();
		if(nickName.equals("")) {
			JOptionPane.showMessageDialog( this, "Campo de nombre vacio", 
					"error", JOptionPane.ERROR_MESSAGE );
		}
		return panel.getTxtNick();
	}
	
	public String getIconPath() {
		return panel.getIconPath();
	}
	
	/**
	 * Verifica si la contraseņa ingresada en los campos "Contraseņa" y "Repetir contraseņa"
	 * son iguales, y retorna un string con el valor ingresado
	 * @return la contraseņa, si son iguales. En caso contrario retorna la constante String 
	 * definida en esta clase dialogo
	 */
	public String getPassword() {
		String password = "";
		if(panel.getTxtPassword().equals(panel.getTxtRPassword())&&!panel.getTxtPassword().equals("")) {
			password = panel.getTxtPassword();
		}else {
			if((panel.getTxtPassword().equals(panel.getTxtRPassword())&&panel.getTxtPassword().equals(""))){
				JOptionPane.showMessageDialog( this, "El campo de contraseņa esta vacio", 
						"error", JOptionPane.ERROR_MESSAGE );
			}else {	
				password = NO_VALID_PASSWORD;
				JOptionPane.showMessageDialog( this, "Las contraseņas no coinciden", 
					"error", JOptionPane.ERROR_MESSAGE );
			}
		}
		return password;
	}
	
	
	
}
