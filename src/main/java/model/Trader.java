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
	private boolean isSelling; // determines if the Trader wants to sell
								// something to the player or wants to buy
								// something from the player
	private GoodType goodOfInterest; // determines which good the Trader wants
										// to trade
	private double credits;

	public Trader(int rep) {
		setPilot(1);
		setFighter((int) (rep * .5));
		setTrader(1);
		setEngineer((int) (rep * .5));
		setInvestor(1);
		generateShip(rep);
		generateCredits(rep);
		generateIsSelling();
		generateGood();
		generatePrice();
		generateQuantity(rep);
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
				- goodOfInterest.getMinTraderPrice() + 1)
				+ goodOfInterest.getMinTraderPrice();
	}

	/**
	 * Randomly generates the quantity of the good of interest
	 * 
	 * @param rep
	 *            the player's traderRep
	 */
	private void generateQuantity(int rep) {
		this.quantity = (int) (rep * Math.random() + 5);
	}

	/**
	 * Returns true if trader wants to sell to the player Returns false if the
	 * trader wants to buy from the player
	 * 
	 * @return true if the trader wants to sell to the player false otherwise
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

	/**
	 * Generates a ship based off of player's tradeRep
	 * 
	 * @param rep
	 *            Player's accountability with traders
	 */
	private void generateShip(int rep) {
		ShipType[] shiptypes = ShipType.values();
		int index = (rep - 1) / 2;
		ShipType shiptype = shiptypes[index];
		setShip(new Ship(shiptype));
	}

	/**
	 * Generates the Trader's available credits off of player's tradeRep
	 * 
	 * @param rep
	 *            Player's accountability with traders
	 */
	private void generateCredits(int rep) {
		credits = (double) (rep * 800);
	}

	/**
	 * Returns Trader's credits
	 * 
	 * @return trader's credits
	 */
	public double getCredits() {
		return credits;
	}

}
