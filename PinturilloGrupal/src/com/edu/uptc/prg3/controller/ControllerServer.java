package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.prg3.view.PinturilloProfileFrame;
import com.edu.uptc.structure.LinkedList;
import com.google.gson.Gson;

public class ControllerServer implements ActionListener{
	private LoginFrame loginFrame;
	private Comunicator comm;
	private PinturilloProfileFrame ppFrame;
	private Gson gsonParser;
	private String nickName;
	public ControllerServer(){
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
			System.out.println(recieved);
			switch(recieved) {
			case "scc":
				loginFrame.closeCreateAccountDialog();
				loginFrame.printInfoMessage("Cuenta registrada exitosamente");
				break;
			case "wrn":
				loginFrame.printErrorMessagge("El nombre de usuario ya ha sido seleccionado, por favor ingrese otro");
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
		System.out.println(command);
		switch(command) {
		case "crear_cuenta"://listo
			loginFrame.createAccountDialog(this);
			break;
		case "crear_nueva_cuenta"://listo
				this.register();
			break;
		case "iniciar_sesion":
			this.nickName = loginFrame.getTxtNickName();
			if(login()) {
				ppFrame = new PinturilloProfileFrame(this, nickName, 0, "");
				loginFrame.dispose();
			}
			else 
				loginFrame.printErrorMessagge("Cuenta no registrada o contraseña incorrecta");
			break;
		case "ver_amigos":
			ppFrame.createUserFriendsFrame(this, nickName, friendList());
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
			if(createPrivateRoom()) {
				
			}else {
				//TODO imprimir en la vista que el id ya esta siendo usado
			}
			break;
		case "cerrar_sesion":
			comm.sendMessage("/lgo" + nickName);
			this.ppFrame.dispose();
			this.loginFrame = new LoginFrame(this);
			break;
		case "entrar_sala_privada":
			comm.sendMessage("/joi" + Long.toString(ppFrame.enterToPrivateRoom()));
			break;
		}
	}
	private boolean createPrivateRoom() {
		comm.sendMessage("/crt" + nickName + "," + Long.toString(ppFrame.createPrivateRoom()));
		String recieved = comm.recieveMessage();
		return (recieved.equals("scc"));
	}
	private boolean login() {
		comm.sendLoginInfo(loginFrame.getTxtNickName(), loginFrame.getTxtPassword());
		String recieved = comm.recieveMessage();
		return (recieved.equals("scc"));
	}
	private void register() {
		String[] info = loginFrame.getNewAccountData();
		if(info!=null) sendNewAccountInfo(info);		
	}
	
	/**
	 * Ejecutable temporal... pasar a paquete runner
	 * @param args
	 */
	public static void main(String[] args) {
		ControllerServer controller = new ControllerServer();
	}
}
