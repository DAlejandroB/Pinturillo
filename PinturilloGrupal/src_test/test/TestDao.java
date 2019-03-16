package test;

import java.io.IOException;

import com.edu.uptc.model.persistence.Dao;
import com.edu.uptc.structure.LinkedList;
import com.edu.uptc.structure.Node;

public class TestDao {
	
	Dao dao = new Dao();
	public static void main(String[] args) throws IOException {
		TestDao t = new TestDao();
		t.showList(t.dao.readWords());
	}
	
	public void showList(LinkedList<String> list) {
		Node<String> aux = list.getHead();
		while(aux != null) {
			System.out.println(aux.getInfo());
			aux = aux.getNext();
		}
	}

}
