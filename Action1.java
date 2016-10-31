import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Action1 extends DatagramSendReceive implements ActionListener,
		Runnable {

	private static DatagramSocket DS = null;
	@SuppressWarnings("unused")
	private static String message = "";

	private InetAddress Ip;

	private int SPort;

	public static DatagramSocket getDS() {
		return DS;
	}

	public void setDS(DatagramSocket dS) {
		DS = dS;
	}

	public InetAddress getIp() {
		return Ip;
	}

	public void setIp(InetAddress ip) {
		Ip = ip;
	}

	public int getSPort() {
		return Port;
	}

	public void setSPort(int port) {
		Port = port;
	}

	private String M = " ";

	public void send(String d) {

		String Ip = "127.0.0.1"; 

		InetAddress adr = null;

		try {

			adr = InetAddress.getByName(Ip);

		} catch (UnknownHostException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		M = d;

		DatagramSocket ds2 = null;

		try {

			ds2 = new DatagramSocket(50000);

		} catch (SocketException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		System.out.println(ds2.isBound() + "ESD");

		int Pn2 = 43000; 

		DatagramPacket dp1 = new DatagramPacket(getM().getBytes(), getM()
				.length(), adr, Pn2);

		try {

			ds2.send(dp1);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(getM());

	}
	
public void send(String d, InetAddress ia, int port) {
	
		System.out.println(DS.getPort());
		
		M = d; 
		
		DatagramPacket dp1 = new DatagramPacket(getM().getBytes(),
				getM().length(), ia, port);
		
		try {

			DS.send(dp1);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getM() {
		return M;
	}

	public void setM(String m) {
		M = m;
	}

	public void receive() {
		
		byte[] r = new byte[1024];

		DatagramPacket dp2 = new DatagramPacket(r,
				r.length);
		
		while(true) {

			try {
				
				System.out.println("Waiting To Receive.....");
				
				DS = new DatagramSocket(64000);

				DS.receive(dp2);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("There's an error");
				e.printStackTrace();
			} 

			InetAddress fromAddress = dp2.getAddress();
			int fromPort = dp2.getPort();
			
			System.out.println("Message From " + fromAddress.getHostAddress());
			System.out.println("Port         " + fromPort);
			
			String reme = new String(dp2.getData())
					+ ", from the IP address: " + dp2.getAddress().getHostAddress()
					+ ", and from port: " + dp2.getPort();
			
			System.out.println(reme); // Received Message

			Ip = dp2.getAddress();
			Port = dp2.getPort();
			System.out.println(dp2.getAddress());
			System.out.println(dp2.getPort());
			g = new String(dp2.getData(), 0, dp2.getLength());
			setG(g);
			System.out.println(g);
		}

	}

	public void actionPerformed(ActionEvent e) {
		
		System.out.println("SAS");
		
		Action1 a = new Action1(DS,Ip, SPort);
		
		System.out.println("For the action1 method this is the port: " + SPort);
		System.out.println("For the action1 method this is the IP: " + Ip);
		//System.out.println("For the action1 method this is the port: " + SPort);
		
		a.send(M, Ip, SPort);

	}

	public Action1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Action1(DatagramSocket sd, InetAddress a, int Port) {
		
		DS = sd;
		Ip = a;
		message = "Support";
		SPort = Port;
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("Thread is working");

		try {
			DS = new DatagramSocket(64000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		receive();
	}

}
