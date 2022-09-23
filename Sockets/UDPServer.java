import java.io.*;
import java.net.*;
import java.security.*;
import java.math.*;

class UDPServer {
	public static void main(String args[]) throws Exception {

		int porta = 9876;
		int numConn = 1;
		
		try (DatagramSocket serverSocket = new DatagramSocket(porta)) {
			byte[] receiveData = new byte[4096];
			byte[] sendData = new byte[4096];

			while (true) {

				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				System.out.println("Esperando por datagrama UDP na porta " + porta);
				serverSocket.receive(receivePacket);
				System.out.println("Datagrama UDP [" + numConn + "] recebido...");

				String sentence = new String(receivePacket.getData());
				System.out.println("Senha: "+ sentence);
				System.out.println("=========================================== ");
				
				InetAddress IPAddress = receivePacket.getAddress();

				int port = receivePacket.getPort();

				MessageDigest m=MessageDigest.getInstance("MD5");
				m.update(sentence.getBytes(),0,sentence.length());

				
				String md5 = new BigInteger(1,m.digest()).toString(16);

				String teste = md5.toUpperCase();

				sendData = teste.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, port);

				
				System.out.println("Criptografando - Técnica MD5 ");
				System.out.println("MD5: "+ teste);
					
				System.out.print("Enviando " + sendData + "...");

				serverSocket.send(sendPacket);
				System.out.println("OK\n");
				System.out.println("=========================================== ");
			}
		}
	}
}