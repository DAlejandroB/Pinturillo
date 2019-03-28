package com.edu.uptc.comm;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.edu.uptc.structure.LinkedList;


public class Comunicator {
	private static final int PORT = 10345;
//	private static final String SERVER_IP = "192.168.0.7"; diego
	private static final String SERVER_IP = "192.168.0.41";
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	
	public Comunicator(){
		try {
			socket = new Socket(SERVER_IP, PORT);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
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
			message =  dis.readUTF();
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	public void sendRegisterInfo(String nickname, String password, BufferedImage image) {
		sendMessage("/rgs" + nickname + "," + password);
		//TODO crear metodo para enviar no solo nombre y contraseña, pero tambien imagen
	}
	public void sendChatMessage(String message) {
		sendMessage("/msg" + message);
	}
}
