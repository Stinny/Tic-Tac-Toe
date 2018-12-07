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

    @FXML
    public void handleSquareOneClick(ActionEvent event) {
        out.println("MOVE0");
        System.out.println("topleft");
    }

    @FXML
    public void handleSquareTwoClick(ActionEvent event) {
        out.println("MOVE1");
        System.out.println("top center");
    }

    @FXML
    public void handleSquareThreeClick(ActionEvent event) {
        out.println("MOVE2");
        System.out.println("top right");
    }

    @FXML
    public void handleSquareFourClick(ActionEvent event) {
        out.println("MOVE3");
        System.out.println("mid left");
    }

    @FXML
    public void handleSquareFiveClick(ActionEvent event) {
        out.println("MOVE4");
        System.out.println("mid center");
    }

    @FXML
    public void handleSquareSixClick(ActionEvent event) {
        out.println("MOVE5");
        System.out.println("mid right");
    }

    @FXML
    public void handleSquareSevenClick(ActionEvent event) {
        out.println("MOVE6");
        System.out.println("bottom left");
    }

    @FXML
    public void handleSquareEightClick(ActionEvent event) {
        out.println("MOVE7");
        System.out.println("bottom center");
    }

    @FXML
    public void handleSquareNineClick(ActionEvent event) {

        out.println("MOVE8");
        System.out.println("bottom right");
    }

    @FXML
    public void handlePlayClick(ActionEvent event){
        try {
            play();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        public void play() throws IOException {
            String welcome;
            try {
                connectToServer();
                welcome = in.readLine();
                System.out.println(welcome);

                        char mark = welcome.charAt(7);
                        System.out.println("You are " + mark);

                        if (mark == 'X') {
                            icon = new ImageView(Cross);
                            opponentIcon = new ImageView(Circle);

                        } else {
                            icon = new ImageView(Circle);
                            opponentIcon = new ImageView(Cross);
                        }
                        //frame.setTitle("Tic Tac Toe - Player " + mark);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        public void handleButtonClick(Button button, ImageView mark){
            button.setGraphic(mark);
        }


    public int playerMove(String response){
        if (response.startsWith("VALID_MOVE")) {
            int location = Integer.parseInt(response.substring(5));
            if (location == 0) {
                handleButtonClick(topLeft, icon);
                return 0;
            } else if (location == 1) {
                handleButtonClick(topCenter, icon);
                return 1;
            } else if (location == 2) {
                handleButtonClick(topRight, icon);
                return 2;
            } else if (location == 3) {
                handleButtonClick(midLeft, icon);
                return 3;
            } else if (location == 4) {
                handleButtonClick(midCenter, icon);
                return 4;
            } else if (location == 5) {
                handleButtonClick(midRight, icon);
                return 5;
            } else if (location == 6) {
                handleButtonClick(botLeft, icon);
                return 6;
            } else if (location == 7) {
                handleButtonClick(botMid, icon);
                return 7;
            } else if (location == 8) {
                handleButtonClick(botRight, icon);
                return 8;
            }
        }
        return -1;
    }
    public int opponentMoved(String response){
            if (response.startsWith("OPPONENT_MOVED")) {
            int location = Integer.parseInt(response.substring(15));
            if (location == 0) {
                handleButtonClick(topLeft, opponentIcon);
            } else if (location == 1) {
                handleButtonClick(topCenter, opponentIcon);
            } else if (location == 2) {
                handleButtonClick(topRight, opponentIcon);
            } else if (location == 3) {
                handleButtonClick(midLeft, opponentIcon);
            } else if (location == 4) {
                handleButtonClick(midCenter, opponentIcon);
            } else if (location == 5) {
                handleButtonClick(midRight, opponentIcon);
            } else if (location == 6) {
                handleButtonClick(botLeft, opponentIcon);
            } else if (location == 7) {
                handleButtonClick(botMid, opponentIcon);
            } else if (location == 8) {
                handleButtonClick(botRight, opponentIcon);
            }
        } else if (response.startsWith("VICTORY")) {
            //messageLabel.setText("You win");

        } else if (response.startsWith("DEFEAT")) {
            //messageLabel.setText("You lose");

        } else if (response.startsWith("TIE")) {
            //messageLabel.setText("You tied");

        }
        return -1;
    }

    public static void connectToServer(){
        try {
            socket = new Socket("localhost", 7777);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e + " fuck");
        }
    }
}

