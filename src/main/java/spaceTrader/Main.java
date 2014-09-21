package spaceTrader;

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
	// TODO: Create main planet scene and implement the actions
	// TODO: Add in logging upon approval of group
	// TODO: Fix broken JAR file by getting it to know about controlsFX
	// TODO: Begin implementing Save and Load functions
	// TODO: Add exit button to MainScene

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(
				"../view/StartScreen.fxml"));
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