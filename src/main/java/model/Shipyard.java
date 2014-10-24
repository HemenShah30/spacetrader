package model;

import java.util.HashMap;
import java.util.Map;

import model.Enum.GoodType;
import model.Enum.ShipType;

public class Shipyard {
	private Map<ShipType, Integer> ships;
	private Planet planet;
	
	public Shipyard(Planet planet) {
		
	}
	
	/**
	 * Returns the quantity of a given good in the marketplace
	 * 
	 * @param good
	 *            The good whose quantity is being asked for
	 * @return The quantity of the given good
	 */
	public int getQuantity(GoodType good) {
		return 0;
		//return quantities.get(good);
	}

	/**
	 * Returns the price of a given good in the marketplace to be sold by the
	 * player
	 * 
	 * @param good
	 *            The good in the marketplace
	 * @return The price of the good in the marketplace
	 */
	public double getSellPrice(GoodType good) {
		if (good.getMinTechLevelToUse() > planet.getTechLevel().getValue())
			return -1;
		return 0;
		//return prices.get(good);
	}
}
