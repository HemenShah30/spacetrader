package view;

import controller.GameEngine;
import model.Gadget;
import model.Player;
import model.Ship;
import model.ShipUpgrade;
import model.enums.LaserType;
import model.enums.ShieldType;
import model.enums.ShipType;

import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Controller for the Shipyard screen
 * 
 * @author Hemen Shah
 *
 */
public class ShipyardScreenController {

    @FXML
    private ImageView playerShipImage;

    @FXML
    private ImageView shipyardShipImage;

    @FXML
    private Label playerShipNameLbl;

    @FXML
    private Label shipyardShipShieldSlotsLbl;

    @FXML
    private Label playerNameLbl;

    @FXML
    private Label shipyardShipNameLbl;

    @FXML
    private Label planetResourceLbl;

    @FXML
    private Label shipyardShipHPLbl;

    @FXML
    private Label shipyardShipGadgetSlotsLbl;

    @FXML
    private Label planetNameLbl;

    @FXML
    private Label planetTechLevelLbl;

    @FXML
    private Label playerShipFuelLbl;

    @FXML
    private Button buyShipBtn;

    @FXML
    private Label playerShipShieldSlotsLbl;

    @FXML
    private Label playerShipGadgetSlotsLbl;

    @FXML
    private Label shipyardShipCargoSizeLbl;

    @FXML
    private Label playerCreditsLbl;

    @FXML
    private Label playerShipWeaponSlotsLbl;

    @FXML
    private Label shipyardShipFuelLbl;

    @FXML
    private Label playerShipCargoSizeLbl;

    @FXML
    private Label shipyardShipWeaponSlotsLbl;

    @FXML
    private Label shipyardShipCrewSpaceLbl;

    @FXML
    private Label playerShipCrewSpaceLbl;

    @FXML
    private ComboBox<ShipType> shipDropDown;

    @FXML
    private Label playerShipHPLbl;

    @FXML
    private Button backToPlanetBtn;

    @FXML
    private Button sellWeaponBtn;

    @FXML
    private Button buyShieldBtn;

    @FXML
    private ListView<Gadget> buyGadgetList;

    @FXML
    private Button buyGadgetBtn;

    @FXML
    private ListView<LaserType> buyWeaponList;

    @FXML
    private ListView<LaserType> sellWeaponList;

    @FXML
    private Button sellGadgetBtn;

    @FXML
    private ListView<ShieldType> buyShieldList;

    @FXML
    private ListView<ShieldType> sellShieldList;

    @FXML
    private ListView<Gadget> sellGadgetList;

    @FXML
    private Button buyWeaponBtn;

    @FXML
    private Button sellShieldBtn;
    
    private static final int numShipImages = 7;

    private static String[] shipImageNames = { "resources/noShipImage.png",
            "resources/ship1.png", "resources/ship2.png",
            "resources/ship3.png", "resources/ship4.png",
            "resources/ship5.png", "resources/ship6.png" };

    private static Image[] shipImages;
    
    
    /**
     * Method for selecting a new ship from the drop down menu
     * 
     * @param event
     *            The event that fired the method
     */
    @FXML
    private void selectShip(Event event) {
        updateShipLabels();
    }

    /**
     * Method for buying a ship from the shipyard
     * 
     * @param event
     *            The event that fired the method
     */
    @FXML
    private void buyShipFromShipyard(Event event) {
        if (MultiPageController.isValidAction(event)) {
            ShipType ship = shipDropDown.getValue();
            List<String> errors = GameEngine.getGameEngine().tradeWithShipyard(ship);
            if (errors.size() == 0) {
                initializePage();
            } else {
                String errorMsg = "";
                for (String error : errors) {
                    errorMsg += error + "\n";
                }
                displayError(errorMsg);
            }
        }
    }

