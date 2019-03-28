package com.edu.uptc.prg3.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.edu.uptc.prg3.view.CreateAccountPanel;
import com.edu.uptc.prg3.view.LoginFrame;

public class CreateAccountDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final String NO_VALID_PASSWORD = "contraseña_no_valida";
	private CreateAccountPanel panel;
	
	public CreateAccountDialog(LoginFrame principal, ActionListener actionListener) {
		super(principal,true);
		this.panel = new CreateAccountPanel(actionListener, this);
		this.add(panel, BorderLayout.CENTER);
		this.setTitle("Nueva Cuenta");
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
	 * Gets the nickName of the textField
	 * @return the entered nickName
	 */
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
	 * Verify if the entered passwords in the "Contraseña" and "Repetir contraseña" are equals, 
	 * and returns a string with the entered data 
	 * @return the password, if are equals. In another case, returns the string constant definied in this class
	 */
	public String getPassword() {
		String password = "";
		if(panel.getTxtPassword().equals(panel.getTxtRPassword())&&!panel.getTxtPassword().equals("")) {
			password = panel.getTxtPassword();
		}else {
			if((panel.getTxtPassword().equals(panel.getTxtRPassword())&&panel.getTxtPassword().equals(""))){
				JOptionPane.showMessageDialog( this, "El campo de contraseña esta vacio", 
						"error", JOptionPane.ERROR_MESSAGE );
			}else {	
				password = NO_VALID_PASSWORD;
				JOptionPane.showMessageDialog( this, "Las contraseñas no coinciden", 
					"error", JOptionPane.ERROR_MESSAGE );
			}
		}
		return password;
	}
}
