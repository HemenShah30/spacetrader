package controller;

import model.Player;
import model.Ship;
import model.NPC;
//import model.Laser;
//import model.Shield;
//import model.Gadget;


/**
 * Class providing methods for NPC fights
 * 
 * @author Caroline Zhang
 * 
 */
public class FightEngine {

	private Player player;
	private Ship playerShip;
	private NPC npc;
	private Ship npcShip;

	/**
	 * Constructor for the FightEngine, taking in the main game player
	 * 
	 * @param p The player that will be fighting
	 */
	public FightEngine(Player p, NPC n) {
		player = p;
		playerShip = p.getShip();
		npc = n;
		npcShip = n.getShip();
	}
}