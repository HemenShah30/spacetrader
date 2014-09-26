package model;

/**
 * Enum representing the tech level of a planet
 * 
 * @author Larry He
 * 
 */
public enum TechLevel {
	PREAGRICULTURE(0), AGRICULTURE(1), MEDIEVAL(2), RENAISSANCE(3), EARLYINDUSTRIAL(
			4), INDUSTRIAL(5), POSTINDUSTRIAL(6), HITECH(7);

	private int value;

	private TechLevel(int value) {
		this.value = value;
	}

	/**
	 * Getter for value of a tech level. Used in calculating price of a good.
	 * 
	 * @return Value of a tech level
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		switch (this) {
		case PREAGRICULTURE:
			return "Preagriculture";
		case AGRICULTURE:
			return "Agriculture";
		case MEDIEVAL:
			return "Medieval";
		case RENAISSANCE:
			return "Renaissance";
		case EARLYINDUSTRIAL:
			return "Early Industrial";
		case INDUSTRIAL:
			return "Industrial";
		case POSTINDUSTRIAL:
			return "Post-Industrial";
		case HITECH:
			return "High Tech";
		default:
			return null;
		}
	}
}