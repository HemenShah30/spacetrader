package model;

import model.Enum.EncounterType;

/**
 * Class representing an Encounter with an NPC or other
 * 
 * @author Jack Croft
 *
 */
public class Encounter {
	private EncounterType encounterType;
	private String message;

	/**
	 * Constructor for an Encounter taking a type and message
	 * 
	 * @param t
	 *            The type of encounter
	 * @param m
	 *            The message that describes the encounter
	 */
	public Encounter(EncounterType t, String m) {
		encounterType = t;
		message = m;
	}

	/**
	 * Returns the type of encounter
	 * 
	 * @return The type of encounter
	 */
	public EncounterType getEncounterType() {
		return encounterType;
	}

	/**
	 * Returns the message describing the encounter
	 * 
	 * @return The message describing the encounter
	 */
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}