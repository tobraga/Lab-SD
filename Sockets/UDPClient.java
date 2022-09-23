import java.io.*;
import java.net.*;

class UDPClient {
	public static void main(String args[]) throws Exception {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));

		DatagramSocket clientSocket = new DatagramSocket();

		String servidor = "localhost";
		int porta = 9876;

		InetAddress IPAddress = InetAddress.getByName(servidor);

		byte[] sendData = new byte[4096];
		byte[] receiveData = new byte[4096];
		
		System.out.println("====ENVIO PARA O SERVIDOR===== ");
		System.out.println("=============================== ");
		System.out.print("Entre com a sua senha: ");
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, porta);

		//System.out.println("=============================== ");
		System.out
				.println("Enviando pacote UDP para " + servidor + ":" + porta);
		clientSocket.send(sendPacket);
		//System.out.println("=============================== ");

		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);


		clientSocket.receive(receivePacket);
//		System.out.println("====RECEBIMENTO DO SERVIDOR===== ");
		System.out.println("Senha criptografada recebida");
		//System.out.println("=============================== ");

		String modifiedSentence = new String(receivePacket.getData());

		System.out.println("Senha:" + modifiedSentence);
		clientSocket.close();

		System.out.println("Socket cliente fechado!");
		System.out.println("=============================== ");
	}
}