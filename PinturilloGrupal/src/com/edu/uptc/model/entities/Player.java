package com.edu.uptc.model.entities;

import com.edu.uptc.structure.LinkedList;

public class Player extends User{
	
	public Player(String nickName, String password, LinkedList<User> friends, long globalScore) {
		super(nickName, password, friends, globalScore);
	}

	private PlayerType typeMode;
	protected int score;
	private Byte strikeCount;
	
	private void switchType() {
		if(typeMode.equals(PlayerType.VIEWER)) {
			typeMode = PlayerType.DRAWER;
		}else {
			typeMode = PlayerType.VIEWER;
		}
	}

}
