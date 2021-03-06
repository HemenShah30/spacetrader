package model;

import model.enums.EncounterType;

/**
 * Class representing Police
 * 
 * @author Eric Wan and Larry He
 * 
 */
public class Police extends NPC {
    private double minimumBribe;

    /**
     * Constructor for Police, taking in the player PoliceReputation and their credit amount
     * 
     * @param rep
     *            The PoliceReputation of the player
     * @param playerCred
     *            The amount of credits the player has
     */
    public Police(int rep) {
        setPilotSkill((int) (rep * .09));
        setFighterSkill((int) (rep * .09));
        setTraderSkill(1);
        setEngineerSkill(3);
        setInvestorSkill(1);
        generateShip(rep, EncounterType.POLICE);
        minimumBribe = (double) (rep * (Math.random() * 90) + 100);
    }

    /**
     * Checks to see if Police accepts the player's bribe or not
     * 
     * @param bribe
     *            double of credits offered by player
     * @return boolean whether the police accepts or not
     */
    public boolean willAcceptBribe(double bribe) {
        if (bribe >= minimumBribe) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Police";
    }
}