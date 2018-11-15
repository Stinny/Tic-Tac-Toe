import java.net.Socket;

/**
 *
 */

public class PlayerHandler {
    private String name;
    private Socket socket;
    private String password;

    public PlayerHandler(String userName, String userPassword, Socket userSocket)
    {
        this.name = userName;
        this.socket = userSocket;
        this.password = userPassword;
    }
    public String getUserName(){
        return name;
    }

    public String getPassword(){ return password;}

    public Socket getSocke(){
        return socket;
    }
}