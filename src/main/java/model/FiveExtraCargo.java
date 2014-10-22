package model;

/**
 * Gadget subclass which represents an additional 5 cargo spaces to the given
 * ship
 * 
 * @author Caroline Zhang
 *
 */
public class FiveExtraCargo extends Gadget {

	public static final int PRICE = 2000;
	public static final int MIN_TECH_LEVEL = 4;

	/**
	 * FiveExtraCargo constructor which passes up the price and minimum
	 * TechLevel to the Gadget superclass
	 */
	public FiveExtraCargo() {
		super(PRICE, MIN_TECH_LEVEL);
	}
}