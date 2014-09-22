package model;

/**
 * Class representing the marketplace of a planet
 * 
 * @author Larry He
 * 
 */
public class MarketPlace {
	
	public static void main(String[] args) {
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
			tradegood.generatePrice(null);
			System.out.println(tradegood);
		}
	}
}
