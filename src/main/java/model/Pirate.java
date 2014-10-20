package model;

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
		generateShip(rep);
	}

	@Override
	public String toString() {
		return "Pirate";
	}
}
