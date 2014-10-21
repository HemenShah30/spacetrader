package controller;

import java.util.List;

import model.DeathException;
import model.NPCEncounter;
import model.Player;
import model.Police;
import model.Ship;
import model.NPC;
import model.Enum.EncounterResult;
import model.Enum.GoodType;
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
	public EncounterResult playerAttack(NPCEncounter encounter) {
		if (encounter.getTurnCount() == 0) {
			regenExtraShields();
		} else {
			regenShields();
		}

		encounter.takeTurn();
		NPC npc = encounter.getNPC();
		npcShip = npc.getShip();
		List<LaserType> lasers = playerShip.getLasers();
		int damage = 0;
		for (LaserType laser : lasers) {
			damage += laser.getBaseDamage()
					+ ((player.getFighterSkill() - 1) * 2);
		}
		try {
			npcShip.takeDamage(damage);
		} catch (DeathException death) {
			regenShields();
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
	public EncounterResult playerFlee(NPCEncounter encounter) {
		if (encounter.getTurnCount() == 0) {
			regenExtraShields();
		} else {
			regenShields();
		}

		encounter.takeTurn();
		NPC npc = encounter.getNPC();
		if (player.getPilotSkill() + (int) (Math.random() * 3) > npc
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
	public void playerSurrender(NPCEncounter encounter) {
		regenExtraShields();
		encounter.takeTurn();
		int cargoRemoved = 0;
		double percentCreditsLost = .5;
		for (GoodType g : GoodType.values()) {
			int cargoAmt = playerShip.amountInCargo(g);
			playerShip.removeFromCargo(g, cargoAmt);
			cargoRemoved += cargoAmt;
		}
		if (cargoRemoved == 0)
			player.decreaseCredits(player.getCredits() * percentCreditsLost);
	}

	/**
	 * Calculates the NPC decision in the fight
	 * 
	 * @param npc
	 *            The NPC whose turn is being calculated
	 * @return The result of the NPC turn
	 */
	private EncounterResult npcTurn(NPC npc) {
		double healthRatio = (double) npcShip.getCurrHP()
				/ (double) npcShip.getMaxHP();
		if (healthRatio <= .2) {
			int nPilot = npc.getPilotSkill();
			int pPilot = player.getPilotSkill();
			if (nPilot + 1 < pPilot) {
				regenShields();
				return EncounterResult.NPCSURRENDER;
			}
			if (nPilot + (int) (Math.random() * 3) > pPilot) {
				regenShields();
				return EncounterResult.NPCFLEESUCCESS;
			}
			return EncounterResult.NPCFLEEFAIL;
		}
		List<LaserType> nLasers = npcShip.getLasers();
		int nDamage = 0;
		for (LaserType laser : nLasers) {
			nDamage += laser.getBaseDamage()
					+ ((npc.getFighterSkill() - 1) * 2);
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
	public boolean consentToSearch(NPCEncounter encounter) {
		if (encounter.getTurnCount() == 0) {
			regenExtraShields();
		}
		encounter.takeTurn();
		int amtFirearms = playerShip.amountInCargo(GoodType.FIREARMS);
		int amtNarcotics = playerShip.amountInCargo(GoodType.NARCOTICS);

		if (amtFirearms != 0 || amtNarcotics != 0) {
			playerShip.removeFromCargo(GoodType.FIREARMS, amtFirearms);
			playerShip.removeFromCargo(GoodType.NARCOTICS, amtNarcotics);
			// pay fine
			if (player.getPoliceRep() < 10) {
				player.setPoliceRep(player.getPoliceRep() + 1);
			}
			return true;
		}
		if (player.getPoliceRep() > 1) {
			player.setPoliceRep(player.getPoliceRep() - 1);
		}
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
	public boolean bribePolice(NPCEncounter encounter, int credits) {
		Police police = (Police) encounter.getNPC();
		regenExtraShields();
		encounter.takeTurn();
		if (police.willAcceptBribe(credits)) {
			player.decreaseCredits(credits);
			return true;
		}
		return false;
	}

	/**
	 * Regenerates a portion of the player's shields based on engineer skill
	 */
	private void regenShields() {
		Ship ship = player.getShip();
		ship.addShieldHP((int) (ship.getMaxShieldHP()
				* player.getEngineerSkill() / 100.));
	}

	/**
	 * Regenerates more of the player's shields at the beginning and end of an
	 * encounter
	 */
	private void regenExtraShields() {
		regenShields();
		regenShields();
	}
}