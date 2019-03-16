package test;

import com.edu.uptc.model.logic.Turn;

public class TurnTest {
	

	public static void main(String[] args) throws InterruptedException {
		Turn t = new Turn("camilo", 40);
		while(t.getSeconds() > 0) {
			System.out.println(t.getSeconds());
			Thread.sleep(950);
		}
		
	}

}
