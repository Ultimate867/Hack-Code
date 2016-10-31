import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver extends Action1 implements ActionListener, Runnable {

	private String message = " ";
	private String s;
	private static InetAddress ia;
	private int p;
	private TextField myTextField;
	private TextArea myTextArea;
	private String MYM;
	private String n;
	private static Driver a2 = new Driver();
	private JPanel textFieldPanel;
	private JButton sendButton;
	private JFrame frame2;
	//private JFrame frame3;
	//private DatagramPacket dp1;
	//private DatagramPacket dp2;

	private static DatagramSocket DS;

	public InetAddress getIa() {
		return ia;
	}

	@SuppressWarnings("static-access")
	public void setIa(InetAddress ia) {
		this.ia = ia;
	}

	public static DatagramSocket getDS() {
		return DS;
	}

	public void setDS(DatagramSocket dS) {
		DS = dS;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public void send(String d, InetAddress ia, int port) { 

		message = d; 

		DatagramPacket dp1 =  new DatagramPacket(getmessage().getBytes(),
				getmessage().length(), ia, port);

		try {

//			System.out.println(getmessage());
//			System.out.println(p);
//			System.out.println(ia);
			DS.send(dp1);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receive() {

		//String[] a1 = new String[10];

		//int[] aa = new int[10];

		//InetAddress[] ab = new InetAddress[10];

		//int i = 0;
		
		byte[] r = new byte[1024];
		
		DatagramPacket dp2 = new DatagramPacket(r, r.length);

		//while (true) {
		
//		try {
//			DS.getBroadcast();
//		} catch (SocketException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

			try {

				System.out.println("Waiting To Receive.....");

				DS.receive(dp2);//dp2
				//DS.getBroadcast();/////

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("There's an error");
				e.printStackTrace();
			} 

			InetAddress fromAddress = dp2.getAddress();
			int fromPort = dp2.getPort();
			String fromMessage = new String(dp2.getData());

			System.out.println("Message From " + fromAddress.getHostAddress());
			System.out.println("Port         " + fromPort);
			System.out.println("Message         " + fromMessage);

			String reme = new String(dp2.getData()) + ", from the IP address: "
					+ dp2.getAddress().getHostAddress() + ", and from port: "
					+ dp2.getPort();

			System.out.println(reme); 

		//	try {
				s = "THEM: " + new String(dp2.getData());
			//} catch (SocketException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}

		//	a1[i] = new String(dp2.getData());
		//	aa[i] = dp2.getPort();
		//	ab[i] = dp2.getAddress();

		//	System.out.println(a1[i]);
		//	System.out.println(aa[i]);
		//	System.out.println(ab[i]);

			ia = dp2.getAddress();
			p = dp2.getPort();

		//	System.out.println(i);
		//	System.out.println(ab[i]);
			
			//if(i == 0){

			frame2.setTitle("Name:" + n + " IP: " + ia + " Port: " + p);

			frame2.setVisible(true);
			frame2.setSize(400, 400);

			myTextField.addActionListener((ActionListener) this);

			textFieldPanel.add(myTextField, BorderLayout.CENTER);

			myTextField.setText("");

			MYM = myTextField.getText();

			myTextArea.append(MYM);
			myTextArea.append(s);

			frame2.add(myTextArea, BorderLayout.CENTER);

			frame2.setVisible(true);

			sendButton.addActionListener(a2);

			textFieldPanel.add(sendButton, BorderLayout.EAST);

			frame2.add(textFieldPanel, BorderLayout.SOUTH);
			
			//receive();
			
		//	}
			
			///////
			
//			if(i >= 1){
//				
//				//myTextArea.setText(null);
//
//				frame2.setTitle("Name:" + n + " IP: " + ia + " Port: " + p);
//
//				frame2.setVisible(true);
//				frame2.setSize(400, 400);
//
//				myTextField.addActionListener((ActionListener) this);
//
//				textFieldPanel.add(myTextField, BorderLayout.CENTER);
//
//				myTextField.setText("");
//
//				MYM = myTextField.getText();
//
//				myTextArea.append(MYM);
//				myTextArea.append(s);
//
//				frame2.add(myTextArea, BorderLayout.CENTER);
//
//				frame2.setVisible(true);
//
//				sendButton.addActionListener(a2);
//
//				textFieldPanel.add(sendButton, BorderLayout.EAST);
//
//				frame2.add(textFieldPanel, BorderLayout.SOUTH);
//				
//				}

			//i++;

		//}
	}

	public void actionPerformed(ActionEvent e) {

		message = myTextField.getText();

		myTextArea.append("\nME: " + message + "\n");

		System.out.println("For the Driver method this is the message: "
				+ message);
		System.out.println("For the Driver method this is the IP: " + ia);
		System.out.println("For the Driver method this is the port: " + p);

		send(message, ia, p);
		
		receive();
	}

	public Driver() {

		super();
		//byte[] r = new byte[1024];
		this.myTextField = new TextField();
		this.myTextArea = new TextArea();
		this.textFieldPanel = new JPanel();
		this.sendButton = new JButton();
		this.frame2 = new JFrame();
	//	frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		this.dp1 =  new DatagramPacket(getmessage().getBytes(),
//				getmessage().length(), ia, p);
		//this.dp2 = new DatagramPacket(r, r.length);///

	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public static void main(String[] args) {

		try {
			DS = new DatagramSocket(45000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			DS.setBroadcast(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread s2 = new Thread(a2);
		s2.start();
	}

	@Override
	public void run() {

		@SuppressWarnings("resource")
		Scanner d = new Scanner(System.in);
		System.out.println("REPLY: ");
		String r = "????? " + d.next();
		
		n = r.substring(5);

		p = 64000;//
		String g = "255.255.255.255";

		try {
			ia = InetAddress.getByName(g);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		send(r, ia, p);
		receive();

	}
}