package view;

import java.io.IOException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import model.Mercenary;
import model.Planet;
import model.Player;
import model.Enum.ShipType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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
    private Player p;

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
        Player player = game.getPlayer();
        p = player;
        List<Mercenary> mercenariesPlanet = game.getPlanetMercenaries();
        hireDropDown.getItems().addAll(mercenariesPlanet);
        hireDropDown.setConverter(new StringConverter<Mercenary>() {

            @Override
            public String toString(Mercenary m) {
                if (m == null)
                    return null;
                return m.getName();
            }

            @Override
            public Mercenary fromString(String string) {
                for (int i = 0; i < mercenariesPlanet.size(); i++) {
                    if (mercenariesPlanet.get(i).getName().equalsIgnoreCase(string)) {
                        System.out.println("Match found!");
                        return mercenariesPlanet.get(i);
                    }
                }
                System.out.println("Returned null");
                return null;
            }
        });
        hireDropDown.setValue(mercenariesPlanet.get(0));
        List<Mercenary> mercenariesPlayer = player.getShip().getMercenaries();
        fireDropDown.getItems().addAll(mercenariesPlayer);
        fireDropDown.setConverter(new StringConverter<Mercenary>() {

            @Override
            public String toString(Mercenary m) {
                if (m == null)
                    return null;
                return m.getName();
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
        planetTechLevelLbl.setText("Tech Level: "
                + player.getPlanet().getTechLevel().toString());
        planetResourceLbl.setText("Resource: "
                + player.getPlanet().getResource().toString());
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
            Mercenary m = hireDropDown.getValue();
            List<String> errors = GameEngine.getGameEngine().hireMercenary(m);
            String errorMsg = "";
            for (String error : errors)
                errorMsg += error + "\n";
            if (errors.size() > 0) {
                displayHireError(errorMsg);
            } else {
                hireDropDown.getItems().remove(m);
                if (hireDropDown.getItems().size() != 0) {
                    hireDropDown.setValue(hireDropDown.getItems().get(0));
                } else {
                    hireDropDown.setValue(null);
                }
                fireDropDown.getItems().add(m);
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
            Mercenary m = fireDropDown.getValue();
            GameEngine.getGameEngine().fireMercenary(m);
            fireDropDown.getItems().remove(m);
            if (fireDropDown.getItems().size() != 0) {
                fireDropDown.setValue(fireDropDown.getItems().get(0));
            } else {
                fireDropDown.setValue(null);
            }
            hireDropDown.getItems().add(m);
            if (hireDropDown.getItems().size() != 0) {
                hireDropDown.setValue(hireDropDown.getItems().get(0));
            } else {
                hireDropDown.setValue(null);
            }
        }
    }

    private void displayHireError(String msg) {
        Dialogs.create().owner(hireMercBtn.getScene().getWindow())
        .title("Error").message(msg).showError();
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
                Mercenary m = hireDropDown.getValue();
                mercNameLbl.setText(m.getName());
                mercWageLbl.setText(Double.toString(m.getWage()));
                mercEngiLbl.setText(Integer.toString(m.getEngineerSkill()));
                mercTradeLbl.setText(Integer.toString(m.getTraderSkill()));
                mercFightLbl.setText(Integer.toString(m.getFighterSkill()));
                mercPilotLbl.setText(Integer.toString(m.getPilotSkill()));
                mercInvestLbl.setText(Integer.toString(m.getInvestorSkill()));
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
                Mercenary m = fireDropDown.getValue();
                mercNameLbl.setText(m.getName());
                mercWageLbl.setText(Double.toString(m.getWage()));
                mercEngiLbl.setText(Integer.toString(m.getEngineerSkill()));
                mercTradeLbl.setText(Integer.toString(m.getTraderSkill()));
                mercFightLbl.setText(Integer.toString(m.getFighterSkill()));
                mercPilotLbl.setText(Integer.toString(m.getPilotSkill()));
                mercInvestLbl.setText(Integer.toString(m.getInvestorSkill()));
            }
        }
        playerTradeLbl.setText(Integer.toString(p.getTraderSkill()));
        playerEngiLbl.setText(Integer.toString(p.getEngineerSkill()));
        playerPilotLbl.setText(Integer.toString(p.getPilotSkill()));
        playerInvestLbl.setText(Integer.toString(p.getInvestorSkill()));
        playerFightLbl.setText(Integer.toString(p.getFighterSkill()));
    }

    /**
     * Sends player back to the planet screen from the market screen
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void toPlanetScreen(Event e) {
        if (MultiPageController.isValidAction(e)) {
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