import java.io.*;  
import java.net.*;

public class IRCServer {

	public static void main(String[] args) {
		ServerSocket serverSoc = null;
		Socket clientSoc = null;

		try{
			serverSoc = new ServerSocket(12345);

		}catch (IOException e) {
			System.out.println("Open Socket Error");
			System.exit(1);
		}
		while(true){
			try { 
		         clientSoc = serverSoc.accept(); 
		         PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
		         BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
	             // Send a welcome message to the client.
	             out.println("Thank you for connecting");
		 	     System.out.println ("Connection successful with client " + clientSoc.getLocalAddress() );
			     System.out.println ("Waiting for input from " + clientSoc.getLocalAddress());
	        } catch (IOException e) { 
		         System.out.println("Accept Socket Error"); 
		         System.exit(1); 
	        }
		}  

	}
}
