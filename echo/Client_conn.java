import java.io.*;
import java.net.*;

public class Client_conn implements Runnable {
	private String hostName;
	private int portNumber;

	public Client_conn(String host, int port) {
		hostName = host;
		portNumber = port;
	}

	public void run() {
		try (
		Socket echoSocket = new Socket(hostName, portNumber);
		/*
		DataInputStream in = new DataInputStream(new BufferedInputStream(echoSocket.getInputStream()));
		DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream());
		*/
		// use message objects to send/receive information NOT printwriter
		PrintWriter out =
			new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in =
			new BufferedReader(
				new InputStreamReader(echoSocket.getInputStream()));
		BufferedReader stdIn =
			new BufferedReader(
				new InputStreamReader(System.in))
	) {
		System.out.println("Client socket opened successfully on port " + portNumber);

		/**
		 * get username and send to server to check if unique
		 */
		// verfiy
		String userInput, server_resp;
		while (true) {
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
			}
			// get response from server
			server_resp = in.readLine();
			// if resp says unique username then allow to chat
			if (server_resp == "0") break;
		}

		
	} catch (UnknownHostException e) {
		System.err.println("Don't know about host " + hostName);
		System.exit(1);
	} catch (IOException e) {
		System.err.println("Couldn't get I/O for the connection to " +
			hostName);
		System.exit(1);
	} 
	}
		
}