    /**
     * Sends player back to the planet screen from the market screen
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void toPlanetScreen(Event event) {
        if (MultiPageController.isValidAction(event)) {
            try {
                Stage stage = (Stage) backToPlanetBtn.getScene().getWindow();
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
     * Method for initializing the page with starting information
     */
    public void initializePage() {
        GameEngine game = GameEngine.getGameEngine();
        Player player = game.getPlayer();
        List<ShipType> shipTypes = game.getAvailableShips();
        shipTypes.remove(player.getShip().getShipType());
        shipDropDown.getItems().removeAll(ShipType.values());
        shipDropDown.getItems().addAll(shipTypes);
        shipDropDown.setConverter(new StringConverter<ShipType>() {

            @Override
            public String toString(ShipType ship) {
                if (ship == null) {
                    return null;
                }
                return ship.toString();
            }

            @Override
            public ShipType fromString(String string) {
                return ShipType.valueOf(string.toUpperCase());
            }
        });
        shipDropDown.setValue(shipTypes.get(0));
        planetNameLbl.setText(player.getPlanet().getName());
        planetTechLevelLbl.setText("Tech Level: " + player.getPlanet().getTechLevel().toString());
        planetResourceLbl.setText("Resource: " + player.getPlanet().getResource().toString());
        playerNameLbl.setText(player.getName());
        playerCreditsLbl.setText("Credits: " + player.getCredits());
        updateShipLabels();
        updateSellableListViews();

//        Image test = new Image("view/shiptest.png");
//        playerShipImage.setImage(test);
    }
    
    private void setShipImage(ImageView view, int shipImageNumber) {
        try {
            view.setImage(shipImages[shipImageNumber]);
        } catch (Exception e){
            view.setImage(shipImages[0]);
        }
    }

    /**
     * Updates the various ship labels
     */
    private void updateShipLabels() {
        if (shipImages == null) {
            shipImages = new Image[numShipImages];
            for(int i = 0; i < numShipImages; i++) {
                shipImages[i] = new Image(shipImageNames[i]);
            }
        }
        if (shipDropDown.getItems().size() > 0) {
            GameEngine game = GameEngine.getGameEngine();
            ShipType playerShipType = game.getPlayer().getShip().getShipType();
            playerShipNameLbl.setText(playerShipType.toString());
            playerShipFuelLbl.setText(Integer.toString(playerShipType.getFuel()));
            playerShipHPLbl.setText(Integer.toString(playerShipType.getTotalHP()));
            playerShipCargoSizeLbl.setText(Integer.toString(playerShipType.getCargoSize()));
            playerShipWeaponSlotsLbl.setText(Integer.toString(playerShipType.getWeaponSlots()));
            playerShipShieldSlotsLbl.setText(Integer.toString(playerShipType.getShieldSlots()));
            playerShipGadgetSlotsLbl.setText(Integer.toString(playerShipType.getGadgetSlots()));
            playerShipCrewSpaceLbl.setText(Integer.toString(playerShipType.getCrewSpace()));
            ShipType selectedShipType = shipDropDown.getValue();
            shipyardShipNameLbl.setText(selectedShipType.toString());
            shipyardShipFuelLbl.setText(Integer.toString(selectedShipType.getFuel()));
            shipyardShipHPLbl.setText(Integer.toString(selectedShipType.getTotalHP()));
            shipyardShipCargoSizeLbl.setText(Integer.toString(selectedShipType.getCargoSize()));
            shipyardShipWeaponSlotsLbl.setText(Integer.toString(selectedShipType.getWeaponSlots()));
            shipyardShipShieldSlotsLbl.setText(Integer.toString(selectedShipType.getShieldSlots()));
            shipyardShipGadgetSlotsLbl.setText(Integer.toString(selectedShipType.getGadgetSlots()));
            shipyardShipCrewSpaceLbl.setText(Integer.toString(selectedShipType.getCrewSpace()));
            buyShipBtn
                    .setText("Buy For "
                            + (int) (selectedShipType.getPrice() - game.getPlayerAssetValue())
                            + " Credits");
            int i;
            try{
                i = playerShipType.getShipImageNum();
                setShipImage(playerShipImage, i);
            } catch (Exception e) {
                setShipImage(playerShipImage, 0);

            }
            try {
                i = selectedShipType.getShipImageNum();
                setShipImage(shipyardShipImage, i);
            } catch (Exception e) {
                setShipImage(shipyardShipImage, 0);
            }
        }
    }

