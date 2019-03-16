package test;

import com.edu.uptc.model.entities.Player;
import com.edu.uptc.model.entities.Status;
import com.edu.uptc.model.entities.User;
import com.edu.uptc.model.logic.Game;
import com.edu.uptc.model.logic.GameRoom;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class TestGameRoom {
	
	public static void main(String[] args) throws InterruptedException {
		TestGameRoom t = new TestGameRoom();
		LinkedList<String> words = new LinkedList<>();
		words.add("hola");
		words.add("moto");
		words.add("helado");
		words.add("camilo");
		words.add("matera");
		words.add("camion");
		words.add("cerveza");
		words.add("hojalata");
		words.add("Molano");
		Game game = new Game(words);
		game.setWords(words);
		
		User user = new User("Monster", "abcd123");
		Player player = new Player(user);
		game.createRoom(true, player);
		GameRoom gameRoom = game.getRoomsList().getHead().getInfo();
		System.out.println("ID ROOM " + gameRoom.getId());
		System.out.println("-THREE RANDOM WORDS");
		t.showList(gameRoom.generateWords());
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		System.out.println("-ALL THE LIST");
		t.showList(gameRoom.getWords());
		System.out.println("-Search By Index");
		System.out.println(gameRoom.getWords().getByIndex(0));
		System.out.println(gameRoom.getWords().getByIndex(5));
		System.out.println(gameRoom.getWords().getByIndex(2));
		
		User user2 = new User("0molano", "abd123");
		Player player2 = new Player(user2);
		System.out.println("Add new player to the Room");
		gameRoom.addPlayer(player2);
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		System.out.println("---------");
		System.out.println("Add new player to the Room OFFLINE");
		User user4 = new User("0mo4", "bd1s223");
		user4.setStatus(Status.OFFLINE);
		Player player4 = new Player(user4);
		System.out.println("add correctly? " + gameRoom.addPlayer(player4));
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		User user5 = new User("camiloMOLANOSALAMANCA", "bd1s2423");
		Player player5 = new Player(user5);
		System.out.println("JOIN " + user5.getNickName() + " to " + " 121");
		game.joinRoom(player5, 121);
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		User user6 = new User("camiloMO", "b1s2423");
		Player player6 = new Player(user6);
		System.out.println("JOIN " + user6.getNickName() + " to " + gameRoom.getId());
		game.joinRoom(player6, gameRoom.getId());
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		
		System.out.println("-----------");
		System.out.println("Start room");
		System.out.println("Time lobby "  + gameRoom.getLobbyTime());
		gameRoom.startRoom();
		//Thread.sleep(1000*15);
		System.out.println("Time lobby after run "  + gameRoom.getLobbyTime());
		System.out.println("----");
		System.out.println("Add new player to the Room out of lobby");
		User user3 = new User("0molano14", "bd123");
		Player player3 = new Player(user3);
		System.out.println("add correctly? " + gameRoom.addPlayer(player3));
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		
		//ESTE TEST SIGUE CORRIENDO PORQUE INICIAN LOS TURNOS Y ESO TODAVIA NO SE HA PROGRAMADO
		
	}
	
	public void showList(LinkedList<String> list) {
		Node<String> aux = list.getHead();
		while(aux != null) {
			System.out.println(aux.getInfo());
			aux = aux.getNext();
		}
	}
	public void showList2(LinkedList<Player> list) {
		Node<Player> aux = list.getHead();
		while(aux != null) {
			System.out.println(aux.getInfo().getNickName());
			aux = aux.getNext();
		}
	}

}
