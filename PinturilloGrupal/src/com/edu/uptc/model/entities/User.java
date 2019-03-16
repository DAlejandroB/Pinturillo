package com.edu.uptc.model.entities;

import com.edu.uptc.structure.LinkedList;

public class User {

	protected String nickName;
	protected String password;
	protected LinkedList<User> friends;
	protected long globalScore;

	public User(String nickName, String password) {
		super();
		this.nickName = nickName;
		this.password = password;
	}

	public void addFriend(User friend) {
		friends.add(friend);
	}

	public void deleteFriend(User friend) {
		friends.delete(friend);
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getGlobalScore() {
		return globalScore;
	}

	public void setGlobalScore(long globalScore) {
		this.globalScore = globalScore;
	}

}
