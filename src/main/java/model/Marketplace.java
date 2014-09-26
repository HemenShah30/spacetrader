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
		prices = new HashMap<GoodType, Double>();
		quantities = new HashMap<GoodType, Integer>();
		for (GoodType type : GoodType.values()) {
			double price = generatePrice(type);
			prices.put(type, price);
			int quantity = generateQuantity(type);
			quantities.put(type, quantity);
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
	
	
	/**
	 * Generates the quantity of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param good
	 *            The TradeGood whose quantity is being determined
	 * @return The quantity of the TradeGood
	 */
	public int generateQuantity(GoodType type) {
		TechLevel techlevel = planet.getTechLevel();
		if (techlevel.getValue() < type.getMinTechLevel() || techlevel.getValue() > type.getMaxTechLevel()) {
			return 0;
		}
		int quantity = (techlevel.getValue() - type.getMinTechLevel())^2
				+ (int) (2 * Math.random() * type.getVariance() - type
						.getVariance());
		if (techlevel.getValue() == type.getBiggestProducer()) {
			quantity += type.getBiggestProducer();
		}
		return quantity;
	}
}