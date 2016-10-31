import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DatagramSendReceive  {

	private static InetAddress IP = null;

	static String g;
	
	static int Port;

	private String name = " ";

	private static String message = "";

	@SuppressWarnings("unused")
	private DatagramSocket s1 = null;

	public static String getG() {
		return g;
	}

	public static void setG(String g) {
		DatagramSendReceive.g = g;
	}

	@SuppressWarnings("unused")
	private DatagramPacket p1 = null;
	static InetAddress address;

	@SuppressWarnings("unused")
	public DatagramSendReceive(DatagramSocket s) {
		super();
		this.s1 = s;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			@SuppressWarnings("resource")
			DatagramSocket s1 = new DatagramSocket(8888);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatagramSendReceive(String message) {

		setName(message);
	}

	public DatagramSendReceive() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public void run(String d) throws IOException { 

		System.out.println("Thread is working");

		String Ip = "192.168.1.100"; 

		InetAddress adr = null;

		try {

			adr = InetAddress.getByName(Ip);

		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message = " ???? " + d;

		DatagramSocket ds1 = null; 

		try {
			ds1 = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DatagramSocket ds2 = null;

		try {
			ds2 = new DatagramSocket(43000); // 43000
			// ds2 = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

		DatagramPacket dp1 = new DatagramPacket(getMessage().getBytes(),
				getMessage().length(), adr, 64000);
		try {

			ds2.send(dp1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DatagramPacket dp2 = new DatagramPacket(getMessage().getBytes(),
				getMessage().length());

		while (true) {

			ds1.receive(dp2); 

			String reme = new String(dp2.getData(), 0, dp2.getLength())
					+ ", from the IP address: " + dp2.getAddress()
					+ ", and from port: " + dp2.getPort();

			System.out.println("You have received info: " + reme); 

			IP = dp2.getAddress();
			Port = dp2.getPort();
			System.out.println(IP);
			System.out.println(Port);
			g = new String(dp2.getData(), 0, dp2.getLength());
			setG(g);
		}

	}

	public String getName() {
		return name;
	}

	public static InetAddress getIP() {
		return IP;
	}

	public static void setIP(InetAddress s) {
		DatagramSendReceive.IP = s;
	}

	public static int getPort() {
		return Port;
	}

	public static void setPort(int port) {
		Port = port;
	}

	public static void setMessage(String message) {
		DatagramSendReceive.message = message;
	}

	public void send(String d) {

		String Ip = "127.0.0.1"; // Receiver's Ip; It could be any IP address

		InetAddress adr = null;

		try {

			adr = InetAddress.getByName(Ip);

		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		message = d; // Sent Message

		DatagramSocket ds2 = null;// Sender Socket

		try {

			ds2 = new DatagramSocket(50000);

		} catch (SocketException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int Pn2 = 43000; 

		DatagramPacket dp1 = new DatagramPacket(getMessage().getBytes(),
				getMessage().length(), adr, Pn2);

		

		try {

			ds2.send(dp1);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println( getMessage() );

	}

	public static String getMessage() {
		return message;
	}

	public void setName(String name) {
		this.name = name;
	}


	public static void removeChatFrame(InetAddress otherIP, int otherPort) {
		// TODO Auto-generated method stub

	}
}
