import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by lucasraza on 3/8/17
 *
 *
 */
public class Server {

	private static HashMap<String, PlayerHandler> matching;

	private static ArrayList<Socket> sockets;


	public static void main(String[] args){

		int PORT = 2000;
		// Have the server run until it is killed.
		while(true)
		{
			// Monitor connections to PORT (if it is available).
			try
			{
				ServerSocket serverSocket = new ServerSocket(PORT);
				System.out.println("The server is now running on port " + PORT + "...");

				Socket connectionSocket = serverSocket.accept();
				System.out.println("A user has connected from " + connectionSocket.getInetAddress());

				BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				DataOutputStream serverOutput = new DataOutputStream(connectionSocket.getOutputStream());

				//Gets user's info and connects player
				matching = connectPlayer(serverSocket);

				//Match players
				while(!(matching.isEmpty()){



			}

				//Create Game

				//Store above process as a thread in a HashMap

				//Tic Tac Toe Room Implementation


				// Create a server-game based version of TicTacToe.
//				ServerGame game = new ServerGame(clientInput, serverOutput);

			}

			// Catch any binding/IO errors that may occur.
			catch(IOException e)
			{
				System.err.println(e);
			}
	}





}

	private static HashMap<String, PlayerHandler> connectPlayer(ServerSocket serverSocket){
		PlayerHandler player;
		HashMap<String, PlayerHandler> matching = new HashMap<>();
			try {
				Socket s;
				BufferedReader receive = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String userName = receive.readLine();
				String password = receive.readLine();
				if ((s = serverSocket.accept()) != null) {
					player = createUser(userName, password,s);
				}
			}catch(IOException e){
				System.out.println(e);

			}

			matching.put(sockets.toString(), player);


			return matching;
	}



	public static PlayerHandler createUser(String userName, String password, Socket s){


			PlayerHandler player = new PlayerHandler(userName,password,s);

			sockets = new ArrayList<>();
			sockets.add(s);

		    return player;

	}

	public boolean connected(String userName, Socket chatSocket) {
		if (chatSocket.isConnected()) {
			System.out.println(userName + " has connected to server on port: " + chatSocket.getLocalPort() + ". IP Address: " + chatSocket.getInetAddress());

			return true;
		} else {
			return false;
		}
	}



public class ServerGame{

	public boolean connected = false;

	public ArrayList<String> sockets = new ArrayList<String>();

	private boolean paired = false;

	private BufferedReader input;

	private DataOutputStream output;

	private ArrayList<Thread> threads = new ArrayList<Thread>();

	public ServerGame(BufferedReader clientInput, DataOutputStream serverOutput){
		input = clientInput;
		output = serverOutput;
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
				while ((message = r.readLine()) != null) {
					if (message.equals(Protocol.MAKE_MOVE)) {
						String move = in.readLine();
						String[] l = move.split(" ");

						board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "X");
						out2.println(Protocol.MOVE_MADE);
						out2.println(move);
						switch(board.getStatus()) {
							case "X":
								out2.println(Protocol.GAME_LOST);
								out.println(Protocol.GAME_WON);
								break;
							case "O":
								out2.println(Protocol.GAME_WON);
								out.println(Protocol.GAME_LOST);
								break;
							case "tie":
								out2.println(Protocol.GAME_TIED);
								out.println(Protocol.GAME_TIED);
								break;
						}

					}
				}
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

	private void write(String message, Socket socket, String name) {
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


}
