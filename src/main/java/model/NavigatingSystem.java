package model;

/**
 * A NavigatingSystem gadget which improves the flight skill of the player
 * piloting its given ship
 * 
 * @author Caroline Zhang
 *
 */
public class NavigatingSystem extends Gadget {

	public static final int PRICE = 25000;
	public static final int MIN_TECH_LEVEL = 5;

	/**
	 * A constructor for the given NavigatingSystem which passes the price and
	 * minimum TechLevel up to the Gadget superclass
	 */
	public NavigatingSystem() {
		super(PRICE, MIN_TECH_LEVEL);
	}

}
