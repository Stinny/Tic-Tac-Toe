import java.io.*;

import java.net.Socket;
import java.net.URL;
import javax.imageio.ImageIO;
import javafx.application.Application;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Priority;

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.net.*;

import java.io.*;



public class TicTacToeGUI extends Application{

	private Scene sc1;
	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;
	private static int PORT = 4444;
	private static Button topCenter, topLeft, topRight, midLeft, midCenter, midRight, botLeft, botMid, botRight;
	private static ImageView icon;
	private static ImageView opponentIcon;
	private static String zero, one, two, three, four, five, six, seven, eight;


	private static Image Circle = new Image("Circle.png");
	private static Image Cross = new Image("Cross.png");

	Parent buildScene() {

		GridPane gridPane = new GridPane();

		gridPane.setAlignment(Pos.CENTER);

		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		gridPane.setPadding(new Insets(100));

		topLeft = new Button();
		zero = topLeft.toString();

		topLeft.setPrefHeight(Cross.getHeight());
		topLeft.setPrefWidth(Cross.getWidth());

		topLeft.setMaxWidth(Double.MAX_VALUE);

		topLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topLeft, 0, 0);

		GridPane.setHgrow(topLeft, Priority.ALWAYS);

		GridPane.setVgrow(topLeft, Priority.ALWAYS);




		topCenter = new Button();
		one = topCenter.toString();

		topCenter.setPrefHeight(145);
		topCenter.setPrefWidth(145);

		topCenter.setMaxWidth(Double.MAX_VALUE);

