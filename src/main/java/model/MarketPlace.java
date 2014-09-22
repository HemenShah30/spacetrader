package model;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class MarketPlace {
	
	public static void main(String[] args) {
		Location location = new Location(10 ,10);
		Planet planet = new Planet("test", TechLevel.HITECH, SpecialResource.NEVER, Government.ANARCHY, location);
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
			tradegood.generatePrice(planet);
			System.out.println(tradegood);
		}
	}
}
