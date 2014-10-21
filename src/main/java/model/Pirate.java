package model;

import model.Enum.EncounterType;

/**
 * Class representing Pirates.
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Pirate extends NPC {

	public Pirate(int rep) {
		setPilotSkill((int) (rep * .6));
		setFighterSkill((int) (rep * .6));
		setTraderSkill(1);
		setEngineerSkill(3);
		setInvestorSkill(1);
		generateShip(rep, EncounterType.PIRATE);
	}

	@Override
	public String toString() {
		return "Pirate";
	}
}
