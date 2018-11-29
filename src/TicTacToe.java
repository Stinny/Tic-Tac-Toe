import java.io.IOException;

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

		System.out.print(client.getStatus());

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
			}
			else if(client.getStatus()==Status.CIRCLE){
				topCenter.setGraphic(new ImageView(circle));
			}

			String move = "1,0,1";

			try{

				client.sendMove(move);}

			catch(IOException a) {

				a.printStackTrace();

			}

		});

		topRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				topRight.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				topRight.setGraphic(new ImageView(circle));
			}

			String move = "1,0,2";

			try{

				client.sendMove(move);

			} catch(IOException a) {

				a.printStackTrace();
			}
		});

		midLeft.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midLeft.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				midLeft.setGraphic(new ImageView(circle));
			}
			String move = "1,1,0";
			try {
				client.sendMove(move);
			} catch(IOException a) {a.printStackTrace();
			}



		});

		midCenter.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midCenter.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				midCenter.setGraphic(new ImageView(circle));
			}
			String move = "1,1,1";
			try{
				client.sendMove(move);
			} catch(IOException a) {
				a.printStackTrace();
			}
		});

		midRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				midRight.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				midRight.setGraphic(new ImageView(circle));
			}
			String move = "1,1,2";
			try {
				client.sendMove(move);
			} catch (IOException a) {
				a.printStackTrace();
			}
		});

		botLeft.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botLeft.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				botLeft.setGraphic(new ImageView(circle));
			}

			String move = "1,2,0";

			try {
				client.sendMove(move);
			} catch (IOException a) {
				a.printStackTrace();
			}
		});

		botCenter.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botCenter.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				botCenter.setGraphic(new ImageView(circle));
			}

			String move = "1,2,1";

			try {
				client.sendMove(move);
			} catch (IOException a) {
				a.printStackTrace();
			}
		});

		botRight.setOnMouseClicked(e -> {
			if(client.getStatus()==Status.CROSS) {
				botRight.setGraphic(new ImageView(cross));
			}
			else if(client.getStatus()==Status.CIRCLE){
				botRight.setGraphic(new ImageView(circle));
			}

			String move = "1,2,2";

			try {
				client.sendMove(move);
			} catch (IOException a) {
				a.printStackTrace();
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
public static void main(String[] args) {
		launch(args);
	}
}
