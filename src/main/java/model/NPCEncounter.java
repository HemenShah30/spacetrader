package model;

import model.Enum.EncounterType;

/**
 * Encounter with a player that involves an NPC
 * 
 * @author Jack Croft
 *
 */
public class NPCEncounter extends Encounter {

    private NPC npc;
    private int turnCount;

    /**
     * Constructor for the NPC Encounter taking in an EncounterType and a NPC
     * 
     * @param type
     *            The type of encounter
     * @param n
     *            The NPC that is involved in the encounter
     */
    public NPCEncounter(EncounterType type, NPC npc) {
        super(type);
        this.npc = npc;
        turnCount = 0;
    }

    /**
     * Returns the NPC associated with this encounter
     * 
     * @return The NPC associated with this encounter
     */
    public NPC getNPC() {
        return npc;
    }

    /**
     * Returns the turn count of the NPCEncounter
     * 
     * @return The current turn count of the NPCEncounter
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Causes the turn count to increment as a result of a turn being taken in the encounter
     */
    public void takeTurn() {
        turnCount++;
    }

    @Override
    public String doEncounter() {
        switch (encounterType) {
        case TRADER:
            return "You fly towards the unidentified blip on your radar"
                    + "and discover a traveling trader!";
        case PIRATE:
            return "You glance at the viewing monitor and see a ship"
                    + "approaching with pirate markings!";
        case POLICE:
            return "A police ship hails you and flashes its blue lights"
                    + "in an attempt to pull you over!";
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        switch (encounterType) {
        case TRADER:
            return "Trader";
        case PIRATE:
            return "Pirate";
        case POLICE:
            return "Police";
        default:
            return "";
        }
    }
}