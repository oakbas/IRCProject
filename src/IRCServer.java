import java.io.*;  
import java.net.*;

public class IRCServer {
	
    private ServerSocket serverSocket;
    private int port;
    PrintWriter out;
    
    public IRCServer (int port) {
        this.port = port;
    	try {
    		serverSocket = new ServerSocket(port);
    	} catch(IOException e) {
    		System.out.println("Open Socket Error");
    	}
    }
    
    public void listen() {
    	
    	while (true) {
    		Socket clientSoc = null;
        	    		
    		try {
    			clientSoc = serverSocket.accept();
    		} catch (IOException e) { 
    	         System.out.println("Accept Socket Error"); 
    	         System.exit(1); 
            }
    		
    		/* Send Welcome Message */
    		try {
    			out = new PrintWriter(clientSoc.getOutputStream(), true);
    			out.println("Thank you for connecting");
    			System.out.println ("Connection successful with client " + clientSoc.getLocalAddress() );
    			System.out.println ("Waiting for input from " + clientSoc.getLocalAddress());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		new Thread(new ClientConnected(clientSoc, out)).start();
    	}		
    }
    
    public void incomingParser (String data, Socket cSoc) {
    	if (data.equals("BYE")) {
    		try {
				cSoc.close();
			} catch (IOException e) {
				System.out.println("Close Error");
			}
    	}
    	else {
    		try {
				PrintWriter out = new PrintWriter(cSoc.getOutputStream(), true);
				out.println("ERR");
			} catch (IOException e) {
				e.printStackTrace();
			}    		
    	}
    }
    
	public static void main(String[] args) {
		int serverPort = 12345;
		IRCServer ircServer = new IRCServer(serverPort);

		System.out.println("Waiting for the client");
		ircServer.listen();
	}
}
