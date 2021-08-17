import java.net.*;
import java.io.*;

/**
 * How to run:
 * java EchoServer 5000
 */
public class EchoServer {

	// stores info on all connected clients
	private static String[] bigd;
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int port_number = Integer.parseInt(args[0]);

		ServerSocket serv_sock = null;
		Socket client_sock = null;
		/**
		 * open single server socket. The main thread listens on the given port for incoming 
		 * client connections and creates a new thread with a connection to the server for each 
		 * one that comes in
		 */
		try {
			// this should be in () after try maybe?
			serv_sock = new ServerSocket(port_number);
		} catch (IOException e) {
			System.err.println("Error when attempting to listen on port" + port_number);
			e.printStackTrace();
		}

		while (true) {
			client_sock = serv_sock.accept();
			System.out.println("Server socket opened successfully on port " + port_number);
			Thread t = new Thread(new Server_conn(port_number, client_sock));
			t.start();
		}
    }
}