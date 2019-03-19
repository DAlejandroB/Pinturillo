package com.edu.uptc.controller;


import java.util.LinkedList;

import com.edu.uptc.comm.ServerComm;
import com.edu.uptc.model.entities.User;
import com.edu.uptc.model.logic.Game;
import com.edu.uptc.structure.GraphWList;
import com.edu.uptc.structure.Vertex;

public class Controller {
	private GraphWList<User,Boolean> users;
	private LinkedList<User> connectedUsers;
	private ServerComm serverComm;
	private Game game;
	
	
	/*
	 * Delegar a la persistencia la carga del grafo
	 * Una vez leido el grafo, inicializarlo
	 * Metodos de busqueda dentro del grafo por criterios "nickname" y "password"
	 * 
	 * */
	public Controller() {
		serverComm = new ServerComm();
		while(true) {
			String incomingMessage = serverComm.readMessage();
			String messageType = incomingMessage.substring(0, 3);
			switch (messageType) {
			case "/lgn":
				//TODO
				break;
			case "/rgs":
				String[] newUserInfo = incomingMessage.substring(4,incomingMessage.length()-1).split("/");
				this.registerUser(newUserInfo[0], newUserInfo[1]);
				break;
			case "/msg":
				//TODO
				break;
			default:
				break;
			}
		}
	}
	private void connectUser(String username, String userpassword) {
		
	}
	private void registerUser(String nickName, String password) {
		User newUser = new User(nickName, password);
		users.add(new Vertex<User, Boolean>(newUser));
	}
}
