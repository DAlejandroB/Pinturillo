package edu.uptc.prg3.pinturillo.model.entities;

import com.edu.uptc.structure.LinkedList;

public class User {

	private String nickName;
	private String password;
	private LinkedList<User> friends;
	protected long globalScore;

	public User(String nickName, String password, LinkedList<User> friends, long globalScore) {
		super();
		this.nickName = nickName;
		this.password = password;
		this.friends = friends;
		this.globalScore = globalScore;
	}

	
}
