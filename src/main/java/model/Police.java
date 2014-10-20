package model;

/**
 * Class representing Police.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Police extends NPC {
	private double minimumBribe;

	public Police(int rep, double playerCred) {
		setPilotSkill((int) (rep * .6));
		setFighterSkill((int) (rep * .6));
		setTraderSkill(1);
		setEngineerSkill(3);
		setInvestorSkill(1);
		generateShip(rep);
		minimumBribe = (double) (rep * playerCred / 10);
	}

	/**
	 * Checks to see if Police accepts the player's bribe or not
	 * 
	 * @param bribe
	 *            double of credits offered by player
	 * @return boolean whether the police accepts or not
	 */
	public boolean willAcceptBribe(double bribe) {
		if (bribe >= minimumBribe) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Police";
	}
}