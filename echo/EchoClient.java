import java.io.*;

/**
 * How to run:
 * java EchoClient seans-macbook-air-2.local 5000
 * Then can run separate clients in diff terminal windows
 */
public class EchoClient {

	private static String hostname; 
    public static void main(String[] args) throws IOException {
        
		// start gui
		// connect to hamachi

        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }


        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

		// only need multiple threads on client side for GUI etc...
		Thread t1 = new Thread(new Client_conn(hostName, portNumber));
		t1.start();

		// ask for username
		// verify if unique
		// if so allow to chat

        
    }

}