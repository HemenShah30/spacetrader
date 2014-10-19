package model.Enum;

/**
 * Enum representing the various lasers
 * 
 * @author Caroline Zhang
 *
 */
public enum LaserType {

	PULSELASER(20, 5000, 4), 
	BEAMLASER(35, 15000, 5), 
	MILITARYLASER(60, 35000, 6);

	private int baseDamage;
	private int minTechLevel;
	private int price;

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
	private LaserType(int bd, int p, int mtl) {
		baseDamage = bd;
		price = p;
		minTechLevel = mtl;
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