    /**
     * Updates the lists of sellable and buyable items
     */
    private void updateSellableListViews() {
        GameEngine game = GameEngine.getGameEngine();
        Ship ship = game.getPlayer().getShip();
        List<LaserType> lasers = game.getAvailableLasers();
        List<LaserType> playerLasers = ship.getLasers();
        lasers.removeAll(playerLasers);
        buyWeaponList.getItems().removeAll(LaserType.values());
        buyWeaponList.getItems().addAll(lasers);
        sellWeaponList.getItems().removeAll(LaserType.values());
        sellWeaponList.getItems().addAll(playerLasers);

        List<ShieldType> shields = game.getAvailableShields();
        List<ShieldType> playerShields = ship.getShields();
        shields.removeAll(playerShields);
        buyShieldList.getItems().removeAll(ShieldType.values());
        buyShieldList.getItems().addAll(shields);
        sellShieldList.getItems().removeAll(ShieldType.values());
        sellShieldList.getItems().addAll(playerShields);

        List<Gadget> gadgets = game.getAvailableGadgets();
        List<Gadget> playerGadgets = ship.getGadgets();
        gadgets.removeAll(playerGadgets);
        buyGadgetList.getItems().removeAll(Gadget.getAllGadgets());
        buyGadgetList.getItems().addAll(gadgets);
        sellGadgetList.getItems().removeAll(Gadget.getAllGadgets());
        sellGadgetList.getItems().addAll(playerGadgets);
    }

    /**
     * Creates a dialog error box with the given message
     * 
     * @param msg
     *            The message for the error dialog to display
     */
    private void displayError(String msg) {
        Dialogs.create().owner(buyShipBtn.getScene().getWindow()).title("Error").message(msg)
                .showError();
    }

    /**
     * Method that causes a buy transaction for a given upgrade
     * 
     * @param event
     *            The event that fired the method
     */
    @FXML
    private void buySellable(Event event) {
        if (MultiPageController.isValidAction(event)) {
            ShipUpgrade sellable = null;
            Button btn = (Button) event.getSource();
            String id = btn.getId();
            switch (id) {
            case "buyWeaponBtn": {
                sellable = buyWeaponList.getSelectionModel().getSelectedItem();
                break;
            }
            case "buyShieldBtn": {
                sellable = buyShieldList.getSelectionModel().getSelectedItem();
                break;
            }
            case "buyGadgetBtn": {
                sellable = buyGadgetList.getSelectionModel().getSelectedItem();
                break;
            }
            default: {
                break;
            }
            }
            List<String> errors = GameEngine.getGameEngine().tradeShipUpgradeWithShipyard(sellable,
                    true);
            String errorMsg = "";
            for (String error : errors) {
                errorMsg += error + "\n";
            }
            if (errors.size() > 0) {
                displayError(errorMsg);
            }
            initializePage();
        }
    }

    /**
     * Method that causes a sell transaction for a given upgrade
     * 
     * @param event
     *            The event that fired the method
     */
    @FXML
    private void sellSellable(Event event) {
        if (MultiPageController.isValidAction(event)) {
            ShipUpgrade sellable = null;
            Button btn = (Button) event.getSource();
            String id = btn.getId();
            switch (id) {
            case "sellWeaponBtn": {
                sellable = sellWeaponList.getSelectionModel().getSelectedItem();
                break;
            }
            case "sellShieldBtn": {
                sellable = sellShieldList.getSelectionModel().getSelectedItem();
                break;
            }
            case "sellGadgetBtn": {
                sellable = sellGadgetList.getSelectionModel().getSelectedItem();
                break;
            }
            default: {
                sellable = null;
                break;
            }
            }
            List<String> errors = GameEngine.getGameEngine().tradeShipUpgradeWithShipyard(sellable,
                    false);
            String errorMsg = "";
            for (String error : errors) {
                errorMsg += error + "\n";
            }
            if (errors.size() > 0) {
                displayError(errorMsg);
            }
            initializePage();
        }
    }
}