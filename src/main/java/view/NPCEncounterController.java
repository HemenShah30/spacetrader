package view;

import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.NPCEncounter;
import model.Player;
import model.Ship;
import model.Enum.EncounterType;

/**
 * Controller for the encounter screen with NPCs
 * 
 * @author Jack Croft
 *
 */
public class NPCEncounterController {
	private TravelScreenController parent;
	private NPCEncounter encounter;

	@FXML
	private Label playerShipLbl;

	@FXML
	private Label playerHPLbl;

	@FXML
	private Label playerSheildsLbl;

	@FXML
	private Label NPCShipLbl;

	@FXML
	private Label NPCHPLbl;

	@FXML
	private Label NPCShieldsLbl;

	@FXML
	private Button attackBtn;

	@FXML
	private Button fleeLeaveBtn;

	@FXML
	private Button surrenderConsentTradeBtn;

	@FXML
	private Button bribeBtn;

	/**
	 * Initializes the page with all the proper encounter data
	 * 
	 * @param tsc
	 *            The controller that created the popup
	 * @param type
	 *            The type of encounter that is happening
	 */
	public void initializePage(TravelScreenController tsc, NPCEncounter e) {
		encounter = e;
		parent = tsc;
		// go through and set all player and npc ship stats
		Player player = GameEngine.getGameEngine().getPlayer();
		Ship ship = player.getShip();
		Ship NPCShip = e.getNPC().getShip();
		playerShipLbl.setText("Ship Type: " + ship.getShipType().toString());
		playerHPLbl.setText("HP: " + ship.getCurrHP());
		playerSheildsLbl.setText("Shields: " + ship.getCurrShieldHP());
		NPCShipLbl.setText("Ship Type: " + NPCShip.getShipType().toString());
		NPCHPLbl.setText("HP: " + NPCShip.getCurrHP());
		NPCShieldsLbl.setText("Shields: " + NPCShip.getCurrShieldHP());
		if (encounter.getEncounterType() == EncounterType.PIRATE) {
			bribeBtn.setVisible(false);
		} else if (encounter.getEncounterType() == EncounterType.POLICE) {
			surrenderConsentTradeBtn.setText("Consent To Search");
		} else {
			surrenderConsentTradeBtn.setText("Trade");
			fleeLeaveBtn.setText("Leave");
			bribeBtn.setVisible(false);
		}
	}

	@FXML
	private void attack(Event e) {

	}

	@FXML
	private void flee(Event e) {

	}

	@FXML
	private void surrenderConsentTrader(Event e) {
		
	}

	@FXML
	private void bribe(Event e) {
		boolean done = false;
		GameEngine game = GameEngine.getGameEngine();
		while (!done) {
			Optional<String> bribe = Dialogs.create()
					.owner(bribeBtn.getScene().getWindow()).title("Bribe")
					.message("Please input your bribe amount").showTextInput();

			if (bribe.isPresent()) {
				int bribeAmt;
				try {
					bribeAmt = Integer.valueOf(bribe.get());
					if (bribeAmt > 0
							&& bribeAmt <= game.getPlayer().getCredits()) {
						if (game.bribe(encounter, bribeAmt)) {
							Dialogs.create()
									.owner(bribeBtn.getScene().getWindow())
									.title("Success")
									.message(
											"The police accept your bribe and leave")
									.showInformation();

							Stage popupStage = (Stage) bribeBtn.getScene()
									.getWindow();
							popupStage.close();
							parent.doEncounters();
						} else {
							Dialogs.create()
									.owner(bribeBtn.getScene().getWindow())
									.title("Failure")
									.message("The police refuse your bribe")
									.showInformation();
							bribeBtn.setDisable(true);
						}
					} else {
						Dialogs.create()
								.owner(bribeBtn.getScene().getWindow())
								.title("Error")
								.message(
										"You must enter a positive number less than your total credits")
								.showError();
					}
				} catch (NumberFormatException n) {
					Dialogs.create().owner(bribeBtn.getScene().getWindow())
							.title("Error")
							.message("You must enter a valid number")
							.showError();
				}
			}
		}
	}
}