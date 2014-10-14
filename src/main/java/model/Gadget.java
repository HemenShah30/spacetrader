package model;

/**
 * Class encompassing the various gadgets
 * 
 * @author Caroline Zhang
 *
 */
//why did we want to make a Gadget interface? pls help.
public class Gadget {
	private int price, minTechLevel;

	public Gadget(int p, int mtl) {
		price = p;
		minTechLevel = mtl;
	}
	
	//TODO: have some way of checking for gadgets in order to give additional stats to ship/player
	//either before using any of the stats, or when adding the gadget to the ship
	//if we choose to update stats when gadget is added to ship, we also need to re-update when gadget is sold
}