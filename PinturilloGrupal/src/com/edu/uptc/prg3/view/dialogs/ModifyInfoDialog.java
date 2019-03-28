package com.edu.uptc.prg3.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.edu.uptc.prg3.view.CreateAccountPanel;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.prg3.view.ModifyInfoPanel;
import com.edu.uptc.prg3.view.PinturilloProfileFrame;

public class ModifyInfoDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private static final String NO_VALID_PASSWORD = "contrase�a_no_valida";
	private ModifyInfoPanel panel;
	
	public ModifyInfoDialog(PinturilloProfileFrame principal, ActionListener actionListener, 
											String nickName, String iconPath) {
		super(principal,true);
		this.panel = new ModifyInfoPanel(actionListener, this, nickName, iconPath);
		this.add(panel, BorderLayout.CENTER);
		this.setTitle("Modificar cuenta");
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
	 * Verify if the entered passwords in the "Contrase�a" and "Repetir contrase�a" are equals, 
	 * and returns a string with the entered data 
	 * @return the password, if are equals. In another case, returns the string constant definied in this class
	 */
	public String getPassword() {
		String password = "";
		if(panel.getTxtPassword().equals(panel.getTxtRPassword())&&!panel.getTxtPassword().equals("")) {
			password = panel.getTxtPassword();
		}else {
			if((panel.getTxtPassword().equals(panel.getTxtRPassword())&&panel.getTxtPassword().equals(""))){
				JOptionPane.showMessageDialog( this, "El campo de contrase�a esta vacio", 
						"error", JOptionPane.ERROR_MESSAGE );
			}else {	
				password = NO_VALID_PASSWORD;
				JOptionPane.showMessageDialog( this, "Las contrase�as no coinciden", 
					"error", JOptionPane.ERROR_MESSAGE );
			}
		}
		return password;
	}
}
