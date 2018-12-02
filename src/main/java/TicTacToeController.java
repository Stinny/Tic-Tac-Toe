import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by lucasraza on 12/1/18.
 */
public class TicTacToeController implements Initializable {

    private static Socket socket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static Image Circle = new Image("./Circle.png");
    private static Image Cross = new Image("./Cross.png");
    private static ImageView icon, opponentIcon;

        @FXML
        Button topLeft, topRight, topCenter,
                midLeft, midCenter, midRight,
                botLeft, botMid, botRight;
        @FXML
        Button play;

        public void initialize(URL url, ResourceBundle rb) {

        }

        public Button buttonClickHandler(ActionEvent evt) throws Exception{
                Button clickedButton = (Button) evt.getTarget();

                String button =((Control)evt.getSource()).getId();
                System.out.println(button);

                if(button.equals("play")){
                    play();
                }
                else if (button.equals("topLeft")) {
                    out.print("MOVE0");
                    //do something
                } else if (button.equals("topRight")) {
                    //do something
                    out.print("MOVE2");
                } else if (button.equals("topCenter")) {
                    //do something
                    out.print("MOVE1");
                } else if (button.equals("midLeft")) {
                    //do something
                    out.print("MOVE3");
                } else if (button.equals("midCenter")) {
                    //do something
                    out.print("MOVE4");
                } else if (button.equals("midRight")) {
                    //do something
                    out.print("MOVE5");
                } else if (button.equals("botLeft")) {
                    //do something
                    out.print("MOVE6");
                } else if (button.equals("botMid")) {
                    //do something
                    out.print("MOVE7");
                } else if (button.equals("botRight")) {
                    //do something
                    out.print("MOVE8");
                }
            return clickedButton;



        }
        public void play() throws IOException {
            String response;

            try {
                connectToServer();
                response = in.readLine();
                System.out.println(in);
                while(response == null) {
                    if (response.startsWith("WELCOME")) {
                        char mark = response.charAt(8);
                        System.out.println("You are " + mark);

                        if (mark == 'X') {
                            icon = new ImageView(Circle);
                            opponentIcon = new ImageView(Cross);
                            System.out.println(icon + " " + opponentIcon);

                        } else {
                            icon = new ImageView(Cross);
                            opponentIcon = new ImageView(Circle);
                        }
                        //frame.setTitle("Tic Tac Toe - Player " + mark);
                    }
                }
                while (true) {
                    response = in.readLine();
                    if (response.startsWith("VALID_MOVE")) {
                        int location = Integer.parseInt(response.substring(5));
                        if(location == 0){
                            handleButtonClick(topLeft,icon);
                        }else if(location == 1)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 2)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 3)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 4)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 5)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 6)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 7)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 7)
                        {
                            handleButtonClick(topLeft,icon);
                        }
                        else if(location == 8){
                            handleButtonClick(topLeft,icon);
                        }
                    } else if (response.startsWith("OPPONENT_MOVED")) {
                        int location = Integer.parseInt(response.substring(15));
                        if(location == 0){
                            handleButtonClick(topLeft,opponentIcon);
                        }else if(location == 1)
                        {
                            handleButtonClick(topCenter,opponentIcon);
                        }
                        else if(location == 2)
                        {
                            handleButtonClick(topRight,opponentIcon);
                        }
                        else if(location == 3)
                        {
                            handleButtonClick(midLeft,opponentIcon);
                        }
                        else if(location == 4)
                        {
                            handleButtonClick(midCenter,opponentIcon);
                        }
                        else if(location == 5)
                        {
                            handleButtonClick(midRight,opponentIcon);
                        }
                        else if(location == 6)
                        {
                            handleButtonClick(botLeft,opponentIcon);
                        }
                        else if(location == 7)
                        {
                            handleButtonClick(botMid,opponentIcon);
                        }
                        else if(location == 8){
                            handleButtonClick(botRight,opponentIcon);
                        }
                    }

                    else if (response.startsWith("VICTORY")) {
                        //messageLabel.setText("You win");
                        break;
                    } else if (response.startsWith("DEFEAT")) {
                        //messageLabel.setText("You lose");
                        break;
                    } else if (response.startsWith("TIE")) {
                        //messageLabel.setText("You tied");
                        break;
                    }
                }
                out.println("QUIT");
            }
            finally {
                socket.close();
            }
        }
        public void handleButtonClick(Button button, ImageView mark){
            button.setGraphic(mark);
        }

    public static void connectToServer(){
        try {
            socket = new Socket("localhost", 7777);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

