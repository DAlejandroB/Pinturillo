package com.edu.uptc.model.logic;

import java.io.EOFException;
import java.util.Random;

import com.edu.uptc.model.entities.Player;
import com.edu.uptc.model.entities.Status;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;


public class GameRoom {

	private int id;
	private boolean isPublic;
	private boolean isAlive;
	private LinkedList<Player> players;
	private LinkedList<String> words;
	private String word;
	private int turnTime;
	private int lobbyTime;
	private int chooseTime;
	private int turns;

	public GameRoom(boolean isPublic, Player player, int turnTime, int lobbyTime, int chooseTime, int turns,
			LinkedList<String> words) {
		super();
		id = randomId();
		players = new LinkedList<>();
		players.add(player);
		isAlive = true;
		word = "";
		this.isPublic = isPublic;
		this.turns = turns;
		this.chooseTime = chooseTime;
		this.lobbyTime = lobbyTime;
		this.turnTime = turnTime;
		this.words = words;
	}

	public int randomId() {
		Random r = new Random();
		return r.nextInt(500000);
	}

	public boolean addPlayer(Player player) {
		if (lobbyTime != 0 && player.getStatus().equals(Status.ONLINE)) {
			players.add(player);
			return true;
		} else {
			return false;
		}
	}

	public void startRoom() {

		while (lobbyTime != 0 && isAlive == true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			lobbyTime--;
		}
		startGame();
	}

	public void startGame() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < turns; i++) {
					Node<Player> auxN = players.getHead();
					while (auxN != null) {
						
						new Turn(selectedWord(generateWords()), turnTime);
						setWord("");
						auxN = auxN.getNext();
					}
				}
			}
		});
		thread.start();
	}

	public String selectedWord(LinkedList<String> wordsG) {
		while (chooseTime != 0 && word.equals("")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			chooseTime--;
		}
		if (word.equals("")) {
			word = randomWord(wordsG);
		}
		return word;
	}

	public void changeDrawer(Player prev, Player post) {
		if (!prev.equals(null)) {
			prev.switchType();
			post.switchType();
		} else {
			post.switchType();
		}
	}

	public LinkedList<String> generateWords() {
		LinkedList<String> threeWords = new LinkedList<>();
		Random rnd = new Random();
		Node<String> aux = words.getHead();
		for (int i = 0; i < 3; i++) {
			int randomNumber = rnd.nextInt(words.getSize() - 1);

			if (i > 0) {
				Node<String> auxN = threeWords.getHead();
				while (auxN != null) {
					while (words.getByIndex(randomNumber).equals(auxN.getInfo())) {
						randomNumber = rnd.nextInt(words.getSize() - 1);
					}
					auxN = auxN.getNext();
				}
			}

			int number = 0;
			while (number != randomNumber) {
				aux = aux.getNext();
				number++;
			}
			threeWords.add(aux.getInfo());
			aux = words.getHead();
		}
		return threeWords;
	}

	private String randomWord(LinkedList<String> wordsG) {
		Random rnd = new Random();
		return wordsG.getByIndex(rnd.nextInt(3));
	}

	private void closeRoom() {
		isAlive = false;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public LinkedList<Player> getPlayers() {
		return players;
	}

	public LinkedList<String> getWords() {
		return words;
	}

	public int getLobbyTime() {
		return lobbyTime;
	}

	public int getId() {
		return id;
	}

}
