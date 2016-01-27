import java.io.*;  
import java.net.*;

public class IRCServer {
	
    private ServerSocket serverSocket;
    private int port;
    
    public IRCServer (int port) {
        this.port = port;
    }
    
    public Socket listen() {
		ServerSocket serverSoc = null;
		Socket clientSoc = null;
    	try {
    		serverSoc = new ServerSocket(port);
    	} catch(IOException e) {
    		System.out.println("Open Socket Error");
    	}
    	
		System.out.println("Waiting for the client");
		
		try {
			clientSoc = serverSoc.accept();
		} catch (IOException e) { 
	         System.out.println("Accept Socket Error"); 
	         System.exit(1); 
        }
		
		/* Send Welcome Message */
		try {
			PrintWriter out = new PrintWriter(clientSoc.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));
			out.println("Thank you for connecting");
			System.out.println ("Connection successful with client " + clientSoc.getLocalAddress() );
			System.out.println ("Waiting for input from " + clientSoc.getLocalAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clientSoc;
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
		Socket cSoc = ircServer.listen();
		
		while (true) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(cSoc.getInputStream()));
				String incomingData = "";

				while ((incomingData = in.readLine()) != null) {
					System.out.println(incomingData);
					ircServer.incomingParser(incomingData, cSoc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
