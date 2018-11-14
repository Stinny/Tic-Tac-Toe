import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lucasraza on 3/8/17.
 */
public class Server {

	private static boolean connected = false;
	private static ArrayList<String> sockets = new ArrayList<String>();

	private static HashMap<String, PlayerHandler> playerMap;

	private static BufferedReader receive;

	private static ArrayList<Thread> threads = new ArrayList<Thread>();

	public static void main(String[] args) throws java.io.IOException {

		playerMap = new HashMap<>();

		ServerSocket serverSocket = new ServerSocket(2000);

		Socket s;
		while(!(playerMap.isEmpty())) {
			if ((s = serverSocket.accept())!=null) {

				receive = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String userName = receive.readLine();
				try {

					if(connected(userName,s)) {
						sockets.add(s.toString());

						playerMap userHandler = new playerMap(userName, s);

						userMap.put(s.toString(), userHandler);

						Thread send = new Thread(new ReceiveThread(s));
						send.start();
						threads.add(send);
					}
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
	}

	static class ReceiveThread implements Runnable {
		Socket socket = null;
		BufferedReader receive;

		public ReceiveThread(Socket chatSocket) {
			socket = chatSocket;

		}

		public void run() {
			String message;

			try (BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				while (true) {
					message = r.readLine();
					for (String object : sockets) {
						if (!(object.equals(socket.toString()))) {
							write(message, userMap.get(object).getSocket(), userMap.get(socket.toString()).getUserName());
						}
					}
				}


			} catch (IOException e) {System.out.println(e);
			}
		}
	}
	private static void write(String message, Socket socket, String name) {
		//Why upon closing the PrintWrite does the socket connection close
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			if (message != null) {
				System.out.println(name + ": " + message);
				out.println(name + ": " + message);
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}//closes write method

	static public boolean connected(String userName, Socket chatSocket) {
		if (chatSocket.isConnected()) {
			System.out.println(userName + " has connected to server on port: " + chatSocket.getLocalPort() + ". IP Address: " + chatSocket.getInetAddress());

			return true;
		} else {
			return false;
		}
	}
}
