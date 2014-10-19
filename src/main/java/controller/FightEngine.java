package controller;

import model.Player;
import model.Ship;
import model.NPC;
import model.Enum.EncounterResult;
//import model.Laser;
//import model.Shield;
//import model.Gadget;
import model.Enum.LaserType;

/**
 * Class providing methods for NPC fights
 * 
 * @author Caroline Zhang
 * 
 */
public class FightEngine {

	// TODO: plunder
	// TODO: escape pod
	// TODO: destroyShip
	// TODO: surrender

	private Player player;
	private Ship playerShip;
	private NPC npc;
	private Ship npcShip;

	/**
	 * Constructor for the FightEngine, taking in the main game player
	 * 
	 * @param p
	 *            The player that will be fighting
	 */
	public FightEngine(Player p) {
		player = p;
		playerShip = p.getShip();
	}

	public EncounterResult playerAttack(NPC n) {
		npc = n;
		npcShip = n.getShip();
		LaserType[] lasers = playerShip.getLasers();
		int damage = 0;
		for (LaserType laser : lasers) {
			damage += laser.getBaseDamage()
					+ ((player.getFighterSkill() - 1) / 10 * 10);
		}
		npcShip.takeDamage(damage);
		double healthRatio = (double) npcShip.getCurrHP() / (double) npcShip.getTotalHP();
		if (healthRatio <= 0.00) {
			return EncounterResult.NPCDEATH;
		} else if (healthRatio <= 0.20) {
			int nPilot = n.getPilot();
			int pPilot = player.getPilotSkill();
			if (nPilot + 2 < pPilot) {
				return EncounterResult.NPCSURRENDER;
			}
			if (nPilot + (int) (Math.random() * 4) > pPilot) {
				return EncounterResult.NPCFLEESUCCESS;
			}
			return EncounterResult.NPCFLEEFAIL;	
		}
		// now, NPC attacks
		LaserType[] nLasers = npcShip.getLasers();
		int nDamage = 0;
		for (LaserType laser : nLasers) {
			nDamage += laser.getBaseDamage()
					 + ((n.getFighter() - 1) / 10 * 10);
		}
		playerShip.takeDamage(nDamage);
		// handle death?
		return EncounterResult.NPCATTACK;
		
	}

	// //check for weapons before calling
	// public void attack() {
	// //damage is first dealt to shields
	// npcShip.setCurrHP(npcShip.getCurrHP() - 5); //arbitrary value until we
	// solidify a way of storing lasers
	// //damage determined by:
	// //LaserType.baseDamage + ((fightSkill-1)/10 *10)
	// //for each laser
	// }

	// called in between each attack
	// called while in flight??
	// can you repair shields?
	// public void repair() {
	// if (playerShip.getCurrHP() != playerShip.getTotalHP()) {
	// int repairs = playerShip.getCurrHP()
	// + ((player.getEngineerSkill()-1)/10 *20);
	// if (repairs >= playerShip.getTotalHP()) {
	// playerShip.setCurrHP(playerShip.getTotalHP());
	// } else {
	// playerShip.setCurrHP(repairs);
	// }
	// }

	// if (npcShip.getCurrHP() != npcShip.getTotalHP()) {
	// int repairs = npcShip.getCurrHP()
	// + ((npc.getEngineer()-1)/10 *20);
	// if (repairs >= npcShip.getTotalHP()) {
	// npcShip.setCurrHP(npcShip.getTotalHP());
	// } else {
	// npcShip.setCurrHP(repairs);
	// }
	// }
	// }

	// public boolean playerCanFlee() {
	// double playerFlee = Math.random()*2 + player.getPilotSkill();
	// double npcFlee = Math.random()*2 + npc.getPilot();
	// return (playerFlee > npcFlee);
	// }

	// public boolean npcCanFlee() {
	// double playerFlee = Math.random()*2 + player.getPilotSkill();
	// double npcFlee = Math.random()*2 + npc.getPilot();
	// return (npcFlee > playerFlee);
	// }

}