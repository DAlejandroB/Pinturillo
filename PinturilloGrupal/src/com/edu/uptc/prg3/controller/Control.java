package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.structure.LinkedList;

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
			String []info = frame.getNewAccountData();//verificar si el nickName no esta ya en uso!!
			comm.sendRegisterInfo(info[0], info[1], null);
			String operationStatus = comm.recieveMessage();
			if(operationStatus.substring(0, 3) == "/scc") {
				//TODO comunicar la creacion del usuario al cliente
			}else if(operationStatus.substring(0, 3) == "/wrn") {
				//TODO comunicar que el nombre de usuario ya está ocupado
			}
			if(info!=null) frame.closeCreateAccountDialog();
			//llamar a este metodo frame.getNewAccountData(); verificar si es nulo, no hacer nada
		}else if(e.getActionCommand().equals("iniciar_sesion")) {
			//encontrar usuario usando frame.getTxtNickName(), y comparar la contraseña registrada con la 
			//ingresada en frame.getTxtPassword(), extraer la puntuación actual del usuario encontrado
			//si el usuario no es encontrado imprimir un mensaje con frame.printMessagge("No se ha encontrado al usuario");
			frame.createProfileFrame(this, frame.getTxtNickName(), 50, "./data/icons/default_profile_icon.png");
//			comm.sendLoginInfo(frame.getTxtNickName(), frame.getTxtPassword()); descomentarear
			this.frame.dispose();
		}else if(e.getActionCommand().equals("ver_amigos")) {
			
		}else if(e.getActionCommand().equals("modificar_info")) {
			
		}else if(e.getActionCommand().equals("eliminar_cuenta")) {
			
		}else if(e.getActionCommand().equals("cerrar_sesion")) {
			
		}
	}
	
	public static void main(String[] args) {
		Control control = new Control();
	}
	
}
