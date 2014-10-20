package controller;

import java.util.List;

import model.DeathException;
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

	// TODO: escape pod/destroyShip/surrender

	private Player player;
	private Ship playerShip;
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
		npcShip = n.getShip();
		List<LaserType> lasers = playerShip.getLasers();
		int damage = 0;
		for (LaserType laser : lasers) {
			damage += laser.getBaseDamage()
					+ ((player.getFighterSkill() - 1) / 10 * 10);
		}
		try {
			npcShip.takeDamage(damage);
		} catch (DeathException death) {
			return EncounterResult.NPCDEATH;
		}
		double healthRatio = (double) npcShip.getCurrHP()
				/ (double) npcShip.getTotalHP();
		if (healthRatio <= .2) {
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
		List<LaserType> nLasers = npcShip.getLasers();
		int nDamage = 0;
		for (LaserType laser : nLasers) {
			nDamage += laser.getBaseDamage() + ((n.getFighter() - 1) / 10 * 10);
		}
		try {
			playerShip.takeDamage(nDamage);
		} catch (DeathException death) {
			return EncounterResult.PLAYERDEATH;
		}
		return EncounterResult.NPCATTACK;
	}

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
	// }
}