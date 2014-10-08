package model;

/**
 * Class representing Pirates.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Pirate extends NPC {
	private double credits, chanceOfAttack;
	private boolean isFighting;

	public Pirate(int rep) {
		setPilot((int) (rep * .6));
		setFighter((int) (rep * .6));
		setTrader(1);
		setEngineer(3);
		setInvestor(1);
		generateShip(rep);
		generateChance(rep);
	}

	/**
	 * Generates whether the Pirate attacks or not based off player rep
	 * 
	 * @param rep
	 *            notoriety amongst pirates of player
	 */
	private void generateChance(int rep) {
		chanceOfAttack = (double) (Math.random() / 2 + (rep / 20));
		if (Math.random() > chanceOfAttack) {
			setIsFighting(false);
		} else {
			setIsFighting(true);
		}
	}
}
