package model;

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
		
	}
	
	private void generateIsSelling() {
		if (Math.random() < percentSelling) {
			isSelling = true;
		} else {
			isSelling = false;
		}
	}
	
	private void generateGood() {
		this.goodOfInterest = goodOfInterest.getRandomGoodType();
	}
	
	private void generatePrice() {
		
	}
	
	private void generateQuantity() {
		
	}
	
	public boolean getIsSelling() {
		return isSelling;
	}
	
	public GoodType getGood() {
		return goodOfInterest;
	}
	
	
}
