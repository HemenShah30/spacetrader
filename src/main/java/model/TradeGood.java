package model;

/**
 * Class representing a trade good.
 * 
 * @author Larry He
 * 
 */
public class TradeGood {
	private GoodType type;

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

	@Override
	public String toString() {
		return "Type: " + type;
	}
}