package view;

import java.io.IOException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import model.Location;
import model.Planet;
import model.Player;
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
import javafx.scene.input.MouseEvent;
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

	@FXML
	private Label fuelLbl;

	private GraphicsContext localGC;
	private int universeSize;
	private int mapSize = 400;

	/**
	 * Changes the currently selected planet to the next planet in the list
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void showNextPlanet(Event e) {
		if (MultiPageController.isValidAction(e)) {
			List<Planet> withinRange = game.getPlanetsWithinRange();
			int index = withinRange.indexOf(selectedPlanet);
			selectedPlanet = withinRange.get((index + 1) % withinRange.size());
			setPlanetInfo();
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
		MouseEvent event = (MouseEvent) e;
		double x = event.getX();
		double y = event.getY();
		System.out.println("You clicked (" + x + ", " + y + ")");
	}

	/**
	 * Selects a planet that has been clicked on the local map
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void selectPlanet(Event e) {
		MouseEvent event = (MouseEvent) e;
		double x = event.getX() / (mapSize / universeSize);
		double y = event.getY() / (mapSize / universeSize);
		Planet p = game.getPlanetAtLocation(new Location((int) x, (int) y));
		if (p != null) {
			selectedPlanet = p;
			setPlanetInfo();
		}
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
		int distance = game.getDistanceToPlanet(selectedPlanet);
		Player p = game.getPlayer();
		if (selectedPlanet != p.getPlanet()) {
			if (p.getShip().getFuel() >= distance) {
				game.goToPlanet(selectedPlanet);
				setPlanetInfo();
			} else {
				displayError("You do not have enough fuel");
			}
		} else {
			displayError("You are already on this planet");
		}
	}

	/**
	 * Loads up the travel screen
	 */
	public void initializePage() {
		localGC = localMapCanvas.getGraphicsContext2D();
		game = GameEngine.getGameEngine();
		Universe u = game.getUniverse();
		universeSize = u.getUniverseSize();
		int x, y;
		int radius;
		localGC.setFill(Color.BLACK);
		localGC.fillRect(0, 0, 400, 400);
		for (Planet p : u.getPlanets()) {
			x = p.getLocation().getX() * (mapSize / universeSize);
			y = p.getLocation().getY() * (mapSize / universeSize);
			radius = p.getRadius();
			localGC.setFill(p.getColor());
			localGC.fillOval(x, y, radius, radius);
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
		policeLevelLbl.setText("Police Level: "
				+ selectedPlanet.getPoliceEncounterRate());
		pirateLevelLbl.setText("Pirate Level: "
				+ selectedPlanet.getPirateEncounterRate());
		fuelLbl.setText("Fuel: " + game.getPlayer().getShip().getFuel());
	}

	/**
	 * Creates a dialog error box with the given message
	 * 
	 * @param msg
	 *            The message for the error dialog to display
	 */
	private void displayError(String msg) {
		Dialogs.create().owner(goBtn.getScene().getWindow()).title("Error")
				.message(msg).showError();
	}
}