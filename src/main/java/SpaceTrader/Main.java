package SpaceTrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class which starts off the program
 * 
 * @author Jack Croft
 */
public class Main extends Application {
	// This will be the main To-Do list for any specific code level items that
	// need to be taken care of
	// TODO: Determine how to implement the location system for the game

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(
				"../view/MainScene.fxml"));
		primaryStage.setTitle("Space Traders");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}

	/**
	 * Main method for running the whole program
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}