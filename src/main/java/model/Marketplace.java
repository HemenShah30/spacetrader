package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class Marketplace {
	private Map<GoodType, Double> prices;
	private Map<GoodType, Integer> quantities;
	private Planet planet;

	/**
	 * Constructor for Marketplace that takes in the planet that it is on
	 * 
	 * @param planet
	 *            The location of the marketplace
	 */
	public Marketplace(Planet planet) {
		this.planet = planet;

		prices = new HashMap<GoodType, Double>();
		quantities = new HashMap<GoodType, Integer>();
		for (GoodType type : GoodType.values()) {
			double price = generatePrice(type);
			prices.put(type, price);
			int quantity = generateQuantity(type);
			quantities.put(type, quantity);
		}
	}

	/**
	 * Generates the price of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param good
	 *            The TradeGood whose price is being determined
	 * @return The price of the TradeGood
	 */
	private double generatePrice(GoodType type) {
		TechLevel techlevel = planet.getTechLevel();
		double price = type.getBasePrice()
				+ type.getIncPerTechLevel()
				* (techlevel.getValue() - type.getMinTechLevelToProduce())
				+ (int) (2 * Math.random() * type.getVariance() - type
						.getVariance());
		return price;
	}

	/**
	 * Generates the quantity of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param good
	 *            The TradeGood whose quantity is being determined
	 * @return The quantity of the TradeGood
	 */
	private int generateQuantity(GoodType type) {
		TechLevel techlevel = planet.getTechLevel();
		if (techlevel.getValue() < type.getMinTechLevelToProduce()) {
			return 0;
		}

		// int quantity = (int) (Math.pow(
		// techlevel.getValue() - type.getMinTechLevel(), 2) + (2
		// * Math.random() * type.getVariance() - type.getVariance()));
		// if (techlevel.getValue() == type.getBiggestProducer()) {
		// quantity += type.getBiggestProducer();
		// }

		int quantity = techlevel.getValue() + (int) (10 * Math.random());

		return quantity;
	}

	/**
	 * Returns the quantity of a given good in the marketplace
	 * 
	 * @param good
	 *            The good whose quantity is being asked for
	 * @return The quantity of the given good
	 */
	public int getQuantity(GoodType good) {
		return quantities.get(good);
	}

	/**
	 * Returns the price of a given good in the marketplace
	 * 
	 * @param good
	 *            The good in the marketplace
	 * @return The price of the good in the marketplace
	 */
	public double getPrice(GoodType good) {
		return prices.get(good);
	}
	
	/**
	 * Sets the quantity for the good type
	 * @param good The good type to have its quantity changed
	 * @param quantity The new quantity of the good type
	 */
	public void setQuantity(GoodType good, int quantity) {
		quantities.put(good, quantity);
	}
}