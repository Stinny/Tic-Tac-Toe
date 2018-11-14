import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TicTacToe extends Application{
	
	private Parent createPane() {
		Pane pane = new Pane();
		pane.setPrefSize(600, 600);
		
		return pane;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createPane()));
		primaryStage.show();
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}
