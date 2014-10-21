package model.Enum;

/**
 * Enum representing the various shields
 * 
 * @author Caroline Zhang
 *
 */
public enum ShieldType {

	ENERGYSHIELD(50, 10000, 4, 20, 30, 30, 80, 101, 101), 
	REFLECTIVESHIELD(100, 35000, 5, 40, 50, 50, 101, 101, 101);

	private int shieldHP;
	private int minTechLevel;
	private int price;
	private int minPoliceRep;
	private int minPirateRep;
	private int minTraderRep;
	private int maxPoliceRep;
	private int maxPirateRep;
	private int maxTraderRep;
	
	/**
	 * Constructor for the shield enums
	 * 
	 * @param d
	 *            The damage dealt by the shield
	 * @param p
	 *            The price of the shield
	 * @param mtl
	 *            The minimum tech level required for a shield
	 */
	private ShieldType(int hp, int p, int mtl, int minPoliceRep, int minPirateRep, int minTraderRep,
			int maxPoliceRep, int maxPirateRep, int maxTraderRep) {
		shieldHP = hp;
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
	 * Returns the additional hp contributed by the shield
	 * 
	 * @return The additional hp contributed by the shield
	 */
	public int getShieldHP() {
		return shieldHP;
	}

	/**
	 * Gets the minimum tech level for the shield
	 * 
	 * @return The minimum tech level for the shield
	 */
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Returns the price of the shield
	 * 
	 * @return The price of the shield
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
		case ENERGYSHIELD:
			return "Energy Shield";
		case REFLECTIVESHIELD:
			return "Reflective Shield";
		default:
			return null;
		}
	}
}