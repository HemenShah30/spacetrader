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
import model.Enum.EncounterResult;
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
	private Label playerShieldsLbl;

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
		Player player = GameEngine.getGameEngine().getPlayer();
		Ship ship = player.getShip();
		Ship NPCShip = e.getNPC().getShip();
		playerShipLbl.setText("Ship Type: " + ship.getShipType().toString());
		playerHPLbl.setText("HP: " + ship.getCurrHP());
		playerShieldsLbl.setText("Shields: " + ship.getCurrShieldHP());
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

	/**
	 * Updates the labels actively involved in a battle
	 */
	public void updateShipLabels() {
		Player player = GameEngine.getGameEngine().getPlayer();
		Ship ship = player.getShip();
		playerHPLbl.setText(Integer.toString(ship.getCurrHP()));
		playerShieldsLbl.setText(Integer.toString(ship.getCurrShieldHP()));

	}

	/**
	 * Method for executing a player attack
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void attack(Event e) {
		if (MultiPageController.isValidAction(e)) {
			EncounterResult result = GameEngine.getGameEngine().playerAttack(
					encounter);
			String playerMsg = "";
			boolean encounterComplete = false;
			switch (result) {
			case NPCFLEESUCCESS: {
				playerMsg = "The " + encounter.getNPC().toString()
						+ " successfully fled the battle";
				encounterComplete = true;
				break;
			}
			case NPCFLEEFAIL: {
				playerMsg = "The " + encounter.getNPC().toString()
						+ " tried to flee the battle, but failed";
				break;
			}
			case NPCATTACK: {
				playerMsg = "The " + encounter.getNPC().toString()
						+ " attacked you";
				break;
			}
			case NPCSURRENDER: {
				playerMsg = "The " + encounter.getNPC().toString()
						+ " has surrendered to you. You aquire his cargo";
				encounterComplete = true;
				break;
			}
			case NPCDEATH: {
				playerMsg = "The " + encounter.getNPC().toString()
						+ " has died";
				encounterComplete = true;
				break;
			}
			case PLAYERDEATH: {
				playerMsg = "You have died";
				Dialogs.create().owner(attackBtn.getScene().getWindow())
						.title("Death").message(playerMsg).showInformation();
				System.exit(0);
			}
			default: {

			}
			}

			Dialogs.create().owner(attackBtn.getScene().getWindow())
					.title("Result").message(playerMsg).showInformation();

			if (encounterComplete) {
				Stage popupStage = (Stage) attackBtn.getScene().getWindow();
				popupStage.close();
				parent.updatePage();
			}
		}
	}

	/**
	 * Method for executing a player attempt to flee or leave
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void fleeLeave(Event e) {
		if (MultiPageController.isValidAction(e)) {
			boolean encounterComplete = false;
			String playerMsg = "";
			if (encounter.getEncounterType() != EncounterType.TRADER) {
				EncounterResult result = GameEngine.getGameEngine().playerFlee(
						encounter);
				switch (result) {
				case PLAYERFLEESUCCESS: {
					playerMsg = "You have successfully fled the fight";
					encounterComplete = true;
					break;
				}
				case NPCFLEESUCCESS: {
					playerMsg = "The " + encounter.getNPC().toString()
							+ " successfully fled the battle";
					encounterComplete = true;
					break;
				}
				case NPCFLEEFAIL: {
					playerMsg = "The " + encounter.getNPC().toString()
							+ " tried to flee the battle, but failed";
					break;
				}
				case NPCATTACK: {
					playerMsg = "The " + encounter.getNPC().toString()
							+ " attacked you";
					break;
				}
				case NPCSURRENDER: {
					playerMsg = "The " + encounter.getNPC().toString()
							+ " has surrendered to you. You aquire his cargo";
					encounterComplete = true;
					break;
				}
				case NPCDEATH: {
					playerMsg = "The " + encounter.getNPC().toString()
							+ " has died";
					encounterComplete = true;
					break;
				}
				case PLAYERDEATH: {
					playerMsg = "You have died";
					Dialogs.create().owner(attackBtn.getScene().getWindow())
							.title("Death").message(playerMsg)
							.showInformation();
					System.exit(0);
				}
				}
			} else {
				encounterComplete = true;
				playerMsg = "You leave the Trader and continue on";
			}
			Dialogs.create().owner(fleeLeaveBtn.getScene().getWindow())
					.title("Result").message(playerMsg).showInformation();

			if (encounterComplete) {
				Stage popupStage = (Stage) fleeLeaveBtn.getScene().getWindow();
				popupStage.close();
				parent.updatePage();
			}
		}
	}

	/**
	 * Method for executing a player surrender, consent to search, or trade
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void surrenderConsentTrade(Event e) {
		if (MultiPageController.isValidAction(e)) {
			GameEngine game = GameEngine.getGameEngine();
			if (encounter.getEncounterType() == EncounterType.PIRATE) {
				game.surrenderToNPC(encounter);
				Dialogs.create().owner(bribeBtn.getScene().getWindow())
						.title("Surrender")
						.message("You surrender and lose all your cargo")
						.showInformation();
				Stage popupStage = (Stage) surrenderConsentTradeBtn.getScene()
						.getWindow();
				popupStage.close();
				parent.updatePage();
			} else if (encounter.getEncounterType() == EncounterType.POLICE) {
				boolean policeSuccess = game.consentToSearch(encounter);
				String msg = "";
				if (policeSuccess) {
					msg = "The police found and confiscated all your illegal cargo";
				} else {
					msg = "The police did not find any illegal cargo and leave your ship";
				}
				Dialogs.create().owner(bribeBtn.getScene().getWindow())
						.title("Police Search").message(msg).showInformation();
				Stage popupStage = (Stage) surrenderConsentTradeBtn.getScene()
						.getWindow();
				popupStage.close();
				parent.updatePage();
			} else {
				System.out.println("TRADE WITH TRADER");
			}
		}
	}

	/**
	 * Method for executing a player bribe
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void bribe(Event e) {
		if (MultiPageController.isValidAction(e)) {
			GameEngine game = GameEngine.getGameEngine();
			boolean done = false;
			while (!done) {
				Optional<String> bribe = Dialogs.create()
						.owner(bribeBtn.getScene().getWindow()).title("Bribe")
						.message("Please input your bribe amount")
						.showTextInput();
				if (bribe.isPresent()) {
					int bribeAmt;
					try {
						bribeAmt = Integer.valueOf(bribe.get());
						if (bribeAmt > 0
								&& bribeAmt <= game.getPlayer().getCredits()) {
							if (game.bribePolice(encounter, bribeAmt)) {
								Dialogs.create()
										.owner(bribeBtn.getScene().getWindow())
										.title("Success")
										.message(
												"The police accept your bribe and leave")
										.showInformation();

								Stage popupStage = (Stage) bribeBtn.getScene()
										.getWindow();
								popupStage.close();
								parent.updatePage();
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
				} else {
					done = true;
				}
			}
		}
	}
}