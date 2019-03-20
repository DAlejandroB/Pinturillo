package test;

import com.edu.uptc.comm.ServerComm;

public class ServerCommTest {
	public static void main(String[] args) {
		ServerComm serverComunicator = new ServerComm();
		serverComunicator.sendMessage("Chinge su madre el cliente");
	}
}
