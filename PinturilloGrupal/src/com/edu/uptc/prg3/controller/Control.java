package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.prg3.view.LoginFrame;

public class Control implements ActionListener {
	
	/**
	 * Este es solo un objeto de prueba. 
	 */
	public Control() {
		LoginFrame frame = new LoginFrame(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("dar_informacion")) {
			
		}
	}
	
	public static void main(String[] args) {
		Control control = new Control();
	}
	
}
