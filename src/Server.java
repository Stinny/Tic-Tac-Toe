import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by lucasraza on 3/8/17
 *
 *
 */
public class Server {


	public static LinkedList<PlayerHandler> matching;

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

                //Create Game
                //Store above process as a thread in a HashMap
                //Tic Tac Toe Room Implementation
				// Create a server-game based version of TicTacToe.
				//ServerGame game = new ServerGame(clientInput, serverOutput);

			}catch(IOException e)
			{
				System.err.println(e);
			}
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
					player = createUser(userName, password,s);
                    matching.add(player);
				}
			}catch(IOException e){
				System.out.println(e);

			}
			return matching;
	}



	public static PlayerHandler createUser(String userName, String password, Socket s){
			PlayerHandler player = new PlayerHandler(userName,password,s);
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

    public class ServerGame{

	public boolean connected = false;

	private BufferedReader input;

	private DataOutputStream output;




	public ServerGame(PlayerHandler p1, PlayerHandler p2){
		p1.getSocket();
		p2.getSocket();
	}
}

    public class startGame extends Thread{

	    Thread t;
	    private PlayerHandler p1, p2;

	    public startGame(PlayerHandler p1, PlayerHandler p2)
        {
            this.p1 = p1;
            this.p2 = p2;
            t.start();

        }

	    public void run(){
            while(true){
                if(matching.size()>=2)
                {
                    p1 = matching.remove();
                    p2 = matching.remove();
                    GameThread()
                }

            }

        }
    }
    public class GameThread implements Runnable{
	    String name;
	    Thread t;
        private Board myBoard;
        private gameStateTest gameState;
        private Status currPlayer;

	    public GameThread(String thread, PlayerHandler p1, PlayerHandler p2){
	        name = thread;
            t = new Thread(this,name);

            t.start();
        }
        public void run() {

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
        public void playerMove (Status currStatus) { //A player moves based on their assigned Piece (status)
            boolean turnEnd = false;
            do {
                if (currStatus == Status.CROSS) {
                    //Player 1 move
                    System.out.println("Player 'X', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                } else {
                    //Player 2 move
                    System.out.println("Player 'O', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                }
                int row = input.nextInt();
                int col = input.nextInt();
                if (row >= 0  && row < Board.maxRows && col >= 0 && col < Board.maxCol && myBoard.board[row][col].content == Status.EMPTY) {
                    myBoard.board[row][col].content = currStatus;
                    myBoard.currRow = row;
                    myBoard.currCol = col;
                    turnEnd = true;
                } else{
                    System.out.println("Invalid move");
                }
            } while (!turnEnd);
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
