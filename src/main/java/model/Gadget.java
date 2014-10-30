package model;

/**
 * Class encompassing the various gadgets
 * 
 * @author Caroline Zhang
 *
 */
public class Gadget implements Sellable {
	private int minTechLevel;
	private double price;

	/**
	 * Constructor for the Gadget class
	 * 
	 * @param p
	 *            Price in double
	 * @param mtl
	 *            Minimum tech level to be sold
	 */
	public Gadget(double p, int mtl) {
		price = p;
		minTechLevel = mtl;
	}

	/**
	 * Returns the price of the gadget in double
	 * 
	 */
	@Override
	public double getPrice() {
		return price;
	}

	/**
	 * Returns whether or not the ship can hold another gadget
	 * 
	 * @param ship
	 *            the player's ship
	 * @return boolean result
	 */
	@Override
	public boolean canBuy(Ship ship) {
		if (ship.getNumGadgets() + 1 > ship.getShipType().getGadgetSlots()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether or not the ship can sell a gadget
	 * 
	 * @param ship
	 *            the player's ship
	 * @return boolean result
	 */
	@Override
	public boolean canSell(Ship ship) {
		if (ship.getNumGadgets() < 1) {
			return false;
		}
		return true;
	}

	@Override
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Returns a 2 for addSellable purposes
	 * 
	 * @return int type 2 == gadget
	 */
	@Override
	public int getType() {
		return 2;
	}

}