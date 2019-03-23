package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.prg3.view.PinturilloProfileFrame;
import com.edu.uptc.structure.LinkedList;
import com.google.gson.Gson;

public class Controller implements ActionListener{
	private LoginFrame loginFrame;
	private Comunicator comm;
	private PinturilloProfileFrame ppFrame;
	private Gson gsonParser;
	
	public Controller(){
		gsonParser = new Gson();
		loginFrame = new LoginFrame(this);
		comm = new Comunicator();
	}
	private void sendNewAccountInfo(String[] accountInfo) {
		if(accountInfo != null) {
			comm.sendRegisterInfo(accountInfo[0], accountInfo[1], null);
			String recieved = comm.recieveMessage();
			switch(recieved) {
			case "/scc":
				loginFrame.closeCreateAccountDialog();
				loginFrame.printMessagge("La cuenta se ha creado exitosamente");
				break;
			case "/wrn":
				loginFrame.printMessagge("El nombre de usuario ya ha sido seleccionado, por favor ingrese otro");
				break;
			}
		}
	}
	private LinkedList<String> friendList(){
		String list = comm.requestFriendsList();
		return gsonParser.fromJson(list, LinkedList.class);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case "crear_cuenta":
			loginFrame.createAccountDialog(this);
			break;
		case "crear_nueva_cuenta":
			String[] info = loginFrame.getNewAccountData();
			if(info!=null) sendNewAccountInfo(info);			
			break;
		case "iniciar_sesion":
			comm.sendLoginInfo(loginFrame.getTxtNickName(), loginFrame.getTxtPassword());
			break;
		case "ver_amigos":
			ppFrame.createUserFriendsFrame(this, "User", friendList());
			//TODO enviar el nombre del usuario
			break;
		case "amigo_seleccionado":
			break;
		case "modificar_info":
			break;
		case "eliminar_cuenta":
			break;
		case "cerrar_sesion":
			break;
		}
	}
}
