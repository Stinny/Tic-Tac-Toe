import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.layout.*;

import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Scene sc1, sc2;

	@Override

	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();

		root.setCenter(addHBox());

		
		//Scene 1
		Label label1= new Label("This is the first scene");
		Button button1= new Button("Go to scene 2");
		button1.setOnAction(e -> primaryStage.setScene(sc2));   
		//HBox login = addHBox();
		VBox layout1 = new VBox(20);     
		layout1.getChildren().addAll(label1, button1);
		sc1= new Scene(layout1, 300, 250);
		               
		//Scene 2
		Label label2= new Label("This is the second scene");
		Button button2= new Button("Go to scene 1");
		button2.setOnAction(e -> primaryStage.setScene(sc1));
		VBox layout2= new VBox(20);
		layout2.getChildren().addAll(label2, button2);
		sc2= new Scene(layout2,300,250);
		
		play.setOnMouseClicked(e -> {
			primaryStage.setScene(sc2);
			//root.setCenter(addHBox2());
			
			try {
				client.sendMove(0);
			}catch(IOException i){
				i.printStackTrace();;
			}
		});


	}
	public HBox addHBox(){
		HBox hbox = new HBox();

		Button play = new Button("PLAY");

		playerName.setPromptText("Enter player name"); //sets the prompt text for player name text field

		hbox.getChildren().addAll(play,playerName);

		return hbox;
	}
	public HBox addHBox2(){
		HBox hbox = new HBox();

		Button play = new Button("PL");

		playerName.setPromptText("Enter"); //sets the prompt text for player name text field

		hbox.getChildren().addAll(play,playerName);

		return hbox;
	}


	public static void main(String[] args) {

		launch(args);

	}

}