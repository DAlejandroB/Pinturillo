package com.edu.uptc.controller;


import com.edu.uptc.model.entities.User;
import com.edu.uptc.model.logic.Game;
import com.edu.uptc.structure.GraphWList;
import com.edu.uptc.structure.Vertex;

public class Controller {
	private GraphWList<User,Boolean> users;
	private Game game;
	
	public Controller() {
		
	}
	
	public void registerUser(String nickName, String password) {
		User newUser = new User(nickName, password);
		users.add(new Vertex<User, Boolean>(newUser));
	}
}