		topCenter.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topCenter, 0, 1);

		GridPane.setHgrow(topCenter, Priority.ALWAYS);

		GridPane.setVgrow(topCenter, Priority.ALWAYS);


		topRight  = new Button();
		two = topRight.toString();

		topRight.setPrefHeight(145);
		topRight.setPrefWidth(145);

		topRight.setMaxWidth(Double.MAX_VALUE);

		topRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topRight, 0, 2);

		GridPane.setHgrow(topRight, Priority.ALWAYS);

		GridPane.setVgrow(topRight, Priority.ALWAYS);


		midLeft = new Button();
		three = midLeft.toString();

		midLeft.setPrefHeight(145);
		midLeft.setPrefWidth(145);

		midLeft.setMaxWidth(Double.MAX_VALUE);
		midLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midLeft, 1, 0);

		GridPane.setHgrow(midLeft, Priority.ALWAYS);

		GridPane.setVgrow(midLeft, Priority.ALWAYS);


		midCenter = new Button();
		four = midCenter.toString();

		midCenter.setPrefHeight(145);
		midCenter.setPrefWidth(145);

		midCenter.setMaxWidth(Double.MAX_VALUE);

		midCenter.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midCenter, 1, 1);

		GridPane.setHgrow(midCenter, Priority.ALWAYS);

		GridPane.setVgrow(midCenter, Priority.ALWAYS);


		midRight  = new Button();
		five = midCenter.toString();

		midRight.setPrefHeight(140);
		midRight.setPrefWidth(140);

		midRight.setMaxWidth(Double.MAX_VALUE);

		midRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midRight, 1, 2);

		GridPane.setHgrow(midRight, Priority.ALWAYS);

		GridPane.setVgrow(midRight, Priority.ALWAYS);


		botLeft = new Button();
		six = midCenter.toString();

		botLeft.setPrefHeight(145);
		botLeft.setPrefWidth(145);

		botLeft.setMaxWidth(Double.MAX_VALUE);

		botLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botLeft, 2, 0);

		GridPane.setHgrow(botLeft, Priority.ALWAYS);

		GridPane.setVgrow(botLeft, Priority.ALWAYS);

		botMid = new Button();
		seven = botMid.toString();

		botMid.setPrefHeight(145);
		botMid.setPrefWidth(145);

		botMid.setMaxWidth(Double.MAX_VALUE);

		botMid.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botMid, 2, 1);

		GridPane.setHgrow(botMid, Priority.ALWAYS);

		GridPane.setVgrow(botMid, Priority.ALWAYS);


		botRight  = new Button();
		eight = botRight.toString();

		botRight.setPrefHeight(145);
		botRight.setPrefWidth(145);

		botRight.setMaxWidth(Double.MAX_VALUE);

		botRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botRight, 2, 2);

		GridPane.setHgrow(botRight, Priority.ALWAYS);

		GridPane.setVgrow(botRight, Priority.ALWAYS);

		VBox vBox = new VBox(20);

		vBox.getChildren().addAll(gridPane);

		VBox.setVgrow(gridPane, Priority.ALWAYS);

		vBox.setAlignment(Pos.CENTER);

		vBox.setPadding(new Insets(0));



		return vBox;
	}

	public static void handleButtonClick(Button button, ImageView mark){
			button.setGraphic(mark);

			if(button.toString().equals(zero)){
				out.println("MOVE0");
			}
			else if(button.toString().equals(one)){
			out.println("MOVE1");
			}
			else if(button.toString().equals(two)){
			out.println("MOVE2");
			}
			else if(button.toString().equals(three)){
			out.println("MOVE3");
			}
			else if(button.toString().equals(four)){
			out.println("MOVE4");
			}
			else if(button.toString().equals(five)){
			out.println("MOVE5");
			}
			else if(button.toString().equals(six)){
			out.println("MOVE6");
			}
			else if(button.toString().equals(seven)){
			out.println("MOVE7");
			}
			else if(button.toString().equals(eight)){
			out.println("MOVE8");
			}
	}




	@Override

	public void start(Stage primaryStage) throws Exception {

		Scene sc2 = new Scene(buildScene());

		//Scene 1

		TextField playerName = new TextField();

		HBox hbox = new HBox();

		Button play = new Button("PLAY");

		hbox.getChildren().addAll(play, playerName);

		play.setOnAction(e ->

				primaryStage.setScene(sc2));

		VBox layout1 = new VBox(20);

		layout1.getChildren().addAll(playerName, play);

		sc1= new Scene(layout1, 300, 250);

		primaryStage.setScene(sc1);

		primaryStage.show();



	}




	// if checkWin() returns true the game should end and the current player's
	// turn is the winner
	// if false continue whatever loop keeping the game running

	public static void main(String[] args) throws Exception {
				launch(args);
		  		StartTicTacToe("127.0.0.1");
	}



		public static void StartTicTacToe(String serverAddress) {
			try {
				socket = new Socket(serverAddress, 12345);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				play();
			} catch (IOException e) {
				System.out.println(e);
			}

		}


		public static void play() throws IOException {
			String response;
			try {
				response = in.readLine();
				if (response.startsWith("WELCOME")) {
					char mark = response.charAt(8);


					if (mark == 'X') {
						icon = new ImageView(Cross);
						opponentIcon = new ImageView(Circle);

					} else {
						icon = new ImageView(Circle);
						opponentIcon = new ImageView(Cross);
					}


					//frame.setTitle("Tic Tac Toe - Player " + mark);
				}
				while (true) {
					response = in.readLine();
					if (response.startsWith("VALID_MOVE")) {
						int location = Integer.parseInt(response.substring(5));
						if(location == 0){
							handleButtonClick(topLeft,icon);
							System.out.println("clicked");
						}else if(location == 1)
						{
							handleButtonClick(topCenter,icon);
						}
						else if(location == 2)
						{
							handleButtonClick(topRight,icon);
						}
						else if(location == 3)
						{
							handleButtonClick(midLeft,icon);
						}
						else if(location == 4)
						{
							handleButtonClick(midCenter,icon);
						}
						else if(location == 5)
						{
							handleButtonClick(midRight,icon);
						}
						else if(location == 6)
						{
							handleButtonClick(botLeft,icon);
						}
						else if(location == 7)
						{
							handleButtonClick(botMid,icon);
						}
						else{
							handleButtonClick(botRight,icon);
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
						else{
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
//				else if (response.startsWith("MESSAGE")) {
//					messageLabel.setText(response.substring(8));
//				}
				}
				out.println("QUIT");
			}
			finally {
				socket.close();
			}
		}
	}

