package spaceTrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class which starts off the application
 * 
 * @author Jack Croft
 */
public class Main extends Application {
	// This will be the main To-Do list for any specific code level items that
	// need to be taken care of
	// TODO: Create main planet scene and implement the actions
	// TODO: Add in logging
	// TODO: Begin implementing Save and Load functions
	// TODO: Add exit button to MainScene

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new FXMLLoader().load(ClassLoader
				.getSystemResourceAsStream("view/StartScreen.fxml"));
		primaryStage.setTitle("Space Traders");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}

	/**
	 * Main method for running the whole application
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		// Universe u = new Universe();
		// / u.createPlanets();
		// Player p = new Player("Jack",1,10,7,1,1);
		// FileWriter w = new FileWriter();
		// w.saveGameData(p, u);
		// FileReader r = new FileReader();
		// Object[] data=r.loadGameData();

		launch(args);
	}
}