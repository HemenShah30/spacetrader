package model.Enum;

/**
 * Enum representing the various shields
 * 
 * @author Caroline Zhang
 *
 */
public enum ShieldType {

	ENERGYSHIELD(50, 10000, 4), 
	REFLECTIVESHIELD(100, 35000, 5);

	private int shieldHP;
	private int minTechLevel;
	private int price;

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
	private ShieldType(int hp, int p, int mtl) {
		shieldHP = hp;
		price = p;
		minTechLevel = mtl;
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