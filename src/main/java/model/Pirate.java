package model;

import model.enums.EncounterType;

/**
 * Class representing Pirates
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Pirate extends NPC {

    /**
     * Constructor for the pirate taking in the player's pirate reputation
     * 
     * @param rep
     *            The reputation the player has with the Pirate faction
     */
    public Pirate(int rep) {
        setPilotSkill((int) (rep * .09));
        setFighterSkill((int) (rep * .09));
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