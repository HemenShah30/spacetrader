package SpaceTrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class which starts off the program
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws  Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        primaryStage.setTitle("Space Traders");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * Main method for running the whole program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}