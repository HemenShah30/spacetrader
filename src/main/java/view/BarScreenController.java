package view;


import controller.GameEngine;

import model.Mercenary;
import model.Player;

import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Controller for the BarScreen view
 * 
 * @author Larry He
 *
 */
public class BarScreenController {
    private boolean hireDropDownJustPressed = true;
    private Player player;

    @FXML
    private Label mercNameLbl;

    @FXML
    private Button fireMercBtn;

    @FXML
    private Label mercWageLbl;

    @FXML
    private Label mercEngiLbl;

    @FXML
    private Label playerNameLbl;

    @FXML
    private Label planetResourceLbl;

    @FXML
    private Button backToPlanetBtn;

    @FXML
    private Label playerPilotLbl;

    @FXML
    private Label playerEngiLbl;

    @FXML
    private Label planetNameLbl;

    @FXML
    private Label planetTechLevelLbl;

    @FXML
    private Label playerFightLbl;

    @FXML
    private ComboBox<Mercenary> hireDropDown;

    @FXML
    private ComboBox<Mercenary> fireDropDown;

    @FXML
    private Label mercTradeLbl;

    @FXML
    private Label mercFightLbl;

    @FXML
    private Label mercPilotLbl;

    @FXML
    private Label playerCreditsLbl;

    @FXML
    private Button hireMercBtn;

    @FXML
    private Label playerTradeLbl;

    @FXML
    private Label playerInvestLbl;

    @FXML
    private Label mercInvestLbl;

    @FXML
    public void initializePage() {
        GameEngine game = GameEngine.getGameEngine();
        Player playerCur = game.getPlayer();
        player = playerCur;
        final List<Mercenary> mercenariesPlanet = game.getPlanetMercenaries();
        hireDropDown.getItems().addAll(mercenariesPlanet);
        hireDropDown.setConverter(new StringConverter<Mercenary>() {

            @Override
            public String toString(Mercenary merc) {
                if (merc == null) {
                    return null;
                }
                return merc.getName();
            }

            @Override
            public Mercenary fromString(String string) {
                for (int i = 0; i < mercenariesPlanet.size(); i++) {
                    if (mercenariesPlanet.get(i).getName().equalsIgnoreCase(string)) {
                        return mercenariesPlanet.get(i);
                    }
                }
                return null;
            }
        });
        //Code may be breaking here - hemen
        //TODO: try/catch around the setvalue
        if (mercenariesPlanet.size() != 0) {
            hireDropDown.setValue(mercenariesPlanet.get(0));
        } else {
            hireDropDown.setValue(null);
        }
        final List<Mercenary> mercenariesPlayer = player.getShip().getMercenaries();
        fireDropDown.getItems().addAll(mercenariesPlayer);
        fireDropDown.setConverter(new StringConverter<Mercenary>() {

            @Override
            public String toString(Mercenary merc) {
                if (merc == null) {
                    return null;
                }
                return merc.getName();
            }

            @Override
            public Mercenary fromString(String string) {
                for (int i = 0; i < mercenariesPlayer.size(); i++) {
                    if (mercenariesPlayer.get(i).getName().equals(string)) {
                        return mercenariesPlayer.get(i);
                    }
                }
                return null;
            }
        });
        if (mercenariesPlayer.size() != 0) {
            fireDropDown.setValue(mercenariesPlayer.get(0));
        } else {
            fireDropDown.setValue(null);
        }
        planetNameLbl.setText(player.getPlanet().getName());
        planetTechLevelLbl.setText("Tech Level: " + player.getPlanet().getTechLevel().toString());
        planetResourceLbl.setText("Resource: " + player.getPlanet().getResource().toString());
        playerNameLbl.setText(player.getName());
        playerCreditsLbl.setText("Credits: " + player.getCredits());
        updateMercenaryLabels();
    }

    @FXML
    private void selectHire(Event event) {
        hireDropDownJustPressed = true;
        updateMercenaryLabels();
    }

    @FXML
    private void selectFire(Event event) {
        hireDropDownJustPressed = false;
        updateMercenaryLabels();
    }

