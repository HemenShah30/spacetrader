package controller;

import model.Player;
import model.Ship;

/**
 * Class providing methods for mercenary interactions
 * 
 * @author Larry He
 * 
 */
public class MercenaryEngine {
	
	private Player player;
	private Ship ship;
	
	/**
	 * Constructor for the MercenaryEngine, taking in the main game player
	 * 
	 * @param p
	 *            The player that will be hiring mercenaries
	 */
	public MercenaryEngine(Player p) {
		player = p;
		ship = p.getShip();
	}
}
