package model;

/**
 * Abstract class representing NPC's.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
	private int dangerLevel; //represents how dangerous the ship is.
							//ex. if NPC has very bad reputations, the dangerlevel of police ships will be higher, resulting in a tougher enemy
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
	investorSkill;
	private double credits;
	private Ship ship;
	private boolean isFleeing, isFighting;
	
	/**
	 * Returns the NPC's piloting skill
	 * 
	 * @return The NPC's piloting skill
	 */
	public int getPilot() {
		return pilotSkill;
	}

	/**
	 * Returns the NPC's fighter skill
	 * 
	 * @return The NPC's fighter skill
	 */
	public int getFighter() {
		return fighterSkill;
	}

	/**
	 * Returns the NPC's trader skill
	 * 
	 * @return The NPC's trader skill
	 */
	public int getTrader() {
		return traderSkill;
	}

	/**
	 * Returns the NPC's Engineer skill
	 * 
	 * @return The NPC's engineer skill
	 */
	public int getEngineer() {
		return engineerSkill;
	}

	/**
	 * Returns the NPC's investor skill
	 * 
	 * @return The NPC's investor skill
	 */
	public int getInvestor() {
		return investorSkill;
	}

	/**
	 * Returns the NPC's credits
	 * 
	 * @return The NPC's credits
	 */
	public double getCredits() {
		return credits;
	}
	
	/**
	 * Sets the NPC's pilot skill points
	 * 
	 * @param points
	 *            The NPC's new pilot skill points
	 */
	public void setPilot(int points) {
		pilotSkill = points;
	}

	/**
	 * Sets the NPC's fighter skill points
	 * 
	 * @param points
	 *            The NPC's new fighter skill points
	 */
	public void setFighter(int points) {
		fighterSkill = points;
	}

	/**
	 * Set's the NPC's trader skill points
	 * 
	 * @param points
	 *            The NPC's new trader skill points
	 */
	public void setTrader(int points) {
		traderSkill = points;
	}

	/**
	 * Sets the NPC's engineer skill points
	 * 
	 * @param points
	 *            The NPC's new engineer skill points
	 */
	public void setEngineer(int points) {
		engineerSkill = points;
	}

	/**
	 * Sets the NPC's investor skill points
	 * 
	 * @param points
	 *            The NPC's new investor skill points
	 */
	public void setInvestor(int points) {
		investorSkill = points;
	}
	
	/**
	 * Setter for the NPC's ship
	 * 
	 * @param ship
	 *            The new ship for the NPC
	 */
	public void setShip(Ship ship) {
		if (ship == null)
			throw new IllegalArgumentException();
		this.ship = ship;
	}
}
