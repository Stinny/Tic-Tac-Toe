/**
 * Created by lucasraza on 11/27/18.
 */
import java.net.*;
import java.io.*;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private Board board = new Board();
    private BufferedReader clientInput;

    public Client(String address, int port)
    {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    public DataInputStream getInputStream(){
        return input;
    }
    public DataOutputStream getOutputStream(){
        return out;
    }
    public void sendMove(int code) throws IOException{
        out.writeInt(code);
    }
    public void recieveMove(int cordX, int cordY){
       // try {
        //    //int clientInput.readLine();
       // }catch(IOException e){
       //     e.printStackTrace();
       // }

    }
    public Board updateBoard(){
        return board;
    }
}
