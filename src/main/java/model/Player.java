package model;

/**
 * Class representing a game player
 * 
 * @author Larry He
 * 
 */
public class Player {
	private String name;
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
			investorSkill;

	/**
	 * Constructor for the player taking all the appropriate instance variables
	 * 
	 * @param name
	 *            The name of the player
	 * @param pilot
	 *            The amount of pilot skill points
	 * @param fighter
	 *            The amount of fighter skill points
	 * @param trader
	 *            The amount of trader skill points
	 * @param engineer
	 *            The amount of engineer skill points
	 * @param investor
	 *            The amount of investor skill points
	 */
	public Player(String name, int pilot, int fighter, int trader,
			int engineer, int investor) {
		setName(name);
		this.pilotSkill = pilot;
		this.fighterSkill = fighter;
		this.traderSkill = trader;
		this.engineerSkill = engineer;
		this.investorSkill = investor;
	}

	/**
	 * Returns the name of the player
	 * 
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

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
	 * Setter for the name of the player
	 * 
	 * @param name
	 *            The name of the player
	 */
	public void setName(String name) {
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Name cannot be blank");

		this.name = name;
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

	@Override
	public String toString() {
		return "Player: " + name + ", Pilot Skill: " + pilotSkill
				+ ", Fighter Skill: " + fighterSkill + ", Trader Skill: "
				+ traderSkill + ", Engineer Skill: " + engineerSkill
				+ ", Investor Skill: " + investorSkill;
	}
}