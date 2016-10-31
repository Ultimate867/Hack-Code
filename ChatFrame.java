import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChatFrame {

	private static DatagramSocket sendSocket;
	private InetAddress otherIP;
	private int otherPort;
	private TextField myTextField;
	
	public static DatagramSocket getSendSocket() {
		return sendSocket;
	}

	@SuppressWarnings("static-access")
	public void setSendSocket(DatagramSocket sendSocket) {
		this.sendSocket = sendSocket;
	}

	public InetAddress getOtherIP() {
		return otherIP;
	}

	public void setOtherIP(InetAddress otherIP) {
		this.otherIP = otherIP;
	}

	public int getOtherPort() {
		return otherPort;
	}

	public void setOtherPort(int otherPort) {
		this.otherPort = otherPort;
	}

	private TextArea myTextArea;

	@SuppressWarnings("static-access")
	public ChatFrame(DatagramSocket sendSocket, final InetAddress otherIP, final int otherPort ) {

		super();
		
		JFrame j = new JFrame();

		// save the socket
		// save the IP address of the user I am chatting with
		// save the port number of the user I am chatting with
		// All this is used when I send the message in the action listener

		this.sendSocket = sendSocket;
		
		this.otherIP = otherIP;

		this.otherPort = otherPort;

		j.setSize(600, 400);

		j.setResizable(true);

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// this code is used when a ChatFrame is closed.

		// I want to remove the closed ChatFrame from my

		// from my collection of ChatFrames.

		j.addWindowListener(new WindowAdapter()

		{

			public void windowClosing(WindowEvent e)

			{

				DatagramSendReceive.removeChatFrame(otherIP, otherPort);

		    }

		});

		// set the title of the ChatFrame

		j.setTitle("IP = " + otherIP.getHostAddress() + ", Port = " + otherPort);

		// create and format a JPanel for input field and the Send button

		JPanel textFieldPanel = new JPanel(new BorderLayout());

		// create the TextField
		// set the action listener that gets called when you press Enter
		// add the TextField to the JPanel

		this.myTextField = new TextField();

		myTextField.addActionListener((ActionListener) this);

		textFieldPanel.add(myTextField, BorderLayout.CENTER);

		// create the Send button
		// set the action listener for when it is pressed
		// the action listener should be the same as when the Enter is
		// pressed for the TextField
		// add the Send button to the JPanel

		JButton sendButton = new JButton("Send");

		sendButton.addActionListener((ActionListener) this);

		textFieldPanel.add(sendButton, BorderLayout.EAST);

		j.add(textFieldPanel, BorderLayout.SOUTH);

		// create the TextArea
		// add the TextArea to the ChatWindow

		this.myTextArea = new TextArea();

		j.add(myTextArea, BorderLayout.CENTER);

		j.setVisible(true);

	}
}