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
	
	public MarketPlace(TechLevel techlevel) {
		inventory = new ArrayList<TradeGood>();
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
			tradegood.generatePrice(techlevel);
			inventory.add(tradegood);
		}
	}
	
	public static void main(String[] args) {
		Location location = new Location(10 ,10);
		Planet planet = new Planet("test", TechLevel.HITECH, SpecialResource.NEVER, Government.ANARCHY, location);
		for (GoodType good : GoodType.values()) {
			TradeGood tradegood = new TradeGood(good);
			tradegood.generatePrice(TechLevel.HITECH);
			System.out.println(tradegood);
		}
	}
}
