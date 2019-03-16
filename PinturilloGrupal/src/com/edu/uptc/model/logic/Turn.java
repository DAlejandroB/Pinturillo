package com.edu.uptc.model.logic;

import com.edu.uptc.model.entities.Player;

public class Turn {

	private Player winner;
	private String word;
	private boolean isAlive;
	private int seconds;

	public Turn(String word, int seconds) {
		super();
		this.word = word;
		this.seconds = seconds;
		isAlive = true;
		startTurn();
	}

	public void startTurn() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isAlive) {
					try {
						Thread.sleep(1000);
						seconds--;
						if (seconds == 0 || winner != null) {
							isAlive = false;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public void showClues() {

	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

}
