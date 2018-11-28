import java.net.Socket;

/**
 *
 */

public class PlayerHandler {
    private String name;
    private Socket socket;

    public PlayerHandler(String userName, Socket userSocket)
    {
        super();
        this.name = userName;
        this.socket = userSocket;
        this.password = userPassword;
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
}