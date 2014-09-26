package model;

import java.util.ArrayList;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class Marketplace {
	private ArrayList<TradeGood> inventory;
	private Planet planet;

	/**
	 * Constructor for Marketplace that takes in the planet that it is on
	 * 
	 * @param planet
	 *            The location of the marketplace
	 */
	public Marketplace(Planet planet) {
		inventory = new ArrayList<TradeGood>();
		for (GoodType type : GoodType.values()) {
			TradeGood tradegood = new TradeGood(type);
			inventory.add(tradegood);
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
	public double generatePrice(TradeGood good) {
		TechLevel techlevel = planet.getTechLevel();
		GoodType type = good.getType();
		double price = type.getBasePrice()
				+ type.getIncPerTechLevel()
				* (techlevel.getValue() - type.getMinTechLevel())
				+ (int) (2 * Math.random() * type.getVariance() - type
						.getVariance());
		return price;
	}
	
	
	

	public static void main(String[] args) {
		Location location = new Location(10, 10);
		Planet planet1 = new Planet("test", TechLevel.HITECH,
				SpecialResource.NEVER, Government.ANARCHY, location);
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
			System.out.println(tradegood);
		}
	}
}