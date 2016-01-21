import java.io.*;  
import java.net.*;


public class IRCClient {
	
	public static void main(String[] args) {
		Socket clientSoc = null;
		try {
		    clientSoc = new Socket("localhost", 12345);
		    PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
		    System.out.println(in.toString());
		    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
		    System.out.println("Read failed");
		    System.exit(1);
		}
	}
}
