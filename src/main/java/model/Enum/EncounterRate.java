package model.Enum;

/**
 * Enum representing the number of pirates/police you can expect to encounter
 * 
 * @author Larry He
 * 
 */
public enum EncounterRate {
	NONE(0), FEW(1), SOME(2), MANY(3), SWARMS(4);

	private int value;

	/**
	 * Constructor for the EncounterRate taking in the integer value of the enum
	 * 
	 * @param v
	 *            The integer value of the enum
	 */
	private EncounterRate(int v) {
		value = v;
	}

	/**
	 * Returns the integer value of the enum
	 * 
	 * @return The integer value of the enum
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		switch (this) {
		case NONE:
			return "None";
		case FEW:
			return "Few";
		case SOME:
			return "Some";
		case MANY:
			return "Many";
		case SWARMS:
			return "Swarms";
		default:
			return null;
		}
	}
}