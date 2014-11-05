package model;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public boolean canBuy(Ship ship) {
		if (ship.getNumGadgets() + 1 > ship.getShipType().getGadgetSlots()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canSell(Ship ship) {
		return ship.hasGadget(this);
	}

	@Override
	public int getMinTechLevel() {
		return minTechLevel;
	}

	@Override
	public int getType() {
		return 2;
	}

	/**
	 * Returns a list of all the gadgets in the game
	 * 
	 * @return The list of all gadgets in the game
	 */
	public static List<Gadget> getAllGadgets() {
		List<Gadget> gadgets = new ArrayList<Gadget>();
		gadgets.add(new AutoRepairSystem());
		gadgets.add(new FiveExtraCargo());
		gadgets.add(new NavigatingSystem());
		gadgets.add(new TargetingSystem());
		gadgets.add(new CloakingDevice());
		return gadgets;
	}
}