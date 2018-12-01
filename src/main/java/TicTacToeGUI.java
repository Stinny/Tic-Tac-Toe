


import com.sun.javafx.tools.packager.Main;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;



public class TicTacToeGUI extends Application{

//	private Scene sc1;
//	private static Socket socket;
//	private static int PORT = 4444;
//	public static Button topCenter, topLeft, topRight, midLeft, midCenter, midRight, botLeft, botMid, botRight;
//	private static String zero, one, two, three, four, five, six, seven, eight;
//	private static BufferedReader in;
//	private static PrintWriter out;
//	private static ImageView icon, opponentIcon;

	@Override
	public void start(Stage primaryStage) throws Exception{
try {
	BorderPane root = (BorderPane) FXMLLoader.load(Main.class.getResource("/res/TicTacToe.fxml"));
	Scene scene = new Scene(root, 300, 320);
	primaryStage.setScene(scene);
	primaryStage.setResizable(false);
	primaryStage.show();
}catch(Exception e){
	e.printStackTrace();
}
	}

	public static void main(String[] args) {
		Application.launch();
	}

}


