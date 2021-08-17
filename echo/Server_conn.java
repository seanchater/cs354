import java.io.PrintWriter;
import java.io.*;
import java.net.*;

public class Server_conn implements Runnable {
	private int port_number;
	private Socket clientSocket;
	// who sent the message
	private String username;

	public Server_conn(int port, Socket sock) {
		port_number = port;
		clientSocket = sock;
	}

	public void run() {
		try (
			/*
				* datainputstream not neccessarily safe for multithreaded access. ALthough we will
				* only be doing that if the server is reading input from clients from a single port??
			DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			*/
				// DONT use print writer to send messages
			PrintWriter out =
				new PrintWriter(clientSocket.getOutputStream(), true);                   
			BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		) {
			System.out.println("new client connection opened successfully on port " + port_number);           
			// verify username
			String username;
			while ((username = in.readLine()) != null) {
				// check in bigd
				if (/*unique*/)	{
					// add to bigd
					out.println("0");
					break;
				} else {
					// username is not unique
					out.println("1");
				}
			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
				+ port_number + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
	
}