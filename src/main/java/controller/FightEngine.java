package controller;

import java.util.List;

import model.DeathException;
import model.Player;
import model.Police;
import model.Ship;
import model.NPC;
import model.Enum.EncounterResult;
import model.Enum.GoodType;
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

	// TODO: escape pod, increase shields each player turn

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

	/**
	 * Represents an attack phase between the player and the given NPC
	 * 
	 * @param npc
	 *            The NPC who is fighting the player
	 * @return The result of the attack
	 */
	public EncounterResult playerAttack(NPC npc) {
		npcShip = npc.getShip();
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
		return npcTurn(npc);
	}

	/**
	 * Represents a player attempting to flee the given NPC
	 * 
	 * @param npc
	 *            The NPC who the player is fighting
	 * @return The result of the flee attempt or fight itself
	 */
	public EncounterResult playerFlee(NPC npc) {
		if (player.getPilotSkill() + (int) (Math.random() * 4) > npc
				.getPilotSkill()) {
			return EncounterResult.NPCFLEESUCCESS;
		}
		return npcTurn(npc);
	}

	/**
	 * Represents the player surrendering to the given NPC
	 * 
	 * @param npc
	 *            The NPC who the player is surrendering to
	 */
	public void playerSurrender(NPC npc) {
		int cargoRemoved = 0;
		double percentCreditsLost = .5;
		for (GoodType g : GoodType.values()) {
			int cargoAmt = player.getShip().amountInCargo(g);
			player.getShip().removeFromCargo(g, cargoAmt);
			cargoRemoved += cargoAmt;
		}
		if (cargoRemoved == 0)
			player.decreaseCredits(player.getCredits() * percentCreditsLost);
	}

	/**
	 * Calculates the NPC decision in the fight
	 * 
	 * @return The result of the NPC turn
	 */
	private EncounterResult npcTurn(NPC npc) {
		double healthRatio = (double) npcShip.getCurrHP()
				/ (double) npcShip.getTotalHP();
		if (healthRatio <= .2) {
			int nPilot = npc.getPilotSkill();
			int pPilot = player.getPilotSkill();
			if (nPilot + 2 < pPilot) {
				return EncounterResult.NPCSURRENDER;
			}
			if (nPilot + (int) (Math.random() * 4) > pPilot) {
				return EncounterResult.NPCFLEESUCCESS;
			}
			return EncounterResult.NPCFLEEFAIL;
		}
		List<LaserType> nLasers = npcShip.getLasers();
		int nDamage = 0;
		for (LaserType laser : nLasers) {
			nDamage += laser.getBaseDamage()
					+ ((npc.getFighterSkill() - 1) / 10 * 10);
		}
		try {
			playerShip.takeDamage(nDamage);
		} catch (DeathException death) {
			return EncounterResult.PLAYERDEATH;
		}
		return EncounterResult.NPCATTACK;
	}

	/**
	 * Conducts a police search of the player ship looking for illegal goods
	 * 
	 * @param police
	 *            The police that are searching the player
	 * @return The result of the search, false if nothing found, true if illegal
	 *         goods found
	 */
	public boolean consentToSearch(Police police) {
		return false;
	}

	/**
	 * Determines whether or not the police will accept a given bribe
	 * 
	 * @param police
	 *            The police being bribed
	 * @param credits
	 *            The amount of credits the player is offering
	 * @return The success of the bribe attempt
	 */
	public boolean bribePolice(Police police, int credits) {
		if (police.willAcceptBribe(credits)) {
			player.decreaseCredits(credits);
			return true;
		}
		return false;
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