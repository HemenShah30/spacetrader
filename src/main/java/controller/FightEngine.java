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
	
	//TODO: plunder
	//TODO: escape pod
	//TODO: destroyShip
	//TODO: surrender

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

	//check for weapons before calling
	public void attack() {
		//damage is first dealt to shields
		npcShip.setCurrHP(npcShip.getCurrHP() - 5); //arbitrary value until we solidify a way of storing lasers
		//damage determined by:
		//LaserType.baseDamage + ((fightSkill-1)/10 *10)
		//for each laser
	}
	
	//called in between each attack
	//called while in flight??
	//can you repair shields?
	public void repair() {
		if (playerShip.getCurrHP() != playerShip.getTotalHP()) {
			int repairs = playerShip.getCurrHP()
					+ ((player.getEngineerSkill()-1)/10 *20);
			if (repairs >= playerShip.getTotalHP()) {
				playerShip.setCurrHP(playerShip.getTotalHP());
			} else {
				playerShip.setCurrHP(repairs);
			}
		}
		
//		if (npcShip.getCurrHP() != npcShip.getTotalHP()) {
//			int repairs = npcShip.getCurrHP()
//					+ ((npc.getEngineer()-1)/10 *20);
//			if (repairs >= npcShip.getTotalHP()) {
//				npcShip.setCurrHP(npcShip.getTotalHP());
//			} else {
//				npcShip.setCurrHP(repairs);
//			}
//		}
	}
	
	public boolean playerCanFlee() {
		double playerFlee = Math.random()*2 + player.getPilotSkill();
		double npcFlee = Math.random()*2 + npc.getPilot();
		return (playerFlee > npcFlee);
	}

//	public boolean npcCanFlee() {
//		double playerFlee = Math.random()*2 + player.getPilotSkill();
//		double npcFlee = Math.random()*2 + npc.getPilot();
//		return (npcFlee > playerFlee);
//	}
	
}