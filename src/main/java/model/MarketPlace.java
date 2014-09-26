package model;

import java.util.ArrayList;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class MarketPlace {
	private ArrayList<TradeGood> inventory;
	private Planet planet;
	
	public MarketPlace(TechLevel techlevel, Planet planet) {
		inventory = new ArrayList<TradeGood>();
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
//			tradegood.generatePrice(techlevel);
			inventory.add(tradegood);
		}
		this.planet = planet;
	}
	
	public static void main(String[] args) {
		Location location = new Location(10 ,10);
		Planet planet1 = new Planet("test", TechLevel.HITECH, SpecialResource.NEVER, Government.ANARCHY, location);
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
//			tradegood.generatePrice(TechLevel.HITECH);
			System.out.println(tradegood);
		}
	}
	
	/**
	 * Generates the price of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param Techlevel of the current planet
	 * @return price of good
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
}
