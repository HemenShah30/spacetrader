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
