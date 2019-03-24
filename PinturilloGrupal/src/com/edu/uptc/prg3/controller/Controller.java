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
	private String nickName;
	public Controller(){
		gsonParser = new Gson();
		init();
		comm = new Comunicator();
	}
	private void init() {
		loginFrame = new LoginFrame(this);
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
		String list = comm.requestFriendsList(this.nickName);
		return gsonParser.fromJson(list, LinkedList.class);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case "crear_cuenta"://listo
			loginFrame.createAccountDialog(this);
			break;
		case "crear_nueva_cuenta"://listo
				this.register();
				loginFrame.printMessagge("Cuenta registrada exitosamente");
			break;
		case "iniciar_sesion":
			this.nickName = loginFrame.getTxtNickName();
			if(login())
				ppFrame = new PinturilloProfileFrame(this, nickName, 0, "");
			else 
				loginFrame.printMessagge("Cuenta no registrada o contraseña incorrecta");
			break;
		case "ver_amigos":
			ppFrame.createUserFriendsFrame(this, "User", friendList());
			//TODO enviar el nombre del usuario
			break;
		case "borrar_amigo":
			String nickNameFriend = ppFrame.getSelectedFriend();
			if(this.ppFrame.deleteFriendResponse()==1) {
				//metodo para eliminar un amigo por su nickName
			}			
			break;
		case "amigo_seleccionado":
			String selectedFriend = ppFrame.getSelectedFriend();
			//metodo que busca un usuario por su nickName, y retorna su nickName, puntaje global y status
			ppFrame.setFriendInfo(selectedFriend, 0, "online"); //en 0 el puntaje y en "online" el status
			break;
		case "modificar_info":
			break;
		case "eliminar_cuenta":
			break;
		case "crear_sala_privada":
			comm.sendMessage("/crtPrivate");
			break;
		case "crear_sala_publica":
			comm.sendMessage("/crtPublic");
			break;
		case "cerrar_sesion":
			comm.sendMessage("/lgo" + nickName);
			break;
		}
	}
	private boolean login() {
		comm.sendLoginInfo(loginFrame.getTxtNickName(), loginFrame.getTxtPassword());
		String recieved = comm.recieveMessage();
		return (recieved == "lgs");
	}
	private void register() {
		String[] info = loginFrame.getNewAccountData();
		if(info!=null) sendNewAccountInfo(info);		
	}
}
