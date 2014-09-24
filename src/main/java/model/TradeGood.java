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
	 * Generates the price of the good at random based on various GoodType
	 * parameters
	 * 
	 * @param planet
	 * @return
	 */
	public int generatePrice(Planet planet) {
		price = type.getBasePrice()
				+ type.getIncPerTechLevel()
				* (planet.getTechLevel().getValue() - type.getMinTechLevel())
				+ (int) (2 * Math.random() * type.getVariance() - type
						.getVariance());
		return price;
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
		return "Type: " + type + ", Price: " + price;
	}
}