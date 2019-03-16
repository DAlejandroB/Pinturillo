package test;

import com.edu.uptc.model.entities.Player;
import com.edu.uptc.model.entities.Status;
import com.edu.uptc.model.entities.User;
import com.edu.uptc.model.logic.Game;
import com.edu.uptc.model.logic.GameRoom;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class TestTurn {
	
	public static void main(String[] args) throws InterruptedException {
		TestTurn t = new TestTurn();
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
		System.out.println("-THREE RANDOM WORDS");
		LinkedList<String> threeWords = gameRoom.generateWords();
		t.showList(threeWords);
		System.out.println("--PLAYERS");
		t.showList2(gameRoom.getPlayers());
		System.out.println("-ALL THE LIST");
		t.showList(gameRoom.getWords());
		System.out.println("-----------");
		System.out.println("Selected Word " + gameRoom.selectedWord(threeWords));
		
		
		
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
