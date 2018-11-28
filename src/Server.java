
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by lucasraza on 3/8/17
 *
 *
 */
public class Server {
    private ServerSocket serverSocket;
    Board myBoard;
    private gameStateTest gameState;

	public static LinkedList<PlayerHandler> matching;

	public static HashMap<String, Thread> games;

	public Server(int port) throws IOException{
	    serverSocket = new ServerSocket(port);
    }
	public static void main(String[] args){

		int PORT = 2000;
		// Have the server run until it is killed.
		while(true)
		{
			// Monitor connections to PORT (if it is available).
			try
			{
			    int port = Integer.parseInt(args[0]);
			    try{
                    ServerSocket serverSocket = new ServerSocket(PORT);
			        new server(port).acceptClients();
                } catch(IOException e){
			        e.printStackTrace();
                }


				//System.out.println("The server is now running on port " + PORT + "...");

				Socket connectionSocket = serverSocket.accept();
				//System.out.println("A user has connected from " + connectionSocket.getInetAddress());

				BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				DataOutputStream serverOutput = new DataOutputStream(connectionSocket.getOutputStream());

				//Gets user's info and connects player
				matching = connectPlayer(serverSocket);

				//Store matched players in a HashMap
                startGame game = new startGame(matching.remove(),matching.remove());


                //Create Game
                Thread game = new Thread(game);
                game.start();


                //Store above process as a thread in a HashMap
                games.put(game.getName(), game);

                //Tic Tac Toe Room Implementation
                games.

			}catch(IOException e)
			{
				System.err.println(e);
			}
	}
}

    public void checkGameState(Status currStatus) {
        if (myBoard.checkWin(currStatus)) { //if there's a win on the board do this
            gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
        } else if (myBoard.checkDraw()) {
            gameState = gameStateTest.DRAW;
        }
    }

	public static LinkedList<PlayerHandler> connectPlayer(ServerSocket serverSocket){
		PlayerHandler player;
		LinkedList<PlayerHandler> matching = new LinkedList<>();
			try {
				Socket s;
				BufferedReader receive = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String userName = receive.readLine();
				String password = receive.readLine();
				if ((s = serverSocket.accept()) != null) {
					player = createUser(userName, s, status);
                    matching.add(player);
				}
			}catch(IOException e){
				System.out.println(e);

			}
			return matching;
	}

	public static PlayerHandler createUser(String userName, Socket s, Status status){
			PlayerHandler player = new PlayerHandler(userName,s,status);
		    return player;
	}

	private boolean connected(String userName, Socket chatSocket) {
		if (chatSocket.isConnected()) {
			System.out.println(userName + " has connected to server on port: " + chatSocket.getLocalPort() + ". IP Address: " + chatSocket.getInetAddress());
			return true;
		} else {
			return false;
		}
	}


    public static class startGame implements Runnable{

	    private PlayerHandler p1, p2;

	    public startGame(PlayerHandler p1, PlayerHandler p2)
        {
            this.p1 = p1;
            this.p2 = p2;

        }

	    public void run(){
	        int i = 0;

            while(true){

                if(matching.size()>=2)
                {
                    p1 = matching.remove();
                    p2 = matching.remove();
                    i++;
                    Game game = new Game(Integer.toString(i),p1,p2);
                    game.startGame();
                }

            }

        }
        public void start(){
	        System.out.print("New Game Started with " + p1.getUserName() + " and " + p2.getUserName());
        }
    }
    public class Game extends Thread{
	    String name;
	    Thread t;
        private Board myBoard;
        private gameStateTest gameState;
        private Status currPlayer;

	    public GameThread(String thread, PlayerHandler p1, PlayerHandler p2){
	        name = thread;
            t = new Thread(this,name);
        }
        public void run(){

            Board myBoard = new Board();

            startGame();

            do {
                playerMove(currPlayer);
                checkGameState(currPlayer);
                if (gameState == gameStateTest.CROSSWIN) {
                    System.out.println("Cross won");
                } else if (gameState == gameStateTest.CIRCLEWIN){
                    System.out.println("Circle won");
                } else if (gameState == gameStateTest.DRAW) {
                    System.out.println("It's a draw");
                }

                currPlayer = (currPlayer == Status.CROSS) ? Status.CIRCLE : Status.CROSS; // if currplayer is cross, it is circle's turn, otherwise, cross' turn
            } while (gameState == gameStateTest.PLAYING);



        }
        public void startGame() {
            myBoard.startNew();
            currPlayer = Status.CROSS;
            gameState = gameStateTest.PLAYING;
        }


        public void checkGameState(Status currStatus) {
            if (myBoard.checkWin(currStatus)) { //if there's a win on the board do this
                gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
            } else if (myBoard.checkDraw()) {
                gameState = gameStateTest.DRAW;
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
