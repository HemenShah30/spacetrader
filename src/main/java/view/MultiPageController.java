package view;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Class with controller methods that are shared among controllers to reduce
 * code duplication
 * 
 * @author Jack Croft
 *
 */
public class MultiPageController {

	/**
	 * Determines whether or not an event was a valid action, all events are
	 * valid, except for key presses, where only 'Enter' is acceptable for the
	 * key
	 * 
	 * @param e
	 *            The event that is being determined valid or not
	 * @return Whether or not the event is valid to continue
	 */
	public static boolean isValidAction(Event e) {
		boolean isValidAction = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			isValidAction = false;
		}
		return isValidAction;
	}

	/**
	 * Loads up a new view using the given Node and the name of the view
	 * 
	 * @param node
	 *            The FX object that will find the scene
	 * @param viewName
	 *            The name of the view
	 */
	public static void loadView(Node node, String viewName) {
		try {
			Parent newScene = new FXMLLoader().load(ClassLoader
					.getSystemResourceAsStream("view/" + viewName + ".fxml"));
			Stage stage = (Stage) node.getScene().getWindow();
			stage.setScene(new Scene(newScene, 600, 400));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}