package view;

import controller.GameEngine;

import model.Encounter;
import model.Location;
import model.NPCEncounter;
import model.Planet;
import model.Player;
import model.Ship;
import model.Universe;

import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller for the screen directing travel between planets
 * 
 * @author Hemen Shah
 *
 */
public class TravelScreenController implements Controller {
    private GameEngine game;
    private Planet selectedPlanet;
    private List<Encounter> encounters;

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
    private Label traderLevelLbl;

    @FXML
    private Label fuelLbl;

    private GraphicsContext localGraphicsContext;
    private int universeSize;
    private int mapSize = 400;

    /**
     * Changes the currently selected planet to the next planet in the list
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void showNextPlanet(Event event) {
        if (MultiPageController.isValidAction(event)) {
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
    private void selectRegionOfSpace(Event eve) {
        MouseEvent event = (MouseEvent) eve;
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
    private void selectPlanet(Event eve) {
        MouseEvent event = (MouseEvent) eve;
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
    private void toPlanetScreen(Event event) {
        if (MultiPageController.isValidAction(event)) {
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
     * Starts the process for sending the user to a new planet, and checks to make sure a valid
     * planet is selected
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void goToSelectedPlanet(Event event) {
        int distance = game.getDistanceToPlanet(selectedPlanet);
        Player player = game.getPlayer();
        if (selectedPlanet != player.getPlanet()) {
            if (player.getShip().getFuel() >= distance) {
                encounters = game.goToPlanet(selectedPlanet);
                doEncounters();
                setPlanetInfo();
            } else {
                displayError("You do not have enough fuel");
            }
        } else {
            displayError("You are already on this planet");
        }
    }

    @Override
    public void updatePage() {
        if (encounters.size() > 0) {
            doEncounters();
        } else {
            Ship ship = game.getPlayer().getShip();
            ship.addShieldHP(ship.getMaxShieldHP());
        }
    }

    /**
     * Method for continuously displaying the next encounter to the player
     */
    private void doEncounters() {
        boolean NPCEncounter = false;
        while (encounters.size() != 0 && !NPCEncounter) {
            Encounter encounter = encounters.remove(0);
            Dialogs.create().owner(goBtn.getScene().getWindow()).title("Encounter")
                    .message(encounter.doEncounter()).showInformation();

            if (encounter instanceof NPCEncounter) {
                try {
                    Stage encounterPopup = new Stage();
                    encounterPopup.initModality(Modality.APPLICATION_MODAL);
                    encounterPopup.initOwner((Stage) goBtn.getScene().getWindow());

                    FXMLLoader loader = new FXMLLoader(
                            ClassLoader.getSystemResource("view/NPCEncounterPopup.fxml"));
                    Parent newScene = loader.load();
                    encounterPopup.setScene(new Scene(newScene, 400, 300));

                    NPCEncounterController controller = loader.getController();
                    controller.initializePage(this, (NPCEncounter) encounter);
                    encounterPopup.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent we) {
                            we.consume();
                        }
                    });
                    encounterPopup.show();

                    NPCEncounter = true;
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads up the travel screen
     */
    public void initializePage() {
        localGraphicsContext = localMapCanvas.getGraphicsContext2D();
        game = GameEngine.getGameEngine();
        Universe uni = game.getUniverse();
        universeSize = uni.getUniverseSize();
        int x;
        int y;
        int radius;
        localGraphicsContext.setFill(Color.BLACK);
        localGraphicsContext.fillRect(0, 0, 400, 400);
        for (Planet p : uni.getPlanets()) {
            x = p.getLocation().getX() * (mapSize / universeSize);
            y = p.getLocation().getY() * (mapSize / universeSize);
            radius = p.getDiameter();
            localGraphicsContext.setFill(p.getColor());
            localGraphicsContext.fillOval(x, y, radius, radius);
        }
        selectedPlanet = game.getPlayer().getPlanet();
        setPlanetInfo();
    }

    /**
     * Sets all the planet labels with the relevant planet information
     */
    public void setPlanetInfo() {
        planetNameLbl.setText(selectedPlanet.getName());
        governmentLbl.setText("Government: " + selectedPlanet.getGovernment());
        planetTechLevelLbl.setText("Tech Level: " + selectedPlanet.getTechLevel());
        distanceToPlanetLbl.setText("Distance: " + game.getDistanceToPlanet(selectedPlanet)
                + " parsecs");
        policeLevelLbl.setText("Police Level: " + selectedPlanet.getPoliceEncounterRate());
        pirateLevelLbl.setText("Pirate Level: " + selectedPlanet.getPirateEncounterRate());
        traderLevelLbl.setText("Trader Level: " + selectedPlanet.getTraderEncounterRate());
        fuelLbl.setText("Fuel: " + game.getPlayer().getShip().getFuel());
    }

    /**
     * Creates a dialog error box with the given message
     * 
     * @param msg
     *            The message for the error dialog to display
     */
    private void displayError(String msg) {
        Dialogs.create().owner(goBtn.getScene().getWindow()).title("Error").message(msg)
                .showError();
    }
}