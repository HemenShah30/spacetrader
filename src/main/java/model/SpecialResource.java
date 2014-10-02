package model;

/**
 * Enum representing the resource of a planet
 * 
 * @author Larry He
 * 
 */
public enum SpecialResource {
	NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR, DESERT, LOTSOFWATER, RICHSOIL, POORSOIL, RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS, ARTISTIC, WARLIKE;

	@Override
	public String toString() {
		switch (this) {
		case MINERALRICH:
			return "Mineral Rich";
		case MINERALPOOR:
			return "Mineral Poor";
		case DESERT:
			return "Desert";
		case LOTSOFWATER:
			return "Lots of Water";
		case RICHSOIL:
			return "Rich Soil";
		case POORSOIL:
			return "Poor Soil";
		case RICHFAUNA:
			return "Rich Fauna";
		case LIFELESS:
			return "Lifeless";
		case WEIRDMUSHROOMS:
			return "Weird Mushrooms";
		case LOTSOFHERBS:
			return "Lots of Herbs";
		case ARTISTIC:
			return "Artistic";
		case WARLIKE:
			return "Warlike";
		case NOSPECIALRESOURCES:
			return "None";
		default:
			return null;
		}
	}
}