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

	/**
	 * Constructor for the NPC Encounter taking in an EncounterType and a NPC
	 * 
	 * @param type
	 *            The type of encounter
	 * @param npc
	 *            The NPC that is involved in the encounter
	 */
	public NPCEncounter(EncounterType type, NPC n) {
		super(type);
		npc = n;
	}

	/**
	 * Returns the NPC associated with this encounter
	 * 
	 * @return The NPC associated with this encounter
	 */
	public NPC getNPC() {
		return npc;
	}

	@Override
	public String doEncounter() {
		switch (encounterType) {
		case TRADER:
			return "You fly towards the unidentified blip on your radar and discover a traveling trader!";
		case PIRATE:
			return "You glance at the viewing monitor and see a ship approaching with pirate markings!";
		case POLICE:
			return "A police ship hails you and flashes it's blue lights in an attempt to pull you over!";
		default:
			return null;
		}
	}
}