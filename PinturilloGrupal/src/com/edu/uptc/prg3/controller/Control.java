package com.edu.uptc.prg3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.edu.uptc.comm.Comunicator;
import com.edu.uptc.prg3.view.LoginFrame;
import com.edu.uptc.prg3.view.PinturilloProfileFrame;
import com.edu.uptc.structure.LinkedList;

public class Control implements ActionListener {
	private LoginFrame logFrame;
	private PinturilloProfileFrame profileFrame;
	private Comunicator comm;
	/**
	 * Este es solo un objeto de prueba. 
	 */
	public Control() {
		logFrame = new LoginFrame(this);
		comm = new Comunicator();
	}

	/**
	 * Comandos de la vista
	 * mostrar_puntuaciones
	 * mensaje_enviado
	 * reportar
	 * crear_nueva_cuenta
	 * iniciar_sesion
	 * crear_cuenta
	 * entrar_sala_publica
	 * entrar_sala_privada
	 * crear_sala_privada
	 * ver_amigos
	 * modificar_info
	 * eliminar_cuenta
	 * cerrar_sesion
	 * abandonar_sala_publica
	 * amigo_seleccionado //muestra info del amigo seleccionado
	 * borrar_amigo
	 * añadir_amigo
	 * 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("crear_cuenta")) {
			logFrame.createAccountDialog(this);
		}else if(e.getActionCommand().equals("crear_nueva_cuenta")) {
			String []info = logFrame.getNewAccountData();
			if(info!=null) {
				comm.sendRegisterInfo(info[0], info[1], null);
				String operationStatus = comm.recieveMessage();
				if(operationStatus.substring(0, 3) == "/scc") {
					//TODO comunicar la creacion del usuario al cliente
				}else if(operationStatus.substring(0, 3) == "/wrn") {
					logFrame.printErrorMessagge("El nombre de usuario ya se encuentra en uso.");
					//TODO comunicar que el nombre de usuario ya está ocupado
				}else logFrame.closeCreateAccountDialog();
			}
			//llamar a este metodo frame.getNewAccountData(); verificar si es nulo, no hacer nada
		}else if(e.getActionCommand().equals("iniciar_sesion")) {
			//encontrar usuario usando frame.getTxtNickName(), y comparar la contraseña registrada con la 
			//ingresada en frame.getTxtPassword(), extraer la puntuación actual del usuario encontrado
			//si el usuario no es encontrado imprimir un mensaje con frame.printMessagge("No se ha encontrado al usuario");
			this.profileFrame = new PinturilloProfileFrame(this, logFrame.getTxtNickName(), 50, "./data/icons/default_profile_icon.png");
//			comm.sendLoginInfo(frame.getTxtNickName(), frame.getTxtPassword()); descomentarear
			this.logFrame.dispose();
		}else if(e.getActionCommand().equals("ver_amigos")) {
			System.out.println("hola");
			this.profileFrame.createUserFriendsFrame(this, "nickName", new LinkedList<String>());
		}else if(e.getActionCommand().equals("amigo_seleccionado")) {
			String selectedFriend = this.profileFrame.getSelectedFriend();
			//encuentra al usuario seleccionado, y extrae su información, para plasmarla en un textPane
			this.profileFrame.setFriendInfo(selectedFriend, 50, "Online");
		}else if(e.getActionCommand().equals("modificar_info")) {
			this.profileFrame.createModInfoDialog(this, "user", "ruta_imagen");
			
		}else if(e.getActionCommand().equals("eliminar_cuenta")) {
			if(this.profileFrame.generateDeleteAccountDialog()==1) {
				//metodo de eliminar cuenta
			}
		}else if(e.getActionCommand().equals("cerrar_sesion")) {
			if(this.profileFrame.generateCloseSesionDialog()==1){
				//metodo para cerrar sesion
			}
		}else if(e.getActionCommand().equals("entrar_sala_publica")) {
			this.profileFrame.createPublicLobbyFrame(this, 60, new LinkedList<String>());
			this.profileFrame.createGameFrame(this, true);
		}else if(e.getActionCommand().equals("entrar_sala_privada")) {
			long idRoom = this.profileFrame.enterToPrivateRoom();
			if(idRoom!=-1) {
				//si no se encuentra la sala..
				this.profileFrame.roomNotFoundDialog();
			}
		}else if(e.getActionCommand().equals("crear_sala_privada")) {
			long idRoom = this.profileFrame.createPrivateRoom();
			if(idRoom!=-1) {
				//metodo de crear sala privada
			}
		}else if(e.getActionCommand().equals("mostrar_puntuaciones")) {
			this.profileFrame.generateScoreTable(new LinkedList<String> (), new LinkedList<Integer>());
		}else if(e.getActionCommand().equals("borrar_amigo")) {
			String nickNameFriend = profileFrame.getSelectedFriend();
			if(nickNameFriend!=""&&this.profileFrame.deleteFriendResponse()==1) {
				//metodo para eliminar un amigo por su nickName
			}	
		}else if(e.getActionCommand().equals("añadir_amigo")) {
			String friend = this.profileFrame.addNewFriend();
			//metodo que busca y añade al amigo a la lista del usuario local
			//si no lo encuentra
			this.profileFrame.generateFriendNotFoundMessage();
		}else if(e.getActionCommand().equals("modificar_cuenta")) {
			String info[] = this.profileFrame.getModAccountData();
			if(info!=null) {
				this.profileFrame.closeModDialog();
			}
		}
	}
	
	public static void main(String[] args) {
		Control control = new Control();
	}
	
}
