package SpaceTrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class which starts off the program
 * @author Jack Croft
 */
public class Main extends Application {
	//This will be the main To-Do list for any specific code level items that need to be taken care of
	//TODO: Set up alerts to the user for trying to create an invalid character
	//TODO: Modify FXML to look for enter key press events on the main screen, so that clicking isn't necessary
	//TODO: Create some sort of screen that replaces going back to the main screen upon character creation
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
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