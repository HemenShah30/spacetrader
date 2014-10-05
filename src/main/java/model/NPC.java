package model;

/**
 * Abstract class representing NPC's.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
	private int dangerLevel; //represents how dangerous the ship is.
							//ex. if player has very bad reputations, the dangerlevel of police ships will be higher, resulting in a tougher enemy
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
	investorSkill;
	private double credits;
	private Ship ship;
	
	/**
	 * Returns the player's piloting skill
	 * 
	 * @return The player's piloting skill
	 */
	public int getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * Returns the player's fighter skill
	 * 
	 * @return The player's fighter skill
	 */
	public int getFighterSkill() {
		return fighterSkill;
	}

	/**
	 * Returns the player's trader skill
	 * 
	 * @return The player's trader skill
	 */
	public int getTraderSkill() {
		return traderSkill;
	}

	/**
	 * Returns the player's Engineer skill
	 * 
	 * @return The player's engineer skill
	 */
	public int getEngineerSkill() {
		return engineerSkill;
	}

	/**
	 * Returns the player's investor skill
	 * 
	 * @return The player's investor skill
	 */
	public int getInvestorSkill() {
		return investorSkill;
	}

	/**
	 * Returns the player's credits
	 * 
	 * @return The player's credits
	 */
	public double getCredits() {
		return credits;
	}
	
	/**
	 * Sets the player's pilot skill points
	 * 
	 * @param points
	 *            The player's new pilot skill points
	 */
	public void setPilot(int points) {
		pilotSkill = points;
	}

	/**
	 * Sets the player's fighter skill points
	 * 
	 * @param points
	 *            The player's new fighter skill points
	 */
	public void setFighter(int points) {
		fighterSkill = points;
	}

	/**
	 * Set's the player's trader skill points
	 * 
	 * @param points
	 *            The player's new trader skill points
	 */
	public void setTrader(int points) {
		traderSkill = points;
	}

	/**
	 * Sets the player's engineer skill points
	 * 
	 * @param points
	 *            The player's new engineer skill points
	 */
	public void setEngineer(int points) {
		engineerSkill = points;
	}

	/**
	 * Sets the player's investor skill points
	 * 
	 * @param points
	 *            The player's new investor skill points
	 */
	public void setInvestor(int points) {
		investorSkill = points;
	}
}
