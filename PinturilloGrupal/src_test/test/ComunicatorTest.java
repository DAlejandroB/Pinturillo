package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.edu.uptc.comm.Comunicator;

public class ComunicatorTest {
	public static void main(String[] args) throws UnknownHostException {
//		Comunicator comm = new Comunicator();
//		System.out.println(comm.recieveMessage());
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	}
}