import java.io.*;
import java.net.Socket;
import java.util.logging.*;

public class ClientConnected implements Runnable {
	
	private Socket clientSocket;
	private BufferedReader inData;
    private PrintWriter outData;
    private boolean stopFlag;
	
	public ClientConnected (Socket csoc, PrintWriter out) {
		this.clientSocket = csoc;
		this.outData = out;
		this.stopFlag = false;
	}

	@Override
	public void run() {
		 try {
	            inData = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            String incomingData;
	            while ((incomingData = inData.readLine()) != null) {
	            	incomingParser(incomingData);
		   		 	if (stopFlag) {
		   		 		break;
		   		 	}
	            }

	        } catch (IOException ex) {
	            Logger.getLogger(ClientConnected.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	public void incomingParser(String data) {
    	if (data.equals("BYE")) {
    		try {
    			inData.close();
   		 		System.out.println("Coonection is closed, address: " + clientSocket.getLocalAddress());
    			clientSocket.close();
    			stopFlag = true;
			} catch (IOException e) {
				System.out.println("Close Error");
			}
    	}
    	else {
    		try {
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				out.println("ERR");
			} catch (IOException e) {
				e.printStackTrace();
			}    		
    	}
	}

}
