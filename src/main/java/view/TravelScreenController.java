package view;

import java.io.IOException;

import controller.GameEngine;
import model.EncounterRate;
import model.Planet;
import model.Universe;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controller for the screen directing travel between planets
 * 
 * @author Hemen Shah
 *
 */
public class TravelScreenController {
	private GameEngine game;
	private Planet selectedPlanet;

	@FXML
	private Canvas miniMapCanvas;

	@FXML
	private Button backBtn;

	@FXML
	private Canvas localMapCanvas;

	@FXML
	private Button goBtn;

	@FXML
	private Button nextBtn;

	@FXML
	private Label planetNameLbl;

	@FXML
	private Label governmentLbl;

	@FXML
	private Label planetTechLevelLbl;

	@FXML
	private Label distanceToPlanetLbl;

	@FXML
	private Label policeLevelLbl;

	@FXML
	private Label pirateLevelLbl;

	private GraphicsContext localGC;

	/**
	 * Changes the currently selected planet to the next planet in the list
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void showNextPlanet(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Show next planet");
		}
	}

	/**
	 * Selects a given region of space that has been clicked in the mini-map
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void selectRegionOfSpace(Event e) {
		System.out.println("Select region of space");
	}

	/**
	 * Selects a planet that has been clicked on the local map
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void selectPlanet(Event e) {
		System.out.println("Selecting planet, working on");
	}

	/**
	 * Sends the player back to the planet screen
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void toPlanetScreen(Event e) {
		if (MultiPageController.isValidAction(e)) {
			try {
				Stage stage = (Stage) backBtn.getScene().getWindow();
				stage.hide();
				FXMLLoader loader = new FXMLLoader(
						ClassLoader.getSystemResource("view/PlanetScreen.fxml"));
				Parent newScene = loader.load();
				stage.setScene(new Scene(newScene, 600, 400));
				PlanetScreenController controller = loader.getController();
				controller.initializePage();
				stage.show();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Starts the process for sending the user to a new planet, and checks to
	 * make sure a valid planet is selected
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void goToSelectedPlanet(Event e) {
		// make sure planet is selected
		// make sure not already on planet
		// make sure have enough fuel
	}

	/**
	 * Loads up the travel screen
	 */
	public void initializePage() {
		localGC = localMapCanvas.getGraphicsContext2D();
		game = GameEngine.getGameEngine();
		Universe u = game.getUniverse();
		int x, y;
		int rad = 3;
		localGC.setFill(Color.DARKTURQUOISE);
		for (Planet p : u.getPlanets()) {
			x = p.getLocation().getX();
			y = p.getLocation().getY();
			localGC.fillOval(x, y, rad, rad);
		}
		selectedPlanet = game.getPlayer().getPlanet();
		setPlanetInfo();
	}

	/**
	 * Sets all the planet labels with the relevant planet information
	 * 
	 * @param p
	 *            The planet that has been selected
	 */
	public void setPlanetInfo() {
		planetNameLbl.setText(selectedPlanet.getName());
		governmentLbl.setText("Government: " + selectedPlanet.getGovernment());
		planetTechLevelLbl.setText("Tech Level: "
				+ selectedPlanet.getTechLevel());
		distanceToPlanetLbl.setText("Distance: "
				+ game.getDistanceToPlanet(selectedPlanet) + " parsecs");
		policeLevelLbl.setText("Police Level: " + EncounterRate.SWARMS);
		pirateLevelLbl.setText("Pirate Level: " + EncounterRate.SWARMS);
	}
}