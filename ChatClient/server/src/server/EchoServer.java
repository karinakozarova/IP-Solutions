package server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class EchoServer
{

	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
			Socket clientSocket = serverSocket.accept();
			System.out.println("client connected from " + clientSocket.getInetAddress());
			PrintWriter out =
					new PrintWriter(clientSocket.getOutputStream(), true);
		
			BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String inputLine;               
			while(true) {
				if((inputLine = receiveRead.readLine()) != null) {
					if (inputLine.equals("exit")) break;
					System.out.println("Client: " + inputLine); 
				}
				out.println("Server:" + keyRead.readLine());  
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}

			System.out.println("Server closed");
		}
	}                    

}                        