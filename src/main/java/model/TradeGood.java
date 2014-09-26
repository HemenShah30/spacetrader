package model;

/**
 * Class representing a trade good.
 * 
 * @author Larry He
 * 
 */
public class TradeGood {
	private GoodType type;
	private int price;
	private int quantity;

	/**
	 * Constructor for the trade good taking in a good type
	 * 
	 * @param type
	 *            The GoodType of the new TradeGood
	 */
	public TradeGood(GoodType type) {
		this.type = type;
	}

	/**
	 * Returns the type of the trade good
	 * 
	 * @return The type of the trade good
	 */
	public GoodType getType() {
		return type;
	}
	
	/**
	 * Returns the price of the trade good
	 * 
	 * @return The price of the trade good
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the quantity of the trade good
	 * 
	 * @return The quantity of the trade good
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Setter for the price of the trade good
	 * 
	 * @param price
	 *            The new price of the trade good
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Setter for the quantity of the trade good
	 * 
	 * @param quantity
	 *            The new quantity for the trade good
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Type: " + type;
	}
}