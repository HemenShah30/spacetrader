package model;

/**
 * Enum representing the number of pirates/police you can expect to encounter
 * 
 * @author Larry He
 * 
 */
public enum EncounterRate {
	NONE, FEW, SOME, MANY, SWARMS;

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