package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Enum.EncounterType;

/**
 * Controller for the encounter screen with NPCs
 * 
 * @author Jack Croft
 *
 */
public class NPCEncounterController {
	private TravelScreenController parent;
	private EncounterType type;

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
	public void initializePage(TravelScreenController tsc, EncounterType t) {
		type = t;
		parent = tsc;
		// go through and set all player and npc ship stats

		if (type == EncounterType.PIRATE) {
			bribeBtn.setVisible(false);
		} else if (type == EncounterType.POLICE) {
			surrenderConsentTradeBtn.setText("Consent To Search");
		} else {
			surrenderConsentTradeBtn.setText("Trade");
			fleeLeaveBtn.setText("Leave");
			bribeBtn.setVisible(false);
		}
	}
}