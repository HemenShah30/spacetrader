package controller;

import model.DeathException;
import model.NPC;
import model.NPCEncounter;
import model.Pirate;
import model.Player;
import model.Police;
import model.Ship;
import model.Trader;

import model.enums.EncounterResult;
import model.enums.GoodType;
import model.enums.LaserType;

import java.util.List;

/**
 * Class providing methods for NPC fights
 * 
 * @author Caroline Zhang
 * 
 */
public class FightEngine {

    private Player player;
    private Ship playerShip;
    private Ship npcShip;
    private static final int REP_CHANGE = 5;
    private static final int MAX_REP = 100;

    /**
     * Constructor for the FightEngine, taking in the main game player
     * 
     * @param p
     *            The player that will be fighting
     */
    public FightEngine(Player player) {
        this.player = player;
        playerShip = player.getShip();
    }

    /**
     * Represents an attack phase between the player and the given NPC in the NPCEncounter
     * 
     * @param encounter
     *            The encounter the player is involved in
     * @return The result of the encounter turn
     */
    public EncounterResult playerAttack(NPCEncounter encounter) {
        NPC npc = encounter.getNPC();
        if (encounter.getTurnCount() == 0) {
            regenExtraShields();
            if (npc instanceof Pirate) {
                if (player.getPirateRep() + REP_CHANGE > MAX_REP) {
                    player.setPirateRep(MAX_REP);
                } else {
                    player.setPirateRep(player.getPirateRep() + REP_CHANGE);
                }
            } else if (npc instanceof Police) {
                if (player.getPoliceRep() + REP_CHANGE > MAX_REP) {
                    player.setPoliceRep(MAX_REP);
                } else {
                    player.setPoliceRep(player.getPoliceRep() + REP_CHANGE);
                }
            } else if (npc instanceof Trader) {
                if (player.getTraderRep() - REP_CHANGE < 1) {
                    player.setTraderRep(1);
                } else {
                    player.setTraderRep(player.getPoliceRep() - REP_CHANGE);
                }
            }
        } else {
            regenShields();
        }

        encounter.takeTurn();
        npcShip = npc.getShip();
        List<LaserType> lasers = playerShip.getLasers();
        int damage = 0;
        for (LaserType laser : lasers) {
            damage += laser.getBaseDamage()
                    + ((player.getFighterSkill() + playerShip.getFightingBonus() - 1) * 2);
        }
        try {
            if (Math.random() > .2 * (npcShip.hasCloakingDevice() ? 1 : 0)) {
                npcShip.takeDamage(damage);
            }
        } catch (DeathException death) {
            regenShields();
            return EncounterResult.NPCDEATH;
        }
        return npcTurn(npc);
    }

    /**
     * Represents a player attempting to flee the given NPC in the NPCEncounter
     * 
     * @param encounter
     *            The NPCEncounter that the player is involved in
     * @return The result of the player flee attempt
     */
    public EncounterResult playerFlee(NPCEncounter encounter) {
        NPC npc = encounter.getNPC();
        npcShip = npc.getShip();
        if (encounter.getTurnCount() == 0) {
            regenExtraShields();
            if (npc instanceof Pirate) {
                if (player.getPirateRep() - REP_CHANGE < 1) {
                    player.setPirateRep(1);
                } else {
                    player.setPirateRep(player.getPirateRep() - REP_CHANGE);
                }
            } else if (npc instanceof Police) {
                if (player.getPoliceRep() + REP_CHANGE > MAX_REP) {
                    player.setPoliceRep(MAX_REP);
                } else {
                    player.setPoliceRep(player.getPoliceRep() + REP_CHANGE);
                }
            }
        } else {
            regenShields();
        }

        encounter.takeTurn();
        if (player.getPilotSkill() + playerShip.getPilotBonus()
                + (playerShip.hasCloakingDevice() ? 5 : 0) + (int) (Math.random() * 3) > npc
                .getPilotSkill() + npcShip.getPilotBonus()) {
            return EncounterResult.PLAYERFLEESUCCESS;
        }
        return npcTurn(npc);
    }

