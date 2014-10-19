package model.Enum;

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
			return "No Special Resource";
		default:
			return null;
		}
	}

	/**
	 * Returns the enum given the toString() of the enum
	 * 
	 * @param value
	 *            The enum being looked for
	 * @return The enum represented by the value string
	 */
	public static SpecialResource getEnum(String value) {
		value = value.toLowerCase();
		switch (value) {
		case "mineral rich":
			return SpecialResource.MINERALRICH;
		case "mineral poor":
			return SpecialResource.MINERALPOOR;
		case "desert":
			return SpecialResource.DESERT;
		case "lots of water":
			return SpecialResource.LOTSOFWATER;
		case "rich soil":
			return SpecialResource.RICHSOIL;
		case "poor soil":
			return SpecialResource.POORSOIL;
		case "rich fauna":
			return SpecialResource.RICHFAUNA;
		case "lifeless":
			return SpecialResource.LIFELESS;
		case "weird mushrooms":
			return SpecialResource.WEIRDMUSHROOMS;
		case "lots of herbs":
			return SpecialResource.LOTSOFHERBS;
		case "artistic":
			return SpecialResource.ARTISTIC;
		case "warlike":
			return SpecialResource.WARLIKE;
		case "no special resource":
			return SpecialResource.NOSPECIALRESOURCES;
		default:
			return null;
		}
	}
}