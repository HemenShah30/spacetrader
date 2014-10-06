package model;

/**
 * Class representing Police.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Police extends NPC {
	private boolean willCheckCargo;
	private double minCredBribe; // minimum bribe required to avoid a search;
									// hidden from player
	private Ship ship;
	private double credits;

	public Police(int rep, double playerCred) {
		setPilot((int) (rep * .6));
		setFighter((int) (rep * .6));
		setTrader(1);
		setEngineer(3);
		setInvestor(1);
		generateShip(rep);
		generateMinCredBribe(rep, playerCred);
	}

	/**
	 * Creates the ratio needed to bribe the officer
	 */
	private void generateMinCredBribe(int rep, double credits) {
		minCredBribe = (double) (rep * credits / 10);
	}

	/**
	 * Generates Police's ship based off player reputation
	 * 
	 * @param rep
	 *            illegality of Player
	 */
	private void generateShip(int rep) {
		ShipType[] shiptypes = ShipType.values();
		int index = (rep - 1) / 2;
		ShipType shiptype = shiptypes[index];
		ship = new Ship(shiptype);
	}

	/**
	 * Checks to see if Police accepts the player's bribe or not
	 * 
	 * @param bribe
	 *            double of credits offered by player
	 * @return boolean whether the police accepts or not
	 */
	public boolean acceptBribe(double bribe) {
		if (bribe > minCredBribe) {
			willCheckCargo = false;
			return true;
		}
		return false;
	}
}
