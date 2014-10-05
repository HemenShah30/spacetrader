package model;

/**
 * Abstract class representing NPC's.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
	private int threatLevel; //represents how dangerous the ship is.
							//ex. if player has bad reputation, the threatlevel of police ships will be higher, resulting in a tougher enemy
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
	investorSkill;
	private double credits;
	private Ship ship;
	private boolean isFleeing, isFighting, isSurrenduring;
	
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
	 * Returns true if the NPC is trying to flee
	 * 
	 * @return boolean indicating if the NPC is trying to flee
	 */
	public boolean getIsFleeing() {
		return isFleeing;
	}
	
	/**
	 * Returns true if the NPC is wants to fight
	 * 
	 * @return boolean indicating if the NPC wants to fight
	 */
	public boolean getIsFighting() {
		return isFighting;
	}
	
	/**
	 * Returns true if the NPC surrenders
	 * 
	 * @return boolean indicating if the NPC wants to surrender
	 */
	public boolean getIsSurrendering() {
		return isSurrenduring;
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
	 * Sets the NPC's trader skill points
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
	 * Randomly generates the ship the NPC is using
	 */
	public void generateShip() {
		ShipType[] shiptypes = ShipType.values();
		int index = (int) (Math.random() * shiptypes.length);
		ShipType shiptype = shiptypes[index];
		ship = new Ship(shiptype);
	}
	
	/**
	 * Sets if the NPC wants to flee
	 * 
	 * @param flee
	 *            True if NPC wants to flee
	 */
	public void setIsFleeing(boolean flee) {
		isFleeing = flee;
	}
	
	/**
	 * Sets if the NPC wants to fight
	 * 
	 * @param fight
	 *            True if NPC wants to fight
	 */
	public void setIsFighting(boolean fight) {
		isFighting = fight;
	}
	
	/**
	 * Sets if the NPC wants to surrender
	 * 
	 * @param fight
	 *            True if NPC wants to surrender
	 */
	public void setIsSurrenduring(boolean surrender) {
		isSurrenduring = surrender;
	}
}
