import java.net.Socket;

/**
 *
 */

public class PlayerHandler {
    private String name;
    private Socket socket;

    public PlayerHandler(String userName, Socket userSocket)
    {
        this.name = userName;
        this.socket = userSocket;
    }
    public String getUserName(){
        return name;
    }
    public Socket getSocke(){
        return socket;
    }
}