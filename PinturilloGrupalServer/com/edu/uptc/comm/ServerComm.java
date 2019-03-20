package com.edu.uptc.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerComm {
	private static final int PORT = 10345;
	//Clase para recibir y enviar los datos como servidor
	private ServerSocket serverSocket;
	
	public ServerComm() {
		try {
			serverSocket =  new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String readMessage() {
		Socket s = null;
		String message = "";
		try {
			s = serverSocket.accept();
			//TODO eliminar codigo innecesario
			DataInputStream dis = new DataInputStream(s.getInputStream());
			message = dis.readUTF();
			dis.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	public void sendMessage(String message) {
		Socket s = null;
		try {
			s = serverSocket.accept();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(message);
			s.close();
			dos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
