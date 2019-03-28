package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.prg3.view.PinturilloProfileFrame;
import com.edu.uptc.prg3.view.UserFriendsFrame;
import com.edu.uptc.structure.LinkedList;
import com.google.gson.Gson;

public class ControllerServer implements ActionListener{
	private LoginFrame loginFrame;
	private Comunicator comm;
	private PinturilloProfileFrame ppFrame;
	private String nickName;
	public ControllerServer(){
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
		String[] list = comm.requestFriendsList(this.nickName).split(",");
		LinkedList<String> r = new LinkedList<>();
		for (String string : list) {
			r.add(string);
		}
		return r;
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
				if(deleteFriend(nickNameFriend)) {
					ppFrame.printInfoMessage("Amigo eliminado de su lista de amigos");
				}else
					ppFrame.printErrorMessagge("No se encuentra un usuario con ese nickname");
			}
			break;
		case "amigo_seleccionado":
			String selectedFriend = ppFrame.getSelectedFriend();
			if(selectedFriend!=" "){}
			else {
				ppFrame.setFriendInfo(selectedFriend, 0, "online"); //en 0 el puntaje y en "online" el status
			}break;
		case "modificar_info":
			ppFrame.createModInfoDialog(this, nickName, "");
			break;
		case "modificar_cuenta":
			this.modifyInfo(ppFrame.getModAccountData());
			break;
		case "eliminar_cuenta":
			break;
		case "crear_sala_privada":
			if(createPrivateRoom()) {
				//TODO
			}else {
				ppFrame.printErrorMessagge("La id ya está siendo utilizada, ingrese otra porfavor");
			}
			break;
		case "cerrar_sesion":
			comm.sendMessage("/lgo" + nickName);
			this.ppFrame.dispose();
			this.loginFrame = new LoginFrame(this);
			break;
		case "entrar_sala_privada":
			if(joinPrivateRoom()){
				//TODO
			}else {
				ppFrame.printErrorMessagge("No existe una sala con ese ID, ingrese un id existente");
			}
			break;
		case "entrar_sala_publica":
			if(joinPublicRoom()) {
				int s = readSeconds();
				LinkedList<String> playerList = fillPlayers();
				ppFrame.createPublicLobbyFrame(this, s, playerList);
				Thread counter = new Thread(new Runnable() {
					int i = s;
					@Override
					public void run() {
						while(i > 0) {
							i = comm.recieveNumber();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}
							ppFrame.updateLobbyTime(i);
						}
						initGame();
					}
				});
				counter.start();
			}else {
				ppFrame.printErrorMessagge("Ha ocurrido un error, por favor intentelo de nuevo");
			}
			break;
		case "añadir_amigo":
			if(addNewFriend(ppFrame.addNewFriend())) {
				ppFrame.printInfoMessage("El usuario se ha agregado a la lista de amigos");
			}
		}
	}
	private void initGame() {
		ppFrame.createGameFrame(this, true);
	}
	private void modifyInfo(String[] newFields) {
		String fields = nickName + newFields[0] + ","+ newFields[1]  + ","+ newFields[2];
		comm.sendMessage("/mod" + fields);
	}
	private boolean addNewFriend(String nickName) {
		comm.sendMessage("/add" +this.nickName+ ","+ nickName);
		String recieved = comm.recieveMessage();
		return recieved.equals("scc");
	}
	private boolean deleteFriend(String friendNickname) {
		comm.sendMessage("/dlt" +this.nickName+","+friendNickname);
		String recieved = comm.recieveMessage();
		return recieved.equals("scc");
	}
	private LinkedList<String> fillPlayers() {
		String[] players = comm.recieveMessage().split(",");
		LinkedList<String> playerList = new LinkedList<>();
		for (String nickname : players) {
			playerList.add(nickname);
		}
		return playerList;
	}
	private int readSeconds() {
		int r = -1;
		try {
			r = comm.recieveNumber();
		}catch(NumberFormatException e) {}
		
		return r;
	}
	private boolean joinPublicRoom() {
		comm.sendMessage("/jpu" + nickName);
		String recieved = comm.recieveMessage();
		System.out.println("Conectado a sala");
		return (recieved.equals("scc"));
	}
	private boolean joinPrivateRoom() {
		comm.sendMessage("/jpr" + nickName+"," +  Long.toString(ppFrame.enterToPrivateRoom()));
		String recieved = comm.recieveMessage();
		System.out.println(recieved);
		return(recieved.equals("scc"));
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
	public void updateLobbyTime(int parseInt) {
		ppFrame.updateLobbyTime(parseInt);
	}
}
