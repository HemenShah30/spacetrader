package view;

import controller.GameEngine;
import model.Planet;
import model.Player;
import model.Ship;

import org.controlsfx.dialog.Dialogs;

import java.io.File;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Controller for the planet screen window
 * 
 * @author Jack Croft
 *
 */
public class PlanetScreenController {
    @FXML
    Button saveGameBtn;

    @FXML
    Button marketplaceBtn;

    @FXML
    Button toShipyardBtn;

    @FXML
    Button toBarBtn;

    @FXML
    Button toSpaceBtn;

    @FXML
    Button refuelBtn;

    @FXML
    Button repairBtn;

    @FXML
    Label planetNameLbl;

    @FXML
    Label planetTechLevelLbl;

    @FXML
    Label planetResourceLbl;

    @FXML
    Label playerNameLbl;

    @FXML
    Label playerCreditsLbl;

    /**
     * Method for saving the current game data
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void saveGame(Event event) {
        if (MultiPageController.isValidAction(event)) {
            GameEngine.getGameEngine().saveGame();
        }
    }

    /**
     * Sends the player to the marketplace view
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void goToMarketplace(Event event) {
        if (MultiPageController.isValidAction(event)) {
            try {
                Stage stage = (Stage) marketplaceBtn.getScene().getWindow();
                stage.hide();
                FXMLLoader loader = new FXMLLoader(
                        ClassLoader.getSystemResource("view/TradeScreen.fxml"));
                Parent newScene = loader.load();
                stage.setScene(new Scene(newScene, 600, 400));
                TradeScreenController controller = loader.getController();
                controller.updatePage();
                stage.show();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Sends the player to the Shipyard view
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void goToShipyard(Event event) {
        if (MultiPageController.isValidAction(event)) {
            if (GameEngine.getGameEngine().getPlayer().getPlanet().getTechLevel().getValue() > 3) {
                try {
                    Stage stage = (Stage) toShipyardBtn.getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(
                            ClassLoader.getSystemResource("view/ShipyardScreen.fxml"));
                    Parent newScene = loader.load();
                    stage.setScene(new Scene(newScene, 600, 400));
                    ShipyardScreenController controller = loader.getController();
                    controller.initializePage();
                    stage.show();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            } else {
                Dialogs.create().owner(toShipyardBtn.getScene().getWindow()).title("Shipyard")
                        .message("This planet does not have a shipyard").showInformation();
            }
        }
    }

    /**
     * Sends the player to the Bar view
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void goToBar(Event event) {
        if (MultiPageController.isValidAction(event)) {
            if (GameEngine.getGameEngine().getPlayer().getPlanet().getTechLevel().getValue() > 5) {
                try {
                    Stage stage = (Stage) toBarBtn.getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(
                            ClassLoader.getSystemResource("view/BarScreen.fxml"));
                    Parent newScene = loader.load();
                    stage.setScene(new Scene(newScene, 600, 400));
                    BarScreenController controller = loader.getController();
                    controller.initializePage();
                    stage.show();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            } else {
                Dialogs.create().owner(toBarBtn.getScene().getWindow()).title("Bar")
                        .message("This planet does not have a bar").showInformation();
            }
        }
    }

    /**
     * Sends the player to the menu for going to another planet
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void goToSpace(Event event) {
        if (MultiPageController.isValidAction(event)) {
            try {
                Stage stage = (Stage) toSpaceBtn.getScene().getWindow();
                stage.hide();
                FXMLLoader loader = new FXMLLoader(
                        ClassLoader.getSystemResource("view/TravelScreen.fxml"));
                Parent newScene = loader.load();
                stage.setScene(new Scene(newScene, 600, 400));
                TravelScreenController controller = loader.getController();
                controller.initializePage();
                stage.show();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Refuels ship
     * 
     * @param e
     *            event that triggers the function
     */
    @FXML
    private void refuelShip(Event event) {
        // not sure on what needs to be done here
        System.out.println("Refuel still needs to be implemented");
    }

    /**
     * Repairs ship
     * 
     * @param e
     *            event that triggers the function
     */
    @FXML
    private void repairShip(Event event) {
        // not sure what needs to be done here
        System.out.println("Repair still needs to be implemented");
    }

    /**
     * Initializes the main page with the relevant label data
     */
    public void initializePage() {
        GameEngine game = GameEngine.getGameEngine();
        Player player = game.getPlayer();
        Planet planet = player.getPlanet();
        planetNameLbl.setText(planet.getName());
        planetTechLevelLbl.setText("Tech Level: " + planet.getTechLevel().toString());
        planetResourceLbl.setText("Resource: " + planet.getResource().toString());
        playerNameLbl.setText(player.getName());
        playerCreditsLbl.setText("Credits: " + (int) player.getCredits());
        Ship ship = player.getShip();
        int refuelAmount = (int) ship.getRefuelCost();
        int repairAmount = (int) ship.getRepairCost();
        refuelBtn.setText("Refuel ship for " + refuelAmount + " credits");
        repairBtn.setText("Repair ship for " + repairAmount + " credits");
//        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/background.wav").toURI().toString()));
//    	mediaPlayer.setAutoPlay(true);
//    	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//    	MediaPlayer trollPlayer = new MediaPlayer(new Media(new File("resources/spacetrader.wav").toURI().toString()));
//    	trollPlayer.setAutoPlay(true);
//    	trollPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

}