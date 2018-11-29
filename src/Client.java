/**
 * Created by lucasraza on 11/27/18.
 */
import java.net.*;
import java.io.*;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private Board board = new Board();
    private BufferedReader clientInput;
    private PrintWriter out;
    private static gameStateTest gameState;
    private Status status;

    public Client(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new PrintWriter(socket.getOutputStream(),true);
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    public void setStatus(){
        try {
            sendMove("3");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Boolean token = input.ready();
            if(token){
                status = Status.CROSS;
            }else if(!token);
            {
                status = Status.CIRCLE;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public Status getStatus(){

        return this.status;
    }
    public DataInputStream getInputStream(){
        return input;
    }

    public void sendMove(String code) throws IOException{
        out.write(code);
    }

    public boolean updateOppMove(){
       boolean flag = false;
        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if(status==Status.CROSS){
                board.setCoordX(input.read(),input.read());
                flag = true;
            }
            else
                board.setCoordO(input.read(),input.read());
                flag = true;

        }catch(IOException i)
        {
            i.printStackTrace();
        }
        return flag;

    }
    public Board updateBoard(){
        return board;
    }
    public void checkGameState(Status currStatus) {
        if (board.checkWin(currStatus)) { //if there's a win on the board do this
            gameState = (currStatus == Status.CROSS) ? gameState.CROSSWIN : gameState.CIRCLEWIN; //if currState/turn is CROSS, CROSS WINS; else, CIRCLE WINS
        } else if (board.checkDraw()) {
            gameState = gameStateTest.DRAW;
        }

      //  checkGameState(p1.getStatus());

       // checkGameState(p2.getStatus());
    }

}

