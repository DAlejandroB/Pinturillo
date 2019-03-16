package com.edu.uptc.model.entities;

import javax.jws.soap.SOAPBinding.Use;

public class Player extends User {

	private PlayerType playerType;
	protected int score;
	private Byte strikeCount;
	
	public Player(User user) {
		super(user.nickName, user.password);
		playerType = PlayerType.VIEWER;
		status = user.status;
	}

	public void switchType() {
		if (playerType.equals(PlayerType.VIEWER)) {
			playerType = PlayerType.DRAWER;
		} else {
			playerType = PlayerType.VIEWER;
		}
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

}
