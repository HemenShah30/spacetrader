package model;

import java.util.ArrayList;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class Marketplace {
	private ArrayList<GoodType> inventory;
	private Planet planet;

	/**
	 * Constructor for Marketplace that takes in the planet that it is on
	 * 
	 * @param planet
	 *            The location of the marketplace
	 */
	public Marketplace(Planet planet) {
		inventory = new ArrayList<GoodType>();
		for (GoodType type : GoodType.values()) {
			inventory.add(type);
		}

		this.planet = planet;
	}

	/**
	 * Generates the price of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param good
	 *            The TradeGood whose price is being determined
	 * @return The price of the TradeGood
	 */
	public double generatePrice(GoodType type) {
		TechLevel techlevel = planet.getTechLevel();
		double price = type.getBasePrice()
				+ type.getIncPerTechLevel()
				* (techlevel.getValue() - type.getMinTechLevel())
				+ (int) (2 * Math.random() * type.getVariance() - type
						.getVariance());
		return price;
	}
}