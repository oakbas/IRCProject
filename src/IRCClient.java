import java.io.*;  
import java.net.*;

public class IRCClient {
	private Socket clientSoc;
	private String hostname;
	private int port;
	
	public IRCClient (int port, String hostname) {
		this.port = port;
		this.hostname = hostname;
	}
	
	public void connectToServer() {
		
		Socket clientSoc = null;
		
	    try {
			clientSoc = new Socket(hostname, port);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			System.out.println("Unknown host");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Can not set connection");
		}
		
		try {
		    PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
		    System.out.println(in.toString());
		    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
		    System.out.println("Read failed");
		    System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		int port = 12345;
		String hostname = "localhost";
		IRCClient clientSoc = new IRCClient(port,hostname);
		clientSoc.connectToServer();
	}
}
