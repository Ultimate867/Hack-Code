import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DSR3 { //client

  public static void main(String[] args) throws Exception {
    @SuppressWarnings("resource")
	DatagramSocket s = new DatagramSocket();
    byte[] buf = new byte[1000];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);

    InetAddress hostAddress = InetAddress.getByName("localhost");
    while (true) {
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      String outMessage = stdin.readLine();

      if (outMessage.equals("bye"))
        break;
      String outString = "Client say: " + outMessage;
      buf = outString.getBytes();

      DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 9999);
      s.send(out);

      s.receive(dp);
      String rcvd = "rcvd from " + dp.getAddress() + ", " + dp.getPort() + ": "
          + new String(dp.getData(), 0, dp.getLength());
      System.out.println(rcvd);
    }
  }
}


