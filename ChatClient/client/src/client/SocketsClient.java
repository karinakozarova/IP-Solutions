package client;

import java.io.*;
import java.net.*;
public class SocketsClient
{
	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			echoSocket = new Socket("localhost", 10001);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
					new InputStreamReader(System.in));

			String serverEntered;               
			while(true){
				out.println(stdIn.readLine());       
				if((serverEntered = in.readLine()) != null)
					System.out.println(serverEntered);          
			}                
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				echoSocket.close();
			}
		}
	}  
}                        