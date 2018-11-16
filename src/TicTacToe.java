import javafx.application.Application;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import javafx.scene.control.Button;



public class TicTacToe extends Application{



	private Button start = new Button("Start");

	private Button exit = new Button("Exit");



	public void startButton()

	{

		//information needed from server to complete this method

		//will be method that is responsible for connecting game to server

	}





	@Override

	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();

		g.setHgap(10);

		g.setVgap(10);



		g.add(start, 2, 4);

		g.add(exit, 2, 5);

		start.setPrefWidth(50);

		exit.setPrefWidth(50);



		Scene s = new Scene(g, 600, 400);

		primaryStage.setTitle("Tic Tac Toe");

		primaryStage.setScene(s);

		primaryStage.show();



	}



	public static void main(String[] args) {

		launch(args);

	}





}