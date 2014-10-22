package model;

import java.util.Random;

import model.Enum.EncounterType;
import model.Enum.GoodType;

/**
 * Class representing Traders.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Trader extends NPC {
	private final double percentBuying = 0.5;
	private int quantity, price;
	private boolean isBuying; // determines if the Trader wants to sell
								// something to the player or wants to buy
								// something from the player
	private GoodType goodOfInterest; // determines which good the Trader wants
										// to trade
	private double credits;

	/**
	 * Constructor for a Trader that takes in the player reputation with traders
	 * 
	 * @param rep
	 *            The TraderReputation for the player
	 */
	public Trader(int rep) {
		setPilotSkill(1);
		setFighterSkill((int) (rep * .08));
		setTraderSkill(1);
		setEngineerSkill((int) (rep * .08));
		setInvestorSkill(1);
		generateShip(rep, EncounterType.TRADER);
		generateCredits(rep);
		generateIsBuying();
		generateGood();
		generatePrice();
		generateQuantity(rep);
	}

	/**
	 * Determines if the Trader wants to sell or buy or sell from the player
	 * 
	 */
	private void generateIsBuying() {
		if (Math.random() < percentBuying) {
			isBuying = true;
		} else {
			isBuying = false;
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
	 * Returns false if trader wants to sell to the player Returns true if the
	 * trader wants to buy from the player
	 * 
	 * @return false if the trader wants to sell to the playerm true otherwise
	 */
	public boolean isBuying() {
		return isBuying;
	}

	/**
	 * Returns the GoodType of the good the trader is interested in
	 * 
	 * @return GoodType of the good
	 */
	public GoodType getGoodOfInterest() {
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
	 * Returns the quantity of the good that the trader wants to buy or sell
	 * 
	 * @return quantity of good that the trader wants to buy or sell
	 */
	public int getQuantity() {
		return quantity;
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

	@Override
	public double getCredits() {
		return credits;
	}

	@Override
	public String toString() {
		return "Trader";
	}
}