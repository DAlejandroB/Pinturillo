package com.edu.uptc.model.logic;

import com.edu.uptc.model.entities.Player;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class Game {

	private static final int TURN_TIME = 90;
	private static final int LOBBY_TIME = 10; //30
	private static final int CHOOSING_TIME = 15;
	private static final int TURNS = 3;
	protected LinkedList<String> words;
	private LinkedList<GameRoom> roomsList;

	public Game(LinkedList<String> words) {
		super();
		this.words = words;
		roomsList = new LinkedList<>();
	}

	public void createRoom(boolean isPublic, Player player) {
		roomsList.add(new GameRoom(isPublic, player, TURN_TIME, LOBBY_TIME, CHOOSING_TIME, TURNS, words));
	}
	
	public void joinRoom(Player player, int id) {
		Node<GameRoom> aux = roomsList.getHead();
		while(aux != null) {
			if(aux.getInfo().getId() == id) {
				GameRoom gameRoom = roomsList.getByInfo(aux.getInfo()).getInfo();
				gameRoom.addPlayer(player);
			}
			aux = aux.getNext();
		}

	}
	
	public void delateRoom(int id) {
		Node<GameRoom> aux = roomsList.getHead();
		while(aux != null) {
			if(aux.getInfo().getId() == id) {
				roomsList.delete(aux.getInfo());
				aux = null;
			}
			aux = aux.getNext();
		}
	}

	private void fillWords() {
		// TODO words.add();

	}

	public LinkedList<String> getWords() {
		return words;
	}

	public void setWords(LinkedList<String> words) {
		this.words = words;
	}

	public static int getTurnTime() {
		return TURN_TIME;
	}

	public static int getLobbyTime() {
		return LOBBY_TIME;
	}

	public static int getChoosingTime() {
		return CHOOSING_TIME;
	}

	public static int getTurns() {
		return TURNS;
	}

	public LinkedList<GameRoom> getRoomsList() {
		return roomsList;
	}

}