    @FXML
    private void hireMerc(Event event) {
        if (MultiPageController.isValidAction(event)) {
            Mercenary merc = hireDropDown.getValue();
            List<String> errors = GameEngine.getGameEngine().hireMercenary(merc);
            String errorMsg = "";
            for (String error : errors) {
                errorMsg += error + "\n";
            }
            if (errors.size() > 0) {
                displayHireError(errorMsg);
            } else {
                hireDropDown.getItems().remove(merc);
                if (hireDropDown.getItems().size() != 0) {
                    hireDropDown.setValue(hireDropDown.getItems().get(0));
                } else {
                    hireDropDown.setValue(null);
                }
                fireDropDown.getItems().add(merc);
                if (fireDropDown.getItems().size() != 0) {
                    fireDropDown.setValue(fireDropDown.getItems().get(0));
                } else {
                    fireDropDown.setValue(null);
                }
            }
        }
    }

    @FXML
    private void fireMerc(Event event) {
        if (MultiPageController.isValidAction(event)) {
            Mercenary merc = fireDropDown.getValue();
            GameEngine.getGameEngine().fireMercenary(merc);
            fireDropDown.getItems().remove(merc);
            if (fireDropDown.getItems().size() != 0) {
                fireDropDown.setValue(fireDropDown.getItems().get(0));
            } else {
                fireDropDown.setValue(null);
            }
            hireDropDown.getItems().add(merc);
            if (hireDropDown.getItems().size() != 0) {
                hireDropDown.setValue(hireDropDown.getItems().get(0));
            } else {
                hireDropDown.setValue(null);
            }
        }
    }

    private void displayHireError(String msg) {
        Dialogs.create().owner(hireMercBtn.getScene().getWindow()).title("Error").message(msg)
                .showError();
    }

    private void updateMercenaryLabels() {
        if (hireDropDownJustPressed) {
            if (hireDropDown.getValue() == null) {
                mercNameLbl.setText("Name");
                mercWageLbl.setText(Double.toString(0));
                mercEngiLbl.setText(Integer.toString(0));
                mercTradeLbl.setText(Integer.toString(0));
                mercFightLbl.setText(Integer.toString(0));
                mercPilotLbl.setText(Integer.toString(0));
                mercInvestLbl.setText(Integer.toString(0));
            } else if (hireDropDown.getItems().size() > 0) {
                Mercenary merc = hireDropDown.getValue();
                mercNameLbl.setText(merc.getName());
                mercWageLbl.setText(Double.toString(merc.getWage()));
                mercEngiLbl.setText(Integer.toString(merc.getEngineerSkill()));
                mercTradeLbl.setText(Integer.toString(merc.getTraderSkill()));
                mercFightLbl.setText(Integer.toString(merc.getFighterSkill()));
                mercPilotLbl.setText(Integer.toString(merc.getPilotSkill()));
                mercInvestLbl.setText(Integer.toString(merc.getInvestorSkill()));
            }
        } else {
            if (fireDropDown.getValue() == null) {
                mercNameLbl.setText("Name");
                mercWageLbl.setText(Double.toString(0));
                mercEngiLbl.setText(Integer.toString(0));
                mercTradeLbl.setText(Integer.toString(0));
                mercFightLbl.setText(Integer.toString(0));
                mercPilotLbl.setText(Integer.toString(0));
                mercInvestLbl.setText(Integer.toString(0));
            } else if (fireDropDown.getItems().size() > 0) {
                Mercenary merc = fireDropDown.getValue();
                mercNameLbl.setText(merc.getName());
                mercWageLbl.setText(Double.toString(merc.getWage()));
                mercEngiLbl.setText(Integer.toString(merc.getEngineerSkill()));
                mercTradeLbl.setText(Integer.toString(merc.getTraderSkill()));
                mercFightLbl.setText(Integer.toString(merc.getFighterSkill()));
                mercPilotLbl.setText(Integer.toString(merc.getPilotSkill()));
                mercInvestLbl.setText(Integer.toString(merc.getInvestorSkill()));
            }
        }
        playerTradeLbl.setText(Integer.toString(player.getTraderSkill()));
        playerEngiLbl.setText(Integer.toString(player.getEngineerSkill()));
        playerPilotLbl.setText(Integer.toString(player.getPilotSkill()));
        playerInvestLbl.setText(Integer.toString(player.getInvestorSkill()));
        playerFightLbl.setText(Integer.toString(player.getFighterSkill()));
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
}