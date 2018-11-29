
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by lucasraza on 3/8/17
 *
 *
 */
public class Server {
    private static ServerSocket serverSocket;
    public static PlayerHandler player;
    public static LinkedList<PlayerHandler> matching;
    public static HashMap<String, Game> games;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public static void main(String[] args) {
        int PORT = 3000;
        // Have the server run until it is killed.

            // Monitor connections to PORT (if it is available).
            try {
                System.out.println("Starting server on port# " + PORT);
                Server server = new Server(PORT);

                //System.out.println("The server is now running on port " + PORT + "...");
                int i = 0;
                matching = new LinkedList<>();
                boolean x = true;
                while (true) {

                    Socket connectionSocket = serverSocket.accept();
                    System.out.println("A user has connected from " + connectionSocket.getInetAddress());

                    BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                    DataOutputStream serverOutput = new DataOutputStream(connectionSocket.getOutputStream());

                    if (connectionSocket!= null) {
                        PlayerHandler player;
                        if(x) {
                           player = createUser(connectionSocket, Status.CROSS);
                        }else
                            player = createUser(connectionSocket, Status.CIRCLE);

                        matching.add(player);
                    }

                    if(matching.size()==2) {
                        PlayerHandler p1 = matching.removeLast();
                        PlayerHandler p2 = matching.removeLast();

                        p1.setPiece(Status.CROSS);
                        p2.setPiece(Status.CIRCLE);

                        Game game = new Game(Integer.toString(i), p1, p2);
                        i++;

                        System.out.println(p1.getStatus() + " connected");
                        System.out.println(p2.getStatus() + " connected");
                        game.start();

                        String name = Integer.toString(i);

                        games = new HashMap<>();
                        games.put(name, game);
                        System.out.println("game made and stored");
                    }
                }

            } catch (IOException e) {
                System.err.println(e);
            }

  }
    public static PlayerHandler createUser(Socket s, Status status) {
        player = new PlayerHandler(s, status);
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
}

    class PlayerHandler extends Thread{
        char mark;
        private String name;
        private Socket socket;
        private Status mark;

        PlayerHandler opponent;

        public PlayerHandler(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            try{
            // initialize input and output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            } catch(IOException e)
        }

        public void setPiece(Status content){
            this.content = content;
        }

        public void setOpponent(PlayerHandler opponent){
            this.opponent = opponent;

        }

        public void otherPlayerMoved(int location){
            out.println("OPPONENT_MOVED" + location);
            out.println(
                    hasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
        }

        public void turn() {
            String clientResponse;
            try {

                    // read and parse Client response
                    clientResponse = in.readLine();
                    parseCode(clientResponse, out);

            } catch (IOException e) {
                e.printStackTrace();
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

        public void parseCode(String clientResponse, PrintWriter out) throws Exception {
            String[] params = clientResponse.split(",");
            int code = Integer.parseInt((params[0]));

            out = new PrintWriter(socket.getOutputStream(),true);

            switch (code) {
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

                        String x = Integer.toString(cordX);
                        String y = Integer.toString(cordY);

                        out.println(x);
                        out.flush();
                        out.println(y);
                        out.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case(2):
                      try {
                            String winner = params[1];
                            end = true;
                            socket.close();
                      }
                      catch(IOException i) {
                          i.printStackTrace();
                      }
                case(3):
                        if(getStatus() == Status.CROSS) {
                            System.out.println("Yes");
                            out.print(true);
                        }else if(getStatus() == Status.CIRCLE){
                            System.out.println("Yes");
                            out.print(false);
                        }
            }
        }

        public boolean getEnd(){
            end = true;
            return end;

        }
        public void setUserName(String username) {
            this.name = username;
        }

        public String getUserName() {
            return name;
        }

        public Socket getSocket() {
            return socket;
        }

        public Status getStatus() {
            return content;
        }

        public int getCoordX() {
            return this.coordX;
        }

        public int getCoordY() {
            return this.coordY;
        }

        public void setCoordX(int coordX) {
            this.coordX = coordX;
        }

        public void setCoordY(int coordY) {
            this.coordY = coordY;
        }
    }

    class Game extends Thread {
        String name;
        Thread t;
        boolean playing = true;

        private Status currPlayer;
        PlayerHandler p1, p2;

        public Game(String thread, PlayerHandler p1, PlayerHandler p2) {
            name = thread;
            t = new Thread(this, name);
            this.p1 = p1;
            this.p2 = p2;
        }

        public void run() {
            boolean turn = true;
            do {
                if (turn) {
                    move(p1);
                    turn = false;
                } else {
                    move(p2);
                    turn = true;
                }
            } while (p1.getEnd() || p2.getEnd());
        }

        public void move(PlayerHandler player) { //A player moves based on their assigned Piece (status)
            boolean turnX = true;
            boolean turnEnd = false;
            do {
                if (player.getStatus() == Status.CROSS) {
                    player.turn();

                    //System.out.println("Player 'X', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                } else if (player.getStatus() == Status.CIRCLE) {
                    player.turn();
                    //System.out.println("Player 'O', enter your coordinates (row[0 - 2], col[0 - 2]): ");
                }

                int row = player.getCoordX();
                int col = player.getCoordY();

                Socket s = player.getSocket();

            } while (!turnEnd); //
        }
}

