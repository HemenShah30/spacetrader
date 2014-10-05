package model;

import java.util.Random;

/**
 * Abstract class representing Traders.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Trader extends NPC {
	private final double percentSelling = 0.5;
	private int quantity, price;
	private boolean isSelling; //determines if the Trader wants to sell something to the player or wants to buy something from the player
	private GoodType goodOfInterest = GoodType.WATER; //determines which good the Trader wants to trade
	
	public Trader(int pilot, int fighter, int trader,
			int engineer, int investor, Ship ship) {
		setPilot(pilot);
		setFighter(fighter);
		setTrader(trader);
		setEngineer(engineer);
		setInvestor(investor);
		setShip(ship);
		generateIsSelling();
		generateGood();
		generatePrice();
		generateQuantity();
	}
	
	private void generateIsSelling() {
		if (Math.random() < percentSelling) {
			isSelling = true;
		} else {
			isSelling = false;
		}
	}
	
	private void generateGood() {
		//not sure if this is the best way to do this. please let me know if you can think of a better way
		this.goodOfInterest = goodOfInterest.getRandomGoodType();
	}
	
	private void generatePrice() {
		Random random = new Random();
		this.price = random.nextInt(goodOfInterest.getMaxTraderPrice()
				- goodOfInterest.getMinTraderPrice() + 1) + goodOfInterest.getMinTraderPrice();
	}
	
	private void generateQuantity() {
		this.quantity = (int) (10 * Math.random() + 5);
	}
	
	public boolean getIsSelling() {
		return isSelling;
	}
	
	public GoodType getGood() {
		return goodOfInterest;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
