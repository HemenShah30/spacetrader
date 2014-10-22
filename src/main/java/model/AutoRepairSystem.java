package model;

/**
 * Gadget that represents an automatic repair system, adding to the player's
 * Engineer skill
 * 
 * @author Caroline Zhang
 *
 */
public class AutoRepairSystem extends Gadget {

	public static final int PRICE = 25000;
	public static final int MIN_TECH_LEVEL = 5;

	/**
	 * Constructor for the AutoRepairsystem, passing in its price and minimum
	 * TechLevel to the Gadget superclass
	 */
	public AutoRepairSystem() {
		super(PRICE, MIN_TECH_LEVEL);
	}
}