import javafx.application.Application;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import java.io.FileInputStream;

import javafx.scene.layout.HBox;

import javafx.scene.control.TextField;

import javafx.scene.text.*;

import javafx.scene.layout.VBox;

import javafx.geometry.Pos;

import javafx.scene.layout.Priority;



public class TicTacToe extends Application{



	private Button start = new Button("Start");

	private Button exit = new Button("Exit");

	private TextField playerName = new TextField();

	private Text titleArt = new Text();



	public void startButton(String playerName)

	{

		Stage stage = new Stage();



		GridPane gridPane = new GridPane();

		gridPane.setAlignment(Pos.CENTER);

		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		//gridPane.setPadding(new Insets(15));



		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				Button button = new Button();

				button.setMaxWidth(Double.MAX_VALUE);

				button.setMaxHeight(Double.MAX_VALUE);

				gridPane.add(button, i, j);

				GridPane.setHgrow(button, Priority.ALWAYS);

				GridPane.setVgrow(button, Priority.ALWAYS);

			}

		}



		Scene scene2 = new Scene(gridPane, 600, 400);

		stage.setTitle(playerName);

		stage.setScene(scene2);

		stage.show();

	}

	public void exitButton()

	{

		System.exit(0);

	}





	@Override

	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();

		g.setHgap(1);

		g.setVgap(1);



		//ImageView imv = new ImageView();

		//Image image = new Image(TicTacToe.class.getResourceAsStream("tictactoe.png"));

		//imv.setImage(image);



		//HBox picarea = new HBox();

		//picarea.getChildren().add(imv);



		//g.add(picarea, 5, 4);

		g.add(start, 200, 200);

		g.add(playerName, 199, 200);

		playerName.setPromptText("Enter player name"); //sets the prompt text for player name text field



		//g.add(titleArt, 100, 12);

		titleArt.setText("Tic Tac Toe");

		titleArt.setId("fancytext");

		//titleArt.setTextAlignment(TextAlignment.CENTER);



		start.setPrefWidth(75);

		//exit.setPrefWidth(50);



		Scene s = new Scene(g, 600, 400);

		s.getStylesheets().add("styles.css");

		primaryStage.setTitle("Tic Tac Toe");

		primaryStage.setScene(s);

		primaryStage.show();



		start.setOnAction(e -> startButton(playerName.getText()));

		exit.setOnAction(e -> exitButton());

	}



	public static void main(String[] args) {

		launch(args);