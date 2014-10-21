package model.Enum;

/**
 * Enum representing the various lasers
 * 
 * @author Caroline Zhang
 *
 */
public enum LaserType {

	PULSELASER(20, 5000, 4, 0, 0, 10, 40, 60, 101), 
	BEAMLASER(35, 15000, 5, 20, 30, 30, 101, 101, 101), 
	MILITARYLASER(60, 35000, 6, 40, 40, 50, 101, 101, 101);

	private int baseDamage;
	private int minTechLevel;
	private int price;
	private int minPoliceRep;
	private int minPirateRep;
	private int minTraderRep;
	private int maxPoliceRep;
	private int maxPirateRep;
	private int maxTraderRep;

	/**
	 * Constructor for the Laser enums
	 * 
	 * @param d
	 *            The damage dealt by the laser
	 * @param p
	 *            The price of the laser
	 * @param mtl
	 *            The minimum tech level required for a laser
	 */
	private LaserType(int bd, int p, int mtl, int minPoliceRep, int minPirateRep,
			int minTraderRep, int maxPoliceRep, int maxPirateRep, int maxTraderRep) {
		baseDamage = bd;
		price = p;
		minTechLevel = mtl;
		this.minPoliceRep = minPoliceRep;
		this.minPirateRep = minPirateRep;
		this.minTraderRep = minTraderRep;
		this.maxPoliceRep = maxPoliceRep;
		this.maxPirateRep = maxPirateRep;
		this.maxTraderRep = maxTraderRep;
	}

	/**
	 * Returns the damage done by the laser
	 * 
	 * @return The damage done by the laser
	 */
	public int getBaseDamage() {
		return baseDamage;
	}

	/**
	 * Gets the minimum tech level for the laser
	 * 
	 * @return The minimum tech level for the laser
	 */
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Returns the price of the laser
	 * 
	 * @return The price of the laser
	 */
	public int getPrice() {
		return price;
	}

	public int getMinRep(EncounterType type) {
		if (type == EncounterType.POLICE)		return minPoliceRep;
		else if (type == EncounterType.PIRATE)	return minPirateRep;
		else if (type == EncounterType.TRADER)	return minTraderRep;
		return 0;
	}
	
	public int getMaxRep(EncounterType type) {
		if (type == EncounterType.POLICE)		return maxPoliceRep;
		else if (type == EncounterType.PIRATE)	return maxPirateRep;
		else if (type == EncounterType.TRADER)	return maxTraderRep;
		return 0;
	}
	
	@Override
	public String toString() {
		switch (this) {
		case PULSELASER:
			return "Pulse Laser";
		case BEAMLASER:
			return "Beam Laser";
		case MILITARYLASER:
			return "Military Laser";
		default:
			return null;
		}
	}
}