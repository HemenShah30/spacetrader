package model;

/**
 * Class representing the condition of a planet
 * 
 * @author Larry He
 * 
 */
public enum Condition {
	NONE, DROUGHT, COLD, CROPFAIL, WAR, BOREDOM, PLAGUE, LACKOFWORKERS;

	@Override
	public String toString() {
		switch (this) {
		case NONE:
			return "None";
		case DROUGHT:
			return "Drought";
		case COLD:
			return "Cold";
		case CROPFAIL:
			return "Crop Failure";
		case WAR:
			return "War";
		case BOREDOM:
			return "Boredom";
		case PLAGUE:
			return "Plague";
		case LACKOFWORKERS:
			return "Lack of Workers";
		default:
			return null;
		}
	}
}