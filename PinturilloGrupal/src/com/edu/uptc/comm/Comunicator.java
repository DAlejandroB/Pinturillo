package com.edu.uptc.comm;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Comunicator {
	private static final int PORT = 13358;
	private static final String SERVER_IP = "172.16.0.114";
	private DataInputStream dis;
	private DataInputStream disCommands;
	private DataOutputStream dos;
	private Socket socket;
	public Comunicator(){
		try {
			socket = new Socket(SERVER_IP, PORT);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			disCommands = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String requestFriendsList(String nickname){
		sendMessage("/rql" + nickname);
		String message = this.recieveMessage();
		return message;
	}
	
	public void sendMessage(String message) {
		try {
			dos.writeUTF(message);
		} catch (IOException e) {
			System.out.println("Ha ocurrido un problema con el envio de informacion");
			e.printStackTrace();
		}
	}
	public void sendLoginInfo(String nickname, String password) {
		String loginInfo = "/lgn" + nickname + "," + password;
		sendMessage(loginInfo);
	}
	public int recieveNumber() {
		int r = 0;
		try {
			r = dis.readInt();
		}catch (Exception e) {}
		return r;
	}
	public String recieveMessage() {
		String message = "";
		try {
			message = dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	public void sendRegisterInfo(String nickname, String password, BufferedImage image) {
		sendMessage("/rgs" + nickname + "," + password);
		//TODO crear metodo para enviar no solo nombre y contraseņa, pero tambien imagen
	}
	public void sendChatMessage(String message) {
		sendMessage("/msg" + message);
	}
}
