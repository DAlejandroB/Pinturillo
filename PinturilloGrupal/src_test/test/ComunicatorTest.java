package test;

import java.net.UnknownHostException;

import com.edu.uptc.comm.Comunicator;

public class ComunicatorTest {
	public static void main(String[] args) throws UnknownHostException {
		Comunicator comm = new Comunicator();
		comm.sendChatMessage("Hello server");
	}
}