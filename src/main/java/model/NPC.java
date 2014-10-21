package model;

import model.Enum.EncounterType;
import model.Enum.LaserType;
import model.Enum.ShipType;

/**
 * Abstract class representing NPC's.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public abstract class NPC {
	private int threatLevel; // represents how dangerous the ship is.
								// ex. if player has bad reputation, the
								// threatlevel of police ships will be higher,
								// resulting in a tougher enemy
	private int pilotSkill, fighterSkill, traderSkill, engineerSkill,
			investorSkill;
	private double credits;
	private Ship ship;

	/**
	 * Returns the NPC's piloting skill
	 * 
	 * @return The NPC's piloting skill
	 */
	public int getPilotSkill() {
		return pilotSkill;
	}

	/**
	 * Returns the NPC's fighter skill
	 * 
	 * @return The NPC's fighter skill
	 */
	public int getFighterSkill() {
		return fighterSkill;
	}

	/**
	 * Returns the NPC's trader skill
	 * 
	 * @return The NPC's trader skill
	 */
	public int getTraderSkill() {
		return traderSkill;
	}

	/**
	 * Returns the NPC's Engineer skill
	 * 
	 * @return The NPC's engineer skill
	 */
	public int getEngineerSkill() {
		return engineerSkill;
	}

	/**
	 * Returns the NPC's investor skill
	 * 
	 * @return The NPC's investor skill
	 */
	public int getInvestorSkill() {
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
	public void setPilotSkill(int points) {
		pilotSkill = points;
	}

	/**
	 * Sets the NPC's fighter skill points
	 * 
	 * @param points
	 *            The NPC's new fighter skill points
	 */
	public void setFighterSkill(int points) {
		fighterSkill = points;
	}

	/**
	 * Sets the NPC's trader skill points
	 * 
	 * @param points
	 *            The NPC's new trader skill points
	 */
	public void setTraderSkill(int points) {
		traderSkill = points;
	}

	/**
	 * Sets the NPC's engineer skill points
	 * 
	 * @param points
	 *            The NPC's new engineer skill points
	 */
	public void setEngineerSkill(int points) {
		engineerSkill = points;
	}

	/**
	 * Sets the NPC's investor skill points
	 * 
	 * @param points
	 *            The NPC's new investor skill points
	 */
	public void setInvestorSkill(int points) {
		investorSkill = points;
	}

	/**
	 * Randomly generates the ship the NPC is using given the player reputation
	 * 
	 * @param rep
	 *            The reputation of the player
	 */
	protected void generateShip(int rep, EncounterType type) {
		Ship s;
		int index;
		//generate a ship type
		ShipType[] shipTypes = ShipType.values();
		boolean shipTypeGenerated = false;
		while (!shipTypeGenerated) {
			index = (int) Math.random() * shipTypes.length;
			if (shipTypes[index].getMinRep(type) <= rep && shipTypes[index].getMaxRep(type) >= rep) {
				s = new Ship(shipTypes[index]);
				shipTypeGenerated = true;
				setShip(s);
			}
		}
		
		//generate a weapon
		LaserType[] laserTypes = LaserType.values();
		int weaponCapacity = getShip().getShipType().getWeaponSlots();
		for (int i = 0; i < weaponCapacity; i++) {
			index = (int) Math.random() * shipTypes.length;
			if (laserTypes[index].getMinRep(type) <= rep && laserTypes[index].getMaxRep(type) >= rep) {
				try {
					getShip().addLaser(laserTypes[index]);
				} catch (MaxCapacityException m) {
					
				}
			}
		}
		
		//generate shields
		
	}

	/**
	 * Gets the ship of the NPC
	 * 
	 * @return Ship of the NPC
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * Sets the NPCs credits
	 * 
	 * @param credits
	 *            NPC's credits
	 */
	public void setCredits(double credits) {
		this.credits = credits;
	}

	/**
	 * Sets the NPCs ship
	 * 
	 * @param ship
	 *            the ship
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}
}
