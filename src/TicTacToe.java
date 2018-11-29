import java.io.*;

import java.net.Socket;
import java.net.URL;
import javax.imageio.ImageIO;
import javafx.application.Application;

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



public class TicTacToe extends Application {

	Client client = new Client( "127.0.0.1", 3000);

	Scene sc1;

	Image cross = new Image("Cross.png");

	Image circle = new Image("Circle.png");


	Parent buildScene() {

		GridPane gridPane = new GridPane();

		gridPane.setAlignment(Pos.CENTER);

		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		gridPane.setPadding(new Insets(100));

		Button topLeft = new Button();

		topLeft.setPrefHeight(cross.getHeight());
		topLeft.setPrefWidth(cross.getWidth());

		topLeft.setMaxWidth(Double.MAX_VALUE);

		topLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topLeft, 0, 0);

		GridPane.setHgrow(topLeft, Priority.ALWAYS);

		GridPane.setVgrow(topLeft, Priority.ALWAYS);


		Button topCenter = new Button();

		topCenter.setPrefHeight(145);
		topCenter.setPrefWidth(145);

		topCenter.setMaxWidth(Double.MAX_VALUE);

		topCenter.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topCenter, 0, 1);

		GridPane.setHgrow(topCenter, Priority.ALWAYS);

		GridPane.setVgrow(topCenter, Priority.ALWAYS);


		Button topRight  = new Button();

		topRight.setPrefHeight(145);
		topRight.setPrefWidth(145);

		topRight.setMaxWidth(Double.MAX_VALUE);

		topRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(topRight, 0, 2);

		GridPane.setHgrow(topRight, Priority.ALWAYS);

		GridPane.setVgrow(topRight, Priority.ALWAYS);


		Button midLeft = new Button();

		midLeft.setPrefHeight(145);
		midLeft.setPrefWidth(145);

		midLeft.setMaxWidth(Double.MAX_VALUE);
		midLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midLeft, 1, 0);

		GridPane.setHgrow(midLeft, Priority.ALWAYS);

		GridPane.setVgrow(midLeft, Priority.ALWAYS);


		Button midCenter = new Button();

		midCenter.setPrefHeight(145);
		midCenter.setPrefWidth(145);

		midCenter.setMaxWidth(Double.MAX_VALUE);

		midCenter.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midCenter, 1, 1);

		GridPane.setHgrow(midCenter, Priority.ALWAYS);

		GridPane.setVgrow(midCenter, Priority.ALWAYS);


		Button midRight  = new Button();

		midRight.setPrefHeight(140);
		midRight.setPrefWidth(140);

		midRight.setMaxWidth(Double.MAX_VALUE);

		midRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(midRight, 1, 2);

		GridPane.setHgrow(midRight, Priority.ALWAYS);

		GridPane.setVgrow(midRight, Priority.ALWAYS);


		Button botLeft = new Button();

		botLeft.setPrefHeight(145);
		botLeft.setPrefWidth(145);

		botLeft.setMaxWidth(Double.MAX_VALUE);

		botLeft.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botLeft, 2, 0);

		GridPane.setHgrow(botLeft, Priority.ALWAYS);

		GridPane.setVgrow(botLeft, Priority.ALWAYS);


		Button botCenter = new Button();

		botCenter.setPrefHeight(145);
		botCenter.setPrefWidth(145);

		botCenter.setMaxWidth(Double.MAX_VALUE);

		botCenter.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botCenter, 2, 1);

		GridPane.setHgrow(botCenter, Priority.ALWAYS);

		GridPane.setVgrow(botCenter, Priority.ALWAYS);


		Button botRight  = new Button();

		botRight.setPrefHeight(145);
		botRight.setPrefWidth(145);

		botRight.setMaxWidth(Double.MAX_VALUE);

		botRight.setMaxHeight(Double.MAX_VALUE);

		gridPane.add(botRight, 2, 2);

		GridPane.setHgrow(botRight, Priority.ALWAYS);

		GridPane.setVgrow(botRight, Priority.ALWAYS);

		client.setStatus();

		//System.out.print(client.getStatus());

		topLeft.setOnMouseClicked(e -> {

			System.out.println(client.getStatus());
			if(client.getStatus()==Status.CROSS) {
				topLeft.setGraphic(new ImageView(cross));

				String move = "1,0,0,0";

				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				topLeft.setGraphic(new ImageView(circle));
				String move = "1,0,0,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		topCenter.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				topCenter.setGraphic(new ImageView(cross));

				String move = "1,0,1,1";

				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				topCenter.setGraphic(new ImageView(circle));

				String move = "1,0,1,0";

				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		topRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				topRight.setGraphic(new ImageView(cross));

				String move = "1,0,2,1";

				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				topRight.setGraphic(new ImageView(circle));

				String move = "1,0,2,0";

				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}

		});

		midLeft.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midLeft.setGraphic(new ImageView(cross));
				String move = "1,1,0,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				midLeft.setGraphic(new ImageView(circle));
				String move = "1,1,0,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		midCenter.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midCenter.setGraphic(new ImageView(cross));
				String move = "1,1,1,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				midCenter.setGraphic(new ImageView(circle));
				String move = "1,1,1,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		midRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midRight.setGraphic(new ImageView(cross));
				String move = "1,1,2,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				midRight.setGraphic(new ImageView(circle));

				String move = "1,1,2,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		botLeft.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botLeft.setGraphic(new ImageView(cross));
				String move = "1,2,0,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				botLeft.setGraphic(new ImageView(circle));
				String move = "1,2,0,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		botCenter.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botCenter.setGraphic(new ImageView(cross));
				String move = "1,2,1,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				botCenter.setGraphic(new ImageView(circle));
				String move = "1,2,1,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
		});

		botRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botRight.setGraphic(new ImageView(cross));
				String move = "1,2,2,1";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}
			else if(client.getStatus()==Status.CIRCLE){
				botRight.setGraphic(new ImageView(circle));
				String move = "1,2,2,0";
				try {
					client.sendMove(move);}
				catch(IOException a){
					a.printStackTrace();
				}
			}

		});


		VBox vBox = new VBox(20);

		vBox.getChildren().addAll(gridPane);

		VBox.setVgrow(gridPane, Priority.ALWAYS);

		vBox.setAlignment(Pos.CENTER);

		vBox.setPadding(new Insets(0));

		return vBox;
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


	/**
	 * Created by lucasraza on 11/27/18.
	 */

	public class Client {
		private Socket socket = null;
		private DataInputStream input = null;
		private Board board = new Board();
		private PrintWriter out;
		private gameStateTest gameState;
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
		}

	}

	public class Board {
		public int maxRows = 3, maxCol = 3;
		public Cell[][] board;
		public int currRow, currCol;

		public Board() {
			board = new Cell[maxRows][maxCol];
			for (int row = 0; row < maxRows; row++) { // Initialize a 3x3 array of
				// CELLS with starting enum
				// status of EMPTY
				for (int col = 0; col < maxCol; col++) {
					board[row][col] = new Cell(row, col);
				}
			}
		}

		public void startNew() {
			for (int row = 0; row < maxRows; row++) {
				for (int col = 0; col < maxCol; col++) {
					board[row][col].setEmpty();
				}
			}
		}

		public boolean checkDiagonalCase(Status currContent) {

			if (board[1][1].content == currContent) {
				if ((board[0][0].content == currContent && board[2][2].content == currContent) // if
						// diagonal
						// from
						// [0][0]
						// to
						// [2][2]
						|| (board[0][2].content == currContent && board[2][0].content == currContent)) {// or
					// diagonal
					// from
					// [0][2]
					// to
					// [2][0]
					return true;
				}
			}
//		else if (board[1][1].content == Status.CROSS) {
//			if ((board[0][0].content == Status.CROSS && board[2][2].content == Status.CROSS) // if
//																								// diagonal
//																								// from
//																								// [0][0]
//																								// to
//																								// [2][2]
//					|| (board[0][2].content == Status.CROSS && board[2][0].content == Status.CROSS)) {// or
//																										// diagonal
//																										// from
//																										// [0][2]
//																										// to
//																										// [2][0]
//				return true;
//			}
//		}

			return false;
		}

		public boolean checkHorizontalCase(Status currStatus) {

			for (int col = 0; col < maxCol; col++) {
				int row = 0;
				if (board[row][col].content == currStatus) {
					row++;
					if (board[row][col].content == currStatus) {
						row++;
						if (board[row][col].content == currStatus) {
							return true;
						}
					}
				}
//			else if (board[row][col].content == Status.CROSS) {
//				row++;
//				if (board[row][col].content == Status.CROSS) {
//					row++;
//					if (board[row][col].content == Status.CROSS) {
//						return true;
//					}
//				}
//			}
			}
			return false;
		}

		public boolean checkVerticalCase(Status currStatus) {

			for (int row = 0; row < maxRows; row++) {
				int col = 0;
				if (board[row][col].content == currStatus) {
					col++;
					if (board[row][col].content == currStatus) {
						col++;
						if (board[row][col].content == currStatus) {
							return true;
						}
					}
				}
//			else if (board[row][col].content == Status.CROSS) {
//				col++;
//				if (board[row][col].content == Status.CROSS) {
//					col++;
//					if (board[row][col].content == Status.CROSS) {
//						return true;
//					}
//				}
//			}
			}
			return false;
		}

		public void setCoordX(int row, int col) {
			board[row][col].setCross();
		}

		public void setCoordO(int row, int col) {
			board[row][col].setCircle();
		}

		public boolean checkDraw() {
			for (int row = 0; row < maxRows; ++row) {
				for (int col = 0; col < maxCol; ++col) {
					if (board[row][col].content == Status.EMPTY) {
						return false; // if there is an empty cell, there can't be a
						// draw
					}
				}
			}
			return true; // if there is not a single empty cell, all spaces have
			// been filled and no one has won yet
		}

		public boolean checkWin(Status currStatus) {
			if (this.checkDiagonalCase(currStatus) || this.checkVerticalCase(currStatus)  || this.checkHorizontalCase(currStatus)) {
				return true;
			} else
				return false;
		}

		// if checkWin() returns true the game should end and the current player's
		// turn is the winner
		// if false continue whatever loop keeping the game running

	}



	public static void main(String[] args) {
		launch(args);
	}
}
