package model;

import java.util.Random;

/**
 * Class representing Traders.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Trader extends NPC {
	private final double percentSelling = 0.5;
	private int quantity, price;
	private boolean isSelling; //determines if the Trader wants to sell something to the player or wants to buy something from the player
	private GoodType goodOfInterest; //determines which good the Trader wants to trade
	
	public Trader(int pilot, int fighter, int trader,
			int engineer, int investor) {
		setPilot(pilot);
		setFighter(fighter);
		setTrader(trader);
		setEngineer(engineer);
		setInvestor(investor);
		generateShip();
		generateIsSelling();
		generateGood();
		generatePrice();
		generateQuantity();
	}
	
	/**
	 * Determines if the Trader wants to sell or buy or sell from the player
	 * 
	 */
	private void generateIsSelling() {
		if (Math.random() < percentSelling) {
			isSelling = true;
		} else {
			isSelling = false;
		}
	}
	
	/**
	 * Randomly generates the good the trader is interested in
	 * 
	 */
	private void generateGood() {
		Random random = new Random();
		GoodType[] goods = GoodType.values();
		int goodIndex = random.nextInt(goods.length);
		this.goodOfInterest = goods[goodIndex];
	}
	
	/**
	 * Randomly generates the price of/for the good
	 * 
	 */
	private void generatePrice() {
		Random random = new Random();
		this.price = random.nextInt(goodOfInterest.getMaxTraderPrice()
				- goodOfInterest.getMinTraderPrice() + 1) + goodOfInterest.getMinTraderPrice();
	}
	
	/**
	 * Randomly generates the quantity of the good of interest
	 * 
	 */
	private void generateQuantity() {
		this.quantity = (int) (10 * Math.random() + 5);
	}
	
	/**
	 * Returns true if trader wants to sell to the player
	 * Returns false if the trader wants to buy from the player
	 * 
	 * @return true if the trader wants to sell to the player
	 * 			false otherwise
	 */
	public boolean getIsSelling() {
		return isSelling;
	}
	
	/**
	 * Returns the GoodType of the good the trader is interested in
	 * 
	 * @return GoodType of the good
	 */
	public GoodType getGood() {
		return goodOfInterest;
	}
	
	/**
	 * Returns the price of the good
	 * 
	 * @return price of good
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Returns the quantity of the good
	 * 
	 * @return quantity of good
	 */
	public int getQuantity() {
		return quantity;
	}
}