    /**
     * Represents the player surrendering to the given NPC in the NPCEncounter
     * 
     * @param encounter
     *            The NPCEncounter the player is involved in
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
        if (cargoRemoved == 0) {
            player.decreaseCredits(player.getCredits() * percentCreditsLost);
        }
        NPC npc = encounter.getNPC();
        if (npc instanceof Pirate) {
            if (player.getPirateRep() - REP_CHANGE < 1) {
                player.setPirateRep(1);
            } else {
                player.setPirateRep(player.getPirateRep() - REP_CHANGE);
            }
        } else if (npc instanceof Trader) {
            if (player.getTraderRep() + REP_CHANGE > MAX_REP) {
                player.setTraderRep(MAX_REP);
            } else {
                player.setTraderRep(player.getTraderRep() + REP_CHANGE);
            }
        }
    }

    /**
     * Calculates the NPC decision in the fight
     * 
     * @param npc
     *            The NPC whose turn is being calculated
     * @return The result of the NPC turn
     */
    private EncounterResult npcTurn(NPC npc) {
        double healthRatio = (double) npcShip.getCurrHp() / (double) npcShip.getMaxHp();
        if (healthRatio <= .2) {
            int npilot = npc.getPilotSkill() + npcShip.getPilotBonus();
            int ppilot = player.getPilotSkill() + playerShip.getPilotBonus();
            if (npilot + 1 < ppilot) {
                regenShields();
                return EncounterResult.NPCSURRENDER;
            }
            if (npilot + (int) (Math.random() * 3) > ppilot) {
                regenShields();
                return EncounterResult.NPCFLEESUCCESS;
            }
            return EncounterResult.NPCFLEEFAIL;
        }
        List<LaserType> nlasers = npcShip.getLasers();
        int ndamage = 0;
        for (LaserType laser : nlasers) {
            ndamage += laser.getBaseDamage()
                    + ((npc.getFighterSkill() + npcShip.getFightingBonus() - 1) * 2);
        }
        try {
            if (Math.random() > .2 * (playerShip.hasCloakingDevice() ? 1 : 0)) {
                playerShip.takeDamage(ndamage);
            }
        } catch (DeathException death) {
            return EncounterResult.PLAYERDEATH;
        }
        return EncounterResult.NPCATTACK;
    }

    /**
     * Conducts a police search of the player ship looking for illegal goods
     * 
     * @param encounter
     *            The NPCEncounter where the police are looking for illegal goods
     * @return The result of the search, false if nothing found, true if illegal goods found
     */
    public boolean consentToSearch(NPCEncounter encounter) {
        if (encounter.getTurnCount() == 0) {
            regenExtraShields();
        }
        encounter.takeTurn();
        int ppolice = player.getPoliceRep();
        int amtFirearms = playerShip.amountInCargo(GoodType.FIREARMS);
        int amtNarcotics = playerShip.amountInCargo(GoodType.NARCOTICS);
        if (amtFirearms != 0 || amtNarcotics != 0) {
            if ((int) (Math.random() * (MAX_REP / 10) + ppolice) < (int) (MAX_REP / 5)) {
                return false;
            }
            playerShip.removeFromCargo(GoodType.FIREARMS, amtFirearms);
            playerShip.removeFromCargo(GoodType.NARCOTICS, amtNarcotics);
            int fine = (int) (Math.random() * (ppolice / MAX_REP) * player.getCredits());
            player.decreaseCredits(fine);
            if (player.getPoliceRep() + REP_CHANGE > MAX_REP) {
                player.setPoliceRep(MAX_REP);
            } else {
                player.setPoliceRep(ppolice + REP_CHANGE);
            }
            return true;
        } else {
            if (player.getPoliceRep() - REP_CHANGE < 1) {
                player.setPoliceRep(1);
            } else {
                player.setPoliceRep(ppolice - REP_CHANGE);
            }
            return false;
        }
    }

    /**
     * Determines whether or not the police will accept a given bribe
     * 
     * @param encounter
     *            The NPCEncounter where the police are being bribed by the player
     * @param credits
     *            The amount of credits the player is offering
     * @return The success of the bribe attempt
     */
    public boolean bribePolice(NPCEncounter encounter, double credits) {
        regenExtraShields();
        encounter.takeTurn();
        credits = credits * player.getPlanet().getGovernment().getBribeFactor();
        Police police = (Police) encounter.getNPC();
        if (police.willAcceptBribe(credits)) {
            player.decreaseCredits(credits);
            return true;
        }
        if (player.getPoliceRep() + REP_CHANGE > MAX_REP) {
            player.setPoliceRep(MAX_REP);
        } else {
            player.setPoliceRep(player.getPoliceRep() + REP_CHANGE);
        }
        return false;
    }

    /**
     * Regenerates a portion of the player's shields based on engineer skill
     */
    private void regenShields() {
        playerShip.addShieldHp((int) (playerShip.getMaxShieldHp()
                * (player.getEngineerSkill() + playerShip.getEngineerBonus()) / 100.));
    }

    /**
     * Regenerates more of the player's shields at the beginning and end of an encounter
     */
    private void regenExtraShields() {
        regenShields();
        regenShields();
    }
}