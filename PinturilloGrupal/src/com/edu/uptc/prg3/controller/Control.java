package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;

public class Control implements ActionListener {
	LoginFrame frame;
	private Comunicator comm;
	/**
	 * Este es solo un objeto de prueba. 
	 */
	public Control() {
		frame = new LoginFrame(this);
		comm = new Comunicator();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("crear_cuenta")) {
			frame.createAccountDialog(this);
		}else if(e.getActionCommand().equals("crear_nueva_cuenta")) {
			String []info = frame.getNewAccountData();
			comm.sendRegisterInfo(info[0], info[1], null);
			if(info!=null) frame.closeCreateAccountDialog();
			//llamar a este metodo frame.getNewAccountData(); verificar si es nulo, no hacer nada
		}else if(e.getActionCommand().equals("iniciar_sesion")) {
			frame.createProfileFrame(this, "Nombre", 50, "./data/icons/default_profile_icon.png");
			comm.sendLoginInfo(frame.getTxtNickName(), frame.getTxtPassword());
			this.frame.dispose();
		}
	}
	
	public static void main(String[] args) {
		Control control = new Control();
	}
	
}
