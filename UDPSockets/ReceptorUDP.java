import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.security.*;
import java.math.*;

public class ReceptorUDP {

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        if(args.length != 1) {
          System.out.println("Informe a porta a ser ouvida");
          System.exit(0);
        }
  
        try {
          //Converte o argumento recebido para inteiro (numero da porta)
          int port = Integer.parseInt(args[0]);
          //Cria o DatagramSocket para aguardar mensagens, neste momento o método fica bloqueando
          //até o recebimente de uma mensagem
          DatagramSocket ds = new DatagramSocket(port);
          System.out.println("Ouvindo a porta: " + port);
          //Preparando o buffer de recebimento da mensagem
          byte[] msg = new byte[4096];
          //Prepara o pacote de dados
          DatagramPacket pkg = new DatagramPacket(msg, msg.length);
          //Recebimento da mensagem
          ds.receive(pkg);

          /*------------------------------------------------ */
          //criptografia
          /*------------------------------------------------ */
          String sentence = new String(pkg.getData());
				System.out.println("Senha: "+ sentence);
				System.out.println("=========================================== ");
				

				MessageDigest m=MessageDigest.getInstance("MD5");
				m.update(sentence.getBytes(),0,sentence.length());

				
				String md5 = new BigInteger(1,m.digest()).toString(16);

				String teste = md5.toUpperCase();

				
				System.out.println("Criptografando - Técnica MD5 ");
				System.out.println("MD5: "+ teste);
				System.out.println("OK\n");
				System.out.println("=========================================== ");
          ds.close();
        }
  
        catch(IOException ioe) {System.out.println("teste");}
      }
    }
  