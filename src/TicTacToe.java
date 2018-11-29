import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.layout.*;

import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.TextField;

import javafx.scene.text.*;

import javafx.geometry.Pos;


public class TicTacToe extends Application {

	Client client = new Client("127.0.0.1", 80);
	private Button play = new Button("PLAY");
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

		BorderPane root = new BorderPane();

		root.setCenter(addHBox());

		Group g1 = new Group();
		Group g2 = new Group();
		g1.getChildren().add(root);


		Scene sc1 = new Scene(g1, 600, 400);
		Scene sc2 = new Scene(g2, 600, 600);

		play.setOnMouseClicked(e -> {
			primaryStage.setScene(sc2);
			try {
				client.sendMove(0);
			}catch(IOException i){
				i.printStackTrace();;
			}
		});

		//g.setHgap(1);

		//g.setVgap(1);


		//g.add(start, 200, 200);

		//g.add(playerName, 199, 200);

		//titleArt.setText("Tic Tac Toe");

		//titleArt.setId("fancytext");


		//start.setPrefWidth(75);


		//Scene s = new Scene(g, 600, 400);

		sc1.getStylesheets().add("styles.css");

		primaryStage.setTitle("Tic Tac Toe");

		primaryStage.setScene(sc1);

		primaryStage.show();


		//start.setOnMouseClicked(e -> {root.setCenter(g2)
		//}
		//);

		//exit.setOnAction(e -> exitButton());

	}
	public HBox addHBox(){
		HBox hbox = new HBox();

		Button play = new Button("PLAY");

		playerName.setPromptText("Enter player name"); //sets the prompt text for player name text field

		hbox.getChildren().addAll(play,playerName);

		return hbox;

	}


	public static void main(String[] args) {

		launch(args);

	}

}