
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
    private static ServerSocket serverSocket;
    private static Board myBoard;
    private static gameStateTest gameState;
    private static Board board = new Board();

    public static PlayerHandler player;

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
                Server server = new Server(PORT);

                serverSocket.accept();
				//System.out.println("The server is now running on port " + PORT + "...");

				Socket connectionSocket = serverSocket.accept();
				//System.out.println("A user has connected from " + connectionSocket.getInetAddress());

				BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				DataOutputStream serverOutput = new DataOutputStream(connectionSocket.getOutputStream());

				//Gets user's info and connects player
				matching = connectPlayer(serverSocket);

				//Store matched players in a HashMap

                PlayerHandler p1 = matching.removeLast();
                PlayerHandler p2 = matching.removeLast();
                StartGame game = new StartGame(p1,p2);

                //Create Game
                Thread newGame = new Thread(game);
                newGame.start();

                //Store above process as a thread in a HashMap
                games.put(newGame.getName(), newGame);
                //Tic Tac Toe Room Implementation
                //games.
			}catch(IOException e)
			{
				System.err.println(e);
			}
	}
}
    public static void listenForClient(){

    }
    public void checkGameState(Status currStatus) {
        if (myBoard.checkWin(currStatus)) { //if there's a win on the board do this
            gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
        } else if (myBoard.checkDraw()) {
            gameState = gameStateTest.DRAW;
        }
    }
	public LinkedList<PlayerHandler> connectPlayer(ServerSocket serverSocket){
		LinkedList<PlayerHandler> matching = new LinkedList<>();
			try {
				Socket s = serverSocket.accept();
				BufferedReader receive = new BufferedReader(new InputStreamReader(s.getInputStream()));
				if ((s = serverSocket.accept()) != null) {
					player = createUser(player.getUserName(), s, Status.EMPTY );
                    matching.add(player);
				}
			}catch(IOException e){
				System.out.println(e);

			}
			return matching;
	}
	public static PlayerHandler createUser(String userName, Socket s, Status status){
            player = new PlayerHandler(s,status);
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
    public class StartGame implements Runnable{

	    private PlayerHandler p1, p2;

	    public StartGame(PlayerHandler p1, PlayerHandler p2)
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
    public class PlayerHandler implements Runnable {
        private String name;
        private Socket socket;
        private Status content;
        private int coordX;
        private int coordY;

        public PlayerHandler(Socket userSocket, Status content)
        {
            this.socket = userSocket;
            this.content = content;
        }

        public void run(){
            String clientResponse;
            try {
                // initialize input and output streams
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    // read and parse Client response
                    clientResponse = in.readLine();
                    parseCode(clientResponse, out);
                }
            } catch (IOException e) {
                log(e);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public void parseCode(String clientResponse, PrintWriter out) throws Exception{
            String[] params = clientResponse.split(",");
            int code = Integer.parseInt((params[0]));

            switch(code) {
                case (0):
                    try {
                        String username = params[1];
                        setUserName(username);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case (1):
                    try {
                        int cordX = Integer.parseInt(params[1]);
                        int cordY = Integer.parseInt(params[2]);

                        int cCode = 0;

                        String x = Integer.toString(cordX);
                        String y = Integer.toString(cordY);
                        String code = Integer.toString(cCode);

                        out.println(x);
                        out.flush();
                        out.println(y);
                        out.flush();
                        out.println(code);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
        public void setUserName(String username){
            this.name = username;
        }
        public String getUserName(){
            return name;
        }
        public Socket getSocket(){
            return socket;
        }
        public void changeUserName(String newUser) {
            name = newUser;
        }
        public Status getStatus(){
            return content;
        }
        public int getCoordX(){
            return this.coordX;
        }
        public int getCoordY(){
            return this.coordY;
        }
        public void setCoordX(int coordX){
            this.coordX = coordX;
        }
        public void setCoordY(int coordY){
            this.coordY = coordY;
        }
    }
    public class Game extends Thread{
	    String name;
	    Thread t;
        private gameStateTest gameState;
        private Status currPlayer;
        PlayerHandler p1, p2;

	    public Game(String thread, PlayerHandler p1, PlayerHandler p2){
	        name = thread;
            t = new Thread(this,name);
            this.p1 = p1;
            this.p2 = p2;
        }
        public void run(){
            startGame();
            boolean turn = true;
            do {
                if(turn) {
                    move(p1);
                    turn = false;
                }else {
                    move(p2);
                    turn = true;
                }
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
        public void move (PlayerHandler player) { //A player moves based on their assigned Piece (status)
            boolean turnX = true;
            boolean turnEnd = false;
            do {
                if (turnX==true && player.content == Status.CROSS) {
                    System.out.println("Player 'X', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                } else {
                    System.out.println("Player 'O', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                }

                int row = player.getCoordX();
                int col = player.getCoordY();
                if (row >= 0  && row < Board.maxRows && col >= 0 && col < Board.maxCol && myBoard.board[row][col].content == Status.EMPTY) {
                    board.board[row][col].content = currStatus;
                    board.currRow = row;
                    board.currCol = col;
                    turnEnd = true;
                } else{
                    System.out.println("Invalid move");
                }
            } while (!turnEnd); //
        }
        public void startGame() {
            board.startNew();
            currPlayer = Status.CROSS;
            gameState = gameStateTest.PLAYING;
        }
        public void checkGameState(Status currStatus) {
            if (board.checkWin(currStatus)) { //if there's a win on the board do this
                gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
            } else if (myBoard.checkDraw()) {
                gameState = gameStateTest.DRAW;
            }
        }
    }
}
