package com.edu.uptc.model.comm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import sun.util.resources.cldr.ur.CurrencyNames_ur;

public class ServerComm implements Runnable{
	
	private static int PORT;
	private ServerSocket serverSocket;
	private String currentWorldState;
	public ServerComm() {
		//Inicializacion del servidor en el puerto
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}
}

class ClientHandler extends Thread{
	private DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
	private DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
	private final DataInputStream dis;
	private final DataOutputStream dos;
	private final Socket socket;
	
	public ClientHandler(Socket socket, DataInputStream dis, DataOutputStream dos)  { 
        this.socket = socket; 
        this.dis = dis; 
        this.dos = dos; 
    }
	
	public void run() {
		String recieved;
		String toreturn;
		while(true) {
			try {
				dis.readUTF();